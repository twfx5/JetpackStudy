package com.wang.customview.scalableImageView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.wang.customview.dp
import com.wang.customview.getAvatar

/**
 * Created at 2021/7/26 下午10:04.
 * 双向滑动的 ImageView
 * @author wang
 */
private val IMAGE_SIZE = 300.dp
class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, IMAGE_SIZE.toInt())
    // x，y 方向的偏移
    private var offsetX = 0f
    private var offsetY = 0f

    /**
     * 将图片放大成，小图，大图时，缩放比例
     *
     * 因为手机的高度 > 手机的宽度
     * 小图 ：图片的宽度 放大到 手机的宽度
     * 大图 ：图片的高度 放大到 手机的高度
     */
    private var smallScale = 0f
    private var bigScale = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 让图片居中显示
        offsetX = (width - IMAGE_SIZE) / 2f
        offsetY = (height - IMAGE_SIZE) / 2f
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat()
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        var scale = bigScale
        canvas.scale(scale, scale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }


}