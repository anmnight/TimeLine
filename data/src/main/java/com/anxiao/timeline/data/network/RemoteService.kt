package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.vo.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import retrofit2.http.GET
import java.io.IOException

interface RemoteService {

    @GET("getWangYiNews")
    suspend fun getNews(): RestResponse<List<News>>

}

data class RestResponse<T>(val code: Int, val message: String, val result: T)

sealed class Result<out T> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}


suspend fun <T : Any> safeCall(call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Result.Error(e)
    }
}