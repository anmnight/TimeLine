package com.anxiao.timeline.domain.interactor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.anxiao.timeline.data.OCRRepo;
import com.anxiao.timeline.data.factory.Resource;
import com.anxiao.timeline.data.vo.News;
import com.anxiao.timeline.domain.LiveDataUseCase;
import com.anxiao.timeline.expand.executor.PostExecutionThread;
import com.anxiao.timeline.expand.executor.ThreadExecutor;

import java.util.List;

public class ReadIDCardCase extends LiveDataUseCase<String, List<News>> {

    private OCRRepo repo;

//    public ReadIDCardCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
//        super(threadExecutor, postExecutionThread);
//    }

    @Override
    public LiveData<Resource<List<News>>> execute(String params) throws Exception {
        return LiveDataReactiveStreams.fromPublisher(repo.getAccessToken()
                .concatWith(repo.readPicture()));
    }


}
