package com.example.retrofitapp.network

import com.example.retrofitapp.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestfulApi {

    @GET("news")
    fun getAllNews(): Call<List<ResponseDataNewsItem>>

    @GET("film")
    fun getAllFilm() : Call<List<ResponseDataFilmItem>>

    @GET("film/{id}")
    fun getDetailFilm(@Path("id") id: Int) : Call<ResponseDetailFilm>

    @POST("film")
    fun postDataFilm(@Body request : DataFilm) : Call<ResponseAddFilm>

    @PUT("film/{id}")
    fun updateDataFilm(
        @Path("id") id : Int,
        @Body request: DataFilm
    ) : Call<List<ResponseUpdateFilm>>

    @DELETE("film/{id}")
    fun deleteDataFilm(@Path("id") id : Int) : Call<Void>

}