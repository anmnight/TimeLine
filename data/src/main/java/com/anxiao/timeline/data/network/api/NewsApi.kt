package com.anxiao.timeline.data.network.api

import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.timeline.data.vo.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {

    @GET("getWangYiNews")
    fun getNews(): Call<NewsResponse<List<News>>>



}