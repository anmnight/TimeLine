package com.anxiao.timeline

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {




    private lateinit var noticeModel: MediatorLiveData<NoticeModel>



    fun getNoticeModel(): MediatorLiveData<NoticeModel> {
        noticeModel = MediatorLiveData()
        return noticeModel
    }


    public fun go() {

    }


    data class NoticeModel(var toast: String = "", var isShowProgress: Boolean)
}