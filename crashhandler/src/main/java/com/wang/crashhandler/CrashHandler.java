package com.wang.crashhandler;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created at 2021/8/18 上午6:42.
 *
 * @author wang
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler{

    private static final String TAG = "CrashHandler";
    private static final String PATH = Environment.getExternalStorageDirectory() + "/crash/log/";

    private Context mContext;
    private volatile static CrashHandler mCrashHandler;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            synchronized (CrashHandler.class) {
                if (mCrashHandler == null) {
                    mCrashHandler = new CrashHandler();
                }
            }
        }
        return mCrashHandler;
    }

    public void init(Context context) {
        mContext = context.getApplicationContext();
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当 UncaughtException 发生时会转入该重写的方法来处理
     */
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (!handleException(e) && mDefaultHandler != null) {
            // 如果自定义的没有处理 handleException 返回 false
            // 则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e);
        } else {
            // 如果处理了，让程序继续运行3秒再退出，保证文件保存并上传到服务器
            SystemClock.sleep(3000);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            // 或者重启主界面（下面方法又问题）
           /* Intent intent = new Intent(mContext, FirstActivity.class);//跳转到App最开始的页面
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);*/
        }
    }

    /**
     * 自己是否处理了异常
     * @return true 已经处理
     */
    public boolean handleException(Throwable e) {
        if (e == null) {
            return false;
        }
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将重新跳转", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        // 收集设备参数信息
        dumpExceptionToFile(e);
        //上传数据到服务器
        uploadExceptionToServer();
        return true;
    }


    //可以根据自己需求来,比如获取手机厂商、型号、系统版本、内存大小等等
    private void dumpExceptionToFile(Throwable e) {
//        File dir = new File(PATH);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        long timeMillis = System.currentTimeMillis();
//        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeMillis));
//        File file = new File(PATH + time + ".trace");
//        try {
//            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
//            pw.println(time);
//            pw.print("Android版本号 :");
//            pw.println(Build.VERSION.RELEASE);
//            pw.print("手机型号 :");
//            pw.println(Build.MODEL);
//            pw.print("CUP架构 :");
//            pw.println(Build.CPU_ABI);
//            e.printStackTrace(pw);
//            pw.close();
//        } catch (IOException ex) {
//            Log.e(TAG, "dump crash info error");
//        }

    }

    //	上传到Server
    private void uploadExceptionToServer() {
        // TODO
    }
}
