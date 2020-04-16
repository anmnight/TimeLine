package com.anxiao.timeline.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.anxiao.timeline.R
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {


    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        list_news.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(this)
        list_news.adapter = adapter


    }

    override fun onResume() {
        super.onResume()

        viewModel.newsLiveData.observe(this, Observer {
            Log.d("NewsActivity", "STATUS : " + it.status)

            it.data?.let { it1 -> adapter.update(it1) }

        })
    }
}
