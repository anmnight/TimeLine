package com.anxiao.timeline.data.network

import com.anxiao.timeline.data.po.HarvardInfo
import com.anxiao.timeline.data.vo.HarvardImage

data class HarvardResponse(val info: HarvardInfo, val records: List<HarvardImage>) {

    companion object {
        fun empty() = HarvardResponse(HarvardInfo.empty(), emptyList())
    }
}