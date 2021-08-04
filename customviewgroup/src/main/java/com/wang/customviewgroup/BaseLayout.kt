package com.wang.customviewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

abstract class BaseLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    /**
     *  子 View 默认测量方法
     */
    fun View.autoMeasure() {
        this.measure(
            defaultWidthMeasureSpec(parentView = this@BaseLayout),
            defaultHeightMeasureSpec(parentView = this@BaseLayout)
        )
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
     *  默认的测量方式,获取 heightMeasureSpec
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