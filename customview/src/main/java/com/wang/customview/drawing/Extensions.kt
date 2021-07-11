package com.wang.customview.drawing

import android.content.res.Resources
import android.util.TypedValue

//fun Float.dp2px() : Float {
//    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)
//}

// 扩展属性
val Float.px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )