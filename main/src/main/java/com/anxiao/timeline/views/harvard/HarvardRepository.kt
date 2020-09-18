package com.anxiao.timeline.views.harvard

import androidx.paging.PagingData
import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.core.platform.NetworkHandler
import com.anxiao.timeline.data.network.HarvardService
import com.anxiao.timeline.data.network.HarvardResponse
import com.anxiao.timeline.data.network.NewsNetFailure
import com.anxiao.timeline.data.vo.HarvardImage
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import java.lang.Exception


abstract class HarvardRepository {

    abstract fun getHarvardImages(index: Int): Flow<PagingData<HarvardImage>>

    abstract fun getHarvardImageDetails(imageId: Int): Either<Failure, HarvardImage>

    abstract class Network(
        private val networkHandler: NetworkHandler,
        private val service: HarvardService
    ) :
        HarvardRepository() {

        abstract override fun getHarvardImages(index: Int): Flow<PagingData<HarvardImage>>

        override fun getHarvardImageDetails(imageId: Int): Either<Failure, HarvardImage> {
            return when (networkHandler.isConnected) {
                true -> request(
                    service.getHarvardImageDetails(imageId),
                    { it },
                    HarvardImage.empty()
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