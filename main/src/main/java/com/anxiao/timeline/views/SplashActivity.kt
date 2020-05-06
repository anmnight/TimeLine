package com.anxiao.timeline.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.anxiao.timeline.R
import com.anxiao.timeline.TimeLineApplication

class SplashActivity : AppCompatActivity() {


    private var temp = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.decorView.background = null

        window.decorView.viewTreeObserver.addOnPreDrawListener {
            println("Startup Spend : ${System.currentTimeMillis() - TimeLineApplication.time}")
            true
        }

    }


}
