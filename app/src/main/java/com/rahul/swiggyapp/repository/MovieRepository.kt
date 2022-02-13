package com.rahul.swiggyapp.repository

import com.rahul.swiggyapp.modelClass.MovieDetailClass
import com.rahul.swiggyapp.modelClass.ResponseClass
import com.rahul.swiggyapp.network.ApiClient
import com.rahul.swiggyapp.network.Network
import retrofit2.Response

class MovieRepository {
    private val apiClient = Network.getInstance().create(ApiClient::class.java)

    suspend fun getMovieList(name: String, page: Int): Response<ResponseClass> {
        return apiClient.getMovieList(name, page)
    }

    suspend fun getMovieDetails(id: String): Response<MovieDetailClass> {
        return apiClient.getMovieDetail(id)
    }

}