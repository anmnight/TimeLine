package com.anxiao.timeline.domain;

import com.anxiao.timeline.domain.executor.PostExecutionThread;
import com.anxiao.timeline.domain.executor.ThreadExecutor;

/**
 * @author: anxiao
 * @version: V1.0
 * @project: MatmMobile
 * @package: com.chinasofti.matmmobile.domain
 * @description: description
 * @date: 2019-12-31
 * @time: 14:48
 */
public abstract class UseCase<P extends UseCase.RequestValues, R extends UseCase.ResponseValue> {

    private ThreadExecutor mThreadExecutor;
    private PostExecutionThread mPostExecutionThread;

    public UseCase(
            ThreadExecutor threadExecutor,
            PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
    }

    public abstract R execute(P params) throws Exception;

    public void postExecute(final P params, final ResultCallback<R> resultCallback) {
        mThreadExecutor.execute(() -> {
            try {
                final R resp = execute(params);

                mPostExecutionThread.getExecutor().execute(() -> resultCallback.onSuccess(resp));

            } catch (Exception e) {
                e.printStackTrace();
                mPostExecutionThread.getExecutor().execute(() -> resultCallback.onFailed(new Throwable(e.getMessage())));
            }
        });
    }


    public void postExecute(final P params, final ResultCallback<R> resultCallback, final LifeCallback lifeCallback) {
        mThreadExecutor.execute(() -> {
            try {
                mPostExecutionThread.getExecutor().execute(lifeCallback::onStart);
                final R resp = execute(params);
                mPostExecutionThread.getExecutor().execute(() -> resultCallback.onSuccess(resp));

            } catch (Exception e) {
                e.printStackTrace();
                mPostExecutionThread.getExecutor().execute(() -> resultCallback.onFailed(new Throwable(e.getMessage())));
            } finally {
                mPostExecutionThread.getExecutor().execute(lifeCallback::onFinish);

            }
        });
    }

    public interface ResultCallback<R> {

        public void onSuccess(R resp);

        public void onFailed(Throwable throwable);

    }


    public interface LifeCallback {

        public void onStart();

        public void onFinish();
    }


    public interface RequestValues {
    }

    public interface ResponseValue {
    }

}
