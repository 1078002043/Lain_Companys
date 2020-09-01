package util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import base.Lain_Application;

/**
 * Toast 工具类
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/31 10 : 24
 */
public class ToastManage extends Toast implements I_ToastManage {

    private static volatile ToastManage toastManage;

    private  Context toastContext;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            makeText(Lain_Application.getContext(), ""+msg.obj, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 获取上下文
     *
     * @param context 需要使用Toast的Context
     */
    public ToastManage(Context context) {
        super(context);
        toastContext = context;
    }

    /**
     * 单例弹窗
     *
     * @return 单例实例
     */
    @NotNull
    public static ToastManage getInstance() {

        if (toastManage == null)
            synchronized (ToastManage.class) {

                if (toastManage == null) {
                    toastManage = new ToastManage(Lain_Application.getContext());
                    return toastManage;
                }

            }

        return toastManage;

    }

    /**
     * 短时间Toast
     *
     * @param message 发送Toast的Message
     */
    public void toastShortShow(String message) {

        if (Thread.currentThread().getName().equals("main")) {
            Toast.makeText(toastContext, message, Toast.LENGTH_SHORT).show();
        } else {
            Message message1 = new Message();
            message1.obj = message;
            handler.sendMessage(message1);
        }

    }

    /**
     * 长时间Toast
     *
     * @param message 发送Toast的Message
     */
    public void toastLongShow(String message) {
        if (Thread.currentThread().getName().equals("main")) {
            Toast.makeText(toastContext, message, Toast.LENGTH_LONG).show();
        } else {
            Message message1 = new Message();
            message1.obj = message;
            handler.sendMessage(message1);
        }

    }

}
