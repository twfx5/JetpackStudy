package com.wang.customviewgroup

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.*
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

class ItemLayout(context: Context, attrs: AttributeSet?) : BaseLayout(context, attrs) {

    init {
        setBackgroundColor(Color.parseColor("#FFF5F5F8"))
    }

    private val image = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        setImageResource(R.drawable.ic_download)
        layoutParams = MarginLayoutParams(52.dp, 52.dp).apply {
            topMargin = 10.dp
        }
        addView(this)
    }

    private val title = TextView(context).apply {
        setTextSizeSp(14)
        setTextColor(Color.parseColor("#FF000000"))
        text = "WPS Office + ItemLayout"
        layoutParams = MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            leftMargin = 10.dp
            rightMargin = 20.dp
        }
        addView(this)
    }

    private val summary = TextView(context).apply {
        setTextSizeSp(10)
        setTextColor(Color.parseColor("#66000000"))
        text = "最实用的办公国产软件"
        layoutParams = MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            //leftMargin = 10.dp
            topMargin = 3.dp
        }
        addView(this)
    }

    private val size = TextView(context).apply {
        setTextSizeSp(10)
        setTextColor(Color.parseColor("#66000000"))
        text = "35M"
        layoutParams = MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            //leftMargin = 10.dp
            topMargin = 3.dp
        }
        addView(this)
    }

    private val button = TextView(context).apply {
        setTextSizeSp(14)
        setTextColor(Color.BLACK)
        text = "安装"
        setPadding(11.dp, 3.dp, 11.dp, 3.dp)
        background = ResourcesCompat.getDrawable(resources, R.drawable.bg_item, null)
        layoutParams = MarginLayoutParams(50.dp, 25.dp).apply {
            topMargin = 24.dp
        }
        addView(this)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        image.autoMeasure()

        button.autoMeasure()

        // 文字最长宽度
        val maxTextWidth = measuredWidth - image.measuredWidth - title.marginLeft - title.marginRight -
                button.measuredWidth
        title.measure(maxTextWidth.toExactlyMeasureSpec(), defaultHeightMeasureSpec(parentView = this))
        summary.measure(maxTextWidth.toExactlyMeasureSpec(), defaultHeightMeasureSpec(parentView = this))
        size.measure(maxTextWidth.toExactlyMeasureSpec(), defaultHeightMeasureSpec(parentView = this))

        setMeasuredDimension(310.dp, 72.dp)
    }



    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        image.let {
            it.layout(0, it.marginTop)
        }

        title.let {
            it.layout(image.measuredWidth + it.marginLeft, image.marginTop)
        }

        summary.let {
            it.layout(image.measuredWidth + title.marginLeft, title.bottom + it.marginTop)
        }

        size.let {
            it.layout(image.measuredWidth + title.marginLeft, summary.bottom + it.marginTop)
        }

        button.let {
            it.layoutCenterVertical(it.measuredWidth, true)
        }
    }
}