package com.anxiao.timeline.data.network.api

import com.anxiao.timeline.data.po.HarvardInfo

class HarvardResponse<T>(val info: HarvardInfo, val records: List<T>) {

    companion object {
        fun <T> empty() = HarvardResponse(HarvardInfo.empty(), emptyList<T>())
    }
}