package com.anxiao.timeline.domain;

import androidx.lifecycle.LiveData;

import com.anxiao.timeline.data.factory.Resource;
import com.anxiao.timeline.expand.executor.PostExecutionThread;
import com.anxiao.timeline.expand.executor.ThreadExecutor;

/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.matmmobile.domain
 * @description: description
 * @date: 2019-12-31
 * @time: 14:48
 */
public abstract class LiveDataUseCase<P, R> {

//    private ThreadExecutor mThreadExecutor;
//    private PostExecutionThread mPostExecutionThread;
//
//    public LiveDataUseCase(
//            ThreadExecutor threadExecutor,
//            PostExecutionThread postExecutionThread) {
//        mThreadExecutor = threadExecutor;
//        mPostExecutionThread = postExecutionThread;
//    }

    public abstract LiveData<Resource<R>> execute(P params) throws Exception;







}
