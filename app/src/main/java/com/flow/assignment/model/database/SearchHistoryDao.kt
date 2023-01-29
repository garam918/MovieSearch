package com.flow.assignment.model.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM search_word_table ORDER BY created_at DESC LIMIT 10")
    fun getHistory() : Flow<List<SearchWord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchWordInfo : SearchWord)
}