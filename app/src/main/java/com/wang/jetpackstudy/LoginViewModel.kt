package com.wang.jetpackstudy

import androidx.arch.core.util.Function
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    /**
     *  by lazy 的方式初始化 LiveData
     *  lateinit 只用于变量 var，而 lazy 只用于常量 val
     *
     *  lazy 应用于单例模式(if-null-then-init-else-return)，
     *  而且当且仅当变量被第一次调用的时候，委托方法才会执行
     */
    val requestLoginBeanLiveData: MutableLiveData<LoginBean> by lazy {
        MutableLiveData<LoginBean>()
    }

    fun login(name:String, pwd:String) {
        val loginBean = LoginBean(name, pwd)
        requestLoginBeanLiveData.value = loginBean
    }

    // switchMap 模拟去请求网络，返回一个新的 LiveData
    val loginLiveData = Transformations.switchMap(requestLoginBeanLiveData, Function {
        if (it.name == "wangzh" && it.pwd == "123") {
            MutableLiveData(200)
        } else{
            MutableLiveData<Int>(400)
        }

    })
}