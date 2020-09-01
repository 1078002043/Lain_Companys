package bg_run;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.base.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ServerHandshake;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.nio.ByteBuffer;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import base.Lain_Application;
import base.Lain_Base_Activity;
import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;
import util.LainNewApi;
import util.ToastManage;
import util.WebSocketMessageReceive;

/**
 * @ClassName: LainService
 * @Description: 后台报警信息提示服务
 * @Author: YIN LUO FEI
 * @Date: 2020/7/22 10:50
 */
public class LainService extends Service {

    //获取类名
    private static final String TAG = LainService.class.getName();
    //WebSocket 的数据接收接口
    public WebSocketMessageReceive receiveMessage;
    //服务通信的Binder
    private final IBinder mBinder = new LainServiceBinder();

    //通知的ID
    private static final int NOTIFICATION_ID = 10;
    //Android 9.0 以后的前台通知必须创建通道才能进行显示
    private static String NOTIFICATION_CHANNEL_ID = "20";
    //通道的名称
    private static final String CHANNEL_NAME = "bg_alert_channel";

    //通知管理器
    private NotificationManager notificationManager;

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {

            //第一分钟发送一次消息，如果服务器没`有回应，则进行重新连接

            Log.d(TAG, "run: 发送了消息");
//            try {
//                if (client != null && client.isClosed())
//                    client.connect();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            //如果 client 没有初始化过，不进行操作
            if (client == null) {
                return;
            }

            //判断是滞已打开WebSocket
            if (!client.isOpen()) {
                //判断是否已连接WebSocket，如果没有连接，则发起连接
                if (client.getReadyState().equals(ReadyState.NOT_YET_CONNECTED)) {
                    try {
                        client.connect();
                    } catch (IllegalStateException e) {
                        Log.d(TAG, "run: WebSocket 连接异常");
                    }
                } else if (client.getReadyState().equals(ReadyState.CLOSING) || client.getReadyState().equals(ReadyState.CLOSED)) {
                    //如果WebSocket已关闭连接或关闭中，都会进行重连WebSocket
                    client.reconnect();
                }
            }


        }
    };

    // WebSocket 客户端实例
    private WebSocketClient client = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.d(TAG, "onCreate: 服务被绑定");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 服务被创建");

        try {

            //获取服务器IP
            String serverIp = LainNewApi.getInstance().IP.split("http://")[1];
            //如果未对WebSocket初始化，则初始化
            if (client == null) {
                //构建WebSocket的连接
                client = new WebSocketClient(new URI("ws://" + serverIp + ":8080/software/websocket/100")) {

                    @Override
                    public void onWebsocketPong(org.java_websocket.WebSocket conn, Framedata f) {
                        super.onWebsocketPong(conn, f);

                        //发送心跳包，保持连接
//                    LogUtils.showLog(f.getPayloadData().toString());
//                    LogUtils.showLog("socket ping"+f.getOpcode());
                    }

                    @Override
                    public void onOpen(ServerHandshake serverHandshake) {
                        // 连接打开以后的回调
                        Log.d(TAG, "onOpen: 成功连接 Socket");
//                    client.send("我是内容简介");
                    }


                    @Override
                    public void onMessage(String message) {

                        // 收到消息的回调
                        Log.d(TAG, "onMessage: " + message);

                        //将数据回调给调用者
                        if (receiveMessage != null)
                            receiveMessage.receiveMessage(message);

                    }

                    @Override
                    public void onError(Exception ex) {
                        // 连接出错的回调
                        Log.d(TAG, "onError: 连接错误 Socket " + ex.getMessage());
                    }

                    @Override
                    public void onClose(int code, String reason, boolean remote) {

                        // 连接关闭的回调， remote如果为true表示被服务端cut掉了
                        Log.d(TAG, "onOpen: 关闭连接 Socket");

                        //webSocket被关闭时，就会重启服务，两次重复连接WebSocket
//                    startService(new Intent(LainService.this, LainService.class));
                    }

                    @Override
                    public void onMessage(ByteBuffer bytes) {
                        // 返回的字节流消息
                        // 收到消息的回调
                        Log.d(TAG, "onMessage: socket bytebuffer bytes");
                    }
                };
            }

            //连接WebSocket之前，就发送数据和后台通讯
            Timer timer = new Timer();    //如果有回应代码连接中，否则进行重新连接
            timer.schedule(timerTask, 3000, 6 * 10000);
            //连接WebSocket
            client.connect();

            Log.d(TAG, "onCreate: 重新启动");

        } catch (Exception e) {

        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onCreate: 服务被启动");


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.d(TAG, "onCreate: 服务被解绑");

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onCreate: 服务被销毁");
        super.onDestroy();
    }

    /**
     * 开启前台服务
     *
     * @param msg 前台通知显示的信息
     */
    private void startForeground(String msg) {

        //Android 9 之后，前台服务必须创建 CHANNEL ID 才能正常显示
        if (notificationManager == null)
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
            );
            //创建通道
            if (notificationManager != null)
                notificationManager.createNotificationChannel(channel);

        }

        //构建通知的跳转页面
        Intent notificationIntent;
        //延迟跳转
        PendingIntent notificationPending = null;

        try {
            notificationIntent = new Intent(this, Class.forName("view.Lain_View"));
            notificationPending = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        } catch (ClassNotFoundException e) {
            ToastManage.getInstance().toastShortShow("未设置跳转页面");
        }

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("莱安智能");
        bigTextStyle.setSummaryText("莱安智能");
        bigTextStyle.bigText(msg);

        //开启前台服务
        startForeground(NOTIFICATION_ID, new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                //不允许滑动删除
                .setOngoing(true)
                .setSmallIcon(R.drawable.alert_img)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.alert_img))
                .setContentTitle("莱安智能")
                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                //点击进入主页
                .setContentIntent(notificationPending)
                //设置通道ID
                .setChannelId(NOTIFICATION_CHANNEL_ID)
                .build());

    }

    public WebSocketClient getClient() {
        return client;
    }

//    @Override
//    public void receiveMessage(String message) {
//
//        //webSocket 回调的数据
//        Log.d(TAG, "receiveMessage: " + message);
//
//        //显示报警信息
//        startForeground(message);
//
//    }

    /**
     * 获取Service引用实例
     */
    public class LainServiceBinder extends Binder {

        public LainService getService() {
            return LainService.this;
        }

    }

}
