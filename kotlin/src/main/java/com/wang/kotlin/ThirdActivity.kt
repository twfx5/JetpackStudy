package com.wang.kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Created at 2021/8/17 上午8:14.
 * @author wang
 */
private const val TAG = "ThirdActivity"
class ThirdActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        Log.e(TAG, "onCreate: taskId = $taskId")
    }

    /**
     * 跳转 Activity 的最佳写法 ：这样能暴露出所需要的参数
     * 所有定义在 Companion object中 的方法
     * 都可以使用类似java静态方法的形式来调用
     */
    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ThirdActivity::class.java)
            context.startActivity(intent)
        }
    }
}