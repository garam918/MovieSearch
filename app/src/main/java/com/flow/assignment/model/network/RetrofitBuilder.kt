package com.flow.assignment.model.network

import com.flow.assignment.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitBuilder {
    private const val baseUrl = "https://openapi.naver.com"

    private fun getRetrofit() : Retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .client(okHttpClient(AppInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val networkService : NetworkService = getRetrofit().create(NetworkService::class.java)

    private fun okHttpClient(interceptor : AppInterceptor) : OkHttpClient = OkHttpClient.Builder().run {
        addInterceptor(interceptor)
        build()
    }

    class AppInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("X-Naver-Client-Id",BuildConfig.Client_id)
                .addHeader("X-Naver-Client-Secret",BuildConfig.Client_secret)
                .build()

            proceed(newRequest)
        }
    }
}