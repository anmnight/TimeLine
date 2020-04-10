package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.vo.News
import com.anxiao.timeline.data.vo.UserInfo
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RemoteService {

    @POST("base/login")
    fun getOrderInfo(@Body user: UserInfo): Single<UserInfo>

    @GET("getWangYiNews")
    fun getNews(): Single<RestResponse<List<News>>>
}

data class RestResponse<T>(val code: Int, val message: String, val result: T)