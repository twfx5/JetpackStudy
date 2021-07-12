package com.wang.customview.xfermode

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wang.customview.drawing.getAvatar
import com.wang.customview.drawing.px

private val IMAGE_WIDTH = 200f.px // 图片宽度
private val IMAGE_PADDING = 100f.px // 图片间距
private val RADIUS = 110f.px // 图片描边宽度

class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 创建 Xfermode
    private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    // 离屏缓存的大小bounds
    private val bounds = RectF(
        IMAGE_PADDING,
        IMAGE_PADDING,
        IMAGE_PADDING + IMAGE_WIDTH,
        IMAGE_PADDING + IMAGE_WIDTH
    )

    init {
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        // 黑色背景
        canvas.drawCircle(
            IMAGE_PADDING + IMAGE_WIDTH / 2f,
            IMAGE_PADDING + IMAGE_WIDTH / 2f,
            RADIUS,
            paint
        )
        /**
         * 使用Xfermode，必须先创建一块离屏缓冲 canvas.saveLayer
         * 使用完毕后，再恢复 canvas.restoreToCount
         */
        // 创建一块离屏缓冲，大小是图片的bounds
        val saveLayer = canvas.saveLayer(bounds, null)
        // destination 目标：圆形
        canvas.drawOval(bounds, paint)
        // 给 Paint 设置 xfermode
        paint.xfermode = XFERMODE
        // source 资源：头像
        canvas.drawBitmap(
            getAvatar(resources, IMAGE_WIDTH.toInt()),
            IMAGE_PADDING,
            IMAGE_PADDING,
            paint
        )
        paint.xfermode = null
        canvas.restoreToCount(saveLayer)
    }


}