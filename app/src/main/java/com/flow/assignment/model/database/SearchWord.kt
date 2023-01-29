package com.flow.assignment.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_word_table")
class SearchWord(
    @ColumnInfo(name = "created_at") val createdAt : Long = System.currentTimeMillis(),
    @PrimaryKey
    val searchWord : String
)
