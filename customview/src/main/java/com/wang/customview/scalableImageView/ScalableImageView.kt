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
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import com.wang.customview.dp
import com.wang.customview.getAvatar
import kotlin.math.max
import kotlin.math.min

/**
 * Created at 2021/7/26 下午10:04.
 * 双击缩放 和 双向滑动的 ImageView
 * @author wang
 */
private val IMAGE_SIZE = 300.dp
// 放大系数（最大情况下，让 view 可以滑动）
private const val SCALE_FACTOR = 1.5f
class ScalableImageView(context: Context?, attrs: AttributeSet?) : View(context, attrs),
    GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(resources, IMAGE_SIZE.toInt())

    // 图片是否是缩小状态
    private var isSmall = true

    // 初始 x，y 方向的偏移
    private var originOffsetX = 0f
    private var originOffsetY = 0f

    // x，y 方向的偏移
    private var offsetX = 0f
    private var offsetY = 0f

    private val gestureDetector = GestureDetectorCompat(context, this).apply {
        // 设置双击监听
        setOnDoubleTapListener(this@ScalableImageView)
    }

    /**
     * 将图片分别放大成：小图，大图时，缩放比例
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
     *
     *  setValue 后，invalidate()
     */
    private var scaleFraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    /**
     *  属性动画 固定套路 2
     *  延迟初始化：属性动画
     *
     *  by lazy {...}
     */
    private val scaleAnimator by lazy {
        ObjectAnimator.ofFloat(this, "scaleFraction", 0f, 1f)
    }

    /**
     *  创建 OverScroller 对象
     *  OverScroller 就像一个秒表
     *  我们调用 OverScroller.computeScrollOffset() 就相当于掐一下秒表
     *  可以得到当前的位置
     */
    private val scroller = OverScroller(context)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // 让图片居中显示
        originOffsetX = (width - IMAGE_SIZE) / 2f
        originOffsetY = (height - IMAGE_SIZE) / 2f
        if (bitmap.width / bitmap.height.toFloat() > width / height.toFloat()) {
            smallScale = width / bitmap.width.toFloat()
            bigScale = height / bitmap.height.toFloat() * SCALE_FACTOR
        } else {
            smallScale = height / bitmap.height.toFloat()
            bigScale = width / bitmap.width.toFloat() * SCALE_FACTOR
        }
    }

    /**
     *  canvas 多次变化，所以倒着看代码
     *  1、中心位置画图片
     *  2、缩放
     *  3、移动
     */
    override fun onDraw(canvas: Canvas) {
        // 3
        canvas.translate(offsetX, offsetY)

        /**
         *  2
         *  当前缩放的大小 = 初始值 + 差值 * 百分比
         *  差值 = 最大值 - 最小值
         */
        val scale = smallScale + (bigScale - smallScale) * scaleFraction
        canvas.scale(scale, scale, width / 2f, height / 2f)

        // 1
        canvas.drawBitmap(bitmap, originOffsetX, originOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 设置外挂，相当于点击事件中， onTouchEvent里面的 listener
        return gestureDetector.onTouchEvent(event)
    }

    /**
     *  onDown 一定要返回 true
     *  其他方法返回值随意，是给系统看的
     */
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
     * onScroll 不是 view 移动
     * 是手指移动，相当于手指在 view 上的 Move 事件
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
        if (!isSmall) {
            offsetX -= distanceX
            /**
             *  限制 offsetX 的范围，以免滑出屏幕
             *  -（图片宽度 - 屏幕宽度）/ 2 <= offsetX <= （图片宽度 - 屏幕宽度）/ 2
             */
            offsetX = min(offsetX, (bitmap.width * bigScale - width) / 2)
            offsetX = max(offsetX, -(bitmap.width * bigScale - width) / 2)
            offsetY -= distanceY
            // 限制 offsetX 的范围，同 offsetX
            offsetY = min(offsetY, (bitmap.height * bigScale - height) / 2)
            offsetY = max(offsetY, -(bitmap.height * bigScale - height) / 2)
            invalidate()
        }
        return true
    }

    /**
     * 松手后，快滑
     * velocityX, velocityY 单位时间的位移
     * 返回值无所谓
     */
    override fun onFling(
        downEvent: MotionEvent?,
        currentEvent: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (!isSmall) {
            scroller.fling(
                offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(),
                (-(bitmap.width * bigScale - width) / 2).toInt(), // 最小x
                ((bitmap.width * bigScale - width) / 2).toInt(), // 最大x
                (-(bitmap.height * bigScale - height) / 2).toInt(), // 最小y
                ((bitmap.height * bigScale - height) / 2).toInt()) // 最大y
            // postOnAnimation 下一帧时，去执行动画
            postOnAnimation(this)
        }
        return true
    }

    override fun run() {
        if (scroller.computeScrollOffset()) { // 只要还在滚动，就继续调用动画
            offsetX = scroller.currX.toFloat()
            offsetY = scroller.currY.toFloat()
            invalidate()
            postOnAnimation(this)
        }
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
    }

    /**
     *  双击
     */
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