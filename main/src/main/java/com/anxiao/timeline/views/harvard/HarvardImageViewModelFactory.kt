package com.anxiao.timeline.views.harvard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anxiao.core.platform.NetworkHandler
import com.anxiao.timeline.data.network.HarvardService
import com.anxiao.timeline.data.network.Server
import com.anxiao.timeline.views.harvard.paging.ItemKeyedHarvardRepository

class HarvardImageViewModelFactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val itemKeyedHarvardRepository =
            ItemKeyedHarvardRepository(
                NetworkHandler(context),
                HarvardService(Server().retrofit())
            )

        return modelClass.getConstructor(ItemKeyedHarvardRepository::class.java)
            .newInstance(itemKeyedHarvardRepository)
    }

}