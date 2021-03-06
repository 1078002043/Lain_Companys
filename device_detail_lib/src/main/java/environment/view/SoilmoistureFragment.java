package environment.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.SaveInterface;
import adapter.BaseDeviceDetailManageAdapter;
import adapter.NewHistoryAdapter;
import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import environment.bean.SoilmoistureAlertBean;
import environment.bean.SoilmoistureHistoryBean;
import environment.bean.SoilmoistureRealBean;
import http.MyGson;
import http.OkHttpUtil;
import util.DateUtil;
import util.LainNewApi;
import util.RealDataUtil;
import util.ToastManage;
import view.Device8060Fragment;
import view.DeviceHistoryActivity;
import view.i_view.I_RealAdapterListen;
import view_interface.I_DeviceDetailManageLink;
import view_interface.I_HistoryAdapter;
import view_interface.I_UpdateRealView;

/**
 * @ClassName: SoilmoistureFragment
 * @Description: 土壤水分温度
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 13:57
 */
public class SoilmoistureFragment  extends Device8060Fragment<SoilmoistureRealBean, SoilmoistureAlertBean,
        SoilmoistureHistoryBean, SoilmoistureRealBean> implements BaseRecyclerViewAdapter.OnItemClickListener, I_DeviceDetailManageLink<Device_Detail_AddBean>, I_HistoryAdapter<SoilmoistureHistoryBean>, I_RealAdapterListen<SoilmoistureRealBean>, I_UpdateRealView<SoilmoistureRealBean> {

    private UltimateRecyclerView waterPressureRealRecycler;

    List<SoilmoistureRealBean> waterPressureRealBeans = new ArrayList<>();

    //-------------------------------------------------
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, SoilmoistureRealBean> manageAdapter;

    //只在进入页面时，刷新一次设备列表
    private boolean refreshDeviceList = true;

    //保存添加的设备列表适配器
//    public Temperature_Add deviceDetail_AddAdapter;
    //温湿度数据展示面板
    private LinearLayout temperature_top_data;
    //记录第一个 ehmID


    //删除URL
    String deleteUrl = "";
    String changeUrl = "";
    NewHistoryAdapter<SoilmoistureHistoryBean> newHistoryAdapter;
    List<SoilmoistureHistoryBean> lineDataList = new ArrayList<>();
    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();
    private static LayoutInflater layoutInflater;

    //记录当前查看的设备
    private int currentDevice = 0;
    //记录当前的值，如果数值没发生改变，则不更新UI
    private double currentTempValue = 0;     //温度
    private double currentHumValue = 0;      //湿度

    //设备实时数据工具类
    public RealDataUtil<SoilmoistureRealBean> realDataUtil;

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public SoilmoistureFragment(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    public SoilmoistureFragment(String realLink, String alertLink, String historyLink, String manageLink, String deleteUrl, String changeUrl) {
        super(realLink, alertLink, historyLink, manageLink);
        this.deleteUrl = deleteUrl;
        this.changeUrl = changeUrl;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //-------------------------------
        //初始报警页面的控件
        initAlert(1);
        //初始化历史数据中的控件
        initHistoryFloatingButton(2);

        initDeviceManage(3, "溶氧检测");


        //设备管理的列表
//        deleteUrl = LainNewApi.tempDelete;
//        changeUrl = LainNewApi.tempChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans, waterPressureRealBeans, getActivity(), com.example.pool.R.layout.split_air_manage_swiper, deleteUrl, changeUrl, this, "溶氧检测");
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
        initModelAdapter();
        //设置显示的NAV
        setDeviceNav(true, true, true, true);
        //查询实时数据
        device8060Present.queryRealData(SoilmoistureRealBean.class);
//        startRealUpdate();
        //设置历史数据列表
        newHistoryAdapter = new NewHistoryAdapter<>(getActivity(), lineDataList, com.example.pool.R.layout.new_history_temp, this);
        newHistoryAdapter.setItemClickListener(this);
        newHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        newHistoryList.setAdapter(newHistoryAdapter);

        //初始化实时数据工具类
        initRealDataUtil();

    }

    private void initRealDataUtil() {
        realDataUtil = new RealDataUtil<>();
        realDataUtil.initView(viewList.get(0), getActivity(), waterPressureRealBeans, this, this);
    }

    //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
    private void initModelAdapter() {

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);
    }

    @Override
    public List<Integer> getViewPagerView() {


        ArrayList<Integer> viewList = new ArrayList<>();
        viewList.add(com.example.pool.R.layout.base_real_data);
        viewList.add(com.example.pool.R.layout.dissolved_alert);
        viewList.add(com.example.pool.R.layout.dissolved_history);
        viewList.add(com.example.pool.R.layout.dissolved_manage);
        return viewList;

    }

    @Override
    public void queryHistoryData() {
        try {

            //默认查询第一个设备
            SoilmoistureRealBean temperatureBean = waterPressureRealBeans.get(0);
            ///获取设备的ID
            
            int temEhmId = temperatureBean.getEhmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(SoilmoistureHistoryBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(SoilmoistureHistoryBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void run() {

    }

    @Override
    public void requestReal() {
        //更新数据

        try {
            //先清空
            waterPressureRealBeans.clear();
            //更新所有的数据，必须调用 getRealData()，才能获取到数据，必须使用 addAll 才能更新列表
            waterPressureRealBeans.addAll(getRealData());

            //第一次初始化列表更新数据
            if (refreshDeviceList) {

                //刷新数据
                realDataUtil.notifyRealDataSetChanged();
                //设置实时数据
                onItemClickListener(getView(), 0);

                refreshDeviceList = false;

            } else {
                //点击某个设备之后，就只更新点击后的设备的数据
                setCurrentData(currentDevice);
            }

            //数据为空时才更新
            if (alertDeviceBeanList.isEmpty()) {
                //每次查询都保存第一个设备ID
                ehmIDFirst = waterPressureRealBeans.get(0).getEhmId();
                //更新报警查询的设备
                for (SoilmoistureRealBean b :
                        waterPressureRealBeans) {
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

    /**
     * 每次更新时，只更新需要改变的数据
     *
     * @param position
     */
    public void setCurrentData(int position) {
        try {
            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            SoilmoistureRealBean b = waterPressureRealBeans.get(position);

            if (b.getEhmHum() == currentTempValue && b.getEhmTem() == currentHumValue)
                return;

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestAlert() {

        List<SoilmoistureAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            SoilmoistureAlertBean alertBean = air.get(i);
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

            lineDataList.addAll(getHistoryData());
            newHistoryAdapter.notifyDataSetChanged();

//如果是点击曲线图，则查询完成后，直接跳转到详情页
if (intoHistoryDetail) {
	intoHistoryDetail = false;
	View v = new View(getActivity());
	v.setId(R.id.new_history_name);
	onItemClickListener(v, historyBottomPos);
}
        } catch (IndexOutOfBoundsException e) {
            Log.d("history", "initChartTemper: 未获取到历史数据");
        }
    }

    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();

        List<SoilmoistureRealBean> manageData = getManageData();

//        适配添加设备的数据
        for (SoilmoistureRealBean b :
                manageData) {

            String ip = "";
            String port = String.valueOf("");

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
            bean.setIp(b.getDiAddress() + ":" + b.getDiPort());
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();

    }

    @Override
    public void queryDeviceManage() {
        //只执行一次
        if (addBeans.size() == 0)
            device8060Present.queryDeviceManage(SoilmoistureRealBean.class);
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(SoilmoistureAlertBean.class, ehmID, startTime, endTime);
    }
    @Override
    public void onItemClickListener(View v, int position) {

        try {
            //报警数据中选择设备查询时的回调
            if (v.findViewById(com.example.pool.R.id.btn_panel_device) != null) {
                //先保存数据，点击确定后在进行查询
                setEhmID(waterPressureRealBeans.get(position).getEhmId());
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

                for (SoilmoistureHistoryBean temp :
                        lineDataList) {
                    temps.add(temp.getEhhHum() + "");
                    hums.add(temp.getEhhTem() + "");
                    times.add(temp.getEhhTime() + "");
                }

                Intent intent = new Intent(getActivity(), DeviceHistoryActivity.class);
                //传递显示的数据
                intent.putStringArrayListExtra("temps", temps);
                intent.putStringArrayListExtra("hums", hums);
                intent.putStringArrayListExtra("times", times);
                intent.putExtra("markUnit",  waterPressureRealBeans.get(position).getEhmDeviceName());
                intent.putExtra("linesText", "水位计");

                //传递曲线的单位
                ArrayList<String> suffixList = new ArrayList<>();
                suffixList.add("");
                intent.putStringArrayListExtra("suffixList", suffixList);

                intent.putExtra("title", waterPressureRealBeans.get(position).getEhmDeviceName());
                startActivity(intent);
                return;
            }

            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            SoilmoistureRealBean b = waterPressureRealBeans.get(position);
            //设置数据
            realDataUtil.setInnerMax(String.valueOf(b.getEhmMaxHum()));
            realDataUtil.setInnerData(String.valueOf(b.getEhmHum()));
            //设置报警值，必须先设置最大值，再设置当前值，才不会出现问题
            realDataUtil.setOuterMax(String.valueOf(b.getEhmMaxTem()));
            realDataUtil.setOuterData(String.valueOf(b.getEhmTem()));
            //设置文案
            realDataUtil.setInnerTitle("水分 %RH");
            realDataUtil.setOuterTitle("温度 ℃");
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
    public Class<SoilmoistureRealBean> getParseClass() {
        return SoilmoistureRealBean.class;
    }
    @Override
    public void bindHistoryData(BaseViewHolder holder, SoilmoistureHistoryBean data, int position) {
        TextView historyName = holder.getView(com.example.pool.R.id.new_history_name);
        TextView historyTemp = holder.getView(com.example.pool.R.id.new_history_temp);
        TextView historyHum = holder.getView(com.example.pool.R.id.new_history_hum);
        TextView historyTime = holder.getView(com.example.pool.R.id.new_history_time);
        Button loogQx = holder.getView(com.example.pool.R.id.look_qx);

        historyTemp.setText("水流量" + data.getEhhHum());
        historyTime.setText("时间" + data.getEhhTime());


        loogQx.setOnClickListener((v) -> {
            onItemClickListener(holder.getRootView(), position);
        });
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(waterPressureRealBeans.get(position).getEhmId());
        svHolder.deleteDevice(ehmId, position);
    }


    @Override
    public void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {
        //设置图标
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(svHolder.imageView);

        //在这里设置视图
        List<TextView> textViewList = new ArrayList<>();

        svHolder.manageDeviceName.setText(data.getDeviceName());

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
     * 获取设备ID，查询报警，历史
     *
     * @return
     */
    @Override
    public int getEhmID() {
        return this.ehmIDFirst;
    }

    /**
     * 实时数据列表的适配器回调到View类进行操作
     * @param holder
     * @param data
     * @param position
     */
    @Override
    public void realItemAdapter(BaseViewHolder holder, SoilmoistureRealBean data, int position) {

        TextView item_name = holder.getView(com.example.pool.R.id.dissolved_item_name);
        item_name.setText(data.getEhmDeviceName());
        item_name.setOnClickListener((v)->{

            onItemClickListener(holder.getRootView(), position);

        });
    }

    /**
     点击修改按钮时会跳转到编辑页
     **/
    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();


        SoilmoistureRealBean temp = waterPressureRealBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);


        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName(temp.getEhmDeviceName());
        deviceManageEditBean.setActionID(temp.getEhmId());


        deviceManageEditBean.setaDeviceInterval(temp.getIntervalTime());
//
        deviceManageEditBean.setTempRangeMax(temp.getEhmMaxTem());
        deviceManageEditBean.setTempRangeMin(temp.getEhmMinTem());

        deviceManageEditBean.setHumRangeMax(temp.getEhmMaxHum());
        deviceManageEditBean.setHumRangeMin(temp.getEhmMaxHum());

        deviceManageEditBean.setShowFunctionCode(true);
        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);
        deviceManageEditBean.setShowDeviceGallery(true);

        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }
    /**
     点击添加设备按钮时会跳转到编辑页
     **/
    @Override
    public void addNewDevice(String flag) {

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");

        deviceManageEditBean.setaDeviceInterval(30);

        deviceManageEditBean.setTempRangeMin(0);
        deviceManageEditBean.setHumRangeMin(0);

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);
        deviceManageEditBean.setShowFunctionCode(true);
        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

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
        device8060Present.queryRealData(SoilmoistureRealBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(SoilmoistureRealBean.class);
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(SoilmoistureRealBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();

        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(SoilmoistureRealBean.class);
        }, 1000);


    }

    @Override
    public void updateRealView(TextView textView, SoilmoistureRealBean data, int position) {
        //设置列表的数据
        textView.setText(data.getEhmDeviceName());
    }
}
