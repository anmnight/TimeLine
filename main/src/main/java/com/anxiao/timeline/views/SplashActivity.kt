package com.anxiao.timeline.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anxiao.timeline.R
import com.anxiao.timeline.TimeLineApplication
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_splash.*

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

        button.onClick {
            startActivity(Intent(this@SplashActivity, ChartsActivity::class.java))
        }

    }


}
