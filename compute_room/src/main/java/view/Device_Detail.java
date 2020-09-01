package view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.compute_room.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.yw.game.floatmenu.FloatMenuView;

import base.BaseRecyclerViewAdapter;
import base.Base_Devices_Detail;
import base.Lain_Base_Activity;
import computer_room.fragment.DeviceAdministrator;
import computer_room.fragment.New_DeviceManage;
import control.MainControl;
import fragment.VideoMainPage;
import util.FloatButtonUtil;
import util.LainNewApi;
import v_interface.I_Device_Detail;

/**
 * 所有设备的详细页面
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/30 14 : 42
 */

public class Device_Detail extends Lain_Base_Activity implements I_Device_Detail, View.OnClickListener, BaseRecyclerViewAdapter.OnItemClickListener, FloatMenuView.OnMenuClickListener {

    /**
     * 设备的名称，用来设置Toolbar的标题
     */
    public String device_name;
    //设置Menu
    public MenuItem menu;

    //头部工具栏的引用
    private RelativeLayout headLayout;

    //Fragment的基类，用于切换 ViewPager
    private Base_Devices_Detail base_devices_detail;

    //历史数据
    private LinearLayout historyItem;
    //设备管理
    private LinearLayout deviceItem;
    //实时数据
    private LinearLayout realItem;
    //报警数据
    private LinearLayout alertItem;

    //设备管理的引用，用来显示 BottomSheet
    private New_DeviceManage deviceManage;

    //视频监控的Fragment引用
    private VideoMainPage videoMainPage;

    //保存设备的名称
    private String deviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化定位漏水头部导航栏控件
        initLocating();

        //透明状态栏
        makeStatusBarTransparent(this);

        //初始化 顶部工具栏 点击事件  历史数据
        historyItem = findViewById(R.id.history);
        historyItem.setOnClickListener(this);
        //设备管理
        deviceItem = findViewById(R.id.locating_management);
        deviceItem.setOnClickListener(this);

        //实时数据
        realItem = findViewById(R.id.locating_realtime);
        realItem.setOnClickListener(this);
        //报警数据
        alertItem = findViewById(R.id.alarm_data);
        alertItem.setOnClickListener(this);

        //找到 head 的引用
        headLayout = findViewById(R.id.detail_head);
        headLayout.setVisibility(View.VISIBLE);
        //保存设备的标识，名称
        String deviceTag = getIntent().getStringExtra("device_tag");
        deviceName = getIntent().getStringExtra("device_name");

        //视频监控隐藏头部工具栏
        if (deviceName.equals("视频监控"))
            headLayout.setVisibility(View.GONE);

        //以下是某些特殊的设备，需要保存Fragment实例，后面需要调用
        if (deviceName.equals("设备管理"))
            replaceFragment(deviceManage = (New_DeviceManage) MainControl.getInstance().deviceControl(deviceTag, deviceName));
        else if (deviceName.equals("视频监控")) {
            replaceFragment(videoMainPage = (VideoMainPage) MainControl.getInstance().deviceControl(deviceTag, deviceName));
        } else
            replaceFragment(MainControl.getInstance().deviceControl(deviceTag, deviceName));

        //如果实例不为空，将顶部工具栏的View实例传递给 Base_Devices_Detail
        if (base_devices_detail != null)
            base_devices_detail.setTopNav(headLayout, realItem, alertItem, historyItem, deviceItem);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //每次获取焦点后，处理浮动按钮的显示
        if (LainNewApi.INTENT_TAG.equals("设备管理")) {
            FloatButtonUtil.getInstance().deviceManageFloat(Device_Detail.this, Device_Detail.this, Device_Detail.this);
        } else if (LainNewApi.INTENT_TAG.equals("用户管理")) {
            FloatButtonUtil.getInstance().UserManageFloat(Device_Detail.this, Device_Detail.this, Device_Detail.this);
        } else {
            FloatButtonUtil.getInstance().initFloat(Device_Detail.this, Device_Detail.this, Device_Detail.this, historyItem, deviceItem, alertItem);
        }

    }

    /**
     * 初始化 定位漏水 顶部导航栏
     */
    private void initLocating() {

        //实时数据
        LinearLayout locatingRealtime = findViewById(R.id.locating_realtime);
        //报警数据
        LinearLayout alarmData = findViewById(R.id.alarm_data);
        //历史数据
        LinearLayout history = findViewById(R.id.history);
        //设备管理
        LinearLayout locatingManagement = findViewById(R.id.locating_management);
        //实现这些控件的点击事件
        locatingRealtime.setOnClickListener(this);
        alarmData.setOnClickListener(this);
        history.setOnClickListener(this);
        locatingManagement.setOnClickListener(this);

    }

    /**
     * 根据传入的值来设置Toolbar标题，必须在这里对标题进行初始化，
     * 因为封账的了基类Activity中，在子类中的Activity获取Intent的值之前，
     * 该方法就执行了，在onCreate()中在获取的话就不会设置Toolbar Title
     */
    @Override
    protected String getToolbarTitle() {
        //获取传过来的 Toolbar 数据
        device_name = LainNewApi.INTENT_TAG;
        return device_name;
    }

    /**
     * 设置设备详情的Layout ID
     *
     * @return Layout ID
     */
    @Override
    public int setLayoutView() {
        return R.layout.device_detail;
    }

    /**
     * 底部导航栏切换，目前只有一个
     *
     * @param fragment 切换的 Fragment
     */
    public void replaceFragment(Fragment fragment) {
        //获取Fragment管理器
        FragmentManager manager = getSupportFragmentManager();

        try {
            //保存Fragment
            base_devices_detail = (Base_Devices_Detail) fragment;
        } catch (ClassCastException e) {
            Log.d(TAG, "replaceFragment: 类型转换异常");
        }

        //开启Fragment事务
        FragmentTransaction transaction = manager.beginTransaction();
        //替换 Fragment R.id.main_frame = 被替换的控件  fragment = 需要替换的 Fragment
        transaction.replace(R.id.device_detail_frame, fragment);
        //提交事务才能更改成功
        transaction.commit();

    }


    /**
     * 点击设置ViewPager的位置
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        //检查是否为空
        if (base_devices_detail == null)
            return;

        int id = v.getId();//实时数据
        if (id == R.id.locating_realtime) {
            base_devices_detail.setCurrentViewPager(0);
            //报警数据
        } else if (id == R.id.alarm_data) {
            base_devices_detail.setCurrentViewPager(1);
            //历史曲线
        } else if (id == R.id.history) {
            base_devices_detail.setCurrentViewPager(2);
            //设备管理
        } else if (id == R.id.locating_management) {
            base_devices_detail.setCurrentViewPager(3);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        //添加视频监控中 用户账号 Menu
        if (LainNewApi.INTENT_TAG.equals("视频监控")) {
            menuInflater.inflate(R.menu.video_account, menu);
        }

        return true;
    }

    /**
     * 每一个ITEM ID 的判断必须返回 boolean 值，不然会报ID错误
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //主页的 menu
        if (item.getItemId() == android.R.id.home)
            return super.onOptionsItemSelected(item);

        //视频监控的 menu
        if (item.getItemId() == R.id.video_account) {
            videoMainPage.intoVideoUserManage();
            return true;
        }

        return true;

    }

    @Override
    public void onItemClickListener(View v, int position) {

    }

    @Override
    public void onItemClick(int position, String title) {

        //设备详情的标识，默认添加设备标识
        if (deviceManage != null)
            deviceManage.upOrAdd = "add";

        switch (title) {
            case "曲线":

                View view = new View(this);
                view.setId(R.id.new_history_name);

                base_devices_detail.intoHistoryDetail = true;
                base_devices_detail.onItemClickListener(view, base_devices_detail.historyBottomPos);

                break;
            case "报警":
                base_devices_detail.viewPager.setCurrentItem(1);
                base_devices_detail.alertBottomSheetBehavior.show();
                break;
            case "历史":
                Log.d("ljlrtrt", "onItemClick: in to list");
                //如果点击历史进行查询，则不自动进行查询后进入曲线图页面
                base_devices_detail.intoHistoryDetail = false;
                base_devices_detail.viewPager.setCurrentItem(2);

                base_devices_detail.historyBottomSheetBehavior.show();
                break;
            case "添加":
                base_devices_detail.viewPager.setCurrentItem(3);
                base_devices_detail.addDeviceMethod();
                break;
            case "IP":
                deviceManage.viewPager.setCurrentItem(0);

                deviceManage.addNewDevice.setText("添加");
                deviceManage.ipSettingDevice.setVisibility(View.VISIBLE);
                deviceManage.ipSettingDeviceName.setVisibility(View.GONE);
                //显示编辑框
                deviceManage.ipSettingSheet.show();

                break;
            case "8060":

                deviceManage.viewPager.setCurrentItem(1);

                deviceManage.device8060Btn.setText("添加");
                deviceManage.device8060Ip.setVisibility(View.VISIBLE);
                deviceManage.device8060Device.setVisibility(View.VISIBLE);

                deviceManage.device8060Address.setEnabled(true);
                deviceManage.device8060Address.setTextColor(Color.BLACK);
                deviceManage.device8060Gallery.setEnabled(true);
                deviceManage.device8060Gallery.setTextColor(Color.BLACK);

                //显示编辑框
                deviceManage.device8060Sheet.show();

                break;
            case "8052":

                deviceManage.viewPager.setCurrentItem(2);

                deviceManage.device8052Btn.setText("添加");
                deviceManage.device8052Ip.setVisibility(View.VISIBLE);
                deviceManage.device8052Device.setVisibility(View.VISIBLE);

                deviceManage.device8052Address.setEnabled(true);
                deviceManage.device8052Address.setTextColor(Color.BLACK);
                deviceManage.device8052Gallery.setEnabled(true);
                deviceManage.device8052Gallery.setTextColor(Color.BLACK);

                //显示编辑框
                deviceManage.device8052Sheet.show();

                break;
            case "资产管理":

                deviceManage.viewPager.setCurrentItem(3);

                deviceManage.deviceAssetsBtn.setText("添加");
                deviceManage.deviceAssetsIp.setVisibility(View.VISIBLE);

                deviceManage.deviceAssetsAddress.setEnabled(true);
                deviceManage.deviceAssetsAddress.setTextColor(Color.BLACK);
                deviceManage.deviceAssetsNum.setEnabled(true);
                deviceManage.deviceAssetsNum.setTextColor(Color.BLACK);

                //显示编辑框
                deviceManage.deviceAssetsSheet.show();
                break;
            case "用户":
                base_devices_detail.viewPager.setCurrentItem(0);
                base_devices_detail.showUserManagePanel("用户");
                break;
            case "角色":
                base_devices_detail.viewPager.setCurrentItem(1);
                base_devices_detail.showUserManagePanel("角色");
                break;
            case "权限":
                base_devices_detail.viewPager.setCurrentItem(2);
                base_devices_detail.showUserManagePanel("权限");
                break;
        }


    }

    @Override
    public void dismiss() {

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: hide");
        FloatButtonUtil.getInstance().hideFloat();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onPause: onDestroy");
        FloatButtonUtil.getInstance().destroyFloat();
        super.onDestroy();

    }

    /**
     * 将状态栏设置透明
     *
     * @param activity 需要设置的Activity
     */
    public void makeStatusBarTransparent(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

    }

}
