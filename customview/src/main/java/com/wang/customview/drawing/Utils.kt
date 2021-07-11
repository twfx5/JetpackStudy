package com.wang.customview.drawing

import android.content.res.Resources
import android.util.TypedValue

// 工具类
object Utils {
    fun dp2ps(value: Float): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        value,
        Resources.getSystem().displayMetrics
    )

}