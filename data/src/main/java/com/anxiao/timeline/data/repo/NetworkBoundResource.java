package com.anxiao.timeline.data.repo;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.anxiao.timeline.data.Resource;
import com.anxiao.timeline.data.Status;
import com.anxiao.timeline.data.network.RestResponse;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.anxiao.timeline.data.Status.LOADING;

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

        Single<Resource<ResultType>> database = loadFromDb()
                .map(resultType -> new Resource<>(Status.SUCCESS, resultType, null));

        result = Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(new Resource<>(LOADING, null, null)))
                .concatWith(database)
                .flatMap((Function<Resource<ResultType>, Publisher<Resource<ResultType>>>) resultTypeResource -> {
                    if (resultTypeResource.getStatus() == Status.SUCCESS) {
                        if (shouldFetch(resultTypeResource.getData())) {
                            return network
                                    .flatMapCompletable(this::saveCallResult)
                                    .andThen(database)
                                    .toFlowable();
                        } else {
                            return database.toFlowable();
                        }
                    } else {
                        return Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(resultTypeResource)).toFlowable();
                    }
                })
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    throwable.printStackTrace();
                    if (throwable.getMessage() != null) {
                        return new Resource<>(Status.ERROR, null, throwable.getMessage());
                    } else {
                        return new Resource<>(Status.ERROR, null, throwable.toString());
                    }
                });

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
