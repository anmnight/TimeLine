package com.anxiao.timeline.domain.interactor;

import androidx.lifecycle.LiveData;

import com.anxiao.timeline.data.factory.Resource;
import com.anxiao.timeline.domain.LiveDataUseCase;
import com.anxiao.timeline.expand.executor.PostExecutionThread;
import com.anxiao.timeline.expand.executor.ThreadExecutor;

public class ReadIDCardCase extends LiveDataUseCase<String, String> {



    public ReadIDCardCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    public LiveData<Resource<String>> execute(String params) throws Exception {
        return null;
    }


}
