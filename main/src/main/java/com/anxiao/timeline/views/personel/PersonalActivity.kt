package com.anxiao.timeline.views.personel

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import com.anxiao.timeline.R
import com.anxiao.timeline.data.ProvinceRepo
import kotlinx.android.synthetic.main.activity_personal.*

class PersonalActivity : AppCompatActivity() {


    private lateinit var personalViewModel: PersonalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal)

        personalViewModel = defaultViewModelProviderFactory.create(PersonalViewModel::class.java)

        personalViewModel.queryProvinces.observe(this, Observer {

            println("show: ${it.dialogIsShow} ,thread ${Thread.currentThread().name}")

            if (it.dialogIsShow)
                showProgressDialog()
            else
                dismissProgressDialog()
//
//            println(it.provinces)

        })


        button.setOnClickListener {
            personalViewModel.getProvinces()
        }
    }


    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }
}