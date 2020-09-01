package base;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.multidex.MultiDex;

import com.example.base.R;
import com.example.mymodule.MyCrashHandle;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import util.MyPreferencesManager;
import util.MyPropertiesUtil;

/**
 * 初始化，全局数据（Context），全面解析
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/26 17 : 04
 */
public class Lain_Application extends Application {

    // 获取全局的 Context
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    // LitePal 返回的数据库引用
    private SQLiteDatabase database = null;

    //文件路径
    public static String path = "";
    //全局常量，用于请求网络数据时，判断是 实时数据 还是 报警数据
    public static final String HTTP_REAL = "OK_HTTP_READ";
    public static final String HTTP_ALERT = "OK_HTTP_ALERT";
    //延时执行，减少初始化时的任务
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {

            //日志文件
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/lain_log/log.log");
            if (!file.exists()) {
                try {
                    //如果文件不存在，则创建文件
                    boolean success = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //保存文件的路径
            path = file.getAbsolutePath();
            //初始化日志，将打印的日志进行持久化
            Logger.init("logger")
                    .logLevel(LogLevel.INFO)
                    .cacheFile(file.getAbsolutePath());
            //自定义异常拾取类
            MyCrashHandle crashException = new MyCrashHandle();
            //将文件路径传入异常拾取类中，发生异常时，直接上传日志
            crashException.setLogFile(path);
            // 设置当线程由于未捕获到异常而突然终止，并且没有为该线程定义其他处理程序时就会执行这个方法
            Thread.setDefaultUncaughtExceptionHandler(crashException);

        }
    };

    /**
     * 获取全局上下文Context
     *
     * @return 全局上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 系统的入口
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //Dex 内存扩大
        MultiDex.install(this);

        //保存全局的Context
        context = getApplicationContext();

        //初始化 LitePal 数据库
        LitePal.initialize(context);

        //如果数据库存在，则打开，不存在新建
        if (database == null)
            database = LitePal.getDatabase();

        //初始化 MyPreferencesManager
        new MyPreferencesManager(this)
                //设置文件名
                .setName("computer_room")
                //完成初始化
                .init();
        /*
         *  因为多进程的原因，Application 会被多次执行，而有此对象是不能重复创建的
         *  判断是否是主线程，只在主线程进行延迟执行一次
         */
        if (isMain()) {
            Timer timer = new Timer();
            timer.schedule(timerTask, 5000);  //延迟5秒
        }
        try {
            initBugly();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 解决 Application 多次执行的问题
     *
     * @return 是否是主线程
     */
    private boolean isMain() {
        //获取进行 PID
        int pid = android.os.Process.myPid();
        String processName = "";
        //获取系统 Activity管理 实例
        ActivityManager mActivityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        //循环判断 PID 是否为 主线程 的 PID
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                processName = appProcess.processName;
                break;
            }
        }
        //获取包名
        String packageName = this.getPackageName();
        //线程包名来判断是否为主线程，是则返回true
        return processName.equals(packageName);
    }

    /**
     * 崩溃日志 抓取初始化
     */
    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "93f2b80d3c", false);
    }

    /**
     * 获取 Theme color
     *
     * @return 返回全局的颜色
     */
    public static int getThemeColor() {
        return Color.parseColor("#5097FF");
    }

    /**
     * 获取类的名称
     *
     * @param clazz 类实例
     * @return 类的名称
     */
    public static String getClassName(Class clazz) {
        return context.getClass().getName();
    }

}
