package http;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;


import base.Lain_Application;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 线程池的测试，暂时没有使用
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/1 19 : 52
 */

class MyTask implements Callable<String> {

    private int taskID;
    private String url;
    private ArrayList<String> jsonAl = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    jsonAl.add(msg.obj.toString());
                    break;
            }
        }
    };

    public MyTask(int taskID, String url) {
        this.taskID = taskID;
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(200);

        return "call() is run " + Thread.currentThread().getId() + ":::" + taskID;
    }
}
