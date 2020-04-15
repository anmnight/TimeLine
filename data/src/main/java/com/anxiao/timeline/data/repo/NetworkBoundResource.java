package com.anxiao.timeline.data.repo;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.anxiao.timeline.data.Resource;
import com.anxiao.timeline.data.network.RestResponse;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType> {

    private final Flowable<Resource<ResultType>> result;

    public NetworkBoundResource() {

        Single<ResultType> network = createCall()
                .flatMap(resultTypeRestResponse -> {
                    if (resultTypeRestResponse.getCode() == 200) {
                        return Single.just(resultTypeRestResponse.getResult());
                    } else {
                        return Single.error(new Throwable("net error with code : " + resultTypeRestResponse.getCode()));
                    }
                });


        Single<Resource<ResultType>> doDataStream =
                loadFromDb().flatMap(resultType -> {
                    if (shouldFetch(resultType)) {
                        return network
                                .flatMapCompletable(this::saveCallResult)
                                .andThen(loadFromDb());
                    } else {
                        return loadFromDb();
                    }
                }).map(Resource.Companion::success
                );

        result = Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(Resource.Companion.loading(null)))
                .concatWith(doDataStream)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    throwable.printStackTrace();
                    if (throwable.getMessage() != null) {
                        return Resource.Companion.error(throwable.getMessage(), null);
                    } else {
                        return Resource.Companion.error(throwable.getMessage(), null);
                    }
                });

    }

    public Flowable<Resource<ResultType>> asFlowable() {
        return result;
    }


    protected abstract Completable saveCallResult(@NonNull ResultType source);

    protected abstract boolean shouldFetch(ResultType data);

    protected abstract Single<ResultType> loadFromDb();

    protected abstract Single<RestResponse<ResultType>> createCall();


}
