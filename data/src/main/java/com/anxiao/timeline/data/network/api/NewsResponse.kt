package com.anxiao.timeline.data.network.api

import com.anxiao.core.extension.empty

data class NewsResponse<T>(val code: Int, val message: String, val result: T) {

    companion object {
        fun <T> empty(result: T) = NewsResponse(200, String.empty(), result)
    }
}
