package com.flow.assignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.flow.assignment.databinding.SearchHistoryItemBinding
import com.flow.assignment.model.database.SearchWord

class SearchHistoryRecyclerAdapter(listener: OnItemClick) : RecyclerView.Adapter<SearchHistoryRecyclerAdapter.ViewHolder>() {

    var items = ObservableArrayList<SearchWord>()
    val mCallback = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        SearchHistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: SearchHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word : SearchWord) {
            binding.searchWord = word

            binding.root.setOnClickListener { mCallback.search(word.searchWord) }
        }
    }
}