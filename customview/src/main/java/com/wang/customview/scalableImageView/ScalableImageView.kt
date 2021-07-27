package com.wang.customview.scalableImageView

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import com.wang.customview.dp
import com.wang.customview.getAvatar

/**
 * Created at 2021/7/26 下午10:04.
 * 双击缩放 和 双向滑动的 ImageView
 * @author wang
 */
private val IMAGE_SIZE = 300.dp
class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, IMAGE_SIZE.toInt())
    // x，y 方向的偏移
    private var offsetX = 0f
    private var offsetY = 0f

    private val gestureDetector = GestureDetectorCompat(context, this).apply {
        // 设置双击监听
        setOnDoubleTapListener(this@ScalableImageView)
    }

    /**
     * 将图片放大成，小图，大图时，缩放比例
     *
     * 因为手机的高度 > 手机的宽度
     * 小图 ：图片的宽度 放大到 手机的宽度
     * 大图 ：图片的高度 放大到 手机的高度
     */
    private var smallScale = 0f
    private var bigScale = 0f

    /**
     *  属性动画 固定套路 1
     *  属性动画的属性 ：缩放的百分比
     */
    private var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    /**
     *  属性动画 固定套路 2
     *  延迟初始化：属性动画
     */
    private val scaleAnimator by lazy {
        ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f)
    }

    // 图片是否是缩小状态
    private var isSmall = true

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
        /**
         *  当前缩放的大小 = 初始值 + 差值 * 百分比
         *  差值 = 最大值 - 最小值
         */
        val scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas.scale(scale, scale, width / 2f, height / 2f)

        canvas.drawBitmap(bitmap, offsetX, offsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 设置外挂，相当于点击事件中， onTouchEvent里面的 listener
        return gestureDetector.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
        // true 表示 gestureDetector 消费事件
        return true
    }

    /**
     * 按下：包括预按下
     */
    override fun onShowPress(e: MotionEvent?) {
    }

    /**
     * 单击
     * 返回值无所谓
     */
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    /**
     * 手指移动，相当于 Move 事件
     * distanceX, distanceY 是开始位置 - 当前位置的值
     * 和平时用的相反
     *
     * 返回值无所谓
     */
    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return true
    }

    /**
     * 快滑
     * velocityX, velocityY 单位时间的位移
     * 返回值无所谓
     */
    override fun onFling(
        downEvent: MotionEvent?,
        currentEvent: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        isSmall = !isSmall // 双击时，改变动画的大小
        if (isSmall) {
            // 双击后缩小，反转动画
            scaleAnimator.reverse()
        } else {
            // 双击后放大，正向执行动画
            scaleAnimator.start()
        }
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return true
    }


}