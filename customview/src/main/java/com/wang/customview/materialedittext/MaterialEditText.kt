package com.wang.customview.materialedittext

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.wang.customview.R
import com.wang.customview.dp

/**
 * Created at 2021/7/20 上午6:11.
 * @author wang
 * 仿写 Material EditText 的 Floating Label 提示功能
 */

const val TAG = "MaterialEditText"
class MaterialEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val TEXT_SIZE = 13.dp
    private val TEXT_MARGIN = 8.dp
    private val HORIZONTAL_OFFSET = 5.dp
    private val VERTICAL_OFFSET = 23.dp
    private val EXTRA_VERTICAL_OFFSET = 16.dp

    // 是否显示Label
    private var labelShown = false

    /**
     * 属性动画的百分比
     * 因为有多个属性需要改变，所以使用百分比来统一处理
     *
     * 如果这个字段如果用 private 修饰，并且动画只设了终点的值，
     * 那么 get 方法获取的值为 0，就没有动画效果了
     */
    private var fraction = 0f
        set(value) {
            field = value
            invalidate()
        }

    private val animator by lazy {
        /**
         *  by lazy 修饰 val
         *  延迟加载
         */
        ObjectAnimator.ofFloat(this, "fraction", 0f, 1f)
    }

    init {
        // 设置间距，为绘制文字留出位置
        setPadding(
            paddingLeft,
            (paddingTop + TEXT_SIZE + TEXT_MARGIN).toInt(),
            paddingRight,
            paddingBottom
        )
        paint.textSize = TEXT_SIZE

        /**
         * attrs 是xml文件中定义的所有属性
         * 下面的循环打印出配置 attrs 的属性名和属性值
        2021-07-21 07:03:17.314 8831-8831/com.wang.customview D/MaterialEditText: name = id, value = @2131230949
        2021-07-21 07:03:17.315 8831-8831/com.wang.customview D/MaterialEditText: name = layout_width, value = -1
        2021-07-21 07:03:17.315 8831-8831/com.wang.customview D/MaterialEditText: name = layout_height, value = -2
        2021-07-21 07:03:17.315 8831-8831/com.wang.customview D/MaterialEditText: name = hint, value = Username
         *
         */
        for (index in 0 until attrs.attributeCount) {
            Log.d(
                TAG, "name = ${attrs.getAttributeName(index)}, " +
                        "value = ${attrs.getAttributeValue(index)}"
            )
        }
        /**
         * 通过Context，获得 MaterialEditText配置的属性，是一个 int 数组 attrs
         * 然后取出数组的第一个元素(我们把 useFloatingLabel 写在了第一个)
         */
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText)
        val useFloatingLabel = typedArray.getBoolean(R.styleable.MaterialEditText_useFloatingLabel, true)
        typedArray.recycle()

        /**
         * 因此，以上代码可以简化成如下的形式
         */
        val typedArray2 = context.obtainStyledAttributes(attrs, intArrayOf(R.attr.useFloatingLabel))
        val useFloatingLabel2 = typedArray.getBoolean(0, true)
        typedArray2.recycle()
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        if (labelShown && text.isNullOrEmpty()) {
            labelShown = false
            // 反转动画
            animator.reverse()
        } else if (!labelShown && !text.isNullOrEmpty()) {
            labelShown = true
            // 开启动画
            animator.start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /**
         * 通过百分比 fraction 改变多个属性 ： 透明度、竖直方向位移
         * 0xff 是最大透明度
         */
        paint.alpha = (fraction * 0xff).toInt()
        val currentVerticalValue = VERTICAL_OFFSET + EXTRA_VERTICAL_OFFSET * (1 - fraction)

        canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, currentVerticalValue, paint)
    }

}