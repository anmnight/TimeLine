package com.anxiao.timeline.data

import com.anxiao.timeline.data.database.DBRegister
import com.anxiao.timeline.data.network.Server
import com.anxiao.timeline.data.vo.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


class NewsRepo {

    private val services = Server.services()
    private val dao = DBRegister.db().newsDao()

    fun getNews(): Flow<List<News>> {

        return flow {
            val cache = withContext(Dispatchers.IO) {
                dao.find()
            }
            emit(cache)
        }
    }
}