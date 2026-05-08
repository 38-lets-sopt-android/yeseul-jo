package com.example.letssopt.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letssopt.core.data.local.dao.ContentDao
import com.example.letssopt.core.data.local.dao.LibraryDao
import com.example.letssopt.core.data.local.entity.Content
import com.example.letssopt.core.data.local.entity.Library

@Database(entities = [Content::class, Library::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contentDao(): ContentDao
    abstract fun libraryDao(): LibraryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}