package com.anxiao.timeline.data

import com.anxiao.timeline.data.database.dao.NewsDao
import com.anxiao.timeline.data.network.RemoteService
import com.anxiao.timeline.data.vo.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception


class NewsRepo(api: RemoteService, cache: NewsDao) {

    private var services: RemoteService = api
    private var dao: NewsDao = cache

    suspend fun getNews(): List<News> {
        return withContext(Dispatchers.IO) {
            var cache = dao.find()
            if (cache.isEmpty()) {
                val result = services.getNews()
                if (result.code == 200) {
                    dao.insert(result.result)
                    cache = dao.find()
                } else {
                    throw Exception("远端数据获取异常")
                }
            }

            return@withContext cache
        }
    }
}