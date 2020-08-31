package com.anxiao.timeline.data

import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.core.platform.NetworkHandler
import com.anxiao.timeline.data.local.CacheFailure
import com.anxiao.timeline.data.local.dao.NewsDao
import com.anxiao.timeline.data.network.NewsService
import com.anxiao.timeline.data.network.api.NewsNetFailure
import com.anxiao.timeline.data.network.api.NewsResponse
import com.anxiao.timeline.data.vo.News
import retrofit2.Call
import java.lang.Exception


interface NewsRepository {

    fun news(): Either<Failure, List<News>>
    fun newsDetail(): Either<Failure, News>

    class Local(private val dao: NewsDao) : NewsRepository {
        override fun news(): Either<Failure, List<News>> {

            val cache = dao.find()

            return when (cache.size) {
                0 -> Either.Left(CacheFailure.EmptyCacheFailure())
                else -> Either.Right(cache)
            }
        }

        override fun newsDetail(): Either<Failure, News> {

            TODO("Not yet implemented")
        }

    }

    class Network(private val networkHandler: NetworkHandler, private val service: NewsService) :
        NewsRepository {

        override fun news(): Either<Failure, List<News>> {
            return when (networkHandler.isConnected) {
                true -> request(
                    service.getNews(),
                    { handlerException(it) },
                    NewsResponse(
                        200, "default",
                        emptyList()
                    )
                )
                false, null -> Either.Left(Failure.NetworkConnection)
            }
        }

        override fun newsDetail(): Either<Failure, News> {
            TODO("Not yet implemented")
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    false -> Either.Left(Failure.ServerError)
                    true -> Either.Right(transform(response.body() ?: default))
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }

    fun <T> handlerException(response: NewsResponse<T>): T {
        return when (response.code) {
            in 200..209 -> response.result
            else -> throw Exception("Server error")
        }
    }


}