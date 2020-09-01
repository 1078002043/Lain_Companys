package cn.com.lain.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;

import org.litepal.LitePal;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import base.Lain_Base_Activity;
import bean.LoginJSONBean;
import bean.UserInfo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.com.lain.R;
import http.CookiesManager;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import okhttp3.Cookie;
import util.LainNewApi;
import util.MyWiFiManage;
import util.ToastManage;
import view.Lai_Login_View;
import view.Lain_View;

/**
 * 启动欢迎页，目前只有一页，可以做多页
 * author : 文福金
 * date   : 2019/9.9
 */
public class GuidePageActivity extends AppCompatActivity implements OkHttpCallBack, MyWiFiManage.WiFiConnect {
    //ButterKnife
    private Unbinder unbinder;
    //倒计时，尽可能设置3秒以上，不然获取不到WIFI的IP
    private static final int NUM = 3;
    private static final int MSG_COUNT_WHAT = 99;
    //计时器
    private static Timer timer;
    //ViewPagerBanner
    @BindView(R.id.cb_test)
    ConvenientBanner cbTest;
    //图片BitMap
    private ArrayList<Bitmap> arrayList;

    //倒计时文本
    private TextView mCountdownTextView;
    //倒计时的秒数
    private int countdownNum;
    //用于控制倒计时子线程
    private MyHandler countdownHandle;
    //倒计时子线程
    private Runnable runnable;
    //提前判断是否已登陆
    private boolean isLogin = false;
    //文件操作类
    private SharedPreferences preferences;
    //WIFI连接状态
    private boolean wifiConnect = false;
    //控制是否达到定时的时间，如果满足才跳转到下一页
    private boolean isOver = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //透明状态栏
        makeStatusBarTransparent(this);

        //网络连接管理类
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //如果是 Android 7.0 以上，使用注册方式来监听网络
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            assert connectivityManager != null;
            connectivityManager.registerDefaultNetworkCallback(MyWiFiManage.getInstance());
        }

        //设置WIFI状态回调接口，必须是已连接才能登陆
        MyWiFiManage.getInstance().setWiFiConnect(this);

        //初始化
        preferences = getSharedPreferences("APP_config", Context.MODE_PRIVATE);
        //处理网络配置，调用这个方法后，会自动配置网络
        MyWiFiManage.getInstance().getAPPNetwork(preferences);

        setContentView(R.layout.activity_guide_page);

        //ButterKnife
        unbinder = ButterKnife.bind(this);
        //初始化控件
        initView();

        //初始化Handler和Runnable
        initThread();

        //一进入主页时，都必须先判断是否已登陆,如果未登陆，直接跳转到登陆页面
        isLogin = LitePal.findFirst(UserInfo.class) != null;

    }

    //倒计时结束，打开下一个界面
    private void openNextActivity(AppCompatActivity mActivity) {

        //已经登陆过，直接获取账号和密码进行登陆
        if (isLogin) {
            UserInfo userInfo = LitePal.findFirst(UserInfo.class);
            String userName = userInfo.getUsername();
            String password = userInfo.getPassword();
            login(userName, password);
        } else {
            //结束欢迎页面
            finish();
            //如果未登陆过，则需要用户进行输入账号和密码登陆
            Lain_Base_Activity.myStartActivity(GuidePageActivity.this, Lai_Login_View.class);
        }

    }

    //登陆操作
    private void login(String userName, String password) {
        try {
            //POST 账号 密码
            Map<String, String> loginMap = new LinkedHashMap<>();
            loginMap.put("username", userName);
            loginMap.put("password", password);

            //进行登陆
            String url = LainNewApi.getInstance().getRootPath() + LainNewApi.login;


            //获取IP地址
            String[] ips = LainNewApi.getInstance().IP.split("//");

            Log.d("sdfsdf", "login: " + ips.length);

            //获取HttpUrl
            OkHttpUtil.getInstance().cookiesManager = new CookiesManager(this);
            List<Cookie> cookieList = OkHttpUtil.getInstance().cookiesManager.getCookies(ips[1]);

            //如果登陆的Cookie已持久化，则无需重复登陆，或者已过期，都需要进行登陆
            if (cookieList.isEmpty()) {
                //如果没有,需要进行登陆
                //发送登陆请求
                OkHttpUtil.getInstance().sendPostOkHttp("login", url, loginMap, this);

            } else if (cookieList.get(0).expiresAt() < System.currentTimeMillis()) {

                //如果过期，需要进行登陆
                //发送登陆请求
                OkHttpUtil.getInstance().sendPostOkHttp("login", url, loginMap, this);

            } else {

                //Cookies 没过期时，可以直接进入
                Lain_Base_Activity.myStartActivity(this, Lain_View.class);
                finish();

            }


        } catch (IllegalArgumentException e) {
            ToastManage.getInstance().toastLongShow("请输入正确的请求地址");
            Lain_Base_Activity.myStartActivity(this, Lai_Login_View.class);
        }
    }

    //初始化控件
    private void initView() {
        //倒计时，目前隐藏了
        mCountdownTextView = (TextView) findViewById(R.id.id_countdownTextView);
        //引导欢迎界面
        arrayList = new ArrayList<>();
        //保存启动页的图片，必须 使用 BitMap 才能加载网络图片，有多少张图片，启动页就有多少
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.start_ad);
        //保存在List集合中
        arrayList.add(bitmap);
        //初始化启动页的样式
        initGuidePage();
        //加载网络图片，目前只使用了本地图片，后续需要再加进行
        /*GlideApp.with(this).asBitmap().load("https://img.zcool.cn/community/01e8685752e84432f875a4291f3fb5.png@2o.png")
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                        runOnUiThread(() -> {

                            arrayList.add(resource);
                            //初始化底部圆点
                            initGuidePage();

                        });

                    }
                });*/

    }

    //初始化启动页的样式
    private void initGuidePage() {
        cbTest.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                //设置加载哪个布局updateUI
                return R.layout.item_guide_page;

            }
        }, arrayList)
                //底部圆点
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //圆点排版
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //底部圆点是否可见
                .setPointViewVisible(false)
                //是否能循环
                .setCanLoop(false)
                //滑动页时的监控
                .setOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        //滑动中
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        //滑动后
                    }

                    //选中后
                    @Override
                    public void onPageSelected(int index) {
                        //总共添加了三张图片，如果index为2表示到了最后一张图片，隐藏下面的指示器，显示跳转到主页的按钮
                    }
                });
    }

    /**
     * 初始化倒计时Handler和Runnable
     */
    private void initThread() {
        //倒计时变量
        initCountdownNum();
        //handler对象
        countdownHandle = new MyHandler(this);
        //runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                //执行倒计时代码
                timer = new Timer();

                TimerTask task = new TimerTask() {
                    public void run() {
                        //倒计时
                        countdownNum--;
                        /*
                            创建 Message 一共有3和，这种方式可以防止创建重复的 Message 对象，减少内存的使用
                            开发中尽量使用这个方法创建Message
                         */
                        Message msg = countdownHandle.obtainMessage();
                        //message的what值
                        msg.what = MSG_COUNT_WHAT;
                        //倒计时的秒数
                        msg.arg1 = countdownNum;
                        //发送信息
                        countdownHandle.sendMessage(msg);
                    }
                };
                //开始倒计时，1秒
                timer.schedule(task, 0, 1000);
            }
        };
    }

    //点击倒计时面板时，直接停止计时，直接跳转到 下一页
    @OnClick({R.id.id_countdownTextView})
    public void onViewClicked(View view) {
        stopThread();
        openNextActivity(GuidePageActivity.this);//打开下一个界面
    }

    //获取焦点后，才开始计时
    @Override
    protected void onResume() {
        //开启线程
        countdownHandle.post(runnable);
        super.onResume();
    }

    @Override
    protected void onStop() {
        //初始化倒计时的秒数，这样按home键后再次进去欢迎界面，则会重新倒计时
        initCountdownNum();
        //停止倒计时
        stopThread();

        super.onStop();
    }

    //停止倒计时
    private void stopThread() {
        //在这里执行的话，用户点击home键后，不会继续倒计时进入登录界面
        if (timer != null) {
            timer.cancel();//销毁计时器
        }

        //将线程销毁掉
        countdownHandle.removeCallbacks(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ButterKnife 解绑
        unbinder.unbind();
    }

    /*初始化倒计时的秒数*/
    private void initCountdownNum() {
        countdownNum = NUM;
    }

    /**
     * 登陆成功后，才会跳转到指定的页面
     *
     * @param requestTag  请求的 标识
     * @param requestUrl  请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */
    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        Log.d("login_success", "httpRequestSuccess: " + responseStr);
        //解析获取到的结果
        LoginJSONBean jsonBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);
        //如果登陆成功，跳转到主页
        if (jsonBean.getZt()) {

            Lain_Base_Activity.myStartActivity(this, Lain_View.class);

//            Intent intent = new Intent(this, Device_Detail.class);
//            intent.putExtra("device_tag", "运维监控");
//            intent.putExtra("device_name", "服务器");

//            startActivity(intent);

        } else {
            //登陆失败，提示用户，进入登陆页面重新登陆
            ToastManage.getInstance().toastLongShow("登陆出错，请检查 账号/密码 重新登陆");
            Lain_Base_Activity.myStartActivity(this, Lai_Login_View.class);
        }

        //结束欢迎页面
        finish();

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {
        //输出登陆的结果
        Log.d("login_fail", "httpRequestFailure: " + requestTag + "----" + requestUrl + "---" + errorMsg);
        //接口请求失败回调
        ToastManage.getInstance().toastLongShow(errorMsg);
        //请求失败后，跳转到登陆页面
        Lain_Base_Activity.myStartActivity(this, Lai_Login_View.class);
        //结束欢迎页面
        finish();
    }

    /**
     * WIFI连接结果回调，如果已连接，将获取 WIFI 的 IP 保存的 API 中
     * 注意：该方法会被多次回调
     *
     * @param isConnect WIFI 连接的结果
     */
    @Override
    public void wifiIsConnect(boolean isConnect) {

        try {
            //保存WIFI状态
            wifiConnect = isConnect;
            //每次获取都会保存，在这里可以会获取失败
            LainNewApi.getInstance().AutoIp = "http://" + MyWiFiManage.getInstance().getWIFI_HostIp(this);

            //每次如果 WIFI 已连接上，则获取IP并保存在API工具类中
            if (isConnect && LainNewApi.getInstance().IP.isEmpty()) {
                //如果 LainNewApi.getInstance().IP == null 或者 ""，才会进行更新
                LainNewApi.getInstance().IP = "http://" + MyWiFiManage.getInstance().getWIFI_HostIp(this);
                ToastManage.getInstance().toastShortShow("WIFI 已连接，IP: " + LainNewApi.getInstance().IP);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    /**
     * 必须使用静态类：
     * 解决问题：This Handler class should be static or leaks might occur Android
     */
    private static class MyHandler extends Handler {
        // WeakReference to the outer class's instance. 弱引用
        private WeakReference<GuidePageActivity> mOuter;

        public MyHandler(GuidePageActivity activity) {
            //初始化 WeakReference 弱引用
            mOuter = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            //通过弱引用来获取 外部类 的引用，从而在 内部类 中使用
            GuidePageActivity theActivity = mOuter.get();
            if (theActivity != null) {
                //如果获取不为空，则对发送过来的内容进行检查
                switch (msg.what) {
                    case MSG_COUNT_WHAT:
                        //倒计时完成
                        if (msg.arg1 == 0) {

                            //在这里执行的话，不会出现-1S的情况
                            if (timer != null) {
                                //销毁计时器
                                timer.cancel();

                                //WIFI连接成功状态下，才能打开下一个界面
                                theActivity.openNextActivity(theActivity);
                            } else {
                                String text = "跳过 " + msg.arg1 + "s";
                                theActivity.mCountdownTextView.setText(text);
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }

    /**
     * 轮播图2 对应的holder
     */
    public class LocalImageHolderView extends Holder<Bitmap> {
        //轮播时加载图片控件
        private ImageView mImageView;

        //构造器
        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            //初始化 欢迎页 的模板
            mImageView = itemView.findViewById(R.id.iv_guide_page);
            //设置图片的填充方式，X Y 填满 FIT_START 图片居上，高度自适应
            mImageView.setScaleType(ImageView.ScaleType.FIT_END);
        }

        @Override
        public void updateUI(Bitmap data) {
            //更新UI，图片的更新
            mImageView.setImageBitmap(data);
        }
    }

    /**
     * 将状态栏设置透明
     *
     * @param activity 需要设置的Activity
     */
    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);

        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
