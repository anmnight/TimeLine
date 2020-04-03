package com.anxiao.timeline.domain.executor;

import java.util.concurrent.Executor;

public interface PostExecutionThread {
    public Executor getExecutor();
}
