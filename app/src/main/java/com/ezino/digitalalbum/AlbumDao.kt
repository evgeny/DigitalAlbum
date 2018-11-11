package com.ezino.digitalalbum

import android.arch.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY updateTime")
    fun getAlbums(): LiveData<List<Album>>
}