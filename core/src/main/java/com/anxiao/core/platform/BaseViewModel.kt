package com.anxiao.core.platform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anxiao.core.exception.Failure

abstract class BaseViewModel : ViewModel() {

    private val _failure = MutableLiveData<Failure>()

    val failure: LiveData<Failure>
        get() = _failure


    fun handlerFailure(failure: Failure) {
        _failure.value = failure
    }
}