package com.anxiao.timeline.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anxiao.timeline.R
import com.anxiao.timeline.views.fragments.AdFragment

class SplashActivity : AppCompatActivity() {

    private val _loading_without_ad = 3000
    private val _loading_with_ad = 1000
    private lateinit var adFragment:AdFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //todo create ad (FragmentDialog to show AD)
        //not have ad resource show splash 3s
        //have ad resource show 1s ,load ad fragmentDialog




    }



    private fun createAdDialog(){



    }

    private fun navToMain(){

    }

}
