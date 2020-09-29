package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.vo.HarvardImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface HarvardApi {

    companion object {
        private const val key = "459952da-e71f-4e08-b779-e4b9ae13c1f1"
    }

    @GET("/image?apikey=$key")
    suspend fun getHarvardImages(@Query("page") page: Int): HarvardResponse


    @GET("/image/{imageId}?apikey=$key")
    fun getHarvardImageDetails(@Path("imageId") imageId: Int): Call<HarvardImage>

}