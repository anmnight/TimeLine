package com.anxiao.timeline.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anxiao.timeline.data.NewsRepo
import com.anxiao.timeline.data.database.dao.NewsDao
import com.anxiao.timeline.data.network.RemoteService
import com.anxiao.timeline.expand.checkResult
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private lateinit var dao: NewsDao
    private lateinit var api: RemoteService
    private lateinit var newsRepo: NewsRepo



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

