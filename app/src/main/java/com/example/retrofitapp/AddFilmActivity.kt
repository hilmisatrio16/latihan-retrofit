package com.example.retrofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapp.databinding.ActivityAddFilmBinding
import com.example.retrofitapp.viewmodel.FilmViewModel

class AddFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val name = binding.addName.text.toString()
            val director = binding.addDirector.text.toString()
            val description = binding.addDescription.text.toString()
            val image = binding.addImage.text.toString()

            if(name.isNotEmpty() && director.isNotEmpty() && description.isNotEmpty() && image.isNotEmpty()){
                addFilm(name, director, description, image)
                finish()
            }else{
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun addFilm(name : String, director : String, description : String, image : String) {
        val viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)

        viewModel.addDataFilm(name, director, description, image)

        viewModel.postFilm().observe(this, Observer {
            if(it != null){
                Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, FilmActivity::class.java))

            }else{
                Toast.makeText(this, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show()
            }
        })

        //dikasih finish()

    }
}