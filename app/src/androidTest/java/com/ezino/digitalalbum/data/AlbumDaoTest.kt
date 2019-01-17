package com.ezino.digitalalbum.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.equalTo
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AlbumDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var albumDao: AlbumDao

    private val album1 = Album(1, "album #1", "album #1 description", Calendar.getInstance().apply {
        set(2017, 1, 1, 11, 11, 11)
    })

    private val album2 = Album(2, "album #2", "album #2 description", Calendar.getInstance().apply {
        set(2018, 3, 12, 3, 30, 44)
    })

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        albumDao = database.albumDao()

        albumDao.insertNew(album1)
        albumDao.insertNew(album2)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAlbums() {
        val albums = getValue(albumDao.getAlbums())
        assertThat(albums.size, equalTo(2))

        //check sort order
        assertThat(albums[0], equalTo(album2))
    }

    @Test
    fun updateAlbum() {
        val newName = "updated name"
        var updatedAlbum = album1.copy(name = newName)
        albumDao.updateAlbum(updatedAlbum)

        updatedAlbum = getValue(albumDao.getAlbum(updatedAlbum.albumId))

        assertThat(updatedAlbum.name, equalTo(newName))
    }

    @Test(expected = android.database.sqlite.SQLiteConstraintException::class)
    fun addSameAlbum() {
        albumDao.insertNew(album1)
        val albums = getValue(albumDao.getAlbums())
        assertThat(albums.size, equalTo(2))
    }

    @Test
    fun addNewAlbum() {
        albumDao.insertNew(Album(3, "album #3", "album #3 description", Calendar.getInstance()))
        val albums = getValue(albumDao.getAlbums())
        assertThat(albums.size, equalTo(3))
    }

    @Test
    fun findAlbum_Failed() {
        var album = getValue(albumDao.getAlbum(0))
        assertNull("the album with id=0 should not exist", album)
    }

    @Test
    fun findAlbum_Success() {
        var album = getValue(albumDao.getAlbum(1))
        assertNotNull("the album with id=1 is not found", album)
        assertThat(album.name, equalTo("album #1"))
    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        liveData.observeForever { o ->
            data[0] = o
            latch.countDown()
        }
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T
    }
}