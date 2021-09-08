package com.wang.gradleplugin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    lateinit var myView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        myView = findViewById<View>(R.id.myView).apply {
            setOnClickListener {
//                invalidate()
                requestLayout()
            }
        }

        findViewById<View>(R.id.myViewGroup2).apply {
            setOnClickListener {
//                invalidate()
                requestLayout()
            }
        }

        findViewById<View>(R.id.myViewGroup1).apply {
            setOnClickListener {
//                invalidate()
                requestLayout()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // 这里还是获取不到 宽高
        Log.d(TAG, "onAttachedToWindow: width = ${myView.width}, height = ${myView.height}")
    }
}