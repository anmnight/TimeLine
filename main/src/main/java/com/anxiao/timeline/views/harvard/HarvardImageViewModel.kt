package com.anxiao.timeline.views.harvard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anxiao.core.platform.BaseViewModel
import com.anxiao.timeline.data.vo.HarvardImage

class HarvardImageViewModel constructor(private val getHarvardImage: GetHarvardImage) : BaseViewModel() {

    private val _queryHarvardImage = MutableLiveData<List<HarvardImage>>()

    val queryHarvardImage: LiveData<List<HarvardImage>> = _queryHarvardImage

    fun loadHarvardImage() =
        getHarvardImage.invoke(1) { it.fold(::handlerFailure, ::handlerNewsList) }

    private fun handlerNewsList(harvardImages: List<HarvardImage>) {
        _queryHarvardImage.value = harvardImages
    }


}

