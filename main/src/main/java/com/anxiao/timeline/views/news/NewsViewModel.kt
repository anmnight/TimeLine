package com.anxiao.timeline.views.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anxiao.core.interactor.UseCase
import com.anxiao.core.platform.BaseViewModel
import com.anxiao.timeline.data.vo.News

class NewsViewModel constructor(private val getNews: GetNews) : BaseViewModel() {

    private val _queryNews = MutableLiveData<List<News>>()

    val news: LiveData<List<News>> = _queryNews

    fun loadNews() =
        getNews.invoke(UseCase.None()) { it.fold(::handlerFailure, ::handlerNewsList) }

    private fun handlerNewsList(news: List<News>) {
        _queryNews.value = news
    }


}

