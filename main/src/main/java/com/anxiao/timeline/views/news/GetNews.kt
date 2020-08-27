package com.anxiao.timeline.views.news

import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.core.interactor.UseCase
import com.anxiao.timeline.data.NewsRepository
import com.anxiao.timeline.data.vo.News

class GetNews(private val newsRepository: NewsRepository) : UseCase<List<News>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<News>> = newsRepository.news()
}