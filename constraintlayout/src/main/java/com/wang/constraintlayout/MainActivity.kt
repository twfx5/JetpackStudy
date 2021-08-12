package com.wang.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.Group

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 通过group 设置一组控件的可见性
        setContentView(R.layout.activity_group)
        findViewById<Button>(R.id.button).setOnClickListener {
            // 点击后，隐藏 group
            findViewById<Group>(R.id.group).visibility = View.GONE
        }
    }
}