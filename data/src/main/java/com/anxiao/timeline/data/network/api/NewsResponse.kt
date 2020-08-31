package com.anxiao.timeline.data.network.api

open class NewsResponse<T>(val code: Int, val message: String, val result: T)

//todo create empty data class
class EmptyResponse<T>(val code: Int, val message: String)


