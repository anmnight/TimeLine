package com.anxiao.timeline.views.harvard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anxiao.core.debug
import com.anxiao.core.exception.Failure
import com.anxiao.timeline.R
import com.anxiao.timeline.data.vo.HarvardImage
import com.anxiao.timeline.data.vo.News
import kotlinx.android.synthetic.main.news_fragment.*

class HarvardImageFragment : Fragment() {

    companion object {
        fun newInstance() = HarvardImageFragment()
    }

    private lateinit var viewModel: HarvardImageViewModel
    private var mAdapter: HarvardImageListAdapter? = null

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
                HarvardImageViewModelFactory(
                    activity!!.applicationContext
                ),
            ).get(HarvardImageViewModel::class.java)

        viewModel.failure.observe(viewLifecycleOwner, {
            when (it) {
                Failure.ServerError -> handlerServerError(it)
            }
        })

        viewModel.queryHarvardImage.observe(viewLifecycleOwner, {
            renderNewsList(it)
        })

        list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        mAdapter = context?.let { HarvardImageListAdapter(it) }

        list_news.adapter = mAdapter

        viewModel.loadHarvardImage()
    }


    private fun renderNewsList(news: List<HarvardImage>) {
        mAdapter?.loadMore(news)
    }

    private fun handlerServerError(failure: Failure) {
        Toast.makeText(activity, failure.toString(), Toast.LENGTH_SHORT).show()
        debug(failure.toString())
    }

}