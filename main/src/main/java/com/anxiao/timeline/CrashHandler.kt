package com.anxiao.timeline

import android.content.Context

class CrashHandler : Thread.UncaughtExceptionHandler {

    private val mDefaultCrashHandler: Thread.UncaughtExceptionHandler =
        Thread.getDefaultUncaughtExceptionHandler()

    companion object {
        val holder: CrashHandler = CrashHandler()
    }

    fun init(context: Context) {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread?, e: Throwable?) {
        dumpExceptionToSdcard(e)
        uploadExceptionToServer()
        mDefaultCrashHandler.uncaughtException(t, e)
    }

    //写入异常进SD卡
    private fun dumpExceptionToSdcard(e: Throwable?) {

    }

    //上传至Server
    private fun uploadExceptionToServer() {

    }


}