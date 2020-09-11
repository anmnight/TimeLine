package com.anxiao.timeline.views.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anxiao.core.debug
import com.anxiao.core.exception.Failure
import com.anxiao.timeline.R
import com.anxiao.timeline.data.vo.News
import kotlinx.android.synthetic.main.news_fragment.*
import java.lang.Exception

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel
    private var mAdapter: NewsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProvider(
                viewModelStore,
                NewsViewModelFactory(activity!!.applicationContext),
            ).get(NewsViewModel::class.java)

        viewModel.failure.observe(viewLifecycleOwner, {
            when (it) {
                Failure.ServerError -> handlerServerError(it)
            }
        })

        viewModel.queryNews.observe(viewLifecycleOwner, {
            renderNewsList(it)
        })

        list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        mAdapter = context?.let { NewsListAdapter(it) }

        list_news.adapter = mAdapter

        viewModel.loadNews()
    }


    private fun renderNewsList(news: List<News>) {
        mAdapter?.loadMore(news)
    }

    private fun handlerServerError(failure: Failure) {
        Toast.makeText(activity, failure.toString(), Toast.LENGTH_SHORT).show()
        debug(failure.toString())
    }

}