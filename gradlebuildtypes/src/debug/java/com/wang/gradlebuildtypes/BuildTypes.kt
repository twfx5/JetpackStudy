package com.wang.gradlebuildtypes

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup

/**
 * Created at 2021/8/23 上午6:29.
 * @author wang
 */
// debug 版是红色
fun Activity.drawBadge() {
    val decorView = window.decorView as ViewGroup
    val badge = View(this).apply {
        setBackgroundColor(Color.RED)
    }
    decorView.addView(badge, 200, 200)
}