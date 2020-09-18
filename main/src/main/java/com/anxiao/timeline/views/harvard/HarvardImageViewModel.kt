package com.anxiao.timeline.views.harvard

import com.anxiao.core.platform.BaseViewModel
import com.anxiao.timeline.views.harvard.paging.ItemKeyedHarvardRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel

class HarvardImageViewModel constructor(repository: ItemKeyedHarvardRepository) : BaseViewModel() {


    private val clearListCh = Channel<Unit>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val images = repository.getHarvardImages(1)



}

