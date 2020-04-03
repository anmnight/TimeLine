package com.anxiao.timeline.unit

import android.os.Handler
import android.os.Looper
import com.anxiao.timeline.domain.executor.PostExecutionThread
import java.util.concurrent.Executor

class PostMainThreadExecutor : PostExecutionThread {

    val mainHandler = Handler(Looper.getMainLooper())

    override fun getExecutor(): Executor {
        return MainExecutor()
    }

    private inner class MainExecutor : Executor {
        override fun execute(command: Runnable) {
            mainHandler.post(command)
        }
    }

}
