package com.anxiao.timeline.views

import androidx.lifecycle.ViewModel
import com.anxiao.timeline.data.NewsRepo

class NewsViewModel : ViewModel() {

    private var repo: NewsRepo = NewsRepo()


    val newsLiveData = repo.getNews()


}

