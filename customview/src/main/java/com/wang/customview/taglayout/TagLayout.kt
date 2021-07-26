package com.wang.customview.taglayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children
import com.wang.customview.dp

/**
 * Created at 2021/7/24 上午6:32.
 * @author wang
 */
class TagLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {

    private val count = 5
    private val MARGIN_LEFT = 10.dp
    private val PADDING = 5.dp

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (child in children) {
            if (children.indexOf(child) == 0) {
                child.layout(0, 0, (r - l)/ 2, (b - t) / 2)
            } else {
                child.layout((r - l)/ 2, (b - t) / 2, r-l, b -t)
            }
        }
    }
}