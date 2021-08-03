package com.wang.customviewgroup

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created at 2021/8/3 下午10:22.
 * @author wang
 */
class TheLayout(context: Context) : ViewGroup(context) {

    val head = AppCompatImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        setImageResource(R.drawable.xiaoxin)
        layoutParams = LayoutParams(50.dp, 50.dp)
        addView(this)
    }

    val title = AppCompatTextView(context).apply {
        textSize = 15f.px
        setTextColor(resources.getColor(R.color.black))
        addView(this)
    }

    /**
     *  重写 onMeasure 需要先调用 super.onMeasure
     *  目的是把宽高占满父容器
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 子 view 调用 measure() 方法，测量自己
        head.measure(
            head.defaultWidthMeasureSpec(parentView = this),
            head.defaultHeightMeasureSpec(parentView = this)
        )
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }
}