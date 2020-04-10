package com.anxiao.timeline.data.repo;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;

import com.anxiao.timeline.data.Resource;
import com.anxiao.timeline.data.Status;
import com.anxiao.timeline.data.network.RestResponse;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public abstract class NetworkBoundResource<ResultType> {

    private final Flowable<Resource<ResultType>> result;


    public NetworkBoundResource() {

        Flowable<Resource<ResultType>> network;

        try {
            network = createCall()
                    .flatMap((Function<RestResponse<ResultType>, SingleSource<Resource<ResultType>>>) resultTypeRestResponse -> {
                        if (resultTypeRestResponse.getCode() == 200) {
                            saveCallResult(resultTypeRestResponse.getResult());
                            return Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(new Resource<>(Status.SUCCESS, resultTypeRestResponse.getResult(), null)));
                        } else {
                            return Single.create((SingleOnSubscribe<Resource<ResultType>>) emitter -> emitter.onSuccess(new Resource<>(Status.ERROR, null, resultTypeRestResponse.getMessage())));
                        }
                    }).toFlowable();
        } catch (Exception e) {
            network = Flowable.error(new Throwable(e.getMessage()));
        }

        Flowable<Resource<ResultType>> finalNetwork = network;

        result = loadFromDb()
                .flatMap((Function<ResultType, Publisher<Resource<ResultType>>>) resultType -> {
                    if (shouldFetch(resultType)) {
                        return finalNetwork;
                    } else {
                        return Flowable.create((FlowableOnSubscribe<Resource<ResultType>>) emitter -> emitter.onNext(new Resource<>(Status.SUCCESS, resultType, null)), BackpressureStrategy.ERROR);
                    }
                });

    }

    public Flowable<Resource<ResultType>> asFlowable() {
        return result;
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull ResultType source);

    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @MainThread
    protected abstract Single<RestResponse<ResultType>> createCall();


}
