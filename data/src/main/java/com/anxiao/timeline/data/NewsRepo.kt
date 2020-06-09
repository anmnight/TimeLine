package com.anxiao.timeline.data

import com.anxiao.timeline.data.database.dao.NewsDao
import com.anxiao.timeline.data.network.RemoteService
import com.anxiao.timeline.data.network.RestResponse
import com.anxiao.timeline.data.network.Result
import com.anxiao.timeline.data.network.safeCall
import com.anxiao.timeline.data.vo.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.IOException


class NewsRepo(private var api: RemoteService, private var dao: NewsDao) {

    suspend fun getNews(): Result<List<News>> = withContext(Dispatchers.IO) {
        val cache = dao.find()
        if (cache.isEmpty()) {
            return@withContext safeCall { requestNews() }
        }
        return@withContext Result.Success(cache)
    }


     suspend fun requestNews(): Result<List<News>> {
        val response = api.getNews()
        return executeResponse(response, {
            val news = response.result
            dao.insert(news)
        })
    }

    private suspend fun <T : Any> executeResponse(
        response: RestResponse<T>,
        successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            if (response.code == -1) {
                errorBlock?.let { it() }
                Result.Error(IOException(response.message))
            } else {
                successBlock?.let { it() }
                Result.Success(response.result)
            }
        }
    }

}