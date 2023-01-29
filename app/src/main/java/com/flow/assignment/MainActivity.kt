package com.flow.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.flow.assignment.databinding.ActivityMainBinding
import com.flow.assignment.model.database.SearchWord
import com.flow.assignment.ui.SearchHistoryDialog
import com.flow.assignment.utils.toastMessage
import com.flow.assignment.viewmodel.MainViewModel
import com.flow.assignment.viewmodel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        binding.movieSearchBtn.setOnClickListener { searchMovie() }

        binding.searchHistoryBtn.setOnClickListener { SearchHistoryDialog().show(supportFragmentManager,"dialog") }
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this,MainViewModelFactory((this.application as MainApplication).repository))[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun searchMovie() {
        if(!viewModel.searchWord.value.isNullOrBlank()) {
            val searchWord = SearchWord(System.currentTimeMillis(),viewModel.searchWord.value.toString())
            viewModel.addSearchWord(searchWord)

            lifecycleScope.launch {
                viewModel.getMovieInfo(viewModel.searchWord.value.toString(), 100, 1).join()

                if(!viewModel.errorMessage.value.isNullOrBlank()) toastMessage(this@MainActivity,viewModel.errorMessage.value.toString())
            }
        }
        else toastMessage(this,"검색어를 입력해주세요.")
    }
}