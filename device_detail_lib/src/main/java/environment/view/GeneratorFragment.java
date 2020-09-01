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

import com.bumptech.glide.Glide;
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
import environment.bean.GeneratorAlertBean;
import environment.bean.GeneratorHistoryBean;
import environment.bean.GeneratorRealBean;
import util.DateUtil;
import util.LainNewApi;
import view.Device8060Fragment;
import view.SingleHistoryActivity;
import view.i_view.I_RealAdapterListen;
import view_interface.I_DeviceDetailManageLink;
import view_interface.I_HistoryAdapter;

/**
 * @ClassName: GeneratorFragment
 * @Description: 发电机
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 15:14
 */
public class GeneratorFragment extends Device8060Fragment<GeneratorRealBean, GeneratorAlertBean,
        GeneratorHistoryBean, GeneratorRealBean> implements BaseRecyclerViewAdapter.OnItemClickListener, I_DeviceDetailManageLink<Device_Detail_AddBean>, I_HistoryAdapter<GeneratorHistoryBean>, I_RealAdapterListen<GeneratorRealBean> {

    private UltimateRecyclerView waterPressureRealRecycler;

    List<GeneratorRealBean> waterPressureRealBeans;

    //-------------------------------------------------
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, GeneratorRealBean> manageAdapter;

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
    NewHistoryAdapter<GeneratorHistoryBean> newHistoryAdapter;
    List<GeneratorHistoryBean> lineDataList = new ArrayList<>();
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
    public GeneratorFragment(String realLink, String alertLink, String historyLink, String manageLink) {
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

//        //绑定温度
//        waterLevel_cp1 = viewList.get(0).findViewById(com.example.pool.R.id.dissolved_temp_cpv1);
//        waterLevel_cp1.setMunit(" mg/L");
//        waterLevel_cp1.setmHint("当前水位");
//        //绑定湿度
//        waterLevel_cp2 = viewList.get(0).findViewById(com.example.pool.R.id.dissolved_hum_cpv2);

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
        device8060Present.queryRealData(GeneratorRealBean.class);
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
        //发电机 Layout
        viewList.add(R.layout.dissolved_real);
        viewList.add(com.example.pool.R.layout.dissolved_alert);
        viewList.add(com.example.pool.R.layout.dissolved_history);
        viewList.add(com.example.pool.R.layout.dissolved_manage);
        return viewList;

    }

    @Override
    public void queryHistoryData() {
        try {

            //默认查询第一个设备
            GeneratorRealBean temperatureBean = waterPressureRealBeans.get(0);
            ///获取设备的ID
            
            int temEhmId = temperatureBean.getAlternatorId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(GeneratorHistoryBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(GeneratorHistoryBean.class, ehmID, startTime, endTime);
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
                //默认选中第一个
//                onItemClickListener(getView(), 0);
            }

            //如果获取数据成功，则显示数据展示区域，在该设备中不需要这个控件
//            if (waterLevel_cp1.getVisibility() == View.GONE) {
//                waterLevel_cp1.setVisibility(View.VISIBLE);
//                waterLevel_cp2.setVisibility(View.GONE);
//            }

            //数据为空时才更新
            if (alertDeviceBeanList.isEmpty()) {
                //每次查询都保存第一个设备ID
                ehmIDFirst = waterPressureRealBeans.get(0).getAlternatorId();
                //更新报警查询的设备
                for (GeneratorRealBean b :
                        waterPressureRealBeans) {
                    AlertDeviceBean alertB = new AlertDeviceBean();
                    alertB.setName(b.getAlternatorName());

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
            GeneratorRealBean b = waterPressureRealBeans.get(position);

            if (b.getAlternatorAvol() == currentTempValue)
                return;

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestAlert() {

        List<GeneratorAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            GeneratorAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getInfo());
            alertBeansItem.setEhaTime(alertBean.getAlternatorAlarmTime());
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

        List<GeneratorRealBean> manageData = getManageData();

//        适配添加设备的数据
        for (GeneratorRealBean b :
                manageData) {

            String ip = "";
            String port = String.valueOf("");

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getAlternatorName());

            bean.setEhmId(String.valueOf(b.getAlternatorId()));

            //A相
            bean.setaCur(b.getAlternatorMinAcur() + " ~ " + b.getAlternatorMinAcur());
            bean.setaVol(b.getAlternatorMinAvol() + " ~ " + b.getAlternatorMaxAvol());
            //B相
            bean.setbCur(b.getAlternatorMinBcur() + " ~ " + b.getAlternatorMinBcur());
            bean.setbVol(b.getAlternatorMinBvol() + " ~ " + b.getAlternatorMaxBvol());
            //C相
            bean.setcCur(b.getAlternatorMinCcur() + " ~ " + b.getAlternatorMinCcur());
            bean.setcVol(b.getAlternatorMinCvol() + " ~ " + b.getAlternatorMaxCvol());

            //位置
            bean.setPosition(String.valueOf(b.getAlternatorAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getIntervalTime()));
            bean.setIp("");
            addBeans.add(bean);

        }




//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryDeviceManage() {
        if (addBeans.size() == 0)
            device8060Present.queryDeviceManage(GeneratorRealBean.class);
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(GeneratorAlertBean.class, ehmID, startTime, endTime);
    }
    @Override
    public void onItemClickListener(View v, int position) {

        try {
            //报警数据中选择设备查询时的回调
            if (v.findViewById(com.example.pool.R.id.btn_panel_device) != null) {
                //先保存数据，点击确定后在进行查询
                setEhmID(waterPressureRealBeans.get(position).getAlternatorId());
                return;
            }
            //历史数据点击查看 曲线图
            if (v.findViewById(com.example.pool.R.id.new_history_name) != null) {

                ArrayList<String> temps = new ArrayList<>();
                ArrayList<String> hums = new ArrayList<>();
                ArrayList<String> times = new ArrayList<>();

                for (GeneratorHistoryBean temp :
                        lineDataList) {

                    temps.add(temp.getAlternatorAvol() + "");
                    hums.add(temp.getAlternatorHistoryTime() + "");
                }

                Intent intent = new Intent(getActivity(), SingleHistoryActivity.class);
                //传递显示的数据
                intent.putStringArrayListExtra("temps", temps);
                intent.putStringArrayListExtra("times", hums);
                intent.putExtra("markUnit",  waterPressureRealBeans.get(position).getAlternatorName());
                intent.putExtra("linesText", "水位计");

                //传递曲线的单位
                ArrayList<String> suffixList = new ArrayList<>();
                suffixList.add("");
                intent.putStringArrayListExtra("suffixList", suffixList);

                intent.putExtra("title", waterPressureRealBeans.get(position).getAlternatorName());
                startActivity(intent);
                return;
            }

            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            GeneratorRealBean b = waterPressureRealBeans.get(position);

            //保存当前点击的位置，用于实时刷新
            currentDevice = position;
            //记录当前设备的数据
            currentTempValue = b.getAlternatorAvol();       //保存PH值
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
    public Class<GeneratorRealBean> getParseClass() {
        return GeneratorRealBean.class;
    }
    @Override
    public void bindHistoryData(BaseViewHolder holder, GeneratorHistoryBean data, int position) {
        TextView historyName = holder.getView(com.example.pool.R.id.new_history_name);
        TextView historyTemp = holder.getView(com.example.pool.R.id.new_history_temp);
        TextView historyHum = holder.getView(com.example.pool.R.id.new_history_hum);
        TextView historyTime = holder.getView(com.example.pool.R.id.new_history_time);
        Button loogQx = holder.getView(com.example.pool.R.id.look_qx);

        historyTemp.setText("水流量" + data.getAlternatorAvol());
        historyTime.setText("时间" + data.getAlternatorHistoryTime());


        loogQx.setOnClickListener((v) -> {
            onItemClickListener(holder.getRootView(), position);
        });
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(waterPressureRealBeans.get(position).getAlternatorId());
        svHolder.deleteDevice(ehmId, position);
    }

    /**
     * 修改设备
     * @param flag 需要提供设备的名称
     * @param svHolder 修改设备的 View
     */
    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setActionID(waterPressureRealBeans.get(position).getAlternatorId());

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
    public void withBindDeviceManage(adapter.BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {
        //设置图标
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(svHolder.imageView);

        //在这里设置视图
        List<TextView> textViewList = new ArrayList<>();

        svHolder.manageDeviceName.setText(data.getDeviceName());

        TextView devicePos = new TextView(getActivity());
        devicePos.setText("设备地址：" + data.getPosition());
        textViewList.add(devicePos);

        TextView aCur = new TextView(getActivity());
        aCur.setText("A相电流区间：" + data.getaCur());
        textViewList.add(aCur);

        TextView aVol = new TextView(getActivity());
        aVol.setText("A相电压区间：" + data.getaVol());
        textViewList.add(aVol);

        TextView bCur = new TextView(getActivity());
        bCur.setText("B相电流区间：" + data.getbCur());
        textViewList.add(bCur);

        TextView bVol = new TextView(getActivity());
        bVol.setText("B相电压区间：" + data.getbVol());
        textViewList.add(bVol);

        TextView cCur = new TextView(getActivity());
        cCur.setText("C相电流区间：" + data.getcCur());
        textViewList.add(cCur);

        TextView cVol = new TextView(getActivity());
        cVol.setText("C相电压区间：" + data.getcVol());
        textViewList.add(cVol);

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
    public void realItemAdapter(BaseViewHolder holder, GeneratorRealBean data, int position) {

        TextView item_name = holder.getView(R.id.gennerator_name);
        TextView a_electric = holder.getView(R.id.a_electric);
        TextView a_voltage = holder.getView(R.id.a_voltage);
        TextView b_electric = holder.getView(R.id.b_electric);
        TextView b_voltage = holder.getView(R.id.b_voltage);
        TextView c_electric = holder.getView(R.id.c_electric);
        TextView c_voltage = holder.getView(R.id.c_voltage);

        item_name.setText(data.getAlternatorName());
        //A相
        a_electric.setText(String.valueOf(data.getAlternatorAcur()));
        a_voltage.setText(String.valueOf(data.getAlternatorAvol()));
        //B相
        b_electric.setText(String.valueOf(data.getAlternatorBcur()));
        b_voltage.setText(String.valueOf(data.getAlternatorBvol()));
        //C相
        c_electric.setText(String.valueOf(data.getAlternatorCcur()));
        c_voltage.setText(String.valueOf(data.getAlternatorCvol()));
    }


}
