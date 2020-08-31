package com.anxiao.timeline.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Server {
    private var client: Retrofit

    init {

        val logLevel = HttpLoggingInterceptor()
        logLevel.level = HttpLoggingInterceptor.Level.BASIC

        val callClient = OkHttpClient.Builder()
            .addInterceptor(logLevel)
            .build()

        client = Retrofit.Builder()
            .baseUrl("https://api.apiopen.top/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(callClient)
            .build()
    }

    fun retrofit() = client

}