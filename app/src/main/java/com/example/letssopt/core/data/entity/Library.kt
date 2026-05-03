package com.example.letssopt.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "library")
data class Library(
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "content_name")
    val title: String,

    @ColumnInfo(name = "content_image_url")
    val image: Int,

    // 저장 시간
    @ColumnInfo(name = "content_saved_at")
    val savedAt: Long = System.currentTimeMillis()

)