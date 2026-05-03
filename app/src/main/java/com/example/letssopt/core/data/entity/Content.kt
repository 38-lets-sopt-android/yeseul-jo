package com.example.letssopt.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contents")
data class Content (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "content_name")
    val title: String,

    @ColumnInfo(name = "content_image_url")
    val image: Int
)