package util;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import org.java_websocket.client.WebSocketClient;

import bg_run.LainService;
import okhttp3.WebSocket;

/**
 * @ClassName: WebSocketUtil
 * @Description: WebSocket 的工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/8/20 10:16
 */
public class WebSocketUtil implements WebSocketMessageReceive{

    //获取本类的实例，用于打印日志的标识
    public static final String TAG = WebSocketUtil.class.getName();

    //使用单例构建对象
    private volatile static WebSocketUtil webSocketUtil;

    //保存上下文
    private Context context;

    //WebSocket的实例
    public WebSocketClient webSocketClient;

    //WebSocket数据回调到后台服务
    private LainService lainService;
    //收到消息后，回调消息给调用者
    private WebSocketMessageReceive receive;

    public static WebSocketUtil getInstance() {
        if (webSocketUtil == null)
            synchronized (WebSocketUtil.class) {
                if (webSocketUtil == null) {
                    webSocketUtil = new WebSocketUtil();
                }
            }

        return webSocketUtil;
    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            LainService.LainServiceBinder binder = (LainService.LainServiceBinder) service;
            lainService = binder.getService();
            //设置WebSocket Client
            webSocketClient = lainService.getClient();
            Log.d(TAG, "onServiceConnected: 连接成功---" + webSocketClient.isOpen());

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            Log.d("LainService", "onServiceDisconnected: 断开连接");


        }

    };

    public WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    /**
     * 更新WebSocket回调地址
     * @param messageReceive 需要更新的回调地址
     */
    public void setWebSocketCallBack(WebSocketMessageReceive messageReceive) {

        if (messageReceive == null)
            throw new NullPointerException("消息回调接口 WebSocketMessageReceive is null");

        lainService.receiveMessage = messageReceive;
    }

    @Override
    public void receiveMessage(String message) {

    }
}
