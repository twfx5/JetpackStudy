package com.wang.gradlebuildtypes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         *  不同的buildTypes，绘制不同的标记
         *  假设我们构建的是debug，实际执行的代码就是main+debug的代码
         *  假设我们构建的是release，实际执行的代码就是main+release的代码
         */
        drawBadge()
    }

}