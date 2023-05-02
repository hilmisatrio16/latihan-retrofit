package com.example.retrofitapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitapp.adapter.FilmAdapter
import com.example.retrofitapp.model.*
import com.example.retrofitapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<ResponseDataFilmItem>>
    lateinit var postDataFilm : MutableLiveData<ResponseAddFilm>
    lateinit var updateDataFilm : MutableLiveData<List<ResponseUpdateFilm>>
    lateinit var deleteDataFilm : MutableLiveData<Void>
    lateinit var detailFilm : MutableLiveData<ResponseDetailFilm>

    init {
        liveDataFilm = MutableLiveData()
        postDataFilm = MutableLiveData()
        updateDataFilm = MutableLiveData()
        deleteDataFilm = MutableLiveData()
        detailFilm = MutableLiveData()
    }

    fun getLiveDataFilms() : MutableLiveData<List<ResponseDataFilmItem>>{
        return liveDataFilm
    }

    fun getDataDetailFilm()  : MutableLiveData<ResponseDetailFilm>{
        return detailFilm
    }

    fun postFilm() : MutableLiveData<ResponseAddFilm>{
        return postDataFilm
    }

    fun putFilm() : MutableLiveData<List<ResponseUpdateFilm>>{
        return updateDataFilm
    }

    fun delFilm() : MutableLiveData<Void>{
        return deleteDataFilm
    }

    fun callApiFilm(){
        RetrofitClient.instance.getAllFilm()
            .enqueue(object : Callback<List<ResponseDataFilmItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataFilmItem>>,
                    response: Response<List<ResponseDataFilmItem>>
                ) {
                    if(response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else{
                        liveDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })
    }

    fun callDetailFilm(id : Int){
        RetrofitClient.instance.getDetailFilm(id)
            .enqueue(
            object : Callback<ResponseDetailFilm>{
                override fun onResponse(
                    call: Call<ResponseDetailFilm>,
                    response: Response<ResponseDetailFilm>
                ) {
                    if(response.isSuccessful){
                        Log.d("Hasil", response.body().toString())
                        detailFilm.postValue(response.body())
                    }else{
                        detailFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDetailFilm>, t: Throwable) {
                    detailFilm.postValue(null)

                }

            }
        )
    }

    fun addDataFilm(name : String, director : String, description : String, image : String){
        RetrofitClient.instance.postDataFilm(DataFilm(
            description, director, image, name
        )).enqueue(object : Callback<ResponseAddFilm>{
            override fun onResponse(
                call: Call<ResponseAddFilm>,
                response: Response<ResponseAddFilm>
            ) {
                if(response.isSuccessful){
                    postDataFilm.postValue(response.body())
                }else{
                    postDataFilm.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseAddFilm>, t: Throwable) {
                postDataFilm.postValue(null)
            }

        })

    }

    fun callUpdateFilm(id : Int,name : String, director : String, description : String, image : String){
        RetrofitClient.instance.updateDataFilm(id, DataFilm(description, director, image, name)).enqueue(
            object : Callback<List<ResponseUpdateFilm>>{
                override fun onResponse(
                    call: Call<List<ResponseUpdateFilm>>,
                    response: Response<List<ResponseUpdateFilm>>
                ) {
                    if(response.isSuccessful){
                        updateDataFilm.postValue(response.body())
                    }else{
                        updateDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<ResponseUpdateFilm>>, t: Throwable) {
                    updateDataFilm.postValue(null)
                }

            }
        )
    }

    fun callDeleteFilm(id: Int){

        RetrofitClient.instance.deleteDataFilm(id).enqueue(
            object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.isSuccessful){
                        deleteDataFilm.postValue(response.body())
                        Log.d("respon delete" , response.body().toString())
                    }else{
                        deleteDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    deleteDataFilm.postValue(null)
                }
            })
    }


}