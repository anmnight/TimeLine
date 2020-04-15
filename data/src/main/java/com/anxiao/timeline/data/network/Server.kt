package com.anxiao.timeline.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Server {
    private var client: Retrofit

    init {

        val logLevel = HttpLoggingInterceptor()
        logLevel.level = HttpLoggingInterceptor.Level.BODY

        val callClient = OkHttpClient.Builder()
            .addInterceptor(logLevel)
            .build()

        client = Retrofit.Builder()
            .baseUrl("https://api.apiopen.top/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(callClient)
            .build()
    }


    fun services(): RemoteService {
        return client.create(RemoteService::class.java)
    }

}