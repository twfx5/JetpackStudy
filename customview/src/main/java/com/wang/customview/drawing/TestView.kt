package com.wang.customview.drawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wang.customview.px

// 常量
// const val RADIUS = 200f

private val RADIUS = 100f.px
class TestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(ANTI_ALIAS_FLAG) // 抗锯齿
    private val path = Path()

    // 每次View大小改变时，调用 onSizeChanged
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CW)
        /**
         *  Path 的方向
         *  CW 顺时针
         *  CCW 逆时针
         */

        path.addRect(width / 2f - RADIUS, height / 2f, width / 2 + RADIUS, height / 2f + RADIUS * 2,
        Path.Direction.CW)

        // 设置填充type，可以在图形重叠时，有镂空的效果
        /**
         * 有四种 fillType：
         * WINDING,  默认的填充type：旋转楼梯，需要看Path的方向（算起来复杂）
         * EVEN_ODD, 奇数是内部，偶数是外部（不看Path的方向，算起来简单）内部才涂色，外部不涂色
         * INVERSE_WINDING, 和 WINDING 效果相反
         * INVERSE_EVEN_ODD 和 EVEN_ODD 效果相反
         */
        path.fillType = Path.FillType.EVEN_ODD
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //canvas.drawLine(100f, 100f, 200f, 200f, paint)

        //canvas.drawCircle(width / 2f, height / 2f, Utils.dp2ps(100f), paint)
        // 使用Float的扩展属性
        //canvas.drawCircle(width / 2f, height / 2f, 100f.px, paint)

        canvas.drawPath(path, paint)
    }
}