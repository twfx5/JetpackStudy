package com.wang.gradleplugin

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout

/**
 * Created at 2021/8/27 下午9:46.
 * @author wang
 */
const val TAG = "wangzh"
class MyViewGroup1(context: Context, attr: AttributeSet?): RelativeLayout(context, attr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure: MyViewGroup1")
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        Log.d(TAG, "onLayout: MyViewGroup1")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: MyViewGroup1")
    }
}