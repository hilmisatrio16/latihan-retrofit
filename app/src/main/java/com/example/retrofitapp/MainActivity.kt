package com.example.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapp.adapter.NewsAdapter
import com.example.retrofitapp.databinding.ActivityMainBinding
import com.example.retrofitapp.model.ResponseDataNewsItem
import com.example.retrofitapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataNews()
    }

    private fun getDataNews() {
        RetrofitClient.instance.getAllNews()
            .enqueue(object : Callback<List<ResponseDataNewsItem>>{
                override fun onResponse(
                    call: Call<List<ResponseDataNewsItem>>,
                    response: Response<List<ResponseDataNewsItem>>
                ) {
                    if(response.isSuccessful){
                        binding.rvNews.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                        binding.rvNews.adapter = NewsAdapter(response.body()!!)
                    }else{
                        Toast.makeText(this@MainActivity, "Failed load data", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "", Toast.LENGTH_SHORT).show()
                }

            })
    }


}