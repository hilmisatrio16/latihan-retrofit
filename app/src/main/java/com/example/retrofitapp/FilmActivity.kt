package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapp.adapter.FilmAdapter
import com.example.retrofitapp.adapter.NewsAdapter
import com.example.retrofitapp.databinding.ActivityFilmBinding
import com.example.retrofitapp.model.ResponseDataFilmItem
import com.example.retrofitapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFilm()

    }

    private fun getDataFilm() {
        RetrofitClient.instance.getAllFilm()
            .enqueue(object : Callback<List<ResponseDataFilmItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataFilmItem>>,
                    response: Response<List<ResponseDataFilmItem>>
                ) {
                    if(response.isSuccessful){
                        binding.rvFilm.layoutManager = GridLayoutManager(this@FilmActivity,2)
                        binding.rvFilm.adapter = FilmAdapter(response.body()!!)
                    }else{
                        Toast.makeText(this@FilmActivity, "Failed load data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                    Toast.makeText(this@FilmActivity, "Failed load data", Toast.LENGTH_SHORT).show()
                }

            })
    }
}