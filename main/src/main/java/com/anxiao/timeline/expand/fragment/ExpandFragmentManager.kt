package com.anxiao.timeline.expand.fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.anxiao.timeline.R


/**
 * @author:       anxiao
 * @version:      V1.0
 * @project:      MatmMobile
 * @package:      com.chinasofti.ultraframework.expand
 * @description:  description
 * @date:         2019-12-30
 * @time:         18:57
 */


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.setCustomAnimations(
        R.anim.layout_right_in,
        R.anim.layout_left_out,
        R.anim.layout_left_in,
        R.anim.layout_right_out
    )
    fragmentTransaction.func()
    fragmentTransaction.commit()
}
