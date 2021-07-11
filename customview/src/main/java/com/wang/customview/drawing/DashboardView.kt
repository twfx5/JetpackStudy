package com.wang.customview.drawing

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 仪表盘
 */
private val RADIUS = 150f.px // 半径
private const val OPEN_ANGLE = 120f // 开口角度
private val DASH_WIDTH = 2f.px // 刻度的宽度
private val DASH_LENGTH = 5f.px // 刻度的长度
private val LENGTH = 80f.px // 指针长度

class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG) // 抗锯齿
    private val arcPath = Path() // 圆弧的path
    private val dashPath = Path() // 刻度的path
    private lateinit var pathEffect: PathEffect // 虚线 PathEffect

    // init 代码块，初始化时调用
    init {
        paint.strokeWidth = 2f.px
        paint.style = Paint.Style.STROKE
        // 设置刻度 Path
        dashPath.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        arcPath.reset()
        // 设置圆弧 Path
        arcPath.addArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2f + RADIUS,
            height / 2f + RADIUS,
            90 + OPEN_ANGLE / 2f,
            360 - OPEN_ANGLE
        )

        /**
         * 创建 PathMeasure，用来测量Path的长度
         */
        val pathMeasure = PathMeasure(arcPath, false)
        // 通过 PathMeasure 获取 Path 的长度
        val length = pathMeasure.length

        /**
         * 20个刻度：20个间隔，需要21个刻度，所以周长要减去一个刻度的宽度
         * advance 每个刻度的间隔 和 phase 提前画刻度的位置 ，这两个参数的位置需要更换
          */
        pathEffect = PathDashPathEffect(
            dashPath,
            (length - DASH_WIDTH) / 20, // 每个刻度的间隔
            0f, // 提前画刻度的位置
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        // 画弧
        canvas.drawPath(arcPath, paint)

        // 画刻度：先设置pathEffect，然后用PathEffect来画一个圆弧
        paint.pathEffect = pathEffect
        canvas.drawPath(arcPath, paint)
        paint.pathEffect = null

        // 画指针
        canvas.drawLine(
            width / 2f,
            height / 2f ,
            width / 2f + LENGTH,
            height / 2f + LENGTH, paint
        )
    }
}