package com.anxiao.timeline.views.harvard

import androidx.paging.PagingSource
import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.core.interactor.UseCase
import com.anxiao.timeline.data.vo.HarvardImage
import kotlinx.coroutines.flow.Flow


class GetHarvardImagePaging : UseCase<PagingSource<Int, HarvardImage>, Int>() {

    override suspend fun run(params: Int): Either<Failure, PagingSource<Int, HarvardImage>> {
        TODO("Not yet implemented")
    }


}