package com.wang.customview.taglayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.children
import com.wang.customview.R
import com.wang.customview.dp

/**
 * Created at 2021/7/24 上午6:32.
 * @author wang
 */
const val TAG = "SingleLayout"
class SingleLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {

    private val count = 4
    private val singleWidth = 60.dp // 每个图片的宽度
    private val PADDING = 20.dp

    init {
        setdata()
    }

    private fun setdata() {
        for (index in 0 until count) {
            generateImageView()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        setPadding(0, 0, 0, 0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val totalWidth = paddingLeft + paddingRight + singleWidth * count + PADDING * (count - 1)
        val totalHeight = paddingTop + paddingBottom + singleWidth
//        Log.d(TAG, "onMeasure: totalWidth = $totalWidth, totalHeight = $totalHeight")
        setMeasuredDimension(totalWidth.toInt(), totalHeight.toInt())
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (index in 0 until count) {
            Log.d(TAG, "onLayout: left = $l + right = $r")
            val childView = getChildAt(index)
            // left 需要考虑到 paddingLeft
            val left = (paddingLeft + l + index * (singleWidth + PADDING)).toInt()
            val right = (left + singleWidth).toInt()
            val top = t
            val bottom = b
            // childView 布局
            childView.layout(left, top, right, bottom)
        }
    }

    // 创建 ImageView
    private fun generateImageView() : ImageView {
        val imageView = ImageView(context)
        imageView.layoutParams = LayoutParams(singleWidth.toInt(), singleWidth.toInt())
        imageView.setImageResource(R.drawable.xiaoxin)
        addView(imageView)
        return imageView
    }
}