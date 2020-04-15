package com.anxiao.timeline.expand.widget

import android.view.ViewGroup

interface Cell {

    //回收资源
    fun releaseResource()

    //获取ItemView Type
    fun getItemType(): Int

    fun bindViewHolder(viewHolder: RVBaseViewHolder, position: Int)

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVBaseViewHolder
}