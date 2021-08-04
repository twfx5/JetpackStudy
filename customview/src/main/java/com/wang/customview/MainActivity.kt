package com.wang.customview

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Bitmap
import android.graphics.drawable.ColorDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import com.wang.customview.R
import com.wang.customview.animation.CircleView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 测试
//        setContentView(R.layout.activity_main)

        // 仪表盘
//        setContentView(R.layout.activity_dash)

        // 饼图
//        setContentView(R.layout.activity_pie)

        // 头像
//        setContentView(R.layout.activity_avatar)

        // Path 裁切
//        setContentView(R.layout.activity_clip)

        // 属性动画：放大圆的半径
        /*setContentView(R.layout.activity_circle)
        findViewById<CircleView>(R.id.circle_view).run {
            setOnClickListener {
               val anim = ObjectAnimator.ofFloat(this, "radius", 50f.px, 150f.px)
                anim.duration = 2000
                anim.start()
            }
        }*/

        /**
         * 属性动画：View 移动
         *
         * 使用KeyFrame + PropertyValuesHolder 来拆分一个动画的不同阶段
         * 比如移动一段距离，前20%先快、然后60%慢，最后20%再快
         */
        /*
        val length = 200f.px // 移动的总距离
        setContentView(R.layout.activity_move_view)
        val imageView = findViewById<ImageView>(R.id.image)
        val keyframe1 = Keyframe.ofFloat(0f, 0f) // 最初，动画执行0%，移动了0%的距离
        val keyframe2 = Keyframe.ofFloat(0.2f, 0.4f * length) // 然后动画执行20%，移动了40%的距离
        val keyframe3 = Keyframe.ofFloat(0.8f, 0.6f * length) // 动画继续执行40%，移动了20%的距离
        val keyframe4 = Keyframe.ofFloat(1f, 1f * length) // 动画继续执行20%，移动了40%的距离
        val keyFrameHolder = PropertyValuesHolder.ofKeyframe(
            "translationX",
            keyframe1, keyframe2, keyframe3, keyframe4
        )
        val animator = ObjectAnimator.ofPropertyValuesHolder(imageView, keyFrameHolder)
        animator.startDelay = 1000
        animator.duration = 1000
        animator.start()
         */


        // 使用 ktx 的方法来互转
        // Bitmap 转为 Drawable
        /*
        val bitmap = Bitmap.createBitmap(20, 20, Bitmap.Config.ARGB_8888)
        bitmap.toDrawable(resources)

        // Drawable 转为 Bitmap
        val drawable = ColorDrawable()
        drawable.toBitmap()
         */

        // Material EditText
//        setContentView(R.layout.activity_material_edittext)

        // 简单改写已有 View 的尺寸,变为原尺寸的一半
        setContentView(R.layout.activity_square)

        // 完全自定义 View 的尺寸
        //setContentView(R.layout.activity_resize_circle)

        // 自定义 ViewGroup，横向排列一组图片
//        setContentView(R.layout.activity_single_view)

        // 支持 双击缩放 和 双向滑动的 ImageView
//        setContentView(R.layout.activity_scalable_imageview)

        // 支持 双击缩放 和 双向滑动的 ImageView，代码做了优化处理
//        setContentView(R.layout.activity_comp_scalable_imageview)
    }


}