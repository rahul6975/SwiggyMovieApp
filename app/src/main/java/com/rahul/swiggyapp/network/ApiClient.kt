package com.rahul.swiggyapp.network

import com.rahul.swiggyapp.modelClass.MovieDetailClass
import com.rahul.swiggyapp.modelClass.ResponseClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("?apikey=${ApiConstants.API_KEY}")
    suspend fun getMovieList(
        @Query("s") name: String,
        @Query("page") page: Int
    ): Response<ResponseClass>

    @GET("?apikey=${ApiConstants.API_KEY}")
    suspend fun getMovieDetail(@Query("i") id: String): Response<MovieDetailClass>

}