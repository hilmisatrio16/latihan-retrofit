package com.example.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.databinding.ItemFilmBinding
import com.example.retrofitapp.model.ResponseDataFilmItem

class FilmAdapter(private var listFilm : List<ResponseDataFilmItem>) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
       var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.binding.tvTitle.text = listFilm[position].name
        holder.binding.tvDate.text = listFilm[position].date
        holder.binding.tvDirector.text = listFilm[position].director

        Glide.with(holder.itemView)
            .load(listFilm[position].image)
            .into(holder.binding.imgNews)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}