package computer_room.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.ethanco.circleprogresslibrary.TextCircleProgress;
import com.example.device_detail_lib.R;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import action.ActionControl;
import action.SaveInterface;
import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import bean.TemperWebSocke;
import bean.TemperatureBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.adapter.NewHistoryAdapter;
import computer_room.adapter.TemperatureAdapter;
import computer_room.bean.Temperature8060AlertBean;
import computer_room.bean.TemperatureLineBean;
import computer_room.i_interface.I_HistoryAdapter;
import computer_room.i_interface.I_Temperature;
import computer_room.present.TemperaturePresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.ActivityUtil;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;
import util.RealDataUtil;
import util.ToastManage;
import view.DeviceHistoryActivity;
import view_interface.I_UpdateRealView;

public class Temperature8060 extends Device8060Fragment<TemperatureBean, Temperature8060AlertBean,
        TemperatureLineBean, TemperatureBean> implements I_Temperature, I_UpdateRealView<TemperatureBean>,
        BaseRecyclerViewAdapter.OnItemClickListener, I_DeviceDetailManageLink<Device_Detail_AddBean>, I_HistoryAdapter<TemperatureLineBean> {

    //温湿度中的 实时数据列表
    private UltimateRecyclerView temperatureRecycler;

    //LineChart 的数据
    private List<TemperatureLineBean> lineChartList;

    //实时数据显示
    private TextCircleProgress deviceRealData;


    //温湿度Presenter
    private TemperaturePresenter presenter;

    //温湿度的实时数据适配器
    private TemperatureAdapter temperatureAdapter;
    //实时数据，也是设备管理的数据
    private List<TemperatureBean> temperatureBeans = new ArrayList<>();
    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();


    //记录当前查看的设备
    private int currentDevice = 0;
    //记录当前的值，如果数值没发生改变，则不更新UI
    private double currentTempValue = 0;     //温度
    private double currentHumValue = 0;      //湿度

    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, TemperatureBean> manageAdapter;

    //保存添加的设备列表适配器
//    public Temperature_Add deviceDetail_AddAdapter;
    //温湿度数据展示面板
    private LinearLayout temperature_top_data;

    NewHistoryAdapter<TemperatureLineBean> newHistoryAdapter;
    List<TemperatureLineBean> lineDataList = new ArrayList<>();

    private static LayoutInflater layoutInflater;

    private AlertDialog changeDialog;

    private LinearLayout svHolderLayout;

    List<TextView> textViewList;

    private RealDataUtil<TemperatureBean> realDataUtil;

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public Temperature8060(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    /**
     * @param realLink
     * @param alertLink
     * @param historyLink
     * @param manageLink
     * @param tempDeleted 删除设备的链接
     * @param tempChange  修改设备信息的链接
     */
    public Temperature8060(String realLink, String alertLink, String historyLink, String manageLink, String tempDeleted, String tempChange) {
        super(realLink, alertLink, historyLink, manageLink);
        this.deleteUrl = tempDeleted;
        this.changeUrl = tempChange;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化基本控件
        initTemperatureView();
        //初始化Present
        presenter = new TemperaturePresenter(this);

        layoutInflater = getActivity().getLayoutInflater();

        //初始报警页面的控件
        initAlert(1);
        //初始化历史数据中的控件
        initHistoryFloatingButton(2);

        initDeviceManage(3, "温湿度");
//        initDeviceManage(3, DeviceDetailManageAdd.class, "温湿度");


        //设备管理的列表
//        deleteUrl = LainNewApi.tempDelete;
//        changeUrl = LainNewApi.tempChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans, temperatureBeans, getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl, this, "温湿度");

        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
        initModelAdapter();
        //设置显示的NAV
        setDeviceNav(true, true, true, true);
        //查询实时数据
        device8060Present.queryRealData(TemperatureBean.class);

        //设置历史数据列表
        newHistoryAdapter = new NewHistoryAdapter<>(getActivity(), lineDataList, R.layout.new_history_temp, this);
        newHistoryAdapter.setItemClickListener(this);
        newHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        newHistoryList.setAdapter(newHistoryAdapter);

    }


    //初始化基本的控件 与 View
    private void initTemperatureView() {

        realDataUtil = new RealDataUtil<>();

        realDataUtil.initView(viewList.get(0), getActivity(), temperatureBeans, this, this);

        //初始化顶部导航栏
        initTopNav();
    }

    //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
    private void initModelAdapter() {

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

    }

    /**
     * 温湿度中实时数据的临时数据
     */
    @Override
    public void initTemperatureRecycler(List<TemperatureBean> dataList) {


    }

    /**
     * 每次更新时，只更新需要改变的数据
     *
     * @param position
     */
    public void setCurrentData(int position) {
        try {
            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            TemperatureBean b = temperatureBeans.get(position);

            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            realDataUtil.setInnerData(String.valueOf(b.getEhmHum()));
            realDataUtil.setOuterData(String.valueOf(b.getEhmTem()));
            realDataUtil.setOuterMax(String.valueOf(b.getEhmMaxTem()));
            realDataUtil.setInnerMax(String.valueOf(b.getEhmMaxHum()));

            realDataUtil.setInnerTitle("湿度 ℃");
            realDataUtil.setOuterTitle("温度 RH");

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 报警列表
     *
     * @param dataList 设备管理请求的列表数据
     */
    @Override
    public void devicesAlert(List<AlertBeans> dataList) {

        alertBeans.clear();
        alertBeans.addAll(dataList);
        alertAdapter.notifyDataSetChanged();

    }

    /**
     * 显示的ViewPager
     *
     * @return
     */
    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据
        viewList.add(R.layout.base_real_data);
        //报警数据
        viewList.add(R.layout.temperture_alarm);
        //历史数据
        viewList.add(R.layout.new_history_layout);
        //设备管理
        viewList.add(R.layout.device_detail_manage);
        return viewList;
    }

    /**
     * 请求到的实时数据回调
     */
    @Override
    public void requestReal() {

        try {
            //先清空
            temperatureBeans.clear();
            //更新所有的数据，必须调用 getRealData()，才能获取到数据，必须使用 addAll 才能更新列表
            temperatureBeans.addAll(getRealData());

            Log.d("opiper", "requestReal: " + temperatureBeans.size());

            //第一次初始化列表更新数据
            if (refreshDeviceList) {
//                temperatureAdapter.notifyDataSetChanged();
                //刷新数据
                realDataUtil.notifyRealDataSetChanged();

                refreshDeviceList = false;

                //设置实时数据
                onItemClickListener(getView(), 0);

                //第一次获取成功时，才会进行WebSocket通信
                connectSocket("1", this);

            } else {
                //点击某个设备之后，就只更新点击后的设备的数据
                setCurrentData(currentDevice);
            }

            //数据为空时才更新

            if (alertDeviceBeanList.isEmpty()) {

                //每次查询都保存第一个设备ID
                ehmIDFirst = temperatureBeans.get(0).getEhmId();

                //更新报警查询的设备
                for (TemperatureBean b :
                        temperatureBeans) {

                    AlertDeviceBean alertB = new AlertDeviceBean();
                    alertB.setName(b.getEhmDeviceName());
                    alertDeviceBeanList.add(alertB);
                }

                alertPanelDevice.notifyRealDataSetChanged();

            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            TemperatureBean temperatureBean = temperatureBeans.get(0);
            ///获取设备的ID
            int temEhmId = temperatureBean.getEhmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(Temperature8060AlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }

    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(Temperature8060AlertBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void requestAlert() {


        List<Temperature8060AlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            Temperature8060AlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getEhaInfo());
            alertBeansItem.setEhaTime(alertBean.getEhaTime());
            alertBeansItem.setEcmDeviceName(alertBean.getEhaName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();

    }

    @Override
    public void requestHistory() {
        try {

            lineDataList.clear();

            List<Integer> tempList = new ArrayList<>();
            List<Integer> humList = new ArrayList<>();
            List<String> timeList = new ArrayList<>();

            lineDataList.addAll(getHistoryData());
            newHistoryAdapter.notifyDataSetChanged();

            //如果是点击曲线图，则查询完成后，直接跳转到详情页
            if (intoHistoryDetail) {

                View v = new View(getActivity());
                v.setId(R.id.new_history_name);
                onItemClickListener(v, historyBottomPos);
            }

        } catch (IndexOutOfBoundsException e) {
            Log.d("history", "initChartTemper: 未获取到历史数据");
        }
    }

    @Override
    public void onItemClickListener(View v, int position) {

        try {
            //报警数据中选择设备查询时的回调
            if (v.findViewById(com.example.base.R.id.btn_panel_device) != null) {
                //保存点击的位置
                historyBottomPos = position;
                //先保存数据，点击确定后在进行查询
                setEhmID(temperatureBeans.get(position).getEhmId());
                return;
            }
            //历史数据点击查看 曲线图
            if (v.findViewById(com.example.pool.R.id.new_history_name) != null) {

                //必须先查询，才能进入查看曲线图
                if (lineDataList.isEmpty()) {
                    historyBottomSheetBehavior.show();
                    ToastManage.getInstance().toastShortShow("请先查询历史数据");
                    return;
                }

                ArrayList<String> temps = new ArrayList<>();
                ArrayList<String> hums = new ArrayList<>();
                ArrayList<String> times = new ArrayList<>();

                for (TemperatureLineBean temp :
                        lineDataList) {
                    temps.add(temp.getEhhTem() + "");
                    hums.add(temp.getEhhHum() + "");
                    times.add(temp.getEhhTime() + "");
                }

                Intent intent = new Intent(getActivity(), DeviceHistoryActivity.class);
                //传递显示的数据
                intent.putStringArrayListExtra("temps", temps);
                intent.putStringArrayListExtra("hums", hums);
                intent.putStringArrayListExtra("times", times);

                //传递曲线的单位
                ArrayList<String> suffixList = new ArrayList<>();
                suffixList.add("℃");
                suffixList.add("RH");
                intent.putStringArrayListExtra("suffixList", suffixList);

                intent.putExtra("title", temperatureBeans.get(position).getEhmDeviceName());
                startActivity(intent);
                intoHistoryDetail = false;
                return;
            }


            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            TemperatureBean b = temperatureBeans.get(position);

            //设置最大值，必须先设置最大值，再设置当前值，才不会出现问题
            realDataUtil.setOuterMax(String.valueOf(b.getEhmMaxTem()));
            realDataUtil.setInnerMax(String.valueOf(b.getEhmMaxHum()));
            //设置数据
            realDataUtil.setInnerData(String.valueOf(b.getEhmHum()));
            realDataUtil.setOuterData(String.valueOf(b.getEhmTem()));

            //设置文案
            realDataUtil.setInnerTitle("湿度 ℃");
            realDataUtil.setOuterTitle("温度 RH");

            //保存当前点击的位置，用于实时刷新
            currentDevice = position;

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    /**
     * 父类会从这个方法里取出解析类
     *
     * @return
     */
    @Override
    public Class<TemperatureBean> getParseClass() {
        return TemperatureBean.class;
    }

    @Override
    public void queryHistoryData() {
        try {
            //默认查询第一个设备
            TemperatureBean temperatureBean = temperatureBeans.get(0);

            ///获取设备的ID
            int temEhmId = temperatureBean.getEhmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(TemperatureLineBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }

    }

    @Override
    public void queryDeviceManage() {
        //只执行一次
        if (addBeans.size() == 0)
            device8060Present.queryDeviceManage(TemperatureBean.class);
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(TemperatureLineBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();

        List<TemperatureBean> manageData = getManageData();

//        适配添加设备的数据
        for (TemperatureBean b :
                manageData) {

            String ip = b.getEhmDeviceAddress() + "";
            String port = String.valueOf(b.getDiPort());

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getEhmDeviceName());

            bean.setEhmId(String.valueOf(b.getEhmId()));
            //湿度
            bean.setHumidityMin(String.valueOf(b.getEhmMinHum()));
            bean.setHumidityMax(String.valueOf(b.getEhmMaxHum()));
            //温度
            bean.setTemperatureMax(String.valueOf(b.getEhmMaxTem()));
            bean.setTemperatureMin(String.valueOf(b.getEhmMinTem()));
            //位置
            bean.setPosition(String.valueOf(b.getEhmDeviceAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getIntervalTime()));
            bean.setIp(b.getEhmDeviceAddress() + ":" + b.getDiPort());
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();


    }


    /**
     * 获取设备ID，查询报警，历史
     *
     * @return
     */
    @Override
    public int getEhmID() {
        return this.ehmIDFirst;
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(temperatureBeans.get(position).getEhmId());
        svHolder.deleteDevice(ehmId, position);
    }

    @Override
    public void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {

        //设置图标
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(svHolder.imageView);
        //在这里设置视图
        textViewList = new ArrayList<>();

        svHolder.manageDeviceName.setText(data.getDeviceName());

//        TextView deviceName = new TextView(getActivity());
//        deviceName.setText(Html.fromHtml("设备名称：" + "<font color='#333333'>" + data.getDeviceName() + "</font>"));
//        textViewList.add(deviceName);

        TextView devicePos = new TextView(getActivity());
        devicePos.setText("设备地址：" + data.getPosition());
        textViewList.add(devicePos);

        TextView deviceTemp = new TextView(getActivity());
        deviceTemp.setText("温度区间：" + data.getTemperatureMin() + " ~ " + data.getTemperatureMax());
        textViewList.add(deviceTemp);

        TextView deviceHum = new TextView(getActivity());
        deviceHum.setText("湿度区间：" + data.getHumidityMin() + " ~ " + data.getHumidityMax());
        textViewList.add(deviceHum);

        TextView deviceInterval = new TextView(getActivity());
        deviceInterval.setText("保存间隔：" + data.getUpdateTime());
        textViewList.add(deviceInterval);

        TextView deviceIP = new TextView(getActivity());
        deviceIP.setText("IP地址：" + data.getIp());
        textViewList.add(deviceIP);

        DynamicDeviceManage.getInstance().addManageItem(textViewList, svHolder.linearLayout);

    }

    /**
     * 设备管理中添加设备时显示的输入框
     *
     * @return
     */
    @Override
    public List<MaterialEditText> setDeviceAddEdit() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        DynamicMaterialEdit.getInstance().setLayoutInflater(inflater, getActivity());
        List<MaterialEditText> editTexts = new ArrayList<>();

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备名称", "");
        MaterialEditText address = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备地址", "");
        MaterialEditText ip = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "IP端口", "");
        MaterialEditText position = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备位置", "");
        MaterialEditText temp_low = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "温度下限", "");
        MaterialEditText temp_high = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "温度上限", "");
        MaterialEditText hum_low = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "湿度下限", "");
        MaterialEditText hum_high = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "湿度上限", "");
        MaterialEditText interval = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "保存间隔", "");
        editTexts.add(name);
        editTexts.add(address);
        editTexts.add(ip);
        editTexts.add(position);
        editTexts.add(temp_low);
        editTexts.add(temp_high);
        editTexts.add(hum_low);
        editTexts.add(hum_high);
        editTexts.add(interval);
        //转成倒序数据，如果不转，动态添加的View第一个会是最后一个
        Collections.reverse(editTexts);
        return editTexts;
    }


    @Override
    public void bindHistoryData(BaseViewHolder holder, TemperatureLineBean data, int position) {

        TextView historyName = holder.getView(R.id.new_history_name);
        ImageView historyImage = holder.getView(R.id.history_image_list);
        TextView historyTemp = holder.getView(R.id.new_history_temp);
        TextView historyHum = holder.getView(R.id.new_history_hum);
        TextView historyTime = holder.getView(R.id.new_history_time);

        Button loogQx = holder.getView(R.id.look_qx);

        historyTemp.setText("温度" + data.getEhhTem());
        historyHum.setText("温度" + data.getEhhHum());
        historyTime.setText("时间" + data.getEhhTime());

        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(historyImage);

        loogQx.setOnClickListener((v) -> {
            onItemClickListener(holder.getRootView(), position);
        });
    }


    /**
     * 点击添加设备时执行
     */


    @Override
    public void addDeviceAction(List<MaterialEditText> materialEditTexts) {

        //温度
        if (Integer.parseInt(materialEditTexts.get(3).getText().toString()) < Integer.parseInt(materialEditTexts.get(4).getText().toString())) {
            ToastManage.getInstance().toastShortShow("温度上限 不能小于 温度下限");
            return;
        } else if (Integer.parseInt(materialEditTexts.get(4).getText().toString()) > Integer.parseInt(materialEditTexts.get(3).getText().toString())) {
            ToastManage.getInstance().toastShortShow("温度下限 不能大于 温度上限");
            return;
        }
        //湿度
        if (Integer.parseInt(materialEditTexts.get(1).getText().toString()) < Integer.parseInt(materialEditTexts.get(2).getText().toString())) {
            ToastManage.getInstance().toastShortShow("湿度上限 不能小于 湿度下限");
            return;
        } else if (Integer.parseInt(materialEditTexts.get(2).getText().toString()) > Integer.parseInt(materialEditTexts.get(1).getText().toString())) {
            ToastManage.getInstance().toastShortShow("湿度下限 不能大于 湿度上限");
            return;
        }

    }

    /**
     * 点击修改设备时执行
     */
    @Override
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {
        //温度
        if (Integer.parseInt(materialEditTexts.get(3).getText().toString().split("\\.")[0]) < Integer.parseInt(materialEditTexts.get(4).getText().toString().split("\\.")[0])) {
            ToastManage.getInstance().toastShortShow("温度上限 不能小于 温度下限");
            return;
        } else if (Integer.parseInt(materialEditTexts.get(4).getText().toString().split("\\.")[0]) > Integer.parseInt(materialEditTexts.get(3).getText().toString().split("\\.")[0])) {
            ToastManage.getInstance().toastShortShow("温度下限 不能大于 温度上限");
            return;
        }
        //湿度
        if (Integer.parseInt(materialEditTexts.get(1).getText().toString().split("\\.")[0]) < Integer.parseInt(materialEditTexts.get(2).getText().toString().split("\\.")[0])) {
            ToastManage.getInstance().toastShortShow("湿度上限 不能小于 温度下限");
            return;
        } else if (Integer.parseInt(materialEditTexts.get(2).getText().toString().split("\\.")[0]) > Integer.parseInt(materialEditTexts.get(1).getText().toString().split("\\.")[0])) {
            ToastManage.getInstance().toastShortShow("湿度下限 不能大于 温度上限");
            return;
        }

        //解析
        Map<String, String> formBody = new HashMap();
        //将输入的参数添加到 FormBody 中，进行发送给服务器
        formBody.put("ehmDeviceAddress", ActivityUtil.getInstance().EditTextToString(materialEditTexts.get(5)));
        formBody.put("ehmDeviceName", ActivityUtil.getInstance().EditTextToString(materialEditTexts.get(8)));
        formBody.put("ehmMaxTem", ActivityUtil.getInstance().EditTextToString(materialEditTexts.get(3)));
        formBody.put("ehmMinTem", ActivityUtil.getInstance().EditTextToString(materialEditTexts.get(4)));
        formBody.put("ehmMaxHum", ActivityUtil.getInstance().EditTextToString(materialEditTexts.get(1)));
        formBody.put("ehmMinHum", ActivityUtil.getInstance().EditTextToString(materialEditTexts.get(2)));
        formBody.put("intervalTime", ActivityUtil.getInstance().EditTextToString(materialEditTexts.get(0)));

        formBody.put("ehmId", String.valueOf(actionID));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + changeUrl, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 提交添加设备数据时的回调
     *
     * @param keyArray   EditText 的储存集合
     * @param spinnerMap Spinner 的储存集合
     */
    @Override
    public void deterMine(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap) {

        //解析
        Map<String, String> formBody = new HashMap();
        //将输入的参数添加到 FormBody 中，进行发送给服务器
        formBody.put("ehmDeviceAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("ehmDeviceName", getTextString(keyArray.get("deviceName")));
        formBody.put("ehmMaxTem", getTextString(keyArray.get("tempMax")));
        formBody.put("ehmMinTem", getTextString(keyArray.get("tempMin")));
        formBody.put("ehmMaxHum", getTextString(keyArray.get("humMax")));
        formBody.put("ehmMinHum", getTextString(keyArray.get("humMin")));
        formBody.put("intervalTime", getTextString(keyArray.get("interval")));
        formBody.put("diId", getTextString(keyArray.get("diId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.tempAdd, MyGson.getInstance().toJson(formBody), this);

    }

    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        TemperatureBean temp = temperatureBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName(temp.getEhmDeviceName());
        deviceManageEditBean.setTempRangeMin(temp.getEhmMinTem());
        deviceManageEditBean.setTempRangeMax(temp.getEhmMaxTem());
        deviceManageEditBean.setHumRangeMin(temp.getEhmMinHum());
        deviceManageEditBean.setHumRangeMax(temp.getEhmMinHum());
        deviceManageEditBean.setaDeviceInterval(temp.getIntervalTime());

        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getEhmId());
        //设置设备的DiId
        deviceManageEditBean.setDiId(temp.getDiId());

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);

        if (SaveInterface.getInstance().getGroupBeanList() != null)
            deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);
        deviceManageEditBean.setShowFunctionCode(true);

        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }

    @Override
    public void addNewDevice(String flag) {

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setTempRangeMin(0);
        deviceManageEditBean.setTempRangeMax(0);
        deviceManageEditBean.setHumRangeMin(0);
        deviceManageEditBean.setHumRangeMax(0);
        deviceManageEditBean.setaDeviceInterval(30);

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);

        try {
            deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));
        } catch (Exception e) {
            Log.e("addNewDevice", "addNewDevice: 获取数据失败");
        }

        deviceManageEditBean.setShowDeviceAddress(true);
        deviceManageEditBean.setShowFunctionCode(true);

        deviceManageEditBean.setChangeOrAdd("添加");

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }

    /**
     * 提交修改设备数据时的回调
     *
     * @param keyArray   EditText 的储存集合
     * @param spinnerMap Spinner 的储存集合
     */
    @Override
    public void deterChange(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap) {
        //解析
        Map<String, String> formBody = new HashMap();
        //将输入的参数添加到 FormBody 中，进行发送给服务器
        formBody.put("ehmDeviceAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("ehmDeviceName", getTextString(keyArray.get("deviceName")));
        formBody.put("ehmMaxTem", getTextString(keyArray.get("tempMax")));
        formBody.put("ehmMinTem", getTextString(keyArray.get("tempMin")));
        formBody.put("ehmMaxHum", getTextString(keyArray.get("humMax")));
        formBody.put("ehmMinHum", getTextString(keyArray.get("humMin")));
        formBody.put("intervalTime", getTextString(keyArray.get("interval")));

        formBody.put("ehmId", getTextString(keyArray.get("actionID")));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + changeUrl, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(TemperatureBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(TemperatureBean.class);
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(TemperatureBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();

        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(TemperatureBean.class);
        }, 1000);


    }

    /**
     * WebSocket 消息回调接口
     *
     * @param message 接收到的消息
     */
    @Override
    public void receiveMessage(String message) {
        Log.d("lkjlkfdf", "receiveMessage: " + message);

        TemperWebSocke socke = Objects.requireNonNull(MyGson.getInstance()).fromJson(message, TemperWebSocke.class);

        Log.d("dsfasd", "receiveMessage: " + socke.getEhmTem() + "----" + socke.getEhmHum());

        Log.d("lkjlkdfdf", "receiveMessage: ");
        getActivity().runOnUiThread(() -> {

            //温度进度条
            if (realDataUtil != null) {

                //设置数据
                realDataUtil.setInnerData(socke.getEhmHum());
                realDataUtil.setOuterData(socke.getEhmTem());

            }

        });

    }

    /**
     * 刷新View
     *
     * @param textView 更新的View
     * @param data     更新的数据
     * @param position 更新的View所在位置
     */
    @Override
    public void updateRealView(TextView textView, TemperatureBean data, int position) {

        //设置列表的数据
        textView.setText(data.getEhmDeviceName());

    }


}


