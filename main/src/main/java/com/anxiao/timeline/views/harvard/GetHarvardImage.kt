package com.anxiao.timeline.views.harvard

import com.anxiao.core.exception.Failure
import com.anxiao.core.functional.Either
import com.anxiao.core.interactor.UseCase
import com.anxiao.timeline.data.HarvardRepository
import com.anxiao.timeline.data.vo.HarvardImage

class GetHarvardImage(private val harvardRepository: HarvardRepository) :
    UseCase<List<HarvardImage>, Int>() {
    override suspend fun run(params: Int): Either<Failure, List<HarvardImage>> =
        harvardRepository.getHarvardImages(params)
}