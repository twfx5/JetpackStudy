package com.wang.crashhandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CrashHandler.getInstance().init(this)
        findViewById<TextView>(R.id.text).apply {
            setOnClickListener {
                throw RuntimeException("自定义异常,模拟抛出异常~")
            }
        }
    }
}