package com.anxiao.timeline.views.harvard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.anxiao.core.debug
import com.anxiao.core.exception.Failure
import com.anxiao.timeline.R
import com.anxiao.timeline.data.vo.HarvardImage
import com.anxiao.timeline.views.harvard.paging.HarvardImageAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

class HarvardImageFragment : Fragment() {

    companion object {
        fun newInstance() = HarvardImageFragment()
    }

    private lateinit var viewModel: HarvardImageViewModel


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


        //配置list
        val adapter = HarvardImageAdapter()
        list_news.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        list_news.adapter = adapter

        //配置Paging

        lifecycleScope.launchWhenCreated {
            viewModel.images.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            @OptIn(FlowPreview::class)
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { list_news.scrollToPosition(0) }
        }


    }


    private fun renderNewsList(news: List<HarvardImage>) {
//        mAdapter?.loadMore(news)
    }

    private fun handlerServerError(failure: Failure) {
        Toast.makeText(activity, failure.toString(), Toast.LENGTH_SHORT).show()
        debug(failure.toString())
    }

}