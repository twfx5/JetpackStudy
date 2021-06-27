package com.wang.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text
import kotlin.concurrent.thread
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel
    private var count = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)

        // 获取 ViewModel
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        )
            .get(MyViewModel::class.java)

        // 给 ViewModel中的 MutableLiveData 添加观察者
        viewModel.getNumber().observe(this,
            {
                // liveData只有在前台时，才会调用
                Log.d(TAG, "刷新textView: " + viewModel.getNumber().value)
                textView.text = viewModel.getNumber().value.toString()
            })

        findViewById<Button>(R.id.button1).run {
            setOnClickListener {
                viewModel.addNumber(1)
            }
        }

        findViewById<Button>(R.id.button2).run {
            setOnClickListener {
                viewModel.addNumber(2)
            }
        }

        thread {
            // 这里延迟6s执行，如果界面到了后台，并不会刷新UI
            // 只有再次回到前台时，才会刷新UI
            SystemClock.sleep(6000)
            Log.d(TAG, "loopAddNumber: + 1")
            viewModel.loopAddNumber(1)
            count++
        }



    }
}