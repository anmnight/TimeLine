package com.anxiao.core

import android.content.Context
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object Regular {
    const val telNum =
        "/^[1]([3-9])[0-9]{9}\$/"
}

fun screenHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

fun screenWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun dp2px(context: Context, dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    )
}

fun px2dp(context: Context, px: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX,
        px,
        context.resources.displayMetrics
    )
}

fun AppCompatActivity.debug(message: Any?) {
    Log.d(Thread.currentThread().name, message.toString())
}

fun Fragment.debug(message: Any?) {
    Log.d(Thread.currentThread().name, message.toString())
}