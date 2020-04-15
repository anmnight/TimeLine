package com.anxiao.timeline.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.anxiao.timeline.data.factory.NetworkBoundResource
import com.anxiao.timeline.data.factory.Resource
import com.anxiao.timeline.data.database.DBRegister
import com.anxiao.timeline.data.network.RestResponse
import com.anxiao.timeline.data.network.Server
import com.anxiao.timeline.data.vo.News
import io.reactivex.Completable
import io.reactivex.Single

class NewsRepo {

    private val services = Server.services()
    private val dao = DBRegister.db().newsDao()

    fun getNews(): LiveData<Resource<List<News>>> {

        return LiveDataReactiveStreams.fromPublisher(
            object : NetworkBoundResource<List<News>>() {

                override fun shouldFetch(data: List<News>?): Boolean {
                    return true
                }

                override fun loadFromDb(): Single<List<News>> {
                    return dao.find()
                }

                override fun createCall(): Single<RestResponse<List<News>>> {
                    return services.getNews()
                }

                override fun saveCallResult(source: List<News>): Completable {
                    return dao.insert(source)
                }


            }.asFlowable()
        )
    }

    fun insert() {

        Thread {
            val news = News(123, "path", "image", "title", "passtime")
            val disposable = dao.insert(news)
                .subscribe(
                    { println("success") },
                    {
                        it.printStackTrace()
                        println(it.message)
                    })
        }.start()

    }


}