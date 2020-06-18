package com.anxiao.timeline.views.personel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anxiao.timeline.data.ProvinceRepo

class ProvinceViewModelFactory(private val repo: ProvinceRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProvinceRepo::class.java).newInstance(repo)
    }

}