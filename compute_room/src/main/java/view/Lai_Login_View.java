package view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.dd.CircularProgressButton;
import com.example.compute_room.R;
import com.example.compute_room.R2;
import com.github.ybq.android.spinkit.SpinKitView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import base.Lain_Base_Activity;
import bean.ConfigBean;
import bean.UserInfo;
import butterknife.BindView;
import butterknife.OnClick;
import http.MySharePreference;
import http.OkHttpCallBack;
import network.NetWorkSubDevice;
import present.Login_View_Presenter;
import top.defaults.view.PickerView;
import util.ActivityUtil;
import util.AutoStartUtil;
import util.LainNewApi;
import util.MyPropertiesUtil;
import util.MyWiFiManage;
import util.ToastManage;
import v_interface.I_Login_View;

/**
 * 登陆页面
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/30 14 : 29
 */
public class Lai_Login_View extends Lain_Base_Activity implements I_Login_View, OkHttpCallBack {

    //登陆按钮
    @BindView(R2.id.user_login)
    public CircularProgressButton userLogin;
    //用户名
    @BindView(R2.id.userName)
    EditText userName;
    //登陆面板设置
    @BindView(R2.id.login_setting_panel)
    ImageView loginSettingPanel;
    //用户密码
    @BindView(R2.id.password)
    EditText password;
    @BindView(R2.id.server_ip_login_spin)
    SpinKitView serverIPLoginSpin;

    //主视图的Presenter
    private volatile Login_View_Presenter login_view_presenter;
    //WIFI连接状态
    private boolean wifiConnect = false;
    //文件操作类
    private SharedPreferences preferences;

    //保存IP扫描的结果
    List<IpItem> ipList = new ArrayList<>();


    //加载条
    private SpinKitView serverSpin;

    private String loginTag = "login";

    //多个IP下，记录选中的IP
    private String selIP = "";
    private String selPort = "";

    AlertDialog serverIPPanel;

    //IP列表适配器
    PickerView.Adapter ipAdapter;

    //IP设置面板的输入框
    EditText settingIP;
    EditText settingPort;

    //刷新IP时，显示的等待视图
    private SpinKitView refreshSpin;

    //记录检查的IP数量
    private int ipCheckCount = 1;

    /**
     * Toolbar 标题
     *
     * @return 标题
     */
    @Override
    protected String getToolbarTitle() {
        return "登陆";
    }

    /**
     * 返回 Activity Layout
     *
     * @return Layout ID
     */
    @Override
    public int setLayoutView() {
        //返回 R.layout.lain_view
        return R.layout.lai_login_view;
    }

    //初始化，共有功能会抽取到 Lain_Base_Activity
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        //将IP数量清空
        LainNewApi.ipCount = 0;

        //设置状态栏透明
        makeStatusBarTransparent(this);

        //设置 Share 的上下文引用
        MySharePreference.getInstance().setContext(this);

        //初始化主视图的Presenter，传入的 this 是为了 Presenter 和 Activity通信
        login_view_presenter = new Login_View_Presenter(this);

        //记录app的启动状态，后续把数据输入数据库后，设置为 1,下次不用再对数据库操作了
        preferences = getSharedPreferences("APP_config", Context.MODE_PRIVATE);

        //第一次运行初始化配置
        if (preferences.getString("app_start", "").equals("")) {

            //获取后台运行权限
            AutoStartUtil.getInstance().selfStarting(this);
            //第一次使用APP，初始化数据库中的数据，设备列表，由子线程操作
            saveDeviceListFromFile();

            //保存已运行过的配置
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("app_start", "1");
            edit.apply();

        }

        //登陆按钮动画
        userLogin.setIndeterminateProgressMode(true);

        //全屏，不显示Toolbar
        lainViewToolbar.setVisibility(View.GONE);

        //如果进入到了登陆页面，就必须重新登陆，因此必须清空数据库
        LitePal.deleteAll(UserInfo.class);

        //每次进入登陆页面一定要重新进行获取IP
        try {

            String doMain = preferences.getString("doMain", "");
            if (doMain != null && !doMain.isEmpty()) {
                //如果有域名的情况下，则不进行获取服务器地址，直接使用远程服务器
                Log.d(TAG, "onCreate: " + doMain);
            } else {
                //搜索时，显示等待视图
                serverIPLoginSpin.setVisibility(View.VISIBLE);
                NetWorkSubDevice.getInstance().getWifiSubDevice(this, this);
            }

        } catch (IllegalAccessError e) {
            ToastManage.getInstance().toastShortShow("网络连接失败，无法获取服务器地址");
        }

    }

    /**
     * 加载局域网下所有的子设备，并获取后台的IP地址
     */
    public void loadingServerIp() {

        //加载自定义VIew Alert
        View serverIpPanelView = getLayoutInflater().inflate(R.layout.server_ip_panel, null);

        //绑定控件
        serverSpin = serverIpPanelView.findViewById(R.id.server_ip_spin);
        Button selectIP = serverIpPanelView.findViewById(R.id.select_ip_btn);
        //IP选择
        PickerView serverPicker = serverIpPanelView.findViewById(R.id.server_ip_pickerView);

        //保存选择的服务器IP
        serverPicker.setItems(ipList, (item) -> {

            //保存选择的IP
            selIP = item.getIp();
            selPort = item.getPort();

        });

        //多台服务器IP下，通过适配器来配置
        ipAdapter = new PickerView.Adapter() {
            @Override
            public int getItemCount() {
                return ipList.size();
            }

            @Override
            public PickerView.PickerItem getItem(int index) {
                return null;
            }

            @Override
            public String getText(int index) {
                return ipList.get(index).getText();
            }
        };

        //设置服务器IP列表适配器
        serverPicker.setAdapter(ipAdapter);

        //确定选择IP按钮
        selectIP.setOnClickListener((v) -> {

            //如果有多个服务器IP, 用户选择之后，更新IP
            if (selIP.isEmpty()) {
                LainNewApi.getInstance().IP = "http://" + ipList.get(0).getIp();
                LainNewApi.getInstance().PORT = ipList.get(0).getPort();
                LainNewApi.getInstance().IP_Text = ipList.get(0).getIp();

                //保存当前选中的IP和端口
                selIP = ipList.get(0).getIp();
                selPort = ipList.get(0).getPort();

            } else {
                //用户选择服务器后，将对应信息保存到API工具类中
                LainNewApi.getInstance().IP = "http://" + selIP;
                LainNewApi.getInstance().IP_Text = selIP;
                LainNewApi.getInstance().PORT = selPort;
            }

            //更新之后，还要保存在文件中，供下次使用，保存服务器数据到文件中
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("ip", "http://" + selIP);
            editor.putString("port", selPort);
            editor.apply();

            //提示用户选择
            ToastManage.getInstance().toastShortShow("选择了：" + selIP + ":" + selPort);

            //选择后，隐藏选择库
            serverIPPanel.hide();

            //刷新选择后，在编辑框中显示选择的IP信息
            if (loginTag.equals("success")) {
                //刷新IP成功
                settingIP.setText("http://" + selIP);
                settingPort.setText(selPort);
            } else {
                //进行登陆
                loginAction();
            }


        });

        //显示自定义的 View
        serverIPPanel = new AlertDialog.Builder(this)
                .setView(serverIpPanelView)
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * APP 首次运行
     * 将 my_config.properties 中的数据复制到数据库中
     */
    private void saveDeviceListFromFile() {

        String configName = "my_config.properties";

        //环境监控
        List<ConfigBean> environmentMonitoring = MyPropertiesUtil.getInstance().getProperties(Lai_Login_View.this, configName, "environmentMonitoring");
        //动力监控
        List<ConfigBean> powerMonitoring = MyPropertiesUtil.getInstance().getProperties(Lai_Login_View.this, configName, "powerMonitoring");
        //安防监控
        List<ConfigBean> securityMonitoring = MyPropertiesUtil.getInstance().getProperties(Lai_Login_View.this, configName, "securityMonitoring");
        //运维监控
        List<ConfigBean> operationMonitoring = MyPropertiesUtil.getInstance().getProperties(Lai_Login_View.this, configName, "operationMonitoring");
        //系统管理
        List<ConfigBean> systemManagement = MyPropertiesUtil.getInstance().getProperties(Lai_Login_View.this, configName, "systemManagement");
        //水质监控
        List<ConfigBean> waterMonitoring = MyPropertiesUtil.getInstance().getProperties(Lai_Login_View.this, configName, "waterMonitoring");

        //通过线程来操作
        new Thread(() -> {
            for (ConfigBean environment :
                    environmentMonitoring) {
                environment.setClassify("环境监控");
                environment.save();
            }

            for (ConfigBean power :
                    powerMonitoring) {
                power.setClassify("动力监控");
                power.save();
            }

            for (ConfigBean security :
                    securityMonitoring) {
                security.setClassify("安防监控");
                security.save();
            }

            for (ConfigBean operation :
                    operationMonitoring) {
                operation.setClassify("运维监控");
                operation.save();
            }

            for (ConfigBean system :
                    systemManagement) {
                system.setClassify("系统管理");
                system.save();
            }

            for (ConfigBean water :
                    waterMonitoring) {
                water.setClassify("水质监控");
                water.save();
            }
        }).start();

    }

    /**
     * 通过 ButterKnife 注解实现点击，点击登陆按钮进行登陆
     *
     * @param view 点击视图
     */
    @OnClick({R2.id.user_login, R2.id.login_setting_panel})
    public void onViewClicked(View view) {

        int id = view.getId();
        if (id == R.id.user_login) {

            //如果正常搜索服务器，提示用户等待
            if (serverIPLoginSpin.getVisibility() == View.VISIBLE) {
                Toast.makeText(this, "搜索服务器中，请稍等", Toast.LENGTH_SHORT).show();
                return;
            }

            //超过两台服务器需用用户选择后，再进行登陆
            if (!Objects.requireNonNull(preferences.getString("doMain", "")).isEmpty()) {
                //进行登陆
                loginAction();
            } else if (ipList.size() > 1 && !loginTag.equals("success")) {

                //让用户选择IP后再执行登陆
                loadingServerIp();

            } else
                //执行登陆
                loginAction();

        } else if (id == R.id.login_setting_panel) {
            //显示APP网络配置弹窗
            loginSettingPanelAction();
        }


    }

    /**
     * 默认是不需要输入IP和端口的，此处是提供需要输入 域名 / IP / 端口（默认：8080）
     */
    private void loginSettingPanelAction() {

        //加载自定义VIew Alert
        View loginSettingPanelView = getLayoutInflater().inflate(R.layout.login_settting_panl_alrt, null);
        //显示自定义的 View
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(loginSettingPanelView)
                .show();

        //域名
        EditText doMainName = loginSettingPanelView.findViewById(R.id.domain_name);
        //IP
        settingIP = loginSettingPanelView.findViewById(R.id.login_setting_ip);
        //端口
        settingPort = loginSettingPanelView.findViewById(R.id.login_setting_port);
        //保存
        Button settingRight = loginSettingPanelView.findViewById(R.id.login_setting_right);
        //取消
        Button settingCancel = loginSettingPanelView.findViewById(R.id.login_setting_cancel);
        //IP刷新
        Button serverIPRefresh = loginSettingPanelView.findViewById(R.id.refresh_server_ip);
        //刷新时等待视图
        refreshSpin = loginSettingPanelView.findViewById(R.id.login_setting_spin);

        //获取保存的网络配置，填写到面板上
        Map<String, String> networkInfo = MyWiFiManage.getInstance().getAPPNetwork(preferences);
        doMainName.setText(networkInfo.get("doMain"));
        settingIP.setText(networkInfo.get("ip"));
        settingPort.setText(networkInfo.get("port"));

        //保存的监听
        settingRight.setOnClickListener((v) -> {

            //保存输入的 IP / 域名 / 端口
            SharedPreferences preferences = getSharedPreferences("APP_config", Context.MODE_PRIVATE);

            //由 Present 由处理
            String ip = ActivityUtil.getInstance().TextViewToString(settingIP);
            String port = ActivityUtil.getInstance().TextViewToString(settingPort);
            String doMain = ActivityUtil.getInstance().TextViewToString(doMainName);
            // IP 域名 端口，文件类，逻辑操作由 Present 执行
            MyWiFiManage.getInstance().saveAPPNetwork(ip, doMain, port, preferences);

            //设置成功后，取消对话框
            alertDialog.cancel();

        });
        //取消输入
        settingCancel.setOnClickListener((v) -> {
            //关闭对话框
            alertDialog.cancel();
        });

        //刷新IP
        serverIPRefresh.setOnClickListener((v) -> {
            //刷新时，重置IP检查数量
            ipCheckCount = 1;
            //显示刷新视图
            refreshSpin.setVisibility(View.VISIBLE);
            //clear
            ipList.clear();
            loginTag = "refresh";
            NetWorkSubDevice.getInstance().getWifiSubDevice(this, this);
        });

    }

    /**
     * 点击登陆按钮时执行的操作
     */
    private void loginAction() {
        /*
        点击登陆按钮时，开启动画
        初始状态 [0]
        加载中 [1-99]
        加载成功 [100]
        加载失败 [-1]
        */

        //判断登陆信息是否输入完整
        if (userName.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
            ToastManage.getInstance().toastLongShow("账号 / 密码 ，请检查再重新登陆");
            return;
        }

        //设置登陆按钮的进度状态
        userLogin.setProgress(50);

        //开始登陆
        try {
            login_view_presenter.loginApp();
        } catch (IllegalArgumentException e) {
            userLogin.setProgress(-1);
            ToastManage.getInstance().toastLongShow("请输入正确的请求地址");
            //2秒后，恢复登陆初始状态
            new Handler().postDelayed(() -> {
                runOnUiThread(() -> postDelayedChange(0));
            }, 2000);
        }

    }

    //获取用户名
    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    //获取密码
    @Override
    public String getUserPassword() {
        return password.getText().toString();
    }

    //登陆结果
    @Override
    public void loginResult(boolean loginResult) {

        //判断登陆成功或者失败
        if (loginResult) {
            //登陆成功
            loginSuccess();
        } else {
            //登陆失败
            loginFailed();

        }

    }

    /**
     * 获取登陆按钮的实例，在登陆失败后，能马上做出改变按钮的状态
     *
     * @return
     */
    @Override
    public CircularProgressButton getLoginButton() {
        return userLogin;
    }

    /**
     * 登陆失败，网络出错
     *
     * @param errorMsg 出错信息
     */
    @Override
    public void loginFailure(String errorMsg) {

        runOnUiThread(() -> {

            // 由于搜索服务器的是并发请求，在APP中未对并发进行处理，所以在获取服务器的同时进行登陆的话，
            // Okhttp 的 CallBack 因为地址改变了，所以服务器获取地址是否成功的回调方法就到登陆失败的这里了

            ToastManage.getInstance().toastShortShow("请检查服务器是否正常");
            //登陆失败，请求后台接口失败
            userLogin.setProgress(-1);
            //2秒后，恢复登陆初始状态
            new Handler().postDelayed(() -> {
                runOnUiThread(() -> postDelayedChange(0));
            }, 2000);


        });

    }

    /**
     * 登陆成功
     */
    private void loginSuccess() {

        //设置按钮为 成功 状态
        userLogin.setProgress(100);
        //延时跳转
        new Handler().postDelayed(() -> {
            //登陆成功后，结束登陆页，并跳转到主页
            runOnUiThread(() -> {

                //跳转到主页
                myStartActivity(this, Lain_View.class);
                //结束登陆页面
                finish();

            });
        }, 1000);

    }

    /**
     * 登陆失败
     */
    private void loginFailed() {

        //登陆失败，密码 / 账号 不正确
        userLogin.setProgress(-1);
        ToastManage.getInstance().toastShortShow("账号 / 密码 不正确，请检查后重新尝试");
        //2秒后，恢复登陆初始状态
        new Handler().postDelayed(() -> {
            runOnUiThread(() -> postDelayedChange(0));
        }, 2000);

    }

    /**
     * 延时执行，切换登陆按钮的状态
     *
     * @param status 登陆按钮的状态
     */
    public void postDelayedChange(int status) {
        userLogin.setProgress(status);
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        if (Boolean.parseBoolean(responseStr)) {

            //只要有服务器存在，就将等待视图隐藏
            serverIPLoginSpin.setVisibility(View.GONE);

            //保存IP和端口
            String[] serverIP = requestUrl.split("/|//|:");
            IpItem ipBean = new IpItem(serverIP[3], serverIP[4]);
            //添加到IP列表中
            ipList.add(ipBean);

            //在这里更新IP和端口
            LainNewApi.getInstance().IP = "http://" + ipBean.getIp();
            LainNewApi.getInstance().PORT = ipBean.getPort();

            //如果面板中点击刷新，则进行服务器刷新
            if (loginTag.equals("refresh")) {

                loginTag = "success";
                //隐藏等待视图
                refreshSpin.setVisibility(View.GONE);
                //获取IP
                loadingServerIp();

            }
            //更新适配器
            ipAdapter.notifyDataSetChanged();
            //保存服务器数据到文件中
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("ip", "http://" + ipBean.getIp());
            editor.putString("port", ipBean.getPort());
            editor.apply();

        }

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

        //检查完后，没有获取到至少一个服务器，则执行该操作
        if (ipCheckCount == LainNewApi.ipCount) {


            if (loginTag.equals("refresh")) {

                //刷新服务器操作
                refreshServerFailed();

            } else {

                //自动获取服务器操作
                foundServerFailed();

            }

            return;
        }

        //如果未检查完，则记录检查的数量
        ipCheckCount++;

    }

    /**
     * 刷新服务器，未获取到可用的服务器时执行
     */
    private void refreshServerFailed() {
        refreshSpin.setVisibility(View.GONE);

        //提示用户检查服务器失败
        AlertDialog notServerDialog = new AlertDialog.Builder(this)
                .setTitle("获取服务器失败")
                .setMessage("未获取到服务器，请手动设置服务器IP")
                .setNegativeButton("关闭", ((dialog, which) -> {}))
                .show();
    }

    /**
     * 一进入登陆页面，自动获取服务器失败时，执行
     */
    private void foundServerFailed() {
        serverIPLoginSpin.setVisibility(View.GONE);

        //提示用户检查服务器失败
        AlertDialog notServerDialog = new AlertDialog.Builder(this)
                .setTitle("获取服务器失败")
                .setMessage("未获取到服务器，请手动设置服务器IP")
                .setPositiveButton("设置", ((dialog, which) -> {
                    //显示APP网络配置弹窗
                    loginSettingPanelAction();
                }))
                .setNegativeButton("取消", ((dialog, which) -> {}))
                .show();
    }

    /**
     * 将状态栏设置透明
     *
     * @param activity 需要设置的Activity
     */
    public void makeStatusBarTransparent(Activity activity) {

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
