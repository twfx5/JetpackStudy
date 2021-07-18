package com.wang.customview.xfermode

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wang.customview.getAvatar
import com.wang.customview.px

private val IMAGE_WIDTH = 200f.px // 图片宽度
private val RADIUS = 110f.px // 图片描边宽度

class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 创建 Xfermode
    private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    private var leftPadding = 0f
    private var topPadding = 0f

    // 离屏缓存的大小bounds
    private lateinit var bounds: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        leftPadding = (width - IMAGE_WIDTH) / 2f
        topPadding = (height - IMAGE_WIDTH) / 2f
        bounds = RectF(leftPadding, topPadding, leftPadding + IMAGE_WIDTH, topPadding + IMAGE_WIDTH)
    }

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        // 黑色背景
        canvas.drawCircle(
            width / 2f,
            height / 2f,
            RADIUS,
            paint
        )
        /**
         * 使用Xfermode，必须先创建一块离屏缓冲 canvas.saveLayer
         * 使用完毕后，再恢复 canvas.restoreToCount
         */
        // 创建一块离屏缓冲，大小是图片的bounds
        val saveLayer = canvas.saveLayer(bounds, null)
        // destination 绘制目标图形：圆形
        canvas.drawOval(bounds, paint)
        // 给 Paint 设置 xfermode
        paint.xfermode = XFERMODE
        // source 绘制资源：方形 头像
        canvas.drawBitmap(
            getAvatar(resources, IMAGE_WIDTH.toInt()),
            leftPadding,
            topPadding,
            paint
        )
        // 把Xfermode置空和恢复
        paint.xfermode = null
        canvas.restoreToCount(saveLayer)
    }


}