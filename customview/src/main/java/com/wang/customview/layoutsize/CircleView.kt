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
//        val rawWidth = MeasureSpec.getSize(widthMeasureSpec)
//        val rawHeight = MeasureSpec.getSize(heightMeasureSpec)
//        Log.d(TAG, "onMeasure: rawWidth = $rawWidth, rawHeight = $rawHeight" )
//        val size = ((PADDING + RADIUS) * 2).toInt()
//        val width = resolveSize(size, widthMeasureSpec)
//        val height = resolveSize(size, heightMeasureSpec)

        Log.d(TAG, "onMeasure: measuredWidth = $measuredWidth, measuredHeight = $measuredHeight" )
        setMeasuredDimension(400.dp.toInt(), 400.dp.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, paint)
    }
}