package base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.base.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import action.DeviceAction;
import action.DeviceIPBean;
import action.SaveInterface;
import adapter.Alert8052Adapter;
import adapter.AlertAdapter;
import adapter.AlertDeviceAdapter;
import bean.Alert8052Bean;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.DeviceDetailTopBean;
import bg_run.LainService;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.DateUtil;
import util.DeviceIPGetUrl;
import util.DeviceIpGetUtil;
import util.ExcelUtil;
import util.GetViewPager_View;
import util.LainNewApi;
import util.ScaleTransitionPagerTitleView;
import util.ToastManage;
import util.WebSocketMessageReceive;
import util.WebSocketUtil;
import view_interface.I_Base_Devices_Detail;

/**
 * 所有设备详细内容的 基类，设备详细的信息类都要继承本类
 * 默认存在 报警数据，历史数据，设备管理，曲线图，只有第一个 ViewPager 才会动态改变
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/6 20 : 54
 */
public abstract class Base_Devices_Detail extends Lain_Base_Fragment implements I_Base_Devices_Detail,
        Runnable, OkHttpCallBack, BaseRecyclerViewAdapter.OnItemLongClickListener, DeviceAction, View.OnTouchListener,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, BaseRecyclerViewAdapter.OnItemClickListener, WebSocketMessageReceive, UltimateRecyclerView.OnLoadMoreListener {

    //异步，每个设备都使用这个 Handler 来刷新UI和实时获取
    public Handler infoHandler = new Handler();

    //添加新设备列表
    public UltimateRecyclerView newDeviceRecycler;

    //设备详情中的 顶部导航栏
    public MagicIndicator magicIndicator2;
    //设备详情中的 ViewPager
    public ViewPager viewPager;
    //设备详情基类 Presenter
    public Base_Devices_Detail_Presenter presenter;
    //保存 ViewPager 的 View
    protected List<View> viewList;
    //顶部导航栏配置
    public CommonNavigator magicNavigator;

    //添加设备按钮
    public FloatingActionButton device_addAccent;

    //报警数据列表
    public UltimateRecyclerView alertRecycler;
    //报警数据适配器
    public AlertAdapter alertAdapter;
    //8052报警数据适配器
    public Alert8052Adapter alert8052Adapter;
    //报警数据
    public List<AlertBeans> alertBeans = new ArrayList<>();
    //报警数据
    public List<Alert8052Bean> alert8052Beans = new ArrayList<>();

    //报警数据面板的设备列表
    public UltimateRecyclerView alertDeviceRecycler;
    //历史数据面板的设备列表
    protected UltimateRecyclerView historyDeviceRecycler;
    //年选择器
    private TextView year_select;
    //开始时间（月—日）
    private TextView start_data;
    //结束时间（月-日）
    private TextView end_data;
    //用中文表示日期，也可以改为ENGLISH
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);

    //日期选择器
    public DatePickerDialog datePickerDialog = null;
    //时间选择器
    public TimePickerDialog timePickerDialog = null;

    //日期选择器
    public DatePickerDialog dateHistoryDialog = null;
    //时间选择器
    public TimePickerDialog timeHistoryDialog = null;

    //报警面板的开始时间
    public EditText startPanelTime;
    //报警面板的结束时间
    public EditText endPanelTime;


    //历史面板的开始时间
    public EditText startHistoryTime;
    //历史面板的结束时间
    public EditText endHistoryTime;

    //报警面板的设备
    public List<AlertDeviceBean> alertDeviceBeanList = new ArrayList<>();
    //报警面板设备的适配器
    public AlertDeviceAdapter alertPanelDevice;
    //报警面板设备的适配器
    public AlertDeviceAdapter historyPanelDevice;

    //报警编辑设置面板
    public BottomSheetDialog alertBottomSheetBehavior;
    //历史查询设置面板
    public BottomSheetDialog historyBottomSheetBehavior;
    //保存历史面板中ITEM点击位置
    public int historyBottomPos = 0;

    //历史曲线底部的 View
    private View vHistory;
    //报警页面底部的 View
    private View vAlert;

    //是否进入曲线图页面的标识 true 进入  false 不进入
    public boolean intoHistoryDetail = false;

    //下面是顶部导航栏，控制显示或隐藏
    //面板
    public RelativeLayout head_panel;
    //历史数据
    private LinearLayout historyItem;
    //设备管理
    private LinearLayout deviceItem;
    //实时数据
    public LinearLayout realItem;
    //报警数据
    public LinearLayout alertItem;

    public UltimateRecyclerView newHistoryList;

    //日志查询的参数
    public int ehmID = 0;
    //默认都是当日的 开始时间 - 结束时间
    public String startTime = DateUtil.getInstance().getCurrentDate();
    public String endTime = DateUtil.getInstance().getCurrentDate();

    //只在进入页面时，刷新一次设备列表
    public boolean refreshDeviceList = true;

    //保存获取到的 IP 地址
    public List<DeviceIPBean> deviceIPBeanList = new ArrayList<>();

    //删除URL
    public String deleteUrl = "";
    //修改URL
    public String changeUrl = "";

    //记录是跳转还滑动  true 滑动 false 跳转
    public boolean viewPageMode = true;

    //保存设备管理的位置
    public int mangePageNum;

    // WebSocket 数据回调
    private LainService lainService;
    //收到消息后，回调消息给调用者
    private WebSocketMessageReceive receive;

    //电量仪电费计算面板
    public CardView cardViewCalc;
    //电量仪电费计算结果面板
    public LinearLayout electricityCalcResult;
    public EditText electricityCnitPrice;
    private Button electricityCalcBtn;
    public TextView electricityCalcValue;
    public TextView electricityTotalValue;

    //如果已经加载过当前页的数据，切换ViewPager时不同重复加载
    public boolean alertShow = false;
    public boolean historyShow = false;
    public boolean manageShow = false;
    //请求实时数据的第一个设备的ID
    public int ehmIDFirst = 0;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        //设置导航
        magicNavigator = new CommonNavigator(getActivity());
        //设置顶部导航栏中 Tab 为平分模式
        magicNavigator.setAdjustMode(true);
        //初始化 设备详情基类的 Presenter
        presenter = new Base_Devices_Detail_Presenter(this);

        //获取 ViewPager 中的 View
        viewList = GetViewPager_View.getInstance().getViewViewPager(getViewPagerView(), getLayoutInflater());

        //初始化设备详情中的 ViewPager
        Base_DevicesDetail_ViewPager pager = new Base_DevicesDetail_ViewPager(viewList);
        //设置设备详情子ViewPager
        viewPager.setAdapter(pager);
        //设置保存的页数，如果ViewPager比较简单，并且 3-4个页面左右，使用该方法更有效率
        viewPager.setOffscreenPageLimit(4);
        //设置ViewPager的监听
        setViewPagerListener();

        //顶部导航栏
        if (magicIndicator2 != null)
            initMagicIndicator();

        //设置设备管理中的 添加/删除 的回调引用
        SaveInterface.getInstance().setDeviceAction(this);

        getDeviceIP(DeviceIPGetUrl.getInstance().deviceIpUrl(LainNewApi.INTENT_TAG));

    }

    /**
     * 如果WebSocket连接成功后会进行更新数据
     *
     * @param deviceID 设备ID
     */
    public void connectSocket(String deviceID, WebSocketMessageReceive messageReceive) {

        try {
            //每次进入设备详情都会进行WebSocket通信
            if (WebSocketUtil.getInstance().getWebSocketClient().isOpen()) {

                //获取数据前，必须设置消息回调接口
                WebSocketUtil.getInstance().getWebSocketClient().send("{\"deviceType\":\"" + deviceID + "\"}");
                //设置回调地址
                WebSocketUtil.getInstance().setWebSocketCallBack(messageReceive);

            } else {

                //提示未连接
                Toast.makeText(getActivity(), "未连接WebSocket", Toast.LENGTH_SHORT).show();

            }
        } catch (NullPointerException e) {
            Log.d(getClass().getName(), "connectSocket: 获取WebSocket状态失败");
        }

    }

    //设置消息回调接口
    public void setMessageCallback(WebSocketMessageReceive receiveMessage) {

        if (receiveMessage == null)
            throw new NullPointerException("消息回调接口 WebSocketMessageReceive is null");

        this.receive = receiveMessage;
    }

    /**
     * 初始历史曲线中设置面板，由子类调用
     *
     * @param pageNum 根据位置进行初始化
     */
    public void initHistoryFloatingButton(int pageNum) {

        //历史曲线页的设置面板
        FloatingActionButton historySetPanel = viewList.get(pageNum).findViewById(R.id.setting_btn2);
        //底部面板，历史查询面板
        vHistory = LayoutInflater.from(getActivity()).inflate(R.layout.history_bottom_sheet, null, false);
        historyBottomSheetBehavior = new BottomSheetDialog(getActivity());
        historyBottomSheetBehavior.setContentView(vHistory);
        //查询相关信息的设备列表
        historyDeviceRecycler = vHistory.findViewById(R.id.temp_history_bottom);

        //点击浮动按钮完全展开
        historySetPanel.setOnClickListener((v) -> {
            historyBottomSheetBehavior.show();
        });
        //初始化曲线图的控件
        //温度曲线图
        newHistoryList = viewList.get(pageNum).findViewById(R.id.new_history_list);

        //历史数据底部面板
        initHistoryPanel();
        //设置设备管理中添加设备输入框信息
        setDeviceAddEdit();

        //初始化历史数据的导出按钮
        Button exportHistoryBtn = vHistory.findViewById(R.id.export_history_data);
        exportHistoryBtn.setOnClickListener((v) -> {
            exportHistoryMessage();
        });

        //只在电量仪设备上才加载
        if (LainNewApi.INTENT_TAG.equals("电量仪")) {
            //以下按钮只会在电量仪设备中才会使用到
            //获取电量仪中计算电费的View
            cardViewCalc = viewList.get(pageNum).findViewById(R.id.electricity_calc);
            //电量仪的计算面板
            electricityCalcResult = cardViewCalc.findViewById(R.id.electricity_calc_result);
            //输入电量仪的计算单价
            electricityCnitPrice = cardViewCalc.findViewById(R.id.electricity_calc_edit);
            //计算按钮
            electricityCalcBtn = cardViewCalc.findViewById(R.id.electricity_calc_btn);
            electricityCalcBtn.setOnClickListener(v -> {
                //获取输入的 单价，并传入方法，由子类进行重写实现具体操作
                electricityUnitPriceMethod(electricityCnitPrice.getText().toString());
            });
            //显示计算的结果
            electricityCalcValue = cardViewCalc.findViewById(R.id.electricity_calc_result_value);
            electricityTotalValue = cardViewCalc.findViewById(R.id.electricity_total_value);
        }



    }

    /**
     * 计算电量仪的电费，由电量仪设备重写
     * @param uniPrice 单价
     */
    public void electricityUnitPriceMethod(String uniPrice) {

    }

    /**
     * 点击导出历史数据按钮时执行，具体操作由子类实现
     */
    public void exportHistoryMessage() {
        ToastManage.getInstance().toastLongShow("导出历史数据");
    }

    @Override
    public void onItemClickListener(View v, int position) {
        //由子类进行重写
    }

    /**
     * 初始化8052的报警列表
     *
     * @param pageNum 8052设备的页数
     */
    public void init8052Alert(int pageNum) {
        //报警页的设置面板
        FloatingActionButton alertSetPanel = viewList.get(pageNum).findViewById(R.id.setting_btn);

        //报警查询面板
        vAlert = LayoutInflater.from(getActivity()).inflate(R.layout.alert_bottom_sheet, null, false);
        alertBottomSheetBehavior = new BottomSheetDialog(getActivity());
        alertBottomSheetBehavior.setContentView(vAlert);

        //绑定报警列表
        alertRecycler = viewList.get(pageNum).findViewById(R.id.alert_8052_recycler);
        //报警的设置面板
        alertDeviceRecycler = vAlert.findViewById(R.id.temp_alert_bottom);
        //显示面板
        alertSetPanel.setOnClickListener((v) -> {
            //完全展开的状态
            alertBottomSheetBehavior.show();
        });

        //初始化报警适配器
        alert8052Adapter = new Alert8052Adapter(getActivity(), alert8052Beans, R.layout.device_8052_alert_temp);

        //配置加载更多
        moreConfig(alert8052Adapter, alertRecycler);

        alertRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        alertRecycler.setAdapter(alert8052Adapter);
        //初始化报警的面板
        initAlertPanel();
    }

    /**
     * 配置列表的加载更多功能
     * @param adapter 适配器
     * @param recyclerView 列表控件
     */
    private void moreConfig(BaseRecyclerViewAdapter adapter, UltimateRecyclerView recyclerView) {

        Log.d("kllert", "moreConfig: set more");

        //开启加载更多
        adapter.enableLoadMore(true);
        //设置加载更多的等待图标
        recyclerView.setLoadMoreView(R.layout.custom_bottom_progressbar);
        //加载更多的接口调回
        recyclerView.setOnLoadMoreListener(this);

        //关闭加载更多功能
        //recyclerView.disableLoadmore();
    }

    /**
     * 初始化报警面板，由子类调用，基本不做操作
     *
     * @param pageNum 页数
     */
    public void initAlert(int pageNum) {

        //报警页的设置面板
        FloatingActionButton alertSetPanel = viewList.get(pageNum).findViewById(R.id.setting_btn);

        //报警查询面板
        vAlert = LayoutInflater.from(getActivity()).inflate(R.layout.alert_bottom_sheet, null, false);
        alertBottomSheetBehavior = new BottomSheetDialog(getActivity());
        alertBottomSheetBehavior.setContentView(vAlert);

        //绑定报警列表
        alertRecycler = viewList.get(pageNum).findViewById(R.id.alert_recycler);
        //报警的设置面板
        alertDeviceRecycler = vAlert.findViewById(R.id.temp_alert_bottom);

        alertSetPanel.setOnClickListener((v) -> {
            //完全展开的状态
            alertBottomSheetBehavior.show();
        });

        //初始化报警适配器
        alertAdapter = new AlertAdapter(getActivity(), alertBeans, R.layout.alert_temp);
        //配置加载更多
        moreConfig(alertAdapter, alertRecycler);
        alertRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        alertRecycler.setAdapter(alertAdapter);


        //初始化报警的面板
        initAlertPanel();
    }

    /**
     * 初始化设备管理的控件，基本不做操作，由子类来实现
     *
     * @param pageNum    页数
     * @param deviceName 设备名称
     * @param <T>        没有用
     */
    public <T> void initDeviceManage(int pageNum, String deviceName) {

        //保存ViewPager的位置
        mangePageNum = pageNum;

        //设备管理中 的 设备列表
        newDeviceRecycler = viewList.get(pageNum).findViewById(R.id.new_device_recycler);

        //滑动删除
        ScrollSmoothLineaerLayoutManager manager = new
                ScrollSmoothLineaerLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false, 500);
        newDeviceRecycler.setHasFixedSize(false);
        //设置设备的信息
        newDeviceRecycler.setLayoutManager(manager);

    }

    /**
     * 每次点击添加按钮时，都会新建一个 AlertDialog
     */
    public void createAddAlert() {
        //由子类重写
    }

    /**
     * 初始化ViewPager 和 顶部工具栏
     */
    public void initView() {
        //顶部工具栏
        magicIndicator2 = getActivity().findViewById(R.id.base_device_detail_magic2);
        //ViewPager
        viewPager = getActivity().findViewById(R.id.base_devices_detail_viewpager);
    }

    /**
     * 初始化历史数据底部面板
     */
    public void initHistoryPanel() {
        //初始化时间选择器
        initHistoryTime();
        //日期初始化
        Calendar now = Calendar.getInstance();
        //年份，有点多余
        EditText editYear = vHistory.findViewById(R.id.history_year);

        editYear.setOnClickListener((view) -> {

        });

        //开始时间
        startHistoryTime = vHistory.findViewById(R.id.history_start_time);
        //设置当前时间
        String initStart = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);
        startHistoryTime.setText(initStart);

        startHistoryTime.setOnClickListener((v) -> {
            datePickerDialog.show(getFragmentManager(), "history_start_time");
        });

        //结束时间
        endHistoryTime = vHistory.findViewById(R.id.history_end_time);
        endHistoryTime.setText(initStart);

        endHistoryTime.setOnClickListener((v) -> {
            datePickerDialog.show(getFragmentManager(), "history__end_time");
        });

        //取消按钮
        Button cancelBtn = vHistory.findViewById(R.id.cancel_commit);
        //确认按钮
        Button trueBtn = vHistory.findViewById(R.id.true_commit);
        //取消查询
        cancelBtn.setOnClickListener((v) -> {
            historyBottomSheetBehavior.cancel();
        });
        //确认查询
        trueBtn.setOnClickListener((v) -> {

            //如果为0，则将第一个设备的ID赋值
            if (ehmID == 0)
                ehmID = ehmIDFirst;

            querySelectDeviceHistory(ehmID, startTime, endTime);
            historyBottomSheetBehavior.cancel();

            if (viewPager.getCurrentItem() != 2) {
                viewPageMode = false;
                viewPager.setCurrentItem(2);
            }

        });

        //报警设备列表
        historyPanelDevice = new AlertDeviceAdapter(getActivity(), alertDeviceBeanList, R.layout.alert_device_temperature);
        historyPanelDevice.setItemClickListener(this);
        historyDeviceRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        historyDeviceRecycler.setAdapter(historyPanelDevice);
    }

    /**
     * 选定时间，设备来查询设备的历史数据
     *
     * @param ehmID     设备ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        //由子类进行重写
    }

    /**
     * 初始化报警面板
     */
    public void initAlertPanel() {
        //初始化时间选择器
        initAlertTime();
        //日期初始化
        Calendar now = Calendar.getInstance();
        //年份，有点多余
        EditText editYear = vAlert.findViewById(R.id.panel_year);
        editYear.setOnClickListener((view) -> {

        });

        //开始时间
        startPanelTime = vAlert.findViewById(R.id.panel_start_time);
        //设置当前时间
        String initStart = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH);
        startPanelTime.setText(initStart);

        startPanelTime.setOnClickListener((v) -> {
            datePickerDialog.show(getFragmentManager(), "panel_start_time");
        });

        //结束时间
        endPanelTime = vAlert.findViewById(R.id.panel_end_time);
        endPanelTime.setText(initStart);

        endPanelTime.setOnClickListener((v) -> {
            datePickerDialog.show(getFragmentManager(), "panel_end_time");
        });

        //取消按钮
        Button cancelBtn = vAlert.findViewById(R.id.cancel_commit);
        //确认按钮
        Button trueBtn = vAlert.findViewById(R.id.true_commit);
        //取消查询
        cancelBtn.setOnClickListener((v) -> {
            alertBottomSheetBehavior.cancel();
        });
        //确认查询
        trueBtn.setOnClickListener((v) -> {

            querySelectDeviceAlert(ehmID, startTime, endTime);

            alertBottomSheetBehavior.cancel();

            if (viewPager.getCurrentItem() != 1) {
                viewPageMode = false;
                viewPager.setCurrentItem(1);
            }

        });
        //报警设备列表
        alertPanelDevice = new AlertDeviceAdapter(getActivity(), alertDeviceBeanList, R.layout.alert_device_temperature);
        alertPanelDevice.setItemClickListener(this);
        alertDeviceRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        alertDeviceRecycler.setAdapter(alertPanelDevice);

        //初始化导出报警信息的按钮
        Button exportAlertBtn = vAlert.findViewById(R.id.export_alert_data);
        exportAlertBtn.setOnClickListener((v) -> {
            exportAlertMessage();
        });

    }

    /**
     * 点击导出报警信息按钮执行该方法，由子类实现具体的操作
     */
    protected void exportAlertMessage() {
        saveAlertExcel("alert_message");
    }

    /**
     * 设备面板中选择号设备后，点击确认进行查询报警数据
     *
     * @param ehmID     设备ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {

    }

    /**
     * 面板的触摸事件
     *
     * @param view        点击的View
     * @param motionEvent 点击的事件
     * @return 点击的结果
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.panel_start_time) {
            ToastManage.getInstance().toastShortShow("start time");
        }
        if (view.getId() == R.id.panel_end_time) {
            ToastManage.getInstance().toastShortShow("end time");
        }
        return false;
    }

    /**
     * 设置ViewPager的监听
     */
    public void setViewPagerListener() {
        //ViewPager活动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动时回调
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //ViewPager滑动后也能与顶部导航栏联动
                magicIndicator2.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            //滑动完之后回调
            @Override
            public void onPageSelected(int position) {

                //记录是跳转还滑动  true 滑动 false 跳转
                if (!viewPageMode) {
                    viewPageMode = true;
                    return;
                }

                //顶部导航栏联动
                magicIndicator2.onPageSelected(position);
                //将点击事件扩展到子类
                magicSelected(position);
                switch (position) {
                    //实时数据
                    case 0:
                        break;
                    //报警数据
                    case 1:

                        //每次切换时不再重复加载
                        if (alertShow)
                            return;

                        //查询报警数据
                        queryAlertData();
                        ehmID = getEhmID();
                        alertShow = true;

                        break;
                    //历史曲线
                    case 2:

                        //每次切换时不再重复加载
                        if (historyShow)
                            return;

                        //查询历史数据
                        queryHistoryData();
                        ehmID = getEhmID();
                        historyShow = true;

                        break;
                    //设备管理
                    case 3:

                        //每次切换时不再重复加载
                        if (manageShow)
                            return;

                        queryDeviceManage();
                        manageShow = true;

                        break;
                }

            }

            //滑动状态改变时执行
            @Override
            public void onPageScrollStateChanged(int state) {
                //顶部导航栏联动
                magicIndicator2.onPageScrollStateChanged(state);
            }
        });
    }

    /**
     * 顶部导航栏点击的监听，由子类重写
     *
     * @param position 点击的位置
     */
    public void magicSelected(int position) {
    }


    /**
     * 查询历史数据
     */
    public void queryAlertData() {

    }

    /**
     * 查询历史数据
     */
    public void queryHistoryData() {

    }

    /**
     * 查询设备管理数据
     */
    public void queryDeviceManage() {

    }

    /**
     * 设备详细信息中的 顶部导航栏
     */
    protected void initTopNav() {

    }

    /**
     * 设备管理中的 添加设备完成后，会结束Activity并回调该方法
     *
     * @param requestCode 请求码
     * @param resultCode  处理结果码
     * @param data        返回的数据容器
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 设置 设备详情中的 顶部导航栏
     *
     * @param beans 获取到的列表
     */
    @Override
    public void setTopNavigation(List<DeviceDetailTopBean> beans) {

        Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
            //数据获取成功后，设置顶部导航栏
            magicNavigator.setAdapter(new CommonNavigatorAdapter() {
                @Override
                public int getCount() {
                    //获取顶部导航栏的长度
                    return beans.size();
                }

                @Override
                public IPagerTitleView getTitleView(Context context, int index) {
                    //顶部导航栏标题
                    ClipPagerTitleView titleView = new ClipPagerTitleView(context);
                    //设置标题
                    titleView.setText(beans.get(index).getName());
                    //设置标题的颜色
                    titleView.setTextColor(Color.parseColor("#f2c4c4"));
                    titleView.setClipColor(Color.WHITE);
                    //点击标题时的监听
                    titleView.setOnClickListener((v) -> {
                        //点击时，改变ViewPage的位置
                        viewPager.setCurrentItem(index);

                    });

                    //返回 ClipPagerTitleView
                    return titleView;
                }

                //圆点显示
                @Override
                public IPagerIndicator getIndicator(Context context) {
                    return null;
                }
            });
            //设置顶部导航栏生效
            magicIndicator2.setNavigator(magicNavigator);
        });

    }

    /**
     * 每个子类返回自己所对应的 ViewPager View 集合
     *
     * @return View Layout ID
     */
    @Override
    public abstract List<Integer> getViewPagerView();

    /**
     * 返回 Fragment 的 Layout ID
     *
     * @param inflater           加载视图
     * @param container          视图容器
     * @param savedInstanceState 临时保存数据
     * @return Fragment Layout ID
     */
    @Override
    protected View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.device_detail_template, container, false);
    }

    /**
     * 设备管理中的 设备管理长按点击事件，长按会弹出设备的信息
     * 会在子类中回调
     *
     * @param v        视图
     * @param position 点击的ITEM位置
     */
    @Override
    public void onItemLongClickListener(View v, int position) {


    }

    @Override
    public void onDestroy() {
        //活动销毁时，移除实时获取任务
        infoHandler.removeCallbacks(this);
        super.onDestroy();
    }

    /**
     * 实时获取数据的方法，具体实现由子类完成
     */
    @Override
    public abstract void run();

    /**
     * 设置ViewPager的位置
     *
     * @param current 需要设置 ViewPager 的位置
     */
    public void setCurrentViewPager(int current) {

        viewPager.setCurrentItem(current);
    }

    /**
     * 请求数据成功回调该方法
     *
     * @param requestTag  请求的 标识
     * @param requestUrl  请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */
    @Override
    public synchronized void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        Log.d("RequestJSON", "httpRequestSuccess: " + requestTag + ":" + responseStr);

        if (responseStr.equals("[]"))
            return;

        switch (requestTag) {
            //请求实时数据
            case Lain_Application.HTTP_REAL:
                dealWitchReal(requestTag, requestUrl, responseStr);
                break;
            //报警数据回调
            case Lain_Application.HTTP_ALERT:
                dealWitchAlert(requestTag, requestUrl, responseStr);
                break;
            case "change":
                getActivity().runOnUiThread(() -> {
                    if (responseStr.equals("true")) {
                        ToastManage.getInstance().toastLongShow("修改设备成功");
                        changeDeviceSuccess();
                    } else
                        ToastManage.getInstance().toastLongShow("修改设备失败");
                });
                break;
            case "add":
                getActivity().runOnUiThread(() -> {
                    if (responseStr.equals("true")) {
                        ToastManage.getInstance().toastLongShow("添加设备成功");
                        addDeviceSuccess();
                    } else
                        ToastManage.getInstance().toastLongShow("添加设备失败");
                });
                break;
            case "deviceIP":

                //直接保存好设备IP
                try {
                    deviceIPBeanList = MyGson.getInstance().fromJson(responseStr, new TypeToken<List<DeviceIPBean>>() {
                    }.getType());
                } catch (JsonSyntaxException e) {
                    Log.d("TAG", "httpRequestSuccess: 获取设备IP失败");
                }

                break;
        }

    }

    /**
     * 添加设备成功后会调用，具体操作由子类实现
     */
    public void addDeviceSuccess() {
    }

    /**
     * 修改设备信息成功后会调用该方法，具体操作由子类实现
     */
    public void changeDeviceSuccess() {
    }

    /**
     * 处理报警数据
     *
     * @param requestTag  请求的 TAG
     * @param requestUrl  请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */

    public void dealWitchAlert(String requestTag, String requestUrl, String responseStr) {

        //清空报警数据，再重新重新
        alertBeans.clear();
        alertBeans.addAll(OkHttpUtil.getInstance().formatResponse(responseStr, AlertBeans.class));
        alertAdapter.notifyDataSetChanged();

    }

    /**
     * 处理实时数据
     *
     * @param requestTag  请求的 TAG
     * @param requestUrl  请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {
        Log.d("lkdjfler", "dealWitchReal: " + responseStr);
    }

    /**
     * 初始化指示器
     */
    public void initMagicIndicator() {

        //初始化顶部工具栏
        magicNavigator = new CommonNavigator(getContext());
        //自适应屏幕宽度
        magicNavigator.setAdjustMode(true);
        //设置适配器
        magicNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return getTopItem().size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                //放大缩小动画
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                //设置标题
                simplePagerTitleView.setText(getTopItem().get(index));
                //设置字体
                simplePagerTitleView.setTextSize(13);
                //未选中字体颜色
                simplePagerTitleView.setNormalColor(Color.parseColor("#5097FF"));
                //选中字体颜色
                simplePagerTitleView.setSelectedColor(Color.parseColor("#5097FF"));
                //顶部导航栏监听
                simplePagerTitleView.setOnClickListener((v) -> {
                    //点击时，改变ViewPage的位置
                    viewPager.setCurrentItem(index);
                });

                //返回顶部导航栏的配置对象
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                //线性
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                //高度
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                //宽度
                indicator.setLineWidth(UIUtil.dip2px(context, 60));
                //圆角
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                //开始动画
                indicator.setStartInterpolator(new AccelerateInterpolator());
                //结束动画
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                //颜色
                indicator.setColors(Color.parseColor("#5097FF"));

                return indicator;
            }
        });
        //顶部导航栏
        magicIndicator2.setNavigator(magicNavigator);
        //标题
        LinearLayout titleContainer = magicNavigator.getTitleContainer();
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        //设置分割线的padding
        titleContainer.setDividerPadding(UIUtil.dip2px(getContext(), 50));
        //设置分隔线颜色
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        //绑定ViewPager
        ViewPagerHelper.bind(magicIndicator2, viewPager);
    }

    /**
     * 顶部导航栏
     *
     * @return 顶层导航栏的ITEM
     */
    public ArrayList<String> getTopItem() {
        return new ArrayList<>();
    }

    /**
     * 历史查询时间
     */
    public void initHistoryTime() {
        //时间初始化
        Calendar timeCalendar = Calendar.getInstance();

        if (timeHistoryDialog == null) {

            timeHistoryDialog = TimePickerDialog.newInstance(
                    this, timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE),
                    true);
        } else {
            timeHistoryDialog.initialize(this,
                    timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE),
                    timeCalendar.get(Calendar.SECOND),
                    true);
        }
        //时间选择器的颜色
        timeHistoryDialog.setAccentColor(Lain_Application.getThemeColor());
        //取消按钮
        timeHistoryDialog.setCancelText("取消");
        //确认按钮
        timeHistoryDialog.setOkText("查询");
        //时间选择器的样式
        timeHistoryDialog.setVersion(com.wdullaer.materialdatetimepicker.time.TimePickerDialog.Version.VERSION_2);

        //日期初始化
        Calendar now = Calendar.getInstance();

        if (dateHistoryDialog == null) {

            dateHistoryDialog = DatePickerDialog.newInstance(this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));

        } else {

            dateHistoryDialog.initialize(Base_Devices_Detail.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_WEEK));

        }

        //时间选择器的颜色
        dateHistoryDialog.setAccentColor(Lain_Application.getThemeColor());
        //时间选择器的样式
        dateHistoryDialog.setVersion(DatePickerDialog.Version.VERSION_1);
    }

    /**
     * 报警查询时间
     */
    public void initAlertTime() {
        //时间初始化
        Calendar timeCalendar = Calendar.getInstance();

        if (timePickerDialog == null) {

            timePickerDialog = TimePickerDialog.newInstance(
                    this, timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE),
                    true);
        } else {
            timePickerDialog.initialize(this,
                    timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE),
                    timeCalendar.get(Calendar.SECOND),
                    true);
        }
        //时间选择器的颜色
        timePickerDialog.setAccentColor(Lain_Application.getThemeColor());
        //取消按钮
        timePickerDialog.setCancelText("取消");
        //确认按钮
        timePickerDialog.setOkText("查询");
        //时间选择器的样式
        timePickerDialog.setVersion(com.wdullaer.materialdatetimepicker.time.TimePickerDialog.Version.VERSION_2);

        //日期初始化
        Calendar now = Calendar.getInstance();

        if (datePickerDialog == null) {

            datePickerDialog = DatePickerDialog.newInstance(this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));

        } else {

            datePickerDialog.initialize(Base_Devices_Detail.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_WEEK));

        }

        //时间选择器的颜色
        datePickerDialog.setAccentColor(Lain_Application.getThemeColor());
        //时间选择器的样式
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);
    }

    /**
     * 日期选择回调方法
     *
     * @param view        View
     * @param year        选择的年份
     * @param monthOfYear 选择的月份
     * @param dayOfMonth  选择的天
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        String month = (monthOfYear + 1) + "";

        switch (view.getTag()) {
            case "history_start_time":
                startHistoryTime.setText(year + "-" + month + "-" + dayOfMonth);
                startTime = startHistoryTime.getText().toString() + " 23:59:59";
                break;
            case "panel_start_time":
                startPanelTime.setText(year + "-" + month + "-" + dayOfMonth);
                startTime = startPanelTime.getText().toString() + " 00:00:00";

                break;
            case "history_end_time":
                endHistoryTime.setText(year + "-" + month + "-" + dayOfMonth);
                endTime = endHistoryTime.getText().toString() + " 23:59:59";
                break;
            case "panel_end_time":
                endPanelTime.setText(year + "-" + month + "-" + dayOfMonth);
                endTime = endPanelTime.getText().toString() + " 23:59:59";
                break;
        }

    }

    /**
     * 时间选择
     *
     * @param view      View
     * @param hourOfDay 小时
     * @param minute    分钟
     * @param second    秒
     */
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }

    /**
     * 设备详情内所有的 Presenter 请求失败后的回调方法
     *
     * @param requestTag 请求的 标识
     * @param requestUrl 请求的 URL
     * @param errorMsg   请求失败返回的信息
     */
    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }

    /**
     * 保存第一个 设备ID
     *
     * @param ehmID 接收设备的ID
     */
    public void setEhmID(int ehmID) {
        Log.d("kldfsdf", "setEhmID: " + ehmID);
        this.ehmID = ehmID;
    }

    //获取默认的设备ID
    public int getEhmID() {
        return 0;
    }

    /**
     * 显示哪些 ITEM
     *
     * @param real    实时数据
     * @param alert   报警数据
     * @param history 历史数据
     * @param manage  设备管理
     */
    public void setToNavShow(boolean real, boolean alert, boolean history, boolean manage) {
        historyItem.setVisibility(history ? View.VISIBLE : View.GONE);
        deviceItem.setVisibility(manage ? View.VISIBLE : View.GONE);
        alertItem.setVisibility(alert ? View.VISIBLE : View.GONE);
        realItem.setVisibility(real ? View.VISIBLE : View.GONE);
    }

    /**
     * 是否显示顶部工具栏
     *
     * @param isShowPanel 是否显示
     */
    public void setHeadPanelShow(boolean isShowPanel) {
        if (isShowPanel)
            head_panel.setVisibility(View.VISIBLE);
        else
            head_panel.setVisibility(View.GONE);
    }

    /**
     * 保存顶部导航栏ITEM的实例
     *
     * @param head_panel 顶部面板
     * @param real       实时ITEM
     * @param alert      报警ITEM
     * @param history    历史ITEM
     * @param manage     设备管理ITEM
     */
    @Override
    public void setTopNav(RelativeLayout head_panel, LinearLayout real, LinearLayout alert, LinearLayout history, LinearLayout manage) {
        this.realItem = real;
        this.alertItem = alert;
        this.historyItem = history;
        this.deviceItem = manage;
        this.head_panel = head_panel;
    }

    /**
     * 初始化第一个曲线图
     */
    public void initFirstLine() {

    }

    /**
     * 初始化第二个曲线图
     */
    public void initSecondLine() {

    }

    /**
     * 设置设备管理中的 添加设备 / 修改设备 时显示的选项
     */
    public List<MaterialEditText> setDeviceAddEdit() {
        return null;
    }

    /**
     * 提交添加设备数据时的回调
     * 提交修改设备数据时的回调
     *
     * @param keyArray   EditText 的储存集合
     * @param spinnerMap Spinner 的储存集合
     */
    @Override
    public void deterMine(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap) {

    }

    /**
     * 修改设备时的回调
     * 点击确定后的回调方法
     *
     * @param keyArray   EditText 的储存集合
     * @param spinnerMap Spinner 的储存集合
     */
    @Override
    public void deterChange(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap) {

    }

    /**
     * 点击取消后的回调方法
     */
    @Override
    public void cancelMine() {

    }

    /**
     * 点击添加设备按钮时的回调方法
     */
    public void addNewDevice(String flag) {
    }

    /**
     * 添加设备时，调用该方法，由子类来重写
     */
    public void addDeviceAction(List<MaterialEditText> materialEditTexts) {
    }

    /**
     * 修改设备时，调用该方法，由子类来重写
     */
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {
    }

    /**
     * 传入EditText，获取值后返回结果
     *
     * @param editText EditText编辑框的实例
     * @return 获取的结果
     */
    public String getTextString(EditText editText) {

        if (editText != null)
            return editText.getText().toString();

        return "";

    }

    /**
     * 获取Spinner的值
     *
     * @param spinner Spinner 实例
     * @return 获取Spinner的数据
     */
    public String getSpinnerString(Spinner spinner) {

        if (spinner != null) {
            //获取选中的View并转成 TextView 来获取
            TextView textView = (TextView) spinner.getSelectedView();
            return textView.getText().toString();
        }

        return "";
    }

    /**
     * 封装获取设备的IP
     *
     * @param url
     */
    public void getDeviceIP(String url) {
        //更新完设备管理的数据后，请求设备的IP
        DeviceIpGetUtil.getInstance().sendGetOkHttp("deviceIP", url, this);
    }

    /**
     * 添加设备方法，子类具体实现
     */
    public void addDeviceMethod() {
        addNewDevice("添加");
    }

    /**
     * 显示用户管理中编辑面板
     * 由子类实现具体操作
     *
     * @param tag 用户管理中的TAG
     */
    public void showUserManagePanel(String tag) {

    }

    /**
     * WebSocket 接收的回调消息
     *
     * @param message 接收到的消息
     */
    @Override
    public void receiveMessage(String message) {

    }

    /**
     * RecyclerView的加载更多，由子类实现
     * @param itemsCount ITEM的数量
     * @param maxLastVisiblePosition 可见的ITEM
     */
    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        Log.d("kllert", "loadMore: all device alert more");
    }

    /**
     * 保存文件，导出 报警信息 Excel
     *
     * @param saveName 文件名
     */
    private void saveAlertExcel(String saveName) {

        //文件路径
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lain_excel/";
        //文件是否存在
        File file = new File(filePath);
        if (!file.exists())
            file.mkdir();
        //excel 列
        String[] title = {"设备名称", "报警信息", "报警时间"};
        //保存文件的完整文件名
        String excelFileName = "/" + saveName + ".xls";
        //保存路径结果
        String resultPath = file.getAbsolutePath() + excelFileName;

        //保存文件
        ExcelUtil<AlertBeans> alertBeansExcelUtil = new ExcelUtil<>();
        alertBeansExcelUtil.initExcel(resultPath, title, (excelData) -> {

            List<String> excelDataList = new ArrayList<>();

            excelDataList.add(excelData.getEcmDeviceName());
            excelDataList.add(excelData.getEhaInfo());
            excelDataList.add(excelData.getEhaTime());

            return excelDataList;
        });

        //导出功能
        File moduleFile = alertBeansExcelUtil.writeObjectListToExcel(alertBeans, resultPath, getActivity());
        //如果文件存在，代表保存成功
        if (moduleFile != null)
            ToastManage.getInstance().toastShortShow("保存成功！ 路径：" + filePath);

    }

}
