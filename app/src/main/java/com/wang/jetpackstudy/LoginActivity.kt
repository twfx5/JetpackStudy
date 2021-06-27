package com.wang.jetpackstudy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edPwd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(LoginViewModel::class.java)
        loginViewModel.loginLiveData.observe(this, Observer {
            if (it == 200) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show()
            }
        })
        edName = findViewById(R.id.ed_name)
        edPwd = findViewById(R.id.ed_pwd)
        findViewById<Button>(R.id.button).run {
            setOnClickListener {
                loginViewModel.login(getName(), getPwd())
            }
        }
    }

    private fun getName(): String = edName.text.toString().trim()

    private fun getPwd(): String = edPwd.text.toString().trim()

}