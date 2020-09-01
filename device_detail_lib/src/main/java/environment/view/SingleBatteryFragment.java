package environment.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.device_detail_lib.R;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;

import java.util.ArrayList;
import java.util.List;

import action.ActionControl;
import adapter.BaseDeviceDetailManageAdapter;
import adapter.NewHistoryAdapter;
import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import environment.bean.SingleBatteryAlertBean;
import environment.bean.SingleBatteryHistoryBean;
import environment.bean.SingleBatteryRealBean;
import util.DateUtil;
import view.Device8060Fragment;
import view.SingleHistoryActivity;
import view.i_view.I_RealAdapterListen;
import view_interface.I_DeviceDetailManageLink;
import view_interface.I_HistoryAdapter;

/**
 * @ClassName: SingleBatteryFragment
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 14:59
 */
public class SingleBatteryFragment extends Device8060Fragment<SingleBatteryRealBean, SingleBatteryAlertBean,
        SingleBatteryHistoryBean, SingleBatteryRealBean> implements BaseRecyclerViewAdapter.OnItemClickListener, I_DeviceDetailManageLink<Device_Detail_AddBean>, I_HistoryAdapter<SingleBatteryHistoryBean>, I_RealAdapterListen<SingleBatteryRealBean> {

    private UltimateRecyclerView waterPressureRealRecycler;

    List<SingleBatteryRealBean> waterPressureRealBeans;

    //-------------------------------------------------
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, SingleBatteryRealBean> manageAdapter;

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
    NewHistoryAdapter<SingleBatteryHistoryBean> newHistoryAdapter;
    List<SingleBatteryHistoryBean> lineDataList = new ArrayList<>();
    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();
    private static LayoutInflater layoutInflater;

    //记录当前查看的设备
    private int currentDevice = 0;
    //记录当前的值，如果数值没发生改变，则不更新UI
    private double currentTempValue = 0;     //温度
    private double currentHumValue = 0;      //湿度

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public SingleBatteryFragment(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设备列表
        waterPressureRealRecycler = viewList.get(0).findViewById(com.example.pool.R.id.dissolved_real_recycler);
        //初始化适配器
        waterPressureRealBeans = new ArrayList<>();

        waterPressureRealRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));


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
        device8060Present.queryRealData(SingleBatteryRealBean.class);
//        startRealUpdate();
        //设置历史数据列表
        newHistoryAdapter = new NewHistoryAdapter<>(getActivity(), lineDataList, com.example.pool.R.layout.new_history_temp, this);
        newHistoryAdapter.setItemClickListener(this);
        newHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        newHistoryList.setAdapter(newHistoryAdapter);

    }

    //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
    private void initModelAdapter() {

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);
    }

    @Override
    public List<Integer> getViewPagerView() {


        ArrayList<Integer> viewList = new ArrayList<>();
        viewList.add(com.example.pool.R.layout.dissolved_real);
        viewList.add(com.example.pool.R.layout.dissolved_alert);
        viewList.add(com.example.pool.R.layout.dissolved_history);
        viewList.add(com.example.pool.R.layout.dissolved_manage);
        return viewList;

    }

    @Override
    public void queryHistoryData() {
        try {

            //默认查询第一个设备
            SingleBatteryRealBean temperatureBean = waterPressureRealBeans.get(0);
            ///获取设备的ID

            int temEhmId = temperatureBean.getBatteryId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(SingleBatteryHistoryBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(SingleBatteryHistoryBean.class, ehmID, startTime, endTime);
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

                refreshDeviceList = false;

            } else {
                //点击某个设备之后，就只更新点击后的设备的数据
                setCurrentData(currentDevice);
            }

            //数据为空时才更新
            if (alertDeviceBeanList.isEmpty()) {
                //每次查询都保存第一个设备ID
                ehmIDFirst = waterPressureRealBeans.get(0).getBatteryId();
                //更新报警查询的设备
                for (SingleBatteryRealBean b :
                        waterPressureRealBeans) {
                    AlertDeviceBean alertB = new AlertDeviceBean();

                    alertB.setName(b.getBatteryName());
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
            SingleBatteryRealBean b = waterPressureRealBeans.get(position);

            if (b.getBatteryData() == currentTempValue)
                return;

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestAlert() {

        List<SingleBatteryAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            SingleBatteryAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getInfo());
            alertBeansItem.setEhaTime(alertBean.getBatteryAlarmTime());
            alertBeansItem.setEcmDeviceName("");
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

        List<SingleBatteryRealBean> manageData = getManageData();

//        适配添加设备的数据
        for (SingleBatteryRealBean b :
                manageData) {

            String ip = "";
            String port = String.valueOf("");

            Device_Detail_AddBean bean = new Device_Detail_AddBean();

            bean.setDeviceName(b.getBatteryName());

            bean.setEhmId(String.valueOf(b.getBatteryId()));
            //湿度
            bean.setAlertValue(b.getBatteryMinData() + " ~ " + b.getBatteryMaxData());

            //位置
            bean.setPosition(String.valueOf(b.getBatteryAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getIntervalTime()));
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryDeviceManage() {
        //只执行一次
        if (addBeans.size() == 0)
            device8060Present.queryDeviceManage(SingleBatteryRealBean.class);
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(SingleBatteryAlertBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void onItemClickListener(View v, int position) {

        try {
            //报警数据中选择设备查询时的回调
            if (v.findViewById(com.example.pool.R.id.btn_panel_device) != null) {
                //先保存数据，点击确定后在进行查询
                setEhmID(waterPressureRealBeans.get(position).getBatteryId());
                return;
            }
            //历史数据点击查看 曲线图
            if (v.findViewById(com.example.pool.R.id.new_history_name) != null) {

                ArrayList<String> temps = new ArrayList<>();
                ArrayList<String> hums = new ArrayList<>();
                ArrayList<String> times = new ArrayList<>();

                for (SingleBatteryHistoryBean temp :
                        lineDataList) {

                    temps.add(temp.getBatteryData() + "");
                    hums.add(temp.getBatteryHistoryTime() + "");
                }

                Intent intent = new Intent(getActivity(), SingleHistoryActivity.class);
                //传递显示的数据
                intent.putStringArrayListExtra("temps", temps);
                intent.putStringArrayListExtra("times", hums);
                intent.putExtra("markUnit", waterPressureRealBeans.get(position).getBatteryName());
                intent.putExtra("linesText", "水位计");

                //传递曲线的单位
                ArrayList<String> suffixList = new ArrayList<>();
                suffixList.add("");
                intent.putStringArrayListExtra("suffixList", suffixList);

                intent.putExtra("title", waterPressureRealBeans.get(position).getBatteryName());
                startActivity(intent);
                return;
            }

            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            SingleBatteryRealBean b = waterPressureRealBeans.get(position);

            //保存当前点击的位置，用于实时刷新
            currentDevice = position;
            //记录当前设备的数据
            currentTempValue = b.getBatteryData();       //保存PH值
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
    public Class<SingleBatteryRealBean> getParseClass() {
        return SingleBatteryRealBean.class;
    }

    @Override
    public void bindHistoryData(BaseViewHolder holder, SingleBatteryHistoryBean data, int position) {
        TextView historyName = holder.getView(com.example.pool.R.id.new_history_name);
        TextView historyTemp = holder.getView(com.example.pool.R.id.new_history_temp);
        TextView historyHum = holder.getView(com.example.pool.R.id.new_history_hum);
        TextView historyTime = holder.getView(com.example.pool.R.id.new_history_time);
        Button loogQx = holder.getView(com.example.pool.R.id.look_qx);

        historyTemp.setText("水流量" + data.getBatteryData());
        historyTime.setText("时间" + data.getBatteryHistoryTime());


        loogQx.setOnClickListener((v) -> {
            onItemClickListener(holder.getRootView(), position);
        });
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(waterPressureRealBeans.get(position).getBatteryId());
        svHolder.deleteDevice(ehmId, position);
    }

    /**
     * 修改设备
     *
     * @param flag     需要提供设备的名称
     * @param svHolder 修改设备的 View
     */
    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setActionID(waterPressureRealBeans.get(position).getBatteryId());

        List<String> classifyList = new ArrayList<>();
        classifyList.add("卫莱");
        classifyList.add("莱安");
        deviceManageEditBean.setClassifyArr(classifyList);

        List<String> ipList = new ArrayList<>();
        ipList.add("192.168.1.240");
        ipList.add("192.168.9.6");
        deviceManageEditBean.setIpArr(ipList);

        deviceManageEditBean.setShowDeviceAddress(true);
        deviceManageEditBean.setShowFunctionCode(true);

        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }

    /**
     * 添加设备
     *
     * @param flag
     */
    @Override
    public void addNewDevice(String flag) {
        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setaDeviceAlert(0);

        List<String> classifyList = new ArrayList<>();
        classifyList.add("卫莱");
        classifyList.add("莱安");
        deviceManageEditBean.setClassifyArr(classifyList);

        List<String> ipList = new ArrayList<>();
        ipList.add("192.168.1.240");
        ipList.add("192.168.9.6");
        deviceManageEditBean.setIpArr(ipList);

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);
    }

    @Override
    public void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {
        //设置图标
        svHolder.imageView.setImageResource(com.example.pool.R.drawable.icon);

        //在这里设置视图
        List<TextView> textViewList = new ArrayList<>();

        svHolder.manageDeviceName.setText(data.getDeviceName());

        TextView devicePos = new TextView(getActivity());
        devicePos.setText("设备地址：" + data.getPosition());
        textViewList.add(devicePos);

        TextView deviceIP = new TextView(getActivity());
        deviceIP.setText("IP地址：" + data.getIp());
        textViewList.add(deviceIP);

        TextView deviceAlert = new TextView(getActivity());
        deviceAlert.setText("范围区间：" + data.getAlertValue());
        textViewList.add(deviceAlert);

        TextView deviceInterval = new TextView(getActivity());
        deviceInterval.setText("保存间隔：" + data.getUpdateTime());
        textViewList.add(deviceInterval);

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
     *
     * @param holder
     * @param data
     * @param position
     */
    @Override
    public void realItemAdapter(BaseViewHolder holder, SingleBatteryRealBean data, int position) {

        TextView item_name = holder.getView(com.example.pool.R.id.dissolved_item_name);
        item_name.setText(data.getBatteryName());
        item_name.setOnClickListener((v) -> {

            onItemClickListener(holder.getRootView(), position);

        });
    }


}
