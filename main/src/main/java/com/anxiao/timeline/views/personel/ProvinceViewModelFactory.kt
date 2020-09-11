package com.anxiao.timeline.views.personel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anxiao.timeline.data.RegionRepository

class ProvinceViewModelFactory(private val repo: RegionRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RegionRepository::class.java).newInstance(repo)
    }

}