package com.anxiao.timeline.views.harvard.paging

import androidx.paging.PagingSource
import com.anxiao.timeline.data.network.HarvardService
import com.anxiao.timeline.data.vo.HarvardImage
import retrofit2.HttpException
import java.io.IOException

class ItemKeyedHarvardPagingSource(private val harvardService: HarvardService) :
    PagingSource<Int, HarvardImage>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HarvardImage> {

        val nextPageNumber = params.key ?: 1

        return try {
            val response = harvardService.getHarvardImages(nextPageNumber)
            LoadResult.Page(
                data = response.records,
                prevKey = null,
                nextKey = response.info.page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }


    }

}