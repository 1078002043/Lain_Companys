package view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;
import com.example.base.R2;
import com.example.compute_room.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingMenuLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import action.SaveInterface;
import base.Lain_Base_Activity;
import action.DeviceGroupBean;
import bean.LoginUserInfoBean;
import bg_run.LainService;
import butterknife.BindView;
import fragment.MenuListFragment;
import fragment.SmartDevice;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import present.Lain_View_Presenter;
import util.LainNewApi;
import util.WebSocketUtil;
import v_interface.I_Lain_View;

/**
 * 莱安智能化设备APP 主页面
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/26 16 : 58
 */

public class Lain_View extends Lain_Base_Activity implements I_Lain_View, OkHttpCallBack {
    //主页 Presenter
    private Lain_View_Presenter presenter;
    //底部导航栏
    @BindView(R2.id.main_bottom_nav)
    BottomNavigationViewEx mainBottomNav;
    //主页 Fragment 视图
    @BindView(R2.id.main_frame)
    FrameLayout mainFrame;
    //添加设备的 MENU
    private MenuItem menu;
    //保存 主页 Fragment 的视图
    private ArrayList<Fragment> bottomFragments = new ArrayList<>();
    //抽屉布局中的 menu
    @BindView(R2.id.menulayout)
    FlowingMenuLayout menuLayout;
    //抽屉布局
    @BindView(R2.id.drawerlayout)
    FlowingDrawer flowingDrawer;

    //首页
    private SmartDevice smartDevice;
    private MenuListFragment fragmentById;
    private MenuListFragment mMenuFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化 Presenter
        presenter = new Lain_View_Presenter(this);
        //隐藏标题，显示导航按钮
        if (getSupportActionBar() != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);
            lainViewToolbar.setNavigationIcon(R.drawable.navigation_icon);
        }

        try {

            makeStatusBarTransparent(this);

            //通过反射来获取导航栏的图标，来实现 新手向导，目前暂时隐藏
            Class<?> clas = lainViewToolbar.getClass();
            Field field = clas.getDeclaredField("mNavButtonView");
            ImageButton imageButton = null;
            //设置可以访问 private 级别的字段
            field.setAccessible(true);
            //获取 导航按钮 ImageButton
            imageButton = (ImageButton) field.get(lainViewToolbar);
            //新手指导
            NewbieGuide.with(this)
                    //标题
                    .setLabel("grid_view_guide")
                    //是否一起显示
                    .alwaysShow(true)
                    //设置显示的 View
                    .addGuidePage(GuidePage.newInstance()
                            //高亮部分，imageButton ：高亮的View，高亮的样式
                            .addHighLight(imageButton, HighLight.Shape.RECTANGLE)
                            //是否定点任务地方都取消
                            .setEverywhereCancelable(false)
                            //设置显示的 layout 资源
                            .setLayoutRes(R.layout.view_guide, R.id.iv));
            //显示
            // .show();

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //初始化主页 Fragment
        initFragment();
        //默认执行第一个Fragment
        replaceFragment(bottomFragments.get(0));

        //2秒钟后再执行，为了解决跳转黑屏的问题
        new Handler().postDelayed(() -> {

            runOnUiThread(() -> {
                try {

                    //初始化底部导航栏
                    initBottomNav();
                    //初始化Menu
                    initMenu();
                    //先获取账号信息，再登陆，登陆之后 再检查更新, 这里会报错
//                    UserInfo userInfo = LitePal.findFirst(UserInfo.class);
//                    presenter.autoLogin(userInfo.getUsername(), userInfo.getPassword());

                    //进入APP，2秒后，获取所有的组
                    if (SaveInterface.getInstance().getGroupBeanList() == null) {
                        String findGroupUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.findGroup;
                        OkHttpUtil.getInstance().sendGetOkHttp("findGroup", findGroupUrl, this);
                    }

                    //如果已连接则提示
                    if (WebSocketUtil.getInstance().webSocketClient != null && WebSocketUtil.getInstance().webSocketClient.isOpen()) {

                        Toast.makeText(this, "WebSocket 已连接", Toast.LENGTH_SHORT).show();


                    } else {
//                        WebSocketUser.getInstance().connectWebSocket("192.168.1.240", "8080/software/websocket/100", this, null);
                        //登陆成功，成功连接WebSocket后，再开启后台报警信息接收服务
                        Intent intent = new Intent(this, LainService.class);
                        bindService(intent, WebSocketUtil.getInstance().serviceConnection, 0);
                        startService(intent);

                    }

                    //获取用户的信息
                    presenter.getUserMessage();

                } catch (NullPointerException e) {
                    Log.d(Lain_Base_Activity.TAG, "未登陆，无法存储用户的信息");
                }
            });

        }, 2000);

        //成功登陆后，进入主页再进行请求需要的权限
        //APP启动时获取权限
        checkPermission(PERMISSION);


    }

    /**
     * 初始化Menu
     */
    private void initMenu() {
        //点击模式
        flowingDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        //点击Toolbar上的导航按钮打开抽屉布局
        lainViewToolbar.setNavigationOnClickListener((v) -> {
            flowingDrawer.toggleMenu();
        });

        //抽屉布局的 MENU，以Fragment展示
        FragmentManager fm = getSupportFragmentManager();
        fragmentById = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        mMenuFragment = fragmentById;
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            //替换Fragment
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

    /**
     * Toolbar 标题
     *
     * @return Toolbar 标题
     */
    @Override
    protected String getToolbarTitle() {
        return "莱安智能";
    }

    /**
     * 创建主页Toolbar上的 MENU
     *
     * @param menu MENU
     * @return 是否创建成功
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //添加设备的MENU
        inflater.inflate(R.menu.toolbar_tool, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //报警详情的页面
        this.menu = menu.findItem(R.id.information_read);
        return true;
    }

    /**
     * MENU 上的点击事件
     *
     * @param item MenuItem
     * @return 点击事件是否成功执行
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击跳转到报警详情的页面
//        Lain_Base_Activity.myStartActivity(this, AlertInfo_IsRead.class);
        return true;
    }

    /**
     * 初始化 Fragment
     * SmartDevice：主页，展示设备和设备的信息，
     */
    private void initFragment() {

        //主页
        bottomFragments.add(smartDevice = new SmartDevice());
    }

    /**
     * 初始化底部导航栏
     */
    @SuppressLint({"RestrictedApi", "WrongConstant"})
    private void initBottomNav() {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mainBottomNav.getChildAt(0);
        if (menuView != null) {
            menuView.setLabelVisibilityMode(1);
            menuView.updateMenuView();
        }

        //底部导航栏的选择监听
        mainBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            //初始选择的位置
            private int previousPos = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int position = -1;
                int itemId = menuItem.getItemId();

                return true;

            }
        });

    }

    /**
     * 设置主页的 Layout ID
     *
     * @return Layout IDf
     */
    @Override
    public int setLayoutView() {
        return R.layout.lain_view;
    }

    /**
     * 获取主页的 Context
     *
     * @return LainView 的引用
     */
    @Override
    public Context getMContext() {
        return this;
    }

    /**
     * 获取用户相关信息
     * @param loginUserInfoBean
     */
    @Override
    public void getUserLoginInfo(LoginUserInfoBean loginUserInfoBean) {

        mMenuFragment.showUserInfo(loginUserInfoBean);

    }


    /**
     * 底部导航栏切换，一共五个
     *
     * @param fragment 切换的 Fragment
     */
    public void replaceFragment(Fragment fragment) {
        //获取Fragment管理器
        FragmentManager manager = getSupportFragmentManager();
        //开启Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        //替换 Fragment R.id.main_frame = 被替换的控件  fragment = 需要替换的 Fragment
        transaction.replace(R.id.main_frame, fragment);
        //提交事务才能更改成功
        transaction.commit();

    }

    /**
     * 重写这个方法后，menu才会显示图标与标题
     *
     * @param featureId id
     * @param menu      menu
     * @return 创建是否成功
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    //通过反射来实现
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        //视频获取焦点
//        smartDevice.noActivity.videoPlayer.onVideoResume();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onPause() {
        super.onPause();
        //视频失败焦点
//        smartDevice.noActivity.videoPlayer.onVideoPause();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDestroy() {

        //关闭WebSocket
//        try {
////            boolean close = WebSocketUser.getInstance().getWebSocket().close(1000, "关闭");
////            stopService(new Intent(this, LainService.class));
//            Log.d(TAG, "onDestroy: " + close);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        super.onDestroy();
    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        try {
            if (requestTag.equals("findGroup")) {
                //请求所有组的数据成功后，回调到该方法
                String json = "[" + responseStr + "]";

                List<DeviceGroupBean> object = MyGson.getInstance().fromJson(json, new TypeToken<List<DeviceGroupBean>>() {
                }.getType());

                SaveInterface.getInstance().setGroupBeanList(object);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }

    public void connectWebSocket() {


    }



}
