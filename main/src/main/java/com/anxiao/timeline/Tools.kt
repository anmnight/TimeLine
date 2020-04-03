package com.anxiao.timeline

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.TypedValue
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Tools

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


fun nowDateString(): ViewDate {
    val ca = Calendar.getInstance()
    val mYear = ca.get(Calendar.YEAR)
    val mMonth = ca.get(Calendar.MONTH) + 1
    val mDay = ca.get(Calendar.DAY_OF_MONTH)
    val mHour = 9
    val mMinute = 0
    return ViewDate(mYear, mMonth, mDay, mHour, mMinute)
}

fun defaultViewDate(): ViewDate {
    val ca = Calendar.getInstance()
    val mYear = ca.get(Calendar.YEAR)
    val mMonth = ca.get(Calendar.MONTH) + 1
    val mDay = ca.get(Calendar.DAY_OF_MONTH)
    val mHour = 9
    val mMinute = 0
    return ViewDate(mYear, mMonth, mDay, mHour, mMinute)
}

fun getViewDataByData(data: Date): ViewDate {
    val calender = Calendar.getInstance()
    calender.time = data

    return ViewDate(
        calender.get(Calendar.YEAR),
        calender.get(Calendar.MONTH) + 1,
        calender.get(Calendar.DAY_OF_MONTH),
        calender.get(Calendar.HOUR_OF_DAY),
        calender.get(Calendar.MINUTE)
    )

}

fun buildDatePicker(
    context: Context,
    callback: DatePickerDialog.OnDateSetListener
): DatePickerDialog {
    return DatePickerDialog(
        context,
        callback,
        defaultViewDate().year,
        defaultViewDate().month - 1,
        defaultViewDate().day
    )
}

fun buildTimePicker(
    context: Context,
    callback: TimePickerDialog.OnTimeSetListener
): TimePickerDialog {
    return TimePickerDialog(
        context,
        callback, defaultViewDate().hour, defaultViewDate().minute, true
    )
}

@SuppressLint("SimpleDateFormat")
fun getTime(timeString: String): Long {
    var timeStamp = 0L
    val sdf = SimpleDateFormat("dd日MM月yyyy年")
    val d: Date
    try {
        d = sdf.parse(timeString)
        timeStamp = d.time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return timeStamp
}

data class ViewDate(var year: Int, var month: Int, var day: Int, var hour: Int, var minute: Int)

