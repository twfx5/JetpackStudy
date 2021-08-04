package com.wang.customview.layoutsize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.wang.customview.dp

private val RADIUS = 100.dp
private val PADDING = 100.dp
const val TAG = "CircleView"
class CircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 自己设置宽高为 400dp x 400dp
        val size = ((PADDING + RADIUS) * 2).toInt()
        /**
         * resolveSize的作用
         * 根据 MeasureSpec（MeasureSpec ：父控件对我的限制和LayoutParams）[修正] 自己的大小
         * LayoutParams 是 xml 中定义的大小
         * 这样通过 resolveSize 计算后的结果，就和开发者在 xml 中定义的是一样的了
         * 因为[开发者的决定最重要]!!!
         *
         * 例如:
         * 如果在xml中设置的 LayoutParams 是 200dp x 200dp ，那么就显示不完整:
         * 因为我们自己设置的是 400dp x 400dp,但是通过 xml 的 LayoutParams进行修正,会被裁剪一部分
         *
         * 如果在xml中设置的 LayoutParams 是 wrap_content x wrap_content ，就显示我们自己设置的size 400dp x 400dp
         */
        val width = resolveSize(size, widthMeasureSpec)
        val height = resolveSize(size, heightMeasureSpec)
        // 将实际的宽高保存下来
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint)
    }
}