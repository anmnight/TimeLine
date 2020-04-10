package com.anxiao.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.anxiao.timeline.data.Resource
import com.anxiao.timeline.data.repo.NewsRepo
import com.anxiao.timeline.data.vo.News

class SplashViewModel : ViewModel() {

    private val _query = MutableLiveData<String>()

    private var repo: NewsRepo = NewsRepo()

    var newsLiveData: LiveData<Resource<List<News>>> =
        Transformations.switchMap(_query) { repo.getNews() }


    fun refresh() {
        _query.value = "aaa"
    }
}

