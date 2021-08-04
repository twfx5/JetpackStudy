package com.wang.customviewgroup

import android.content.res.Resources
import android.drm.DrmRights
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.TypedValue
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.TextView
import java.lang.IllegalArgumentException


//fun Float.dp2px() : Float {
//    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)
//}

// 扩展属性  dp 转为 px
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

// 扩展属性  sp 转为 px
val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

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

/**
 * 默认的测量方式,获取 widthMeasureSpec
 */
fun View.defaultWidthMeasureSpec(parentView: ViewGroup): Int {
    return when(layoutParams.width) {
        ViewGroup.LayoutParams.MATCH_PARENT -> parentView.measuredWidth.toExactlyMeasureSpec()
        ViewGroup.LayoutParams.WRAP_CONTENT -> ViewGroup.LayoutParams.WRAP_CONTENT.toAtMostMeasureSpec()
        0 -> throw IllegalAccessException("Need special treatment for $this")
        else -> layoutParams.width.toExactlyMeasureSpec()
    }
}

/**
 * 默认的测量方式,获取 heightMeasureSpec
 */
fun View.defaultHeightMeasureSpec(parentView: ViewGroup): Int {
    return when(layoutParams.height) {
        ViewGroup.LayoutParams.MATCH_PARENT -> parentView.measuredHeight.toExactlyMeasureSpec()
        ViewGroup.LayoutParams.WRAP_CONTENT -> ViewGroup.LayoutParams.WRAP_CONTENT.toAtMostMeasureSpec()
        0 -> throw IllegalAccessException("Need special treatment for $this")
        else -> layoutParams.height.toExactlyMeasureSpec()
    }
}

fun Int.toExactlyMeasureSpec(): Int{
    return MeasureSpec.makeMeasureSpec(this, MeasureSpec.EXACTLY)
}

fun Int.toAtMostMeasureSpec(): Int{
    return MeasureSpec.makeMeasureSpec(this, MeasureSpec.AT_MOST)
}

/**
 * 扩展方法:布局
 */
fun View.layout(x: Int, y: Int) {
    layout(x, y, x + measuredWidth, y + measuredHeight)
}

/**
 * 设置字体大小，参数传递为 10.sp
 */
fun TextView.setTextSizeSp(sp: Int) {
    setTextSize(TypedValue.COMPLEX_UNIT_PX, sp.toFloat())
}

