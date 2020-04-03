package com.anxiao.timeline.data.repo;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.anxiao.timeline.data.ApiEmptyResponse;
import com.anxiao.timeline.data.ApiErrorResponse;
import com.anxiao.timeline.data.ApiResponse;
import com.anxiao.timeline.data.ApiSuccessResponse;
import com.anxiao.timeline.data.AppExecutors;
import com.anxiao.timeline.data.Resource;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    final private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
    private AppExecutors mExecutors;

    public NetworkBoundResource(AppExecutors executors) {
        this.mExecutors = executors;

        result.setValue(Resource.Companion.loading(null));

        LiveData<ResultType> dbSource = loadFromDb();

        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType resultType) {
                result.removeSource(dbSource);
                if (shouldFetch(resultType)) {
                    fetchFromNetwork(dbSource);
                } else {
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(ResultType resultType) {
                            setValue(Resource.Companion.success(resultType));
                        }
                    });
                }
            }
        });
    }

    private void fetchFromNetwork(LiveData<ResultType> dbResult) {

        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbResult, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType resultType) {
                setValue(Resource.Companion.success(resultType));
            }
        });


        result.addSource(apiResponse, new Observer<ApiResponse<RequestType>>() {
            @Override
            public void onChanged(ApiResponse<RequestType> requestTypeApiResponse) {

                result.removeSource(dbResult);
                result.removeSource(apiResponse);

                if (requestTypeApiResponse instanceof ApiSuccessResponse) {
                    mExecutors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            saveCallResult(processResponse((ApiSuccessResponse<RequestType>) requestTypeApiResponse));

                            mExecutors.mainThread().execute(new Runnable() {
                                @Override
                                public void run() {
                                    result.addSource(loadFromDb(), new Observer<ResultType>() {
                                        @Override
                                        public void onChanged(ResultType resultType) {
                                            setValue(Resource.Companion.success(resultType));
                                        }
                                    });
                                }
                            });
                        }
                    });
                }

                if (requestTypeApiResponse instanceof ApiEmptyResponse) {
                    mExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            result.addSource(loadFromDb(), new Observer<ResultType>() {
                                @Override
                                public void onChanged(ResultType resultType) {
                                    setValue(Resource.Companion.success(resultType));
                                }
                            });
                        }
                    });
                }

                if (requestTypeApiResponse instanceof ApiErrorResponse) {
                    mExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            onFetchFailed();
                            result.addSource(dbResult, new Observer<ResultType>() {
                                @Override
                                public void onChanged(ResultType resultType) {
                                    setValue(Resource.Companion.error(((ApiErrorResponse<RequestType>) requestTypeApiResponse).getErrorMessage(), resultType));
                                }
                            });
                        }
                    });
                }
            }
        });
    }


    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (result.getValue() != newValue) {
            result.setValue(newValue);
        }
    }


    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }

    protected void onFetchFailed() {
    }

    @WorkerThread
    protected RequestType processResponse(ApiSuccessResponse<RequestType> response) {
        return response.getBody();
    }

    @WorkerThread
    protected abstract void saveCallResult(RequestType source);

    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();


}
