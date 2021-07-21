package com.wang.customview.layoutsize

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * Created at 2021/7/21 下午9:46.
 * @author wang
 */
class SquareImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    /**
     *  改写View的尺寸：
     *  1、重写 onMeasure 方法
     *  2、通过 getMeasuredWidth，getMeasuredHeight 获得测量出的尺寸
     *  3、计算最终我们需要的尺寸（缩放到 1/2）
     *  4、用 setMeasuredDimension 保存尺寸
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth / 2f
        val height = measuredHeight / 2f

        /**
         * getMeasuredWidth()
         * 子View自己测量出来的尺寸 期望尺寸
         * 在 onMeasure 方法时中调用
         *
         * getWidth()
         * 实际尺寸  mRight - mLeft
         * 只有测量完毕后，才能得到
         * 在其他地方时调用
         */


        setMeasuredDimension(width.toInt(), height.toInt())
    }

    /**
     *  如果在 layout 里面改变尺寸，虽然也会达到相同的效果
     *  但是会这样改了，父控件不知道你改动了，会影响到其他子控件的摆放
     *  所以一般重写 layout，不在这里改变已测量好的 l,t,r,b
     *  （测量好的家具，在摆放时锯掉或拉长了）
     *
     *  一般重写 onMeasure ，父控件才知道你改变后的大小
     */
    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        // 计算一半的size
        val size = (r - l) / 2f
        // 摆放时，宽高也是原来的一半
        super.layout(l, t, (l + size).toInt(), (t + size).toInt())
    }
}