package com.anxiao.timeline.views.harvard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anxiao.timeline.R
import com.anxiao.timeline.data.vo.HarvardImage
import com.bumptech.glide.Glide

class HarvardImageListAdapter(val context: Context) :
    RecyclerView.Adapter<HarvardImageListAdapter.VH>() {

    private val mHarvardImages = arrayListOf<HarvardImage>()

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val harvardImage = mHarvardImages[position]
        holder.title.text = harvardImage.copyright
        Glide.with(context)
            .load(harvardImage.baseImageUrl)
            .centerCrop()
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return mHarvardImages.size
    }

    fun loadMore(newHarvardImage: List<HarvardImage>) {
        mHarvardImages.addAll(newHarvardImage)
        notifyItemRangeInserted(mHarvardImages.size, newHarvardImage.size)
    }

    fun refresh(harvardImages: List<HarvardImage>) {
        mHarvardImages.clear()
        mHarvardImages.addAll(harvardImages)
        notifyDataSetChanged()
    }
}