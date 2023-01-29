package com.flow.assignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.flow.assignment.databinding.SearchHistoryDialogBinding
import com.flow.assignment.model.database.SearchWord
import com.flow.assignment.view.OnItemClick
import com.flow.assignment.view.SearchHistoryRecyclerAdapter
import com.flow.assignment.viewmodel.MainViewModel

class SearchHistoryDialog : DialogFragment(), OnItemClick {

    private lateinit var binding: SearchHistoryDialogBinding
    private val viewModel : MainViewModel by activityViewModels()
    private lateinit var adapter : SearchHistoryRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SearchHistoryDialogBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        viewModel.searchHistoryList.observe(this) { list->
            viewModel.searchWordList.clear()
            viewModel.searchWordList.addAll(list)
            adapter.notifyDataSetChanged()
        }

    }

    override fun search(word: String) {
        viewModel.searchWord.value = word
        val searchWord = SearchWord(System.currentTimeMillis(),word)
        viewModel.addSearchWord(searchWord)
        viewModel.getMovieInfo(word,100,1)

        this.dismiss()
    }

    private fun initRecycler() {
        val recycler = binding.searchHistoryRecycler
        adapter = SearchHistoryRecyclerAdapter(this)

        if(recycler.adapter == null) {
            val lm = LinearLayoutManager(recycler.context)
            recycler.layoutManager = lm
            recycler.adapter = adapter
        }
        recycler.addItemDecoration(DividerItemDecoration(this.context,LinearLayout.VERTICAL))
        (recycler.adapter as SearchHistoryRecyclerAdapter).items = viewModel.searchWordList
        recycler.adapter?.notifyDataSetChanged()
    }
}