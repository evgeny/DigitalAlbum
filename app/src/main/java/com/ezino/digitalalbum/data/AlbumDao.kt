package com.ezino.digitalalbum.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY updateTime DESC")
    fun getAlbums(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNew(album: Album)
}