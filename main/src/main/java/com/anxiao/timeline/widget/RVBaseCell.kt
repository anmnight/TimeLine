package com.anxiao.timeline.widget

abstract class RVBaseCell<D>(data: D) : Cell {

    var mData: D = data


    override fun releaseResource() {
        TODO("Not yet implemented")
    }
}