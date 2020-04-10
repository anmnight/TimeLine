package com.anxiao.timeline.data.repo;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.anxiao.timeline.data.Resource;
import com.anxiao.timeline.data.Status;
import com.anxiao.timeline.data.network.RestResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType> {

    private final Flowable<Resource<ResultType>> result;


    public NetworkBoundResource() {

        Single<Resource<ResultType>> network = createCall()
                .flatMap((Function<RestResponse<ResultType>, SingleSource<Resource<ResultType>>>) resultTypeRestResponse -> {
                    if (resultTypeRestResponse.getCode() == 200) {
                        saveCallResult(resultTypeRestResponse.getResult());
                        return Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(new Resource<>(Status.SUCCESS, resultTypeRestResponse.getResult(), null)));
                    } else {
                        return Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(new Resource<>(Status.ERROR, null, resultTypeRestResponse.getMessage())));
                    }
                })
                .onErrorReturn(throwable -> new Resource<>(Status.ERROR, null, throwable.getMessage()));


        Single<Resource<ResultType>> fetchData = loadFromDb()
                .subscribeOn(Schedulers.io())
                .flatMap((Function<ResultType, SingleSource<Resource<ResultType>>>) resultType -> {
                    if (shouldFetch(resultType)) {
                        return network;
                    } else {
                        return Single.create(emitter -> emitter.onSuccess(new Resource<>(Status.SUCCESS, resultType, null)));
                    }
                })
                .onErrorReturn(throwable -> new Resource<>(Status.ERROR, null, throwable.getMessage()));
        ;


        result = Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(new Resource<>(Status.LOADING, null, null)))
                .concatWith(fetchData);

    }

    public Flowable<Resource<ResultType>> asFlowable() {
        return result;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull ResultType source);

    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    @MainThread
    protected abstract Single<ResultType> loadFromDb();

    @MainThread
    protected abstract Single<RestResponse<ResultType>> createCall();


}
