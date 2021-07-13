package com.wang.customview.drawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wang.customview.px

/**
 * 饼图
 */
private val RADIUS = 150f.px // 半径
private val ANGLES = floatArrayOf(60f, 90f, 150f, 60f) // 开口角度
private val COLORS = listOf(Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA) // 颜色

class PieView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) // 抗锯齿

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f // 起始角度

        // 循环画圆弧 withIndex
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]
            if (index == 3) {
                // canvas 移动，留出空白
                canvas.save()
                canvas.translate(20f.px, (-10f).px)
            }
            canvas.drawArc(
                width / 2f - RADIUS,
                height / 2f - RADIUS,
                width / 2f + RADIUS,
                height / 2f + RADIUS,
                startAngle, // 起始角度
                angle,  // 圆弧划过的角度
                true,
                paint
            )
            startAngle += angle // 修改 起始角度

            if (index == 3) {
                // 恢复 canvas
                canvas.restore()
            }
        }


    }
}