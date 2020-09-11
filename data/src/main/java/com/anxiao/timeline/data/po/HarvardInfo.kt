package com.anxiao.timeline.data.po

import com.anxiao.core.extension.empty

data class HarvardInfo(
    var totalrecordsperquery: Int = 0,
    var totalrecords: Int = 0,
    var pages: Int = 0,
    var page: Int = 0,
    var next: String = String.empty()
) {
    companion object {
        fun empty() = HarvardInfo()
    }

}

