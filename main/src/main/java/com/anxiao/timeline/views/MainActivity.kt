package com.anxiao.timeline.views

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.anxiao.timeline.R
import com.anxiao.timeline.views.news.NewsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var newsFragment: NewsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        newsFragment = NewsFragment.newInstance()

        val ts = supportFragmentManager.beginTransaction()
        ts.add(R.id.content, newsFragment)
        ts.commitNow()

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }
}