package com.wang.customview.animation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wang.customview.px


class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }

    /**
     * kotlin 中属性的 getter 和 setter
     */
    private var radius = 50f.px
        set(value) {
            // kotlin setter 方法的写法
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {

        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }
}