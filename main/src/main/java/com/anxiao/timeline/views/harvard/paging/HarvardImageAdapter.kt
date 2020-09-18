package com.anxiao.timeline.views.harvard.paging

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.anxiao.timeline.data.vo.HarvardImage

class HarvardImageAdapter : PagingDataAdapter<HarvardImage, HarvardImageHolder>(diffCallback) {

    override fun onBindViewHolder(holder: HarvardImageHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarvardImageHolder =
        HarvardImageHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<HarvardImage>() {
            override fun areItemsTheSame(oldItem: HarvardImage, newItem: HarvardImage): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: HarvardImage, newItem: HarvardImage): Boolean =
                oldItem == newItem

        }
    }


}