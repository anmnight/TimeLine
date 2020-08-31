package com.anxiao.timeline.views.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anxiao.timeline.R
import com.anxiao.timeline.data.vo.News
import com.bumptech.glide.Glide

class NewsListAdapter(val context: Context) : RecyclerView.Adapter<NewsListAdapter.VH>() {

    private val mNews = arrayListOf<News>()

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.item_news, parent, true))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val news = mNews[position]
        holder.title.text = news.title
        Glide.with(context)
            .load(news.image)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return mNews.size
    }

    fun loadMore(newNews: List<News>) {
        mNews.addAll(newNews)
    }

    fun refresh(news: List<News>) {
        mNews.clear()
        mNews.addAll(news)
    }
}