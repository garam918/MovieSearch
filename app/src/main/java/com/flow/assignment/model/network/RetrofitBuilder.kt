package com.flow.assignment.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val baseUrl = "https://openapi.naver.com"

    fun getRetrofit() : Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create()).build()

    val networkService : NetworkService = getRetrofit().create(NetworkService::class.java)

}