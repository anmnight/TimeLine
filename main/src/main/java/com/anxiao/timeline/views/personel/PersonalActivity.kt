package com.anxiao.timeline.views.personel

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.anxiao.timeline.R
import com.anxiao.timeline.data.ProvinceRepo
import kotlinx.android.synthetic.main.activity_personal.*
import kotlin.properties.Delegates



class PersonalActivity : AppCompatActivity() {

    private lateinit var personalViewModel: PersonalViewModel

    private var name: String by Delegates.observable("anxioa", { prop, old, new ->
        println("prop: $prop ,old: $old new: $new")
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal)


        val repo = ProvinceRepo()
        val factory = ProvinceViewModelFactory(repo)

        personalViewModel = ViewModelProvider(viewModelStore, factory).get(PersonalViewModel::class.java)

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
//            personalViewModel.getProvinces()

            name = "zhang"
            name = "wang"
            name = "li"

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