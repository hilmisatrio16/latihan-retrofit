package com.example.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.databinding.ItemNewsBinding
import com.example.retrofitapp.model.ResponseDataNewsItem

class NewsAdapter(private var listNews : List<ResponseDataNewsItem>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(var binding : ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        var view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.binding.tvJudul.text = listNews[position].title
        holder.binding.tvTanggal.text = listNews[position].createdAt
        Glide.with(holder.itemView)
            .load(listNews[position].image)
            .into(holder.binding.imgNews)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}