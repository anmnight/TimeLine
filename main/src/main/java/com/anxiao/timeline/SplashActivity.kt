package com.anxiao.timeline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anxiao.timeline.data.Resource
import com.anxiao.timeline.data.database.DBRegister
import com.anxiao.timeline.data.repo.NewsRepo
import com.anxiao.timeline.data.vo.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)

        viewModel.newsLiveData.observe(this,
            Observer {

                Log.i(
                    "SplashActivity",
                    "Resource : States ${it.status.name} message : ${it.message} result : ${it.data}"
                )

            })


        button.setOnClickListener {
            viewModel.refresh()
        }

    }


}
