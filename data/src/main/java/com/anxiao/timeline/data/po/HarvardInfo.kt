package com.anxiao.timeline.data.po

import com.anxiao.core.extension.empty

class HarvardInfo {
    var totalrecordsperquery = 0
    var totalrecords = 0
    var pages = 0
    var page = 0
    var next: String? = null

    companion object {
        fun empty() = HarvardInfo()
    }
}