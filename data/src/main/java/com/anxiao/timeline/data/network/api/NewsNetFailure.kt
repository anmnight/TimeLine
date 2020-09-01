package com.anxiao.timeline.data.network.api

import com.anxiao.core.exception.Failure

class NewsNetFailure(private val exception: Throwable) : Failure.FeatureFailure() {

    val msg: String
        get() = exception.toString()
}