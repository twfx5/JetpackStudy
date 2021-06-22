package com.wang.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

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
            { textView.text = viewModel.getNumber().value.toString() })

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

    }
}