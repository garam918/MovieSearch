package com.flow.assignment.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flow.assignment.model.network.Item
import com.flow.assignment.view.MovieRecyclerAdapter

object MainBindingAdapter {

    @BindingAdapter("setRecyclerView")
    @JvmStatic
    fun setRecyclerView(recyclerView: RecyclerView,movieList : ObservableArrayList<Item>) {
        if(recyclerView.adapter == null) {
            val lm = LinearLayoutManager(recyclerView.context)
            val adapter = MovieRecyclerAdapter()
            recyclerView.layoutManager = lm
            recyclerView.adapter = adapter
        }
        (recyclerView.adapter as MovieRecyclerAdapter).items = movieList
        recyclerView.adapter?.notifyDataSetChanged()
    }
}