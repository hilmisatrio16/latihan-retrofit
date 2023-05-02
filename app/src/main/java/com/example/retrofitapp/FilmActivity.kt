package com.example.retrofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitapp.adapter.FilmAdapter
import com.example.retrofitapp.adapter.NewsAdapter
import com.example.retrofitapp.databinding.ActivityFilmBinding
import com.example.retrofitapp.model.ResponseDataFilmItem
import com.example.retrofitapp.network.RetrofitClient
import com.example.retrofitapp.viewmodel.FilmViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmActivity : AppCompatActivity(){
    private lateinit var binding : ActivityFilmBinding
    private lateinit var filmAdapter : FilmAdapter
    private lateinit var viewModelFilm : FilmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        filmAdapter = FilmAdapter(ArrayList())

        viewModelFilm = ViewModelProvider(this).get(FilmViewModel::class.java)

        getDataFilm()

        confirmDeleteFilm()


        binding.btnAddFilm.setOnClickListener {
            startActivity(Intent(this, AddFilmActivity::class.java))
        }



    }

    private fun getDataFilm() {


        viewModelFilm.callApiFilm()

        viewModelFilm.liveDataFilm.observe(this, Observer {
            if(it != null){
                binding.rvFilm.layoutManager = GridLayoutManager(this,2)
                binding.rvFilm.adapter = filmAdapter
                filmAdapter.setData(it)
            }
        })
        confirmDeleteFilm()

    }

    private fun confirmDeleteFilm(){

        filmAdapter.onDelete = {
            val dialogConfirmDelete = AlertDialog.Builder(this)
            dialogConfirmDelete.apply {
                setTitle("Confirmation Delete Film")
                setMessage("Are you sure to delete this movie?")

                setNegativeButton("Cancel") { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }

                setPositiveButton("Yes") { _, _ ->
                    deleteFilm(it.id.toInt())
                }
            }
            dialogConfirmDelete.show()
        }
    }

    private fun deleteFilm(id : Int) {
        viewModelFilm.callDeleteFilm(id)

        viewModelFilm.delFilm().observe(this, Observer { output ->
            Toast.makeText(this, "Data berhasil dihapus $output", Toast.LENGTH_SHORT).show()

            viewModelFilm.callApiFilm()
        })
    }
}