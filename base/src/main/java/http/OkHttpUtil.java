package http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import base.Lain_Application;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.LainNewApi;
import util.ToastManage;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/18 22:16
 * Description：OKHttp 封装
 * POST {@link #sendPostOkHttp}
 * GET {@link #sendGetOkHttp}
 * DELETE {@link #sendDeletedOkHttp}
 * PUT {@link #sendPutOkHttp}
 * RAW {@link #sendPostOkHttp}
 * 文件上传 {@link #uploadFile}
 **/
public class OkHttpUtil extends Handler implements Callback, CookieJar {

    //锁对象
    private Lock lock = new ReentrantLock();

    //OkHttp请求成功后回调接口
    private static volatile OkHttpCallBack okHttpCallBack = null;
    //OKHttp 单例实例
    private static volatile OkHttpUtil okHttpUtil;
    //OKHttp 客户端
    private OkHttpClient okHttpClient;
    //请求的 标识，用于区分是哪个请求
    private volatile String requestTag;
    //GSON 实例
    private Gson gson;
    //当前请求的 URL
    private volatile String currentUrl = "";
    //RAW 请求
    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //保存Cookies
    public HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
    //持久Cookie
    private List<Cookie> cookies = new ArrayList<>();
    private HttpUrl cookiesURL;
    private HttpUrl currentHttpUrl;

    public CookiesManager cookiesManager;

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

    //双重检查
    public static OkHttpUtil getInstance() {
        if (okHttpUtil == null)
            synchronized (OkHttpUtil.class) {
                if (okHttpUtil == null) {
                    okHttpUtil = new OkHttpUtil();
                }
            }
        return okHttpUtil;
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

            if (url.isEmpty() || callBack == null)
                throw new NullPointerException("参数不能为空");
            //保存 当前URL ，回调接口，请求标识
            currentUrl = url;
            okHttpCallBack = callBack;

            //这里获取到的数据是正确的，处理主线程，但两条以上的请求，requestTag 一定会是最后一个，因为他都执行了这行代码，而请求却还没完成
            this.requestTag = requestTag;

            if (okHttpClient == null)
                initClient();
            //构建请求
            Request request = null;

            Log.d("jdjklfsdf", "sendGetOkHttp: " + cookieStore.isEmpty());
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

    /**
     * 发送POST请求
     *
     * @param requestTag 请求标识
     * @param url        请求URL
     * @param value      POST 请求的 参数
     * @param callBack   请求成功后的回调接口
     */
    public void sendPostOkHttp(String requestTag, String url, Map<String, String> value, OkHttpCallBack callBack) {

        if (url.isEmpty() || callBack == null)
            throw new NullPointerException("参数不能为空");

        //保存 当前URL ，回调接口，请求标识
        currentUrl = url;
        okHttpCallBack = callBack;
        this.requestTag = requestTag;

        //遍历 FormBody
        Iterator<Map.Entry<String, String>> iterator = value.entrySet().iterator();
        //POST 参数
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        //遍历所有的 参数，并添加在 FormBody 中
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            bodyBuilder.add(entry.getKey(), entry.getValue());
        }
        //构建FormBody
        FormBody formBody = bodyBuilder.build();
        //保存数据
        currentUrl = url;
        okHttpCallBack = callBack;
        this.requestTag = requestTag;

        if (okHttpClient == null)
            initClient();


        //构建请求
        Request request = null;


        //获取登陆的 Cookies，确保登陆的 Cookies 不为空
        if (!cookieStore.isEmpty()) {

            request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .addHeader("Cookie", "JSESSIONID=" + cookieStore.get(currentHttpUrl).get(0).value())
                    .header("sendGet", requestTag) //请求的标识
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();

        } else {
            //构建请求
            request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .header("Content-Type", "application/json")
                    .header("sendGet", requestTag) //请求的标识
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();
        }


        okHttpClient.newCall(request).enqueue(this);
    }

    /**
     * 初始化 OkHttpClient
     */
    private void initClient() {

        cookiesManager = new CookiesManager(Lain_Application.getContext());

        okHttpClient = new OkHttpClient.Builder()
                //连接超时
                .connectTimeout(5, TimeUnit.SECONDS)
                //读取超时
                .readTimeout(5, TimeUnit.SECONDS)
                //持久化 COOKIES
                .cookieJar(cookiesManager)
                .addInterceptor(new TokenInterceptor())
                //构建
                .build();

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

    /**
     * 通过 GSON 对 JSON 串进行解析
     *
     * @param jsonStr JSON 串
     * @param t       指定具体的解析类
     * @param <T>     泛型类
     * @return 解析后的 List<T> 集合
     */
    public <T> List<T> formatResponse(String jsonStr, Class<T> t) {

        List<T> list = null;
        try {
            if (gson == null)
                createGson();

            list = new ArrayList<T>();
            //解析JSON为数组
            JsonArray array = JsonParser.parseString(jsonStr).getAsJsonArray();
            for (final JsonElement elem : array) {
                //再通过 JSON ELEMENT 来一个一个取出，放到集合中
                list.add(gson.fromJson(elem, t));
            }
        } catch (JsonSyntaxException e) {
            Log.d("JsonSyntaxException", "formatResponse: " + e.getMessage());
//            ToastManage.getInstance().toastShortShow("获取数据失败，请检查网络是否正常");

        } catch (IllegalStateException ignored) {

        }
        //返回处理后的结果
        return list;

    }

    //创建 GSON
    private void createGson() {
        gson = new Gson();
    }

    //Raw请求
    public void sendRowOkHttp(String requestTag, String urlServer, Map<String, String> json, OkHttpCallBack callBack, File file) {

        String url = urlServer;

        OkHttpClient okHttpClient = new OkHttpClient();

        //保存回调
        okHttpCallBack = callBack;

        //保存上传文件的Tag
        this.requestTag = requestTag;

        //上传文件的 Body
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //提交 form-data 数据
        RequestBody fileBody = RequestBody.create(MediaType.parse("multipart/form-data; charset=utf-8"), file);
        //文件参数
        builder.addFormDataPart("file", file.getName(), fileBody);

        //上传文件的后缀
        builder.addFormDataPart("username", json.get("username"));

        if (json.get("password") != null)
            builder.addFormDataPart("password", json.get("password"));

        builder.addFormDataPart("telephone", json.get("telephone"));
        builder.addFormDataPart("email", json.get("email"));

        if (json.get("id") != null)
            builder.addFormDataPart("id", json.get("id"));
        builder.addFormDataPart("gId", json.get("gId"));
        builder.addFormDataPart("icon", file.getName());

        if (json.get("roleList") != null)
            builder.addFormDataPart("roles", json.get("roleList"));


//        if (upOrAdd.equals("add_user")) {
//            //密码
//            userInfo.put("password", ActivityUtil.getInstance().EditTextToString(deviceUserPas));
//        }

        RequestBody requestBody = builder.build();
        Request request;

        //获取登陆的 Cookies，确保登陆的 Cookies 不为空
        if (!cookieStore.isEmpty()) {

            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("Cookie", "JSESSIONID=" + cookieStore.get(currentHttpUrl).get(0).value())
                    .header("sendGet", requestTag) //请求的标识
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();

        } else {
            //构建请求
            //构建好请求的 Body 后，进行请求
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .header("sendGet", requestTag) //请求的标识
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();
        }


        //发送请求
        okHttpClient.newCall(request).enqueue(this);

    }

    //Raw请求
    public void sendRowOkHttp(String requestTag, String url, String json, OkHttpCallBack callBack) {

        //如果Client为空，对OkHttpClient初始化
        if (okHttpClient == null) {

            initClient();

        }
        //回调
        currentUrl = url;
        okHttpCallBack = callBack;
        this.requestTag = requestTag;

        RequestBody requestBody = RequestBody.create(JSON, json);

        //构建请求
        Request request = null;


        //获取登陆的 Cookies，确保登陆的 Cookies 不为空
        if (!cookieStore.isEmpty()) {

            request = new Request.Builder()
                    .post(requestBody)
                    .url(url) //请求的url
                    .addHeader("Cookie", "JSESSIONID=" + cookieStore.get(currentHttpUrl).get(0).value())
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("sendGet", requestTag) //请求的标识
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();

        } else {
            //构建请求
            request = new Request.Builder()
                    .post(requestBody)
                    .url(url) //请求的url
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("sendGet", requestTag) //请求的标识
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();
        }

        okHttpClient.newCall(request).enqueue(this);

    }

    //DELETE 请求
    public void sendDeletedOkHttp(String requestTag, String url, OkHttpCallBack callBack) {

        //如果Client为空，对OkHttpClient初始化
        if (okHttpClient == null) {

            initClient();

        }
        this.requestTag = requestTag;
        okHttpCallBack = callBack;
        //不需要传入参数，但需要构建
        FormBody formBody = new FormBody.Builder().build();
        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("sendGet", requestTag)  //请求的标识
                .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                .delete(formBody);

        Request request = builder.build();

        okHttpClient.newCall(request).enqueue(this);

    }

    //PUT请求
    public void sendPutOkHttp(String requestTag, String url, String json, OkHttpCallBack callBack) {

        //如果Client为空，对OkHttpClient初始化
        if (okHttpClient == null) {

            initClient();

        }

        //回调
        currentUrl = url;
        okHttpCallBack = callBack;
        this.requestTag = requestTag;

        //构建请求
        RequestBody requestBody = RequestBody.create(JSON, json);

        //构建请求
        Request request = null;


        //获取登陆的 Cookies，确保登陆的 Cookies 不为空
        if (!cookieStore.isEmpty()) {

            request = new Request.Builder()
                    .put(requestBody)
                    .url(url) //请求的url
                    .addHeader("Cookie", "JSESSIONID=" + cookieStore.get(currentHttpUrl).get(0).value())
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("sendGet", requestTag)  //请求的标识
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();

        } else {
            //构建请求
            request = new Request.Builder()
                    .put(requestBody)
                    .url(url) //请求的url
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("sendGet", requestTag)  //请求的标识
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();
        }

        okHttpClient.newCall(request).enqueue(this);

    }

    /**
     * 上传文件，默认后缀 .log
     *
     * @param file 上传的文件
     */
    public void uploadFile(String suffix, File file, String upServerUrl, String requestTag, String id, OkHttpCallBack callBack) {

        String url = upServerUrl;

        OkHttpClient okHttpClient = new OkHttpClient();

        //保存回调
        okHttpCallBack = callBack;

        //保存上传文件的Tag
        this.requestTag = requestTag;

        //上传文件的 Body
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //提交 form-data 数据
        RequestBody fileBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file);
        //文件参数
        builder.addFormDataPart("file", file.getName(), fileBody);
        //上传文件的后缀
        builder.addFormDataPart("suffix", suffix);
        Log.d("lkjldfgfdg", "uploadFile: " + id);
        if (id != null)
            builder.addFormDataPart("id", id);

        RequestBody requestBody = builder.build();
        Request request;

        //获取登陆的 Cookies，确保登陆的 Cookies 不为空
        if (!cookieStore.isEmpty()) {

            request = new Request.Builder()
                    .url(url)
                    .put(requestBody)
                    .addHeader("Cookie", "JSESSIONID=" + cookieStore.get(currentHttpUrl).get(0).value())
                    .header("sendGet", requestTag) //请求的标识
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();

        } else {
            //构建请求
            //构建好请求的 Body 后，进行请求
            request = new Request.Builder()
                    .url(url)
                    .put(requestBody)
                    .header("sendGet", requestTag) //请求的标识
                    .addHeader("Accept", "application/json; */*")  //添加header
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();
        }


        //发送请求
        okHttpClient.newCall(request).enqueue(this);

    }

    /**
     * 将MAP集合转成 RAW 请求的 JSON 格式，发送ROW请求之前，一定先做转换
     *
     * @param jsonMap 需要转换的MAP集合
     * @return 转换后的 JSON
     */
    public String mapToRow(Map<String, String> jsonMap) {
        //字符串缓冲
        StringBuffer rawJson = new StringBuffer();
        //添加开始括号
        rawJson.append("{");
        //遍历map，将 KEY 和 Value 添加进缓冲区
        for (String key :
                jsonMap.keySet()) {
            rawJson.append("\"" + key + "\"" + ":" + "\"" + jsonMap.get(key) + "\"" + ",");
        }
        //将最后一个 “，” 填的成 “}”
        rawJson.replace(rawJson.length() - 1, rawJson.length(), "}");
        //返回转换后的数据
        return rawJson.toString();
    }

    /**
     * 加载持久化的 Cookies
     *
     * @param httpUrl 请求的URL
     * @return Cookies 列表
     */
    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        Log.d("sdfsdfsf", "loadForRequest: " + httpUrl.toString());
        List<Cookie> cookies = cookieStore.get(currentUrl);
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }

    /**
     * 保存持久化的Cookies
     *
     * @param httpUrl 请求的URL
     * @param list    Cookies 列表
     */
    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {

       /* if (cookies != null) {
            for (int i = 0, len = cookies.size(); i < len; i++) {
                if ("JSESSIONID".equals(cookies.get(i).name())) {
                    String phpsessid = cookies.get(i).value();
                }
            }
        }*/

        currentHttpUrl = httpUrl;
        //保存每次请求到的Cookies
        cookieStore.put(httpUrl, list);
    }

    /**
     * 登陆方法
     *
     * @param userName 用户名称
     * @param password 用户密码
     */
    public void loginRequest(String userName, String password, OkHttpCallBack callBack) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.login;

        //如果Client为空，对OkHttpClient初始化
        if (okHttpClient == null) {
            initClient();
        }

        //保存回调
        okHttpCallBack = callBack;

        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("username", "admin");
        loginMap.put("password", "admin");

        //POST 参数
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        bodyBuilder.add("username", userName);
        bodyBuilder.add("password", password);

        //构建FormBody
        FormBody formBody = bodyBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .header("sendGet", "login") //请求的标识
                .build();

        //获取请求结果
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
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

                    if (e.getMessage().equals("timeout")) {
                        Log.d(getClass().getName(), "onFailure: 连接超时，请检查网络---");
                        return;
                    }

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

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String result = response.body().string();

                final TokenBean tokenBean = new Gson().fromJson(result, TokenBean.class);

                //解析 Token
                String tokenStr = tokenBean.getToken();
                String loginResult = tokenBean.getZt();

                //构建数据集
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("token", tokenStr);
                tokenMap.put("login", loginResult);

                //保存到Share中
                MySharePreference.getInstance().editPreference("token", tokenMap);


                //设置请求的标识，成功
                String flag = "success";
                //只能传入英文，获取头标识
                String getHead = call.request().header("sendGet");
                //获取当前请求的URL
                String requestUrl = call.request().url().toString();

                //如果获取数据成功，就需要创建Gson对你并进行解析
                if (gson == null)
                    createGson();

                //对返回的数据进行处理
                getResponseString(getHead, requestUrl, result, flag);

            }
        });

    }

}
