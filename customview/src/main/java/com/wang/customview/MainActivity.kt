package com.wang.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wang.customview.R

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
        setContentView(R.layout.activity_avatar)
    }
}