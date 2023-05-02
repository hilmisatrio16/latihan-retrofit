package com.example.retrofitapp.model

import com.google.gson.annotations.SerializedName

data class DataFilm(
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
