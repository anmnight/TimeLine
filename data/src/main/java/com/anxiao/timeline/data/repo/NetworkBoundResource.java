package com.anxiao.timeline.data.repo;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.anxiao.timeline.data.Resource;
import com.anxiao.timeline.data.Status;
import com.anxiao.timeline.data.network.RestResponse;

import org.reactivestreams.Publisher;

import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType> {

    private final Flowable<Resource<ResultType>> result;


    public NetworkBoundResource() {

        Single<ResultType> network = createCall()
                .flatMap((Function<RestResponse<ResultType>, SingleSource<ResultType>>) resultTypeRestResponse -> {
                    if (resultTypeRestResponse.getCode() == 200) {
                        return Single.create((SingleOnSubscribe<ResultType>) emitter -> emitter.onSuccess(resultTypeRestResponse.getResult()));
                    } else {
                        throw new Exception("on error with code : " + resultTypeRestResponse.getCode());
                    }
                });

        Single<Resource<ResultType>> database = loadFromDb()
                .subscribeOn(Schedulers.io())
                .flatMap((Function<ResultType, SingleSource<Resource<ResultType>>>) resultType -> Single.create(emitter -> emitter.onSuccess(new Resource<>(Status.SUCCESS, resultType, null))));


        result = Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(new Resource<>(Status.LOADING, null, null)))
                .concatWith(database)
                .flatMap((Function<Resource<ResultType>, Publisher<Resource<ResultType>>>) resultTypeResource -> {
//                    if (shouldFetch(resultTypeResource.getData())) {
//                        return network
//                                .flatMapCompletable(resultType -> {
//                                    if (resultType != null) {
//                                        return saveCallResult(resultType);
//                                    } else {
//                                        return Completable.complete();
//                                    }
//                                })
//                                .andThen(database)
//                                .toFlowable();
//                    } else {
//                        return database.toFlowable();
//                    }

                    //todo cache network data
                    return network
                            .flatMapCompletable(resultType -> {
                                if (resultType != null) {
                                    return saveCallResult(resultType);
                                } else {
                                    return Completable.complete();
                                }
                            })
                            .andThen(database)
                            .toFlowable();


                })
                .onErrorReturn(throwable -> new Resource<>(Status.ERROR, null, throwable.getMessage()));

    }

    public Flowable<Resource<ResultType>> asFlowable() {
        return result;
    }

    @WorkerThread
    protected abstract Completable saveCallResult(@NonNull ResultType source);

    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    @MainThread
    protected abstract Single<ResultType> loadFromDb();

    @MainThread
    protected abstract Single<RestResponse<ResultType>> createCall();


}
