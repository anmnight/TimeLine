package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.network.api.HarvardApi
import com.anxiao.timeline.data.network.api.HarvardResponse
import com.anxiao.timeline.data.vo.HarvardImage
import retrofit2.Call
import retrofit2.Retrofit

class HarvardService(retrofit: Retrofit) : HarvardApi {

    private val harvardApi by lazy { retrofit.create(HarvardApi::class.java) }

    override fun getHarvardartmuseumsImage(index: Int): Call<HarvardResponse<HarvardImage>> {
        return harvardApi.getHarvardartmuseumsImage(index)
    }

}