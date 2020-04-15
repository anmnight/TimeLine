package com.anxiao.timeline.unit

import com.anxiao.timeline.expand.executor.ThreadExecutor

class BaseThreadExecutor : ThreadExecutor {

    override fun execute(command: Runnable?) {
        Thread(command).start()
    }

}
