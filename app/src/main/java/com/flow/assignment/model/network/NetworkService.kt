package com.flow.assignment.model.network

import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("/v1/search/movie.json")
    suspend fun getMovieInfo(
        @Query ("query") query : String,
        @Query ("display") display : Int,
        @Query ("start") start : Int
    ) : ResponseData
}