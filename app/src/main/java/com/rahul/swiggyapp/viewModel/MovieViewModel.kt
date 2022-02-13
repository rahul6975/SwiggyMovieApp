package com.rahul.swiggyapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rahul.swiggyapp.modelClass.MovieDetailClass
import com.rahul.swiggyapp.modelClass.ResponseClass
import com.rahul.swiggyapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    var isSuccess = MutableLiveData<String>()

    fun getMovieList(name: String, page: Int): LiveData<ResponseClass> {
        return liveData(Dispatchers.IO) {
            val result = movieRepository.getMovieList(name, page)
            if (result.isSuccessful) {
                result.body()?.let { emit(it) }
            }
            if (!result.isSuccessful) {
                isSuccess.postValue(result.message())
            }
        }
    }

    fun getMovieDetails(id: String): LiveData<MovieDetailClass> {
        return liveData(Dispatchers.IO) {
            val result = movieRepository.getMovieDetails(id)
            if (result.isSuccessful) {
                result.body()?.let { emit(it) }
            }
        }
    }
}