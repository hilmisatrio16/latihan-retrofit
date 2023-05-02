package com.example.retrofitapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitapp.databinding.ActivityUpdateFilmBinding
import com.example.retrofitapp.viewmodel.FilmViewModel

class UpdateFilmActivity : AppCompatActivity() {

    lateinit var binding: ActivityUpdateFilmBinding
    lateinit var viewModel : FilmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id = intent.getStringExtra("update")

        viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)

        getDetailFilm(id!!.toInt())

        binding.btnUpdate.setOnClickListener {
            val name = binding.updateName.text.toString()
            val director = binding.updateDirector.text.toString()
            val description = binding.updateDescription.text.toString()
            val image = binding.updateImage.text.toString()

            if(name.isNotEmpty() && director.isNotEmpty() && description.isNotEmpty() && image.isNotEmpty()){
                updateDataFilm(id!!.toInt(), name, director, description, image)
                finish()
            }else{
                Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun getDetailFilm(id : Int) {
        viewModel.callDetailFilm(id)

        viewModel.getDataDetailFilm().observe(this, Observer {
            binding.updateName.setText(it.name)
            binding.updateDirector.setText(it.director)
            binding.updateDescription.setText(it.description)
            binding.updateImage.setText(it.image)
            if(it == null){
                Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun updateDataFilm(id : Int, name : String, director : String, description : String, image : String){

        viewModel.callUpdateFilm(id, name, director, description, image)

        viewModel.putFilm().observe(this, Observer {
            if(it != null){
                Log.d("TAG FILM","sukses")
                Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, FilmActivity::class.java))
            }
        })
    }
}