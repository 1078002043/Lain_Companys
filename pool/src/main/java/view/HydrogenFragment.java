package view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.pool.R;

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
import bean.HydrogenAlertBean;
import bean.HydrogenHistoryBean;
import bean.HydrogenRealBean;
import present.HydrogenPresent;
import util.DateUtil;
import util.LainNewApi;
import util.Pool_API;
import util.RealDataUtil;
import util.ToastManage;
import view.i_view.I_Hydrogen;
import view_interface.I_DeviceDetailManageLink;
import view_interface.I_HistoryAdapter;
import view_interface.I_UpdateRealView;

/**
 * 硫化氢
 */
public class HydrogenFragment extends Device8060Fragment<HydrogenRealBean, HydrogenAlertBean,
        HydrogenHistoryBean, HydrogenRealBean> implements I_Hydrogen, BaseRecyclerViewAdapter.OnItemClickListener, I_DeviceDetailManageLink<Device_Detail_AddBean>, I_HistoryAdapter<HydrogenHistoryBean>, I_UpdateRealView<HydrogenRealBean> {

    //初始化 present
    private HydrogenPresent hyDrogenPresent;
    //设备列表数据
    private UltimateRecyclerView hyDrogenRecycler;

    //实时数据
    private List<HydrogenRealBean> hyDrogenRealBeans = new ArrayList<>();

    //设备实时数据工具类
    public RealDataUtil<HydrogenRealBean> realDataUtil;

    //-------------------------------------------------
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, HydrogenRealBean> manageAdapter;

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
    NewHistoryAdapter<HydrogenHistoryBean> newHistoryAdapter;
    List<HydrogenHistoryBean> lineDataList = new ArrayList<>();
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
    public HydrogenFragment(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Present
        hyDrogenPresent = new HydrogenPresent(this);

        //请求数据
        hyDrogenPresent.dealRealData(Pool_API.Pool_IP + Pool_API.hydrogen, "hyDrogen_real");

        //-------------------------------
        //初始报警页面的控件
        initAlert(1);
        //初始化历史数据中的控件
        initHistoryFloatingButton(2);

        initDeviceManage(3, "溶氧检测");


        //设备管理的列表
//        deleteUrl = LainNewApi.tempDelete;
//        changeUrl = LainNewApi.tempChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans, hyDrogenRealBeans, getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl, this, "溶氧检测");
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
        initModelAdapter();
        //设置显示的NAV
        setDeviceNav(true, true, true, true);
        //查询实时数据
        device8060Present.queryRealData(HydrogenRealBean.class);
//        startRealUpdate();
        //设置历史数据列表
        newHistoryAdapter = new NewHistoryAdapter<>(getActivity(), lineDataList, R.layout.new_history_temp, this);
        newHistoryAdapter.setItemClickListener(this);
        newHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        newHistoryList.setAdapter(newHistoryAdapter);


        //初始化实时数据工具类
        initRealDataUtil();

    }

    private void initRealDataUtil() {
        realDataUtil = new RealDataUtil<>();
        realDataUtil.initView(viewList.get(0), getActivity(), hyDrogenRealBeans, this, this);
    }

    //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
    private void initModelAdapter() {

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);
    }

    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        viewList.add(R.layout.base_real_data);
        viewList.add(R.layout.dissolved_alert);
        viewList.add(R.layout.dissolved_history);
        viewList.add(R.layout.dissolved_manage);
        return viewList;
    }

    @Override
    public void queryHistoryData() {
        try {

            //默认查询第一个设备
            HydrogenRealBean temperatureBean = hyDrogenRealBeans.get(0);
            ///获取设备的ID
            int temEhmId = temperatureBean.getHydrogenSulfideId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(HydrogenHistoryBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(HydrogenHistoryBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void run() {

    }

    @Override
    public void requestReal() {
        //更新数据

        try {
            //先清空
            hyDrogenRealBeans.clear();
            //更新所有的数据，必须调用 getRealData()，才能获取到数据，必须使用 addAll 才能更新列表
            hyDrogenRealBeans.addAll(getRealData());

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
                ehmIDFirst = hyDrogenRealBeans.get(0).getHydrogenSulfideId();
                //更新报警查询的设备
                for (HydrogenRealBean b :
                        hyDrogenRealBeans) {
                    AlertDeviceBean alertB = new AlertDeviceBean();
                    alertB.setName(b.getHydrogenSulfideName());
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
            HydrogenRealBean b = hyDrogenRealBeans.get(position);

            if (b.getHydrogenSulfideData() == currentTempValue)
                return;

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestAlert() {

        List<HydrogenAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            HydrogenAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getInfo());
            alertBeansItem.setEhaTime(alertBean.getHydrogenSulfideAlarmTime());
            alertBeansItem.setEcmDeviceName(alertBean.getHydrogenSulfideName());
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

        List<HydrogenRealBean> manageData = getManageData();

//        适配添加设备的数据
        for (HydrogenRealBean b :
                manageData) {

            String ip = "";
            String port = String.valueOf("");

            Device_Detail_AddBean bean = new Device_Detail_AddBean();

            bean.setDeviceName(b.getHydrogenSulfideName());

            bean.setEhmId(String.valueOf(b.getHydrogenSulfideId()));
            //湿度
            bean.setAlertValue(b.getHydrogenSulfideMinData() + " ~ " + b.getHydrogenSulfideMaxData());

            //位置
            bean.setPosition(String.valueOf(b.getHydrogenSulfideAddress()));
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
            device8060Present.queryDeviceManage(HydrogenRealBean.class);
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(HydrogenAlertBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void onItemClickListener(View v, int position) {

        try {
            //报警数据中选择设备查询时的回调
            if (v.findViewById(R.id.btn_panel_device) != null) {
                //先保存数据，点击确定后在进行查询
                setEhmID(hyDrogenRealBeans.get(position).getHydrogenSulfideId());
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

                for (HydrogenHistoryBean temp :
                        lineDataList) {
                    temps.add(temp.getHydrogenSulfideData() + "");
                    hums.add(temp.getHydrogenSulfideHistoryTime() + "");
                }

                Intent intent = new Intent(getActivity(), SingleHistoryActivity.class);
                //传递显示的数据
                intent.putStringArrayListExtra("temps", temps);
                intent.putStringArrayListExtra("times", hums);
                intent.putExtra("markUnit", hyDrogenRealBeans.get(position).getHydrogenSulfideName());
                intent.putExtra("linesText", "硫化氢");

                //传递曲线的单位
                ArrayList<String> suffixList = new ArrayList<>();
                suffixList.add("");
                intent.putStringArrayListExtra("suffixList", suffixList);

                intent.putExtra("title", hyDrogenRealBeans.get(position).getHydrogenSulfideName());
                startActivity(intent);
                return;
            }

            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            HydrogenRealBean b = hyDrogenRealBeans.get(position);
            //设置数据
            realDataUtil.setInnerMax(String.valueOf(b.getHydrogenSulfideMaxData()));
            realDataUtil.setInnerData(String.valueOf(b.getHydrogenSulfideData()));
            //设置报警值，必须先设置最大值，再设置当前值，才不会出现问题
            realDataUtil.setOuterMax(String.valueOf(b.getHydrogenSulfideMaxData()));
            realDataUtil.setOuterData(String.valueOf(b.getHydrogenSulfideMaxData()));
            //设置文案
            realDataUtil.setInnerTitle("气体 %LEL");
            realDataUtil.setOuterTitle("报警值");
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
    public Class<HydrogenRealBean> getParseClass() {
        return HydrogenRealBean.class;
    }

    @Override
    public void bindHistoryData(BaseViewHolder holder, HydrogenHistoryBean data, int position) {
        TextView historyName = holder.getView(R.id.new_history_name);
        TextView historyTemp = holder.getView(R.id.new_history_temp);
        TextView historyHum = holder.getView(R.id.new_history_hum);
        TextView historyTime = holder.getView(R.id.new_history_time);
        ImageView historyImage = holder.getView(R.id.history_image_list);
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(historyImage);
        Button loogQx = holder.getView(R.id.look_qx);

        historyTemp.setText("硫化氢" + data.getHydrogenSulfideData());
        historyTime.setText("时间" + data.getHydrogenSulfideHistoryTime());


        loogQx.setOnClickListener((v) -> {
            onItemClickListener(holder.getRootView(), position);
        });
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(hyDrogenRealBeans.get(position).getHydrogenSulfideId());
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
        deviceManageEditBean.setActionID(hyDrogenRealBeans.get(position).getHydrogenSulfideId());

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
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(svHolder.imageView);

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


    @Override
    public void hyDrogenRealComplete(List<HydrogenRealBean> hyDrogenRealBeans) {


    }

    @Override
    public void updateRealView(TextView textView, HydrogenRealBean data, int position) {
        //设置列表的数据
        textView.setText(data.getHydrogenSulfideName());
    }
}
