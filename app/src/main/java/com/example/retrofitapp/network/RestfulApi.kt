package com.example.retrofitapp.network

import com.example.retrofitapp.model.ResponseDataFilmItem
import com.example.retrofitapp.model.ResponseDataNewsItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {

    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("film")
    fun getAllFilm() : Call<List<ResponseDataFilmItem>>

}