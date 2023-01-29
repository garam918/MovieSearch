package com.flow.assignment.model.repository

import com.flow.assignment.model.database.SearchHistoryDao
import com.flow.assignment.model.database.SearchWord
import com.flow.assignment.model.network.*
import kotlinx.coroutines.flow.Flow

class Repository(private val searchHistoryDao: SearchHistoryDao) {

    val searchHistoryList : Flow<List<SearchWord>> = searchHistoryDao.getHistory()

    suspend fun getMovieInfo(query: String, display: Int, start: Int) : Flow<ApiResult<ResponseData>> = safeFlow {
        RetrofitBuilder.networkService.getMovieInfo(query,display, start)
    }

    suspend fun addSearchWord(searchWordInfo: SearchWord) = searchHistoryDao.insert(searchWordInfo)
}