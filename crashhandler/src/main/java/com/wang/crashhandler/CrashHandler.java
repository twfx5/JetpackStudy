package com.wang.crashhandler;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.NonNull;

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
            /**
             * 在自定义异常处理类中需要持有线程默认异常处理类。
             * 这样做的目的是在自定义异常处理类无法处理或者处理异常失败时，还可以将异常交给系统做默认处理。
             */
            // 如果自定义的没有处理 handleException 返回 false
            // 则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e);
        } else {
            /**
             * 如果自定义异常处理类成功处理异常，需要进行页面跳转，或者将程序进程“杀死”。
             * 否则程序会一直卡死在崩溃界面，并弹出无响应对话框。
             */
            // 如果处理了，让程序继续运行3秒再退出，保证文件保存并上传到服务器
            SystemClock.sleep(3000);


            // 跳转到异常提示界面 或者 重启主界面
            Intent intent = new Intent(mContext, ErrorNoticeActivity.class);//跳转到App最开始的页面
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);

            // 杀掉程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
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
