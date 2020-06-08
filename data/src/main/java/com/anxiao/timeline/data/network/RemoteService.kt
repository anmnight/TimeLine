package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.vo.News
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface RemoteService {

    @GET("getWangYiNews")
    suspend fun getNews(): RestResponse<List<News>>

}

data class RestResponse<T>(val code: Int, val message: String, val result: T)