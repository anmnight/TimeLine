package com.anxiao.timeline

import android.app.Application
import android.content.Context
import com.anxiao.timeline.CrashHandler.Companion.holder
import com.anxiao.timeline.data.db.DBRegister

class TimeLineApplication : Application() {

    companion object {
        var time = 0L
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        time = System.currentTimeMillis()

    }

    override fun onCreate() {
        super.onCreate()

        holder.init(this)

        DBRegister.init(this, "timeline_db")

        FlutterEngineManager.build(this)

    }
}