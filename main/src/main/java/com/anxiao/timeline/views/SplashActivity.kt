package com.anxiao.timeline.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anxiao.core.debug
import com.anxiao.timeline.R
import com.anxiao.timeline.views.fragments.AdFragment
import com.anxiao.timeline.views.news.NewsFragment

class SplashActivity : AppCompatActivity() {

    private val _loading_without_ad = 3000
    private val _loading_with_ad = 1000
    private lateinit var adFragment: AdFragment
    private lateinit var newsFragment: NewsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        newsFragment = NewsFragment.newInstance()
        //todo create ad (FragmentDialog to show AD)
        //not have ad resource show splash 3s
        //have ad resource show 1s ,load ad fragmentDialog

        debug(classLoader.toString())

        val ts = supportFragmentManager.beginTransaction()
        ts.add(newsFragment, "news")
        ts.commitNowAllowingStateLoss()

    }


}
