package com.wang.jetpackstudy

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), UserModel.LoginStateChange {

    private lateinit var edName : EditText
    private lateinit var edPwd : EditText
    private lateinit var btLogin : Button
    private lateinit var tvState : TextView

    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        edName = findViewById(R.id.ed_name)
        edPwd = findViewById(R.id.ed_pwd)
        btLogin = findViewById(R.id.button)
        tvState = findViewById(R.id.text_state)

        btLogin.setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin() {
        val name = edName.text.toString()
        val pwd = edPwd.text.toString()
        userModel = UserModel(name, pwd)
        userModel.doLogin(this)
    }

    override fun onLoading() {
        tvState.text = "登录中..."
    }

    override fun loginSuccess() {
        tvState.text = "登录成功"
    }

    override fun loginFailed() {
        tvState.text = "登录失败"
    }
}