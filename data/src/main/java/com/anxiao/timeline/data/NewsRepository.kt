package com.anxiao.timeline.data

import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.core.platform.NetworkHandler
import com.anxiao.timeline.data.network.NewsService
import com.anxiao.timeline.data.vo.News
import retrofit2.Call


interface NewsRepository {

    fun news(): Either<Failure, List<News>>
    fun newsDetail(): Either<Failure, News>

    class Network(private val networkHandler: NetworkHandler, private val service: NewsService) :
        NewsRepository {


        override fun news(): Either<Failure, List<News>> {

            return when (networkHandler.isConnected) {
                true -> request(service.getNews(), { it.map { news: News -> news } }, emptyList())
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
                    true -> Either.Right(transform(response.body() ?: default))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }

    }


}