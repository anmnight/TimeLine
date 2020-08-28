package com.anxiao.timeline.views.news

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anxiao.core.platform.NetworkHandler
import com.anxiao.timeline.data.NewsRepository
import com.anxiao.timeline.data.network.NewsService
import com.anxiao.timeline.data.network.Server

class NewsViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val getNews =
            GetNews(
                NewsRepository.Network(
                    NetworkHandler(context),
                    NewsService(Server().retorfit())
                )
            )

        return modelClass.getConstructor(GetNews::class.java).newInstance(getNews)
    }

}