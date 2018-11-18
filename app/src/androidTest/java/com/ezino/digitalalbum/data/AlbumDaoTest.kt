package com.ezino.digitalalbum.data

import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AlbumDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var albumDao: AlbumDao

    private val album1 = Album("1", "test1", "test1 description", Calendar.getInstance().apply {
        set(2018, 1, 1, 11, 11, 11)
    })

    private val album2 = Album("2", "test2", "test2 description", Calendar.getInstance().apply {
        set(2017, 3, 12, 3, 30, 44)
    })

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
    fun testGetPlant() {
        val albums = getValue(albumDao.getAlbums())
        assertThat(albums.size, equalTo(2))
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