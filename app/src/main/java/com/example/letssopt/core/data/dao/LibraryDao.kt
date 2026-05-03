package com.example.letssopt.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.letssopt.core.data.entity.Library
import kotlinx.coroutines.flow.Flow

@Dao
interface LibraryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToLibrary(library: Library)

    @Query("DELETE FROM library WHERE id = :contentId")
    suspend fun deleteFromLibrary(contentId: Long)

    @Query("SELECT * FROM library ORDER BY content_saved_at DESC")
    fun getAllLibraryContents(): Flow<List<Library>>
}