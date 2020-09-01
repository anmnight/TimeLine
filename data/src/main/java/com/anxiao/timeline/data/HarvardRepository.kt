package com.anxiao.timeline.data

import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.core.platform.NetworkHandler
import com.anxiao.timeline.data.network.HarvardService
import com.anxiao.timeline.data.network.api.HarvardApi
import com.anxiao.timeline.data.network.api.HarvardResponse
import com.anxiao.timeline.data.network.api.NewsNetFailure
import com.anxiao.timeline.data.vo.HarvardImage
import retrofit2.Call


interface HarvardRepository {


    fun getHarvardImages(index: Int): Either<Failure, List<HarvardImage>>


    class Network(private val networkHandler: NetworkHandler, private val service: HarvardService) :
        HarvardRepository {

        override fun getHarvardImages(index: Int): Either<Failure, List<HarvardImage>> {
            return when (networkHandler.isConnected) {
                true -> request(
                    service.getHarvardartmuseumsImage(index),
                    { it.records },
                    HarvardResponse.empty()
                )
                else -> Either.Left(Failure.NetworkConnection)
            }
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
                Either.Left(NewsNetFailure(exception))
            }
        }


    }

}