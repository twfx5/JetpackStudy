package com.wang.customview.layoutsize

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView

/**
 * Created at 2021/7/21 下午9:46.
 * @author wang
 */
private const val TAG = "SquareImageView"
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

        Log.d(TAG, "onMeasure: measuredWidth = $width, measuredHeight = $height")
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
        /**
         * measuredWidth 和 width 打印的实际结果：
         * onMeasure: measuredWidth = 525.0, measuredHeight = 525.0
         * layout: width = 262 , height = 262
         *
         * measuredWidth 在 调用 measure 方法后，才被赋值 （布局的测量过程）
         * width 在调用 layout 方法后，这些left、top、right、bottom属性才被赋值，
         * 所以 width才被赋值 （布局的摆放过程）
         *
         * 两者一般情况是相等的，因为我们一般在测量后，摆放时就按照测量的尺寸来摆放
         * 但是如果在 layout （摆放布局）时，手动改变上下左右的值，那么两者就不相等了。
         * 相当于搬家时，测量完毕，摆放时把家具锯掉了或者加长了一部分。
         *
         * 所以在 onMeasure 方法中，我们使用 measuredWidth
         * 在onDraw 方法中，我们使用 width
         */
        Log.d(TAG, "layout: width = $width , height = $height")
    }
}