package util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import base.Lain_Application;
import http.CookiesManager;
import http.MySharePreference;
import http.OkHttpCallBack;
import http.TokenInterceptor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName: DeviceIpGet
 * @Description: 对应设备IP表获取工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/8/3 8:52
 */
public class DeviceIpGetUtil extends Handler implements Callback {

    //OkHttp请求成功后回调接口
    private static volatile OkHttpCallBack okHttpCallBack = null;
    //OKHttp 单例实例
    private static volatile DeviceIpGetUtil deviceIpGetUtil;

    //当前请求的 URL
    private volatile String currentUrl = "";

    //OKHttp 客户端
    private OkHttpClient okHttpClient;
    //请求的 标识，用于区分是哪个请求
    private volatile String requestTag;

    //锁对象
    private Lock lock = new ReentrantLock();

    private HttpUrl currentHttpUrl;

    //GSON 实例
    private Gson gson;

    //保存Cookies
    public HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
    //持久Cookie
    private List<Cookie> cookies = new ArrayList<>();

    //双重检查
    public static DeviceIpGetUtil getInstance() {
        if (deviceIpGetUtil == null)
            synchronized (DeviceIpGetUtil.class) {
                if (deviceIpGetUtil == null) {
                    deviceIpGetUtil = new DeviceIpGetUtil();
                }
            }
        return deviceIpGetUtil;
    }
    
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        //请求成功后，获取返回的JSON串，异步将信息传输出去  请求标识，当前请求URL, 返回的JSON
        try {
            //获取携带数据的对象
            Bundle responseBundle = (Bundle) msg.obj;
            //如果请求的数据为空，直接返回
            if (responseBundle.isEmpty())
                return;

            //头部标识
            String header = responseBundle.getString("header");

            //请求链接
            String requestUrl = responseBundle.getString("requestUrl");
            //请求的结果
            String responseStr = responseBundle.getString("responseStr");
            //打印请求的结果
            //将数据回调
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式

            if (responseBundle.getString("flag").equals("success")) {

                okHttpCallBack.httpRequestSuccess(header, requestUrl, responseStr);
            } else {
                okHttpCallBack.httpRequestFailure(header, requestUrl, responseStr);
            }

            Log.d("ResponseJSON", "handleMessage: " + header + "---" + df.format(new Date()));

        } catch (NullPointerException e) {
            Log.e("UP LOAD FILE", "handleMessage: 文件上传，没有返回值");
        }

    }

    /**
     * 发送GET请求
     *
     * @param requestTag 请求标识
     * @param url        请求URL
     * @param callBack   请求成功后的回调接口
     */
    public void sendGetOkHttp(String requestTag, String url, OkHttpCallBack callBack) {

        lock.lock();

        try {

            if (url == null)
                return;

            if (url.isEmpty() || callBack == null) {
                Log.d(getClass().getName(), "sendGetOkHttp: 参数不能为空");
                return;
            }
            //保存 当前URL ，回调接口，请求标识
            currentUrl = url;
            okHttpCallBack = callBack;

            //这里获取到的数据是正确的，处理主线程，但两条以上的请求，requestTag 一定会是最后一个，因为他都执行了这行代码，而请求却还没完成
            this.requestTag = requestTag;

            if (okHttpClient == null)
                initClient();
            //构建请求
            Request request = null;

            //获取登陆的 Cookies，确保登陆的 Cookies 不为空
            if (!cookieStore.isEmpty()) {
                request = new Request.Builder()
                        .url(url)
                        .addHeader("Cookie", "JSESSIONID=" + cookieStore.get(currentHttpUrl).get(0).value())
                        .header("sendGet", requestTag) //请求的标识
                        .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                        .build();

            } else {
                request = new Request.Builder()
                        .url(url)
                        //只能传入英文，不然解析会失败
                        .header("sendGet", requestTag) //请求的标识
                        .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                        .build();
            }

            okHttpClient.newCall(request).enqueue(this);

        } finally {
            lock.unlock();
        }


    }

    //请求失败，如果多条线程同时请求这个方法，会导致该方法执行不正常
    //用了锁之后，轮循请求就会漰溃
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

        lock.lock();
        //设置请求的标识，失败
        String flag = "fail";
        //只能传入英文，获取头标识
        String getHead = call.request().header("sendGet");
        //获取当前请求的URL
        String requestUrl = call.request().url().toString();

        try {

            Log.e("HTTP REQUEST FAILURE", currentUrl + " ------------- " + e.getMessage());

            if (requestTag.equals("login")) {
                String loginMsg = "登陆失败，请检查网络是否正常";
                //对返回的数据进行处理
                getResponseString(getHead, requestUrl, loginMsg, flag);
            } else {
                String loginMsg = "获取数据失败，请检查网络是否正常";
                //对返回的数据进行处理
                getResponseString(getHead, requestUrl, loginMsg, flag);
            }

        } finally {
            lock.unlock();
        }

    }

    /**
     * 获取请求的结果
     *
     * @param <T>        暂时没用
     * @param header     请求的头部标识
     * @param requestUrl 请求的链接
     * @param response   请求的结果
     */
    private <T> void getResponseString(String header, String requestUrl, String response, String flag) {

        //构建Bundle储存数据
        Bundle bundle = new Bundle();
        bundle.putString("flag", flag);
        bundle.putString("header", header);
        bundle.putString("requestUrl", requestUrl);
        bundle.putString("responseStr", response);

        //储存数据的 Bundle 异步发送
        Message message = new Message();
        message.obj = bundle;

        sendMessage(message);

    }

    //请求成功 ，如果多条线程同时请求这个方法，会导致该方法执行不正常
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        lock.lock();
        try {

            //设置请求的标识，成功
            String flag = "success";
            //只能传入英文，获取头标识
            String getHead = call.request().header("sendGet");
            //获取当前请求的URL
            String requestUrl = call.request().url().toString();
            //当前请求到的数据
            String responseStr = Objects.requireNonNull(response.body()).string();

            Log.d("HTTP REQUEST SUCCESS", " ------------- " + requestUrl);
            //如果获取数据成功，就需要创建Gson对你并进行解析
            if (gson == null)
                createGson();

            //对返回的数据进行处理
            getResponseString(getHead, requestUrl, responseStr, flag);
        } finally {
            lock.unlock();
        }
    }

    //创建 GSON
    private void createGson() {
        gson = new Gson();
    }

    /**
     * 初始化 OkHttpClient
     */
    private void initClient() {

        okHttpClient = new OkHttpClient.Builder()
                //连接超时
                .connectTimeout(5, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                //构建
                .build();

    }

}
