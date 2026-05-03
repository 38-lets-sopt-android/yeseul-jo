package com.example.letssopt.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.letssopt.core.data.entity.Content
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {
    @Insert
    suspend fun insert(content: Content)

    @Query("SELECT * FROM contents")
    fun getAllContents(): Flow<List<Content>>
}