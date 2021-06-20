package com.wang.jetpackstudy

import java.util.*

data class UserModel(var name: String?, var pwd: String?) {
    constructor() : this(null, null)

    private val random = Random()

    fun doLogin(loginStateChange: LoginStateChange) {
        loginStateChange.onLoading()
        // random.nextInt(2)
        // 随机取0 - 1范围内的整数
        if (random.nextInt(2) == 0) {
            loginStateChange.loginSuccess()
        } else {
            loginStateChange.loginFailed()
        }
    }

    interface LoginStateChange {
        fun onLoading()

        fun loginSuccess()

        fun loginFailed()
    }

}
