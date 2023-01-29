package com.flow.assignment.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.*
import com.flow.assignment.model.database.SearchWord
import com.flow.assignment.model.network.ApiResult
import com.flow.assignment.model.network.Item
import com.flow.assignment.model.repository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainViewModel(private val repository : Repository) : ViewModel() {

    val searchHistoryList : LiveData<List<SearchWord>> = repository.searchHistoryList.asLiveData()
    val movieList = ObservableArrayList<Item>()

    val errorMessage = MutableLiveData<String>()

    val searchWord = MutableLiveData<String>()
    val searchWordList = ObservableArrayList<SearchWord>()

    fun getMovieInfo(query : String,display : Int,start : Int) = viewModelScope.launch {
        movieList.clear()

        repository.getMovieInfo(query, display, start).collectLatest { result ->
            when(result) {
                is ApiResult.Success -> {
                    if(result.value.items.isEmpty()) errorMessage.value = "검색된 내용이 없습니다."
                    else {
                        movieList.addAll(result.value.items)
                        errorMessage.value = ""
                    }
                }
                is ApiResult.Error -> errorMessage.value = "잘못된 요청입니다."
            }
        }
    }

    fun addSearchWord(searchWordInfo : SearchWord) = viewModelScope.launch {
        repository.addSearchWord(searchWordInfo)
    }

}

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}