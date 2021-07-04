package com.wang.jetpackstudy.net

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wang.jetpackstudy.R
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


const val TAG = "TestNetActivity"
class TestNetActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)
        findViewById<Button>(R.id.button).run {
            setOnClickListener {

                testInterceptor()

//                testCacheControl()
           }
        }
    }

    // 精细化每一个Request的CacheControl缓存控制策略
    fun testCacheControl() {
        val cache = Cache(
            File(cacheDir, "HttpCache"),
            1024 * 1024 * 10
        )

        val client = OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .cache(cache)
            .build()
        val cacheControl = CacheControl.Builder()
            .noTransform()
            .maxAge(10, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url("https://www.baidu.com/img/bd_logo1.png")
            .cacheControl(cacheControl)
            .build()

        client.newCall(request)
            .enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d(TAG, "onFailure: $e")
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val bytes = response.body()!!.bytes()
                        Log.d(TAG, "onResponse: " + bytes.size)
                    }
                }
            })
    }

    class CacheInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val response: okhttp3.Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.SECONDS) // 10 seconds cache
                .build()
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }

    // 使用拦截器来缓存
    fun testInterceptor() {
        val cache = Cache(
            File(cacheDir, "HttpCache"),
            1024 * 1024 * 10
        )
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(CacheInterceptor())
            .build()

        val request = Request.Builder()
            .url("https://www.baidu.com/img/bd_logo1.png")
            .build()

        client.newCall(request)
            .enqueue(object : okhttp3.Callback {
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.d(TAG, "onFailure: $e")
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    if (response.isSuccessful) {
                        val bytes = response.body()!!.bytes()
                        Log.d(TAG, "onResponse: " + bytes.size)
                    }
                }
            })
    }




    }