package com.rahul.swiggyapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.swiggyapp.R
import com.rahul.swiggyapp.adapter.Adapter
import com.rahul.swiggyapp.databinding.ActivityMainBinding
import com.rahul.swiggyapp.modelClass.SearchClass
import com.rahul.swiggyapp.onClickInterface.MovieClickListener
import com.rahul.swiggyapp.viewModel.MovieViewModel

class MainActivity : AppCompatActivity(), MovieClickListener {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: Adapter
    private lateinit var movieLayout: GridLayoutManager
    private var list = arrayListOf<SearchClass>()
    private lateinit var binding: ActivityMainBinding
    private var page = 1
    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        movieLayout = GridLayoutManager(this, 2)
        setContentView(binding.root)
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    override fun onResume() {
        setRecyclerView()
        getMovieName()
        doPagination()
        super.onResume()
    }

    private fun doPagination() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isLoading) {
                    val start = movieLayout.childCount
                    val end = movieLayout.findFirstCompletelyVisibleItemPosition()
                    val totalItems = movieAdapter.itemCount
                    if (start + end >= totalItems) {
                        page++
                        getNewMovies()
                    }
                }
            }
        })
    }

    private fun getNewMovies() {
        isLoading = true
        val result = movieViewModel.getMovieList(binding.etMovieName.text.toString(), page)
        result.observe(this, Observer { result ->
            if (result != null) {
                list.addAll(result.search as ArrayList<SearchClass>)
                movieAdapter.notifyDataSetChanged()
                isLoading = false
            }
        })
    }

    private fun getMovieName() {
        binding.btnSearch.setOnClickListener {
            if (binding.etMovieName.text.toString().length >= 3) {
                binding.progressCircular.visibility = View.VISIBLE
                list.clear()
                val movie = binding.etMovieName.text.toString()
                val result = movieViewModel.getMovieList(movie, page)
                result.observe(this, Observer { result ->
                    if (result != null) {
                        list.addAll(result.search as ArrayList<SearchClass>)
                        movieAdapter.notifyDataSetChanged()
                        binding.progressCircular.visibility = View.GONE
                    }
                })
            } else {
                Toast.makeText(this, "Please enter valid movie name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRecyclerView() {
        movieAdapter = Adapter(list, this)
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = movieLayout
        }
    }

    override fun onClick(position: Int) {
        startActivity(
            Intent(this, MovieDetailsClass::class.java).putExtra(
                "id",
                list[position].imdbID
            )
        )
    }
}