package com.anxiao.timeline.views.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.anxiao.core.debug
import com.anxiao.core.exception.Failure
import com.anxiao.timeline.R
import com.anxiao.timeline.data.vo.News

class NewsFragment : Fragment() {

    companion object {
        fun newInstance() = NewsFragment()
    }

    private lateinit var viewModel: NewsViewModel

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
                Failure.ServerError -> handlerServerError()
            }
        })

        viewModel.queryNews.observe(viewLifecycleOwner, {
            renderNewsList(it)
        })

        viewModel.loadNews()
    }


    private fun renderNewsList(news: List<News>) {

        debug("news list size : ${news.size}")

    }

    private fun handlerServerError() {

        debug("handlerServerError")
    }

}