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
import org.jetbrains.annotations.NotNull

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsItemVH>() {

    private var newsList: ArrayList<News> = arrayListOf()

    constructor(context: Context, @NotNull news: List<News>) : this(context) {
        newsList = news as ArrayList<News>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemVH {
        val view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return NewsItemVH(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsItemVH, position: Int) {
        val news = newsList[position]
        holder.title.text = news.title
        holder.passtime.text = news.passtime
        //设置滚动不加载
        Glide.with(holder.itemView)
            .load(news.image)
            .optionalCenterCrop()
            .into(holder.image)
    }

    fun update(news: List<News>) {
        newsList = news as ArrayList<News>
        notifyDataSetChanged()
    }


    class NewsItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        val passtime: TextView = itemView.findViewById(R.id.passtime)
    }

}