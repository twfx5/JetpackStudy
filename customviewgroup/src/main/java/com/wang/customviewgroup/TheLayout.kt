package com.wang.customviewgroup

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.*
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

/**
 * Created at 2021/8/3 下午10:22.
 * @author wang
 */
const val TAG = "TheLayout"
class TheLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val bg = AppCompatImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        setImageResource(R.drawable.ad2)
        layoutParams = MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        addView(this)
    }

    private val head = AppCompatImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        setImageResource(R.drawable.xiaoxin)
        layoutParams = MarginLayoutParams(140.dp, 140.dp).apply {
            leftMargin = 30.dp
            topMargin = 30.dp
        }
        addView(this)
    }

    private val title = AppCompatTextView(context).apply {
        textSize = 8f.dp
        text = "小伙伴们，因武汉院调整了组织架构，新办公室的工位图也做了调整，请大家知晓"
        layoutParams = MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            leftMargin = 40.dp
            rightMargin = 40.dp
        }
        setTextColor(Color.BLUE)
        addView(this)
    }

    /**
     *  重写 onMeasure 需要先调用 super.onMeasure
     *  目的是把宽高占满父容器
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        /**
         * 如果不重置宽高,那么必须调用 super.onMeasure,否则会有异常 java.lang.IllegalStateException:
         * TheLayout#onMeasure() did not set the measured dimension by calling setMeasuredDimension()
         *
         * 重写后,本ViewGroup默认占满父容器的宽高
         * Log.d(TAG, "onMeasure: width = ${MeasureSpec.getSize(widthMeasureSpec)} + height = ${MeasureSpec.getSize(heightMeasureSpec)}")
         * TheLayout: onMeasure: width = 1920 + height = 1024
         */
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 子 view 调用 measure() 方法，测量自己
        bg.measure(
            bg.defaultWidthMeasureSpec(parentView = this),
            bg.defaultHeightMeasureSpec(parentView = this)
        )
        head.measure(
            head.defaultWidthMeasureSpec(parentView = this),
            head.defaultHeightMeasureSpec(parentView = this)
        )

        val titleWidth = bg.measuredWidth - head.marginLeft - head.measuredWidth - title.marginLeft - title.marginRight;

        title.measure(
           titleWidth.toExactlyMeasureSpec(),
            title.defaultHeightMeasureSpec(parentView = this)
        )
//        setMeasuredDimension(150.dp, 200.dp)
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        /**
         * super.onMeasure 后
         * 宽高占满了父容器的宽高 l = 0 , t = 0 , r = 1920 , b = 1024
         */
        Log.d(TAG, "onLayout: l = $l , t = $t , r = $r , b = $b")
        bg.let {
            it.layout(0, 0)
        }
        head.let {
            it.layout(it.marginLeft, it.marginTop)
        }
        title.let {
            it.layout(head.right + it.marginLeft, head.top)
        }
    }
}