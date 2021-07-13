package com.wang.customview

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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

//val Int.px
//    get() = this.toFloat().px.toInt()

/**
 * 获取头像
 * 这里不能用系统的 Resources.getSystem()
 * 需要用本地的 resources
 */
fun getAvatar(resources: Resources, width: Int) : Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, R.drawable.xiaoxin, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(resources, R.drawable.xiaoxin, options)
}

