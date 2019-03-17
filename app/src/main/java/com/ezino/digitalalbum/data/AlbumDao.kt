package com.ezino.digitalalbum.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY updateTime DESC")
    fun getAlbums(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNew(album: Album)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateAlbum(album: Album)

    @Query("SELECT * FROM albums WHERE id = :albumId")
    fun getAlbum(albumId: Int): LiveData<Album>

    @Delete
    fun deleteAlbum(album: Album)
}