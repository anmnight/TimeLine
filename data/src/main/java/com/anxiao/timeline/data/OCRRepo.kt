package com.anxiao.timeline.data

import com.anxiao.timeline.data.factory.NetworkBoundResource
import com.anxiao.timeline.data.factory.Resource
import com.anxiao.timeline.data.database.DBRegister
import com.anxiao.timeline.data.network.RestResponse
import com.anxiao.timeline.data.network.Server
import com.anxiao.timeline.data.vo.News
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class OCRRepo {

    private val services = Server.services()
    private val dao = DBRegister.db().newsDao()


    fun getAccessToken(): Flowable<Resource<List<News>>> {
        return object : NetworkBoundResource<List<News>>() {
            override fun saveCallResult(source: List<News>): Completable {
                return Completable.complete()
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                return true
            }

            override fun loadFromDb(): Single<List<News>> {
                return Single.create { }
            }

            override fun createCall(): Single<RestResponse<List<News>>> {
                return services.getNews()
            }

        }.asFlowable()
    }

    fun readPicture(): Flowable<Resource<List<News>>> {
        return object : NetworkBoundResource<List<News>>() {
            override fun saveCallResult(source: List<News>): Completable {
                return Completable.complete()
            }

            override fun shouldFetch(data: List<News>?): Boolean {
                return true
            }

            override fun loadFromDb(): Single<List<News>> {
                return Single.create { }
            }

            override fun createCall(): Single<RestResponse<List<News>>> {
                return services.getNews()
            }

        }.asFlowable()
    }


}