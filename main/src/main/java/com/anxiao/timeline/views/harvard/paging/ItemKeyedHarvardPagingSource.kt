package com.anxiao.timeline.views.harvard.paging

import androidx.paging.PagingSource
import com.anxiao.timeline.data.network.HarvardService
import com.anxiao.timeline.views.harvard.HarvardRepository
import com.anxiao.timeline.data.vo.HarvardImage
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class ItemKeyedHarvardPagingSource(private val harvardService: HarvardService) :
    PagingSource<Int, HarvardImage>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HarvardImage> {


        return try {
            val items =
                harvardService.getHarvardImages(index = if (params is LoadParams.Append) params.key else 0).records

            LoadResult.Page(
                data = items,
                prevKey = items.firstOrNull()?.id,
                nextKey = items.lastOrNull()?.id
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }


    }

}