package com.wang.customview.clip

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.wang.customview.getAvatar
import com.wang.customview.px

private val IMAGE_WIDTH = 200f.px

class ClipView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var IMAGE_PADDING = 0f
    private val clipPath = Path() // 创建裁切的 Path

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        IMAGE_PADDING = (width - IMAGE_WIDTH) / 2f
        // 设置裁切的 Path 大小
        clipPath.addOval(
            IMAGE_PADDING,
            IMAGE_PADDING,
            IMAGE_PADDING + IMAGE_WIDTH,
            IMAGE_PADDING + IMAGE_WIDTH,
            Path.Direction.CW
        )
    }

    override fun onDraw(canvas: Canvas) {
        // 先裁切
        canvas.clipPath(clipPath)
        /**
         * 再画图形，这样画出的图形肯定有锯齿
         * 如果想要平滑，需要使用Xfermode
         */
        canvas.drawBitmap(
            getAvatar(resources, IMAGE_WIDTH.toInt()),
            IMAGE_PADDING,
            IMAGE_PADDING,
            paint
        )
    }
}