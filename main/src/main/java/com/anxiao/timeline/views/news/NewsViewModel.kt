package com.anxiao.timeline.views.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anxiao.timeline.data.NewsRepo
import com.anxiao.timeline.data.database.dao.NewsDao
import com.anxiao.timeline.data.network.RemoteService
import com.anxiao.timeline.checkResult
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private lateinit var dao: NewsDao
    private lateinit var api: RemoteService
    private lateinit var newsRepo: NewsRepo


    private var _query = MutableLiveData<String>()

//    private var newsLiveData:LiveData<>

    fun loadNews() =

        viewModelScope.launch {
            val result = newsRepo.getNews()

            result.checkResult(
                onSuccess = {
                    println("")
                },

                onError = {
                    println(it)
                }
            )
        }


}

