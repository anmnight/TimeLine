package com.anxiao.timeline.views.harvard.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anxiao.core.platform.NetworkHandler
import com.anxiao.timeline.data.network.HarvardService
import com.anxiao.timeline.data.vo.HarvardImage
import com.anxiao.timeline.views.harvard.HarvardRepository
import kotlinx.coroutines.flow.Flow

class ItemKeyedHarvardRepository(
    private val networkHandler: NetworkHandler,
    private val service: HarvardService
) : HarvardRepository.Network(networkHandler, service) {


    override fun getHarvardImages(index: Int): Flow<PagingData<HarvardImage>> {

        return Pager(
            PagingConfig(pageSize = 5, enablePlaceholders = false)
        ) {
            ItemKeyedHarvardPagingSource(service)
        }.flow
    }


}