package com.flow.assignment.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SearchWord::class], version = 1, exportSchema = false)
abstract class SearchHistoryDatabase : RoomDatabase() {

    abstract fun SearchHistoryDao() : SearchHistoryDao

    companion object {

        @Volatile
        private var INSTANCE : SearchHistoryDatabase? = null

        fun getDatabase(context: Context) : SearchHistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SearchHistoryDatabase::class.java,
                    "search_word_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}