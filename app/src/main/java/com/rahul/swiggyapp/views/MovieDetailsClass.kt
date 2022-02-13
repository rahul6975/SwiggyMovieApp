package com.rahul.swiggyapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rahul.swiggyapp.databinding.ActivityMainBinding
import com.rahul.swiggyapp.databinding.ActivityMovieDetailsClassBinding
import com.rahul.swiggyapp.viewModel.MovieViewModel

class MovieDetailsClass : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsClassBinding
    private lateinit var movieViewModel: MovieViewModel
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsClassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        getMovieId()
    }

    private fun getMovieId() {
        id = intent.getStringExtra("id")
        id?.let {
            val result = movieViewModel.getMovieDetails(id!!)
            binding.progressCircular.visibility = View.VISIBLE
            result.observe(this, Observer { response ->
                if (response != null) {
                    binding.apply {
                        progressCircular.visibility = View.GONE
                        tvMovieName.text = response.title
                        tvMovieDesc.text = response.plot
                        Glide.with(movieImage).load(response.poster).into(movieImage)
                        tvMovieRating.text = response.imdbRating
                    }
                }
            })
        }
    }
}