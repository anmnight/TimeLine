package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.vo.HarvardImage
import retrofit2.Call
import retrofit2.Retrofit

class HarvardService(retrofit: Retrofit) : HarvardApi {

    private val harvardApi by lazy { retrofit.create(HarvardApi::class.java) }

    override suspend fun getHarvardImages(page: Int): HarvardResponse {
        return harvardApi.getHarvardImages(page)
    }

    override fun getHarvardImageDetails(imageId: Int): Call<HarvardImage> {
        return harvardApi.getHarvardImageDetails(imageId)
    }


}