package com.flow.assignment.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("/v1/search/movie.json")
    fun getInfo(
        @Query ("query") query : String,
        @Query ("display") display : Int,
        @Query ("start") start : Int,
        @Query ("genre") genre : String,
        @Query ("country") country : String,
        @Query ("yearfrom") yearFrom : Int,
        @Query ("yearto") yearTo : Int
    ) : Call<ResponseData>
}