package com.flow.assignment.view

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flow.assignment.R
import com.flow.assignment.databinding.MovieItemLayoutBinding
import com.flow.assignment.model.network.Item

class MovieRecyclerAdapter: PagingDataAdapter<Item,MovieRecyclerAdapter.MovieRecyclerViewHolder>(diffCallback) {

    var items = ObservableArrayList<Item>()

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRecyclerViewHolder =
        MovieRecyclerViewHolder(MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: MovieRecyclerViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class MovieRecyclerViewHolder(private val binding: MovieItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Item) {
            binding.item = item

            binding.thumbnailImage.load(item.image) {
                error(R.drawable.ic_launcher_background)
            }

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                binding.root.context.startActivity(intent)
            }
        }
    }
}