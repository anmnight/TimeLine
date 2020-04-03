package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.database.vo.UserInfo
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RemoteService {
    @POST("base/login")
    fun getOrderInfo(@Body user: UserInfo?): Single<UserInfo?>?
}