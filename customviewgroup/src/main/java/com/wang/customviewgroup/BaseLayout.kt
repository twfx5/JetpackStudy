package com.wang.customviewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.*
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

abstract class BaseLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    /**
     *  子 View 默认测量方法
     *  View 的宽高符合这三种情况：
     *  MATCH_PARENT、WRAP_CONTENT、准确的数值
     *  而非待定时，都能使用这种测量方式
     */
    fun View.autoMeasure() {
        measure(
            defaultWidthMeasureSpec(parentView = this@BaseLayout),
            defaultHeightMeasureSpec(parentView = this@BaseLayout)
        )
    }

    /**
     * 默认的测量方式,获取 widthMeasureSpec
     */
    fun View.defaultWidthMeasureSpec(parentView: ViewGroup): Int {
        return when(layoutParams.width) {
            MATCH_PARENT -> parentView.measuredWidth.toExactlyMeasureSpec()
            WRAP_CONTENT -> WRAP_CONTENT.toAtMostMeasureSpec()
            0 -> throw IllegalAccessException("Need special treatment for $this")
            else -> layoutParams.width.toExactlyMeasureSpec()
        }
    }

    /**
     *  默认的测量方式,获取 heightMeasureSpec
     */
    fun View.defaultHeightMeasureSpec(parentView: ViewGroup): Int {
        return when(layoutParams.height) {
            MATCH_PARENT -> parentView.measuredHeight.toExactlyMeasureSpec()
            WRAP_CONTENT -> WRAP_CONTENT.toAtMostMeasureSpec()
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

    // View 测量时的宽度，包括左右间距
    val View.measuredWidthWithMargins get() = measuredWidth + marginLeft + marginRight

    // View 测量时的高度，包括上下间距
    val View.measuredHeightWithMargins get() = measuredHeight + marginTop + marginBottom

    /**
     * 扩展方法:布局
     * fromRight 从右边向左边布局
     */
    fun View.layout(x: Int, y: Int, fromRight: Boolean = false) {
        if (!fromRight) {
            layout(x, y, x + measuredWidth, y + measuredHeight)
        } else {
            layout(this@BaseLayout.measuredWidth - x, y)
        }
    }

    /**
     * 扩展方法:垂直居中布局
     * fromRight 从右边向左边布局
     */
    fun View.layoutCenterVertical(x: Int, fromRight: Boolean = false) {
        if (!fromRight) {
            layout(x, (this@BaseLayout.measuredHeight - measuredHeight) / 2, x + measuredWidth, this@BaseLayout.measuredHeight + measuredHeight / 2)
        } else {
            layout(this@BaseLayout.measuredWidth - x, (this@BaseLayout.measuredHeight - measuredHeight) / 2)
        }
    }
}