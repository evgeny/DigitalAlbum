package com.ezino.digitalalbum

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey @ColumnInfo(name = "id") val albumId: String,
    val name: String,
    val description: String,
    val updateTime: Date
)