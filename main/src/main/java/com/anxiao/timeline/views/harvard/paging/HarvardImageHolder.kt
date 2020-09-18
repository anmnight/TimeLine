package com.anxiao.timeline.views.harvard.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anxiao.timeline.R
import com.anxiao.timeline.data.vo.HarvardImage
import com.bumptech.glide.Glide

class HarvardImageHolder(private val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val image = itemView.findViewById<ImageView>(R.id.image)

    var harvardImageView: HarvardImage? = null


    fun bindTo(harvardImage: HarvardImage?) {
        title.text = harvardImage?.altText
        Glide.with(parent.context)
            .load(harvardImage?.baseImageUrl)
            .centerCrop()
            .into(image)
    }

}