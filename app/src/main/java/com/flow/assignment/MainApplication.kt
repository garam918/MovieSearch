package com.flow.assignment

import android.app.Application
import com.flow.assignment.model.database.SearchHistoryDatabase
import com.flow.assignment.model.repository.Repository

class MainApplication : Application() {
    private val database by lazy { SearchHistoryDatabase.getDatabase(this) }
    val repository by lazy { Repository(database.SearchHistoryDao())}
}