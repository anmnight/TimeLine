package com.anxiao.timeline

import android.content.Context
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

object FlutterEngineManager {

    private lateinit var flutterEngine: FlutterEngine

    private val defaultEngine = "default_engine"

    fun build(context: Context) {

        flutterEngine = FlutterEngine(context)

        flutterEngine.navigationChannel.setInitialRoute("/home")

        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())

        FlutterEngineCache.getInstance().put(defaultEngine, flutterEngine)
    }


    fun getDonutEngine(): FlutterEngine {



        return flutterEngine
    }
}