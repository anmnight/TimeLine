package com.anxiao.timeline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

//        viewModel.branchLiveData.observe(this, Observer {
//
//
//            if (it.status == Status.LOADING) {
//                Log.d(
//                    "tag", "loading"
//                )
//            }
//
//            if (it.status == Status.SUCCESS) {
//                Log.i(
//                    "tag", "Size : ${it.data?.size}"
//                )
//            }
//
//            if (it.status == Status.ERROR) {
//                Log.e(
//                    "tag", "on error"
//                )
//            }
//
//        })


        button.setOnClickListener {
            viewModel.refresh()
        }

    }




}
