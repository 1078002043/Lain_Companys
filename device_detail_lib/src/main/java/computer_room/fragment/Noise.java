package computer_room.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.SaveInterface;
import base.BaseRecyclerViewAdapter;
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.bean.NoiseAlertBean;
import computer_room.bean.NoiseBean;
import computer_room.bean.NoiseHistoryBean;
import computer_room.i_interface.I_Noise;
import computer_room.present.NoisePresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.LainNewApi;
import util.RealDataUtil;
import view_interface.I_UpdateRealView;

/**
 * 噪声
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/12 17 : 24
 */
//extends Device8060Fragment<TemperatureBean, Temperature8060AlertBean,
//        TemperatureLineBean, TemperatureBean> implements I_Temperature,
//        BaseRecyclerViewAdapter.OnItemClickListener, I_DeviceDetailManageLink<Device_Detail_AddBean>

public class Noise extends Device8060Fragment<NoiseBean, NoiseAlertBean, NoiseHistoryBean,NoiseBean> implements
        I_Noise, BaseRecyclerViewAdapter.OnItemClickListener, I_DeviceDetailManageLink<Device_Detail_AddBean>,
        I_UpdateRealView<NoiseBean> {




    //LineChart 的数据
    private List<NoiseHistoryBean> lineChartList;

    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, NoiseBean> manageAdapter;

    //温湿度数据展示面板
    private LinearLayout temperature_top_data;
    //记录第一个 ehmID


    //删除URL
    String tempDeleted = "";
    String tempChange = "";

    //新风机数据列表
    private UltimateRecyclerView noise_recycler;
    //实时数据
    private List<NoiseBean> noiseBeans = new ArrayList<>();
    //Presenter
    private NoisePresenter presenter;

    //设备实时数据工具类
    public RealDataUtil<NoiseBean> realDataUtil;

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public Noise(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
        this.tempDeleted = tempDeleted;
        this.tempChange = tempChange;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        presenter = new NoisePresenter();


        //初始报警页面的控件
        initAlert(1);
        //初始化历史数据中的控件
        initHistoryFloatingButton(2);

        initDeviceManage(3, "噪声");


        //设备管理的列表
        String deleteUrl = LainNewApi.deleteNoise;
        String changeUrl = LainNewApi.changeNoise;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans,noiseBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl,this,"噪声");
//        precisionAirAdd = new PrecisionAirAdd(addBeans,precisionBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl);
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
        //设置显示的NAV
        setDeviceNav(true, true, true, true);

        //查询实时数据
        device8060Present.queryRealData(NoiseBean.class);
        newDeviceRecycler.setAdapter(manageAdapter);
        startRealUpdate();

        //初始化实时数据工具类
        initRealDataUtil();

    }

    private void initRealDataUtil() {
        realDataUtil = new RealDataUtil<>();
        realDataUtil.initView(viewList.get(0), getActivity(), noiseBeans, this, this);
    }

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
     * 实时获取数据
     */
    @Override
    public void run() {

    }

    @Override
    public void requestReal() {
        noiseBeans.clear();
        noiseBeans.addAll(getRealData());

        //报警数据
//        initAlertView(noiseBeans);

        //数据为空时才更新
        try {
            if (alertDeviceBeanList.isEmpty()) {

                //刷新数据
                realDataUtil.notifyRealDataSetChanged();
                //设置实时数据
                onItemClickListener(getView(), 0);

                //每次查询都保存第一个设备ID
                ehmIDFirst = noiseBeans.get(0).getEnmId();
                //更新报警查询的设备
                for (NoiseBean b :
                        noiseBeans) {
                    AlertDeviceBean alertB = new AlertDeviceBean();
                    alertB.setName(b.getNoiseName());
                    alertDeviceBeanList.add(alertB);
                }
                alertPanelDevice.notifyRealDataSetChanged();
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        getDeviceIP(LainNewApi.getInstance().getRootPath() + LainNewApi.noiseIP);

    }

    @Override
    public void requestAlert() {
        List<NoiseAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            NoiseAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getInfo());
            alertBeansItem.setEhaTime(alertBean.getTime());
            alertBeansItem.setEcmDeviceName(alertBean.getNoiseName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            NoiseBean temperatureBean = noiseBeans.get(0);
            ///获取设备的ID
            int temEhmId = temperatureBean.getEnmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(NoiseAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(NoiseHistoryBean.class, ehmID,startTime, endTime);
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(NoiseAlertBean.class,ehmID, startTime, endTime);
    }

    @Override
    public void requestHistory() {

        try {

            List<Integer> tempList = new ArrayList<>();
            List<Integer> humList = new ArrayList<>();
            List<String> timeList = new ArrayList<>();

            List<NoiseHistoryBean> lineDataList = getHistoryData();
            //清空数据
            tempList.clear();
            humList.clear();
            for (int i = 0; i < lineDataList.size(); i++) {

                tempList.add((int)lineDataList.get(i).getEnhNoise());

                humList.add(5);
                timeList.add(lineDataList.get(i).getEnhTime());
            }
            Collections.reverse(tempList);
        } catch (IndexOutOfBoundsException e) {
            Log.d("history", "initChartTemper: 未获取到历史数据");
        }
    }

    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();

        List<NoiseBean> manageData = getManageData();

//        适配添加设备的数据
        for (NoiseBean b :
                manageData) {

//            String ip = b.getNoiseAddress()+"";
//            String port = String.valueOf(b.get());

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getNoiseName());

            bean.setEhmId(String.valueOf(b.getEnmId()));
            //噪音大小
            bean.setHumidityMin(String.valueOf(b.getMinNoise()));
            bean.setHumidityMax(String.valueOf(b.getMaxNoise()));

            //位置
            bean.setPosition(String.valueOf(b.getNoiseAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getIntervalTime()));
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();

    }

    //实时数据
    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {
        noiseBeans.clear();
        noiseBeans.addAll(OkHttpUtil.getInstance().formatResponse(responseStr, NoiseBean.class));
        //报警数据
        initAlertView(noiseBeans);
//        presenter.dealAlertData(LainNewApi.NEW_IP + LainNewApi.TEMPRATURE_ALERT, HTTP_ALERT, this);
    }

    @Override
    public void queryHistoryData() {
        try {
            //默认查询第一个设备
            NoiseBean temperatureBean = noiseBeans.get(0);
            ///获取设备的ID
            int temEhmId = temperatureBean.getEnmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(NoiseHistoryBean.class, temEhmId,startTime,endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }
    }

    @Override
    public void queryDeviceManage() {
        device8060Present.queryDeviceManage(NoiseBean.class);
    }

    @Override
    public void onItemClickListener(View v, int position) {
        try {
            //报警数据中选择设备查询时的回调
            if (v.findViewById(com.example.base.R.id.btn_panel_device) != null) {
                //先保存数据，点击确定后在进行查询
                setEhmID(noiseBeans.get(position).getEnmId());

                return;
            }

            //点击切换上面的圆形进度条的数据
            //设置相关的属性值
            NoiseBean b = noiseBeans.get(position);
            //设置数据
            realDataUtil.setInnerData(String.valueOf(b.getNoiseData()));
            realDataUtil.setInnerMax(String.valueOf(b.getMaxNoise()));
            //设置报警值，必须先设置最大值，再设置当前值，才不会出现问题
            realDataUtil.setOuterMax(String.valueOf(b.getMaxNoise()));
            realDataUtil.setOuterData(String.valueOf(b.getMaxNoise()));
            //设置文案
            realDataUtil.setInnerTitle("分贝");
            realDataUtil.setOuterTitle("报警值");


        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更新报警面板的设备列表
     * @param alertDevice
     */
    public void initAlertView(List<NoiseBean> alertDevice) {

        alertDeviceBeanList.clear();

        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (NoiseBean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getNoiseName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(noiseBeans.get(position).getEnmId());
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

        TextView deviceIP = new TextView(getActivity());
        deviceIP.setText("IP地址：" + data.getIp());
        textViewList.add(deviceIP);

        TextView deviceAlert = new TextView(getActivity());
        deviceAlert.setText("噪音区间：" + data.getHumidityMin() + " - " + data.getHumidityMax());
        textViewList.add(deviceAlert);

        TextView interval = new TextView(getActivity());
        interval.setText("更新间隔：" + data.getSaveInterval());
        textViewList.add(interval);

        DynamicDeviceManage.getInstance().addManageItem(textViewList, svHolder.linearLayout);
    }

    /**
     点击修改按钮时会跳转到编辑页
     **/
    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        NoiseBean temp = noiseBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName(temp.getNoiseName());

        deviceManageEditBean.setNoiseRangeMin(temp.getMinNoise());
        deviceManageEditBean.setNoiseRangeMax(temp.getMaxNoise());

        deviceManageEditBean.setaDeviceInterval(temp.getIntervalTime());

        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getEnmId());

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

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


        deviceManageEditBean.setaDeviceAlert(30);
        deviceManageEditBean.setaDeviceInterval(30);

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

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
        Map<String, String> formBody = new HashMap<>();
        //将输入的参数添加到 FormBody 中，进行发送给服务器
        formBody.put("noiseName", getTextString(keyArray.get("deviceName")));
        formBody.put("noiseAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        //噪音最大，小值
        formBody.put("maxNoise", getTextString(keyArray.get("noiseMax")));
        formBody.put("minNoise", getTextString(keyArray.get("noiseMin")));

        formBody.put("intervalTime", getTextString(keyArray.get("interval")));
        formBody.put("diId", getTextString(keyArray.get("diId")));
        formBody.put("gId", getTextString(keyArray.get("gId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.insertNoise, MyGson.getInstance().toJson(formBody), this);

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
        Map<String, String> formBody = new HashMap<>();
        //将输入的参数添加到 FormBody 中，进行发送给服务器
        formBody.put("noiseName", getTextString(keyArray.get("deviceName")));
        formBody.put("maxNoise", getTextString(keyArray.get("noiseMax")));
        formBody.put("minNoise", getTextString(keyArray.get("noiseMin")));
        formBody.put("intervalTime", getTextString(keyArray.get("interval")));
        formBody.put("enmId", getTextString(keyArray.get("actionID")));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.changeNoise, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(NoiseBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(NoiseBean.class);
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(NoiseBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();

        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(NoiseBean.class);
        }, 1000);


    }

    /**
     * 获取设备ID，查询报警，历史
     * @return
     */
    @Override
    public int getEhmID() {
        return this.ehmIDFirst;
    }

    @Override
    public void updateRealView(TextView textView, NoiseBean data, int position) {
        //设置列表的数据
        textView.setText(data.getNoiseName());
    }
}
