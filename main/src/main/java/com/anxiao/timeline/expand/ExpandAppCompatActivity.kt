package com.anxiao.timeline.expand

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * @author:       anxiao
 * @version:      V1.0
 * @project:      MatmMobile
 * @package:      com.chinasofti.ultraframework.expand
 * @description:  description
 * @date:         2019-12-30
 * @time:         19:06
 */

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction { remove(fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

fun AppCompatActivity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showLoading(
    context: Context,
    title: CharSequence,
    message: CharSequence
): Dialog {
    return ProgressDialog.show(context, title, message)
}

fun AppCompatActivity.closeLoading(dialog: Dialog) {
    dialog.hide()
}