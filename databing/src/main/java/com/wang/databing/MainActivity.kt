package com.wang.databing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wang.databing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置 setContentView
        val dataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )

        // 获取 ViewModel
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this.application)
        )
            .get(MyViewModel::class.java)

        // 给 xml 文件中定义的data，设置ViewModel
        dataBinding.data = viewModel

        // 设置 lifecycleOwner，使LiveData生效
        dataBinding.lifecycleOwner = this
    }
}