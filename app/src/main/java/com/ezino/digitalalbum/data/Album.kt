package com.ezino.digitalalbum.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val albumId: Int,
    val name: String,
    val description: String,
    val updateTime: Calendar
)