package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.network.api.NewsApi
import com.anxiao.timeline.data.network.api.NewsResponse
import com.anxiao.timeline.data.vo.News
import retrofit2.Call
import retrofit2.Retrofit

class NewsService(retrofit: Retrofit) : NewsApi {

    private val newsApi by lazy { retrofit.create(NewsApi::class.java) }

    override fun getNews(): Call<NewsResponse<List<News>>> = newsApi.getNews()
}