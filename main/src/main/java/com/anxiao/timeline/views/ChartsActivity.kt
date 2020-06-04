package com.anxiao.timeline.views

import android.content.Context
import android.util.Log
import com.anxiao.timeline.FlutterEngineManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class ChartsActivity : FlutterActivity() {

    private val _channel = "com.test/test"
    private val engine = FlutterEngineManager.getDonutEngine()

    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return engine
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {

        val channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, _channel)

        channel.setMethodCallHandler { methodCall, result ->

            Log.d(_channel, "method name ${methodCall.method}")

            Thread {
                Thread.sleep(3000)
                runOnUiThread {
                    result.success("anxiaos callback")
                }
            }.start()

        }


    }

}