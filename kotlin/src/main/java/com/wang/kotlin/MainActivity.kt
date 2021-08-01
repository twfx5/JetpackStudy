package com.wang.kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // lambda 的使用
        val textView = findViewById<TextView>(R.id.text)
        textView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {

            }
        })
        // 匿名函数
        textView.setOnClickListener(fun(v: View) :Unit{
            println()
        })
        // 匿名函数简化为 lambda 表达式
        textView.setOnClickListener({
            v: View ->
            println()
        })
        // lambda 表达式，最终写法
        textView.setOnClickListener {  }

        textView.setOnClickListener {
            Toast.makeText(this@MainActivity, "123", Toast.LENGTH_LONG).show()
        }

        // 2 高阶函数的使用
        val editor1 = getPreferences(Context.MODE_PRIVATE).edit()
        editor1.putInt("aaa", 10)
        editor1.apply()

        // 不用写 apply() 或 commit（），达到相同的效果
        val editor2 = getPreferences(Context.MODE_PRIVATE).edit {
            putInt("bbb", 20)
            putBoolean("ccc", true)
        }
    }
}