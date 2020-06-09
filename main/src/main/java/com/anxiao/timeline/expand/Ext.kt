package com.anxiao.timeline.expand

import com.anxiao.timeline.data.network.Result

inline fun <T : Any> Result<T>.checkResult(crossinline onSuccess: (T) -> Unit,crossinline onError: (String?) -> Unit) {
    if (this is Result.Success) {
        onSuccess(data)
    } else if (this is Result.Error) {
        onError(exception.message)
    }
}

inline fun <T : Any> Result<T>.checkSuccess(success: (T) -> Unit) {
    if (this is Result.Success) {
        success(data)
    }
}