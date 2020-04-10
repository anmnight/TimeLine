package com.anxiao.timeline.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Server {

    private var client: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.apiopen.top/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun services(): RemoteService {
        return client.create(RemoteService::class.java)
    }

}