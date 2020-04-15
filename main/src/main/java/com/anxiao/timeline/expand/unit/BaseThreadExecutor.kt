package com.anxiao.timeline.expand.unit

import com.anxiao.timeline.domain.executor.ThreadExecutor

class BaseThreadExecutor : ThreadExecutor {

    override fun execute(command: Runnable?) {
        Thread(command).start()
    }

}
