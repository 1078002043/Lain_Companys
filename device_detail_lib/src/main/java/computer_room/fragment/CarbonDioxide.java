package computer_room.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import action.SaveInterface;
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.bean.CarbonAlertBean;
import computer_room.bean.CarbonDioxideBean;
import computer_room.i_interface.I_CarbonDioxide;
import computer_room.present.CarbonDioxidePresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;
import util.RealDataUtil;
import view_interface.I_UpdateRealView;

/**
 * 二氧化碳
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 21 : 27
 */
public class CarbonDioxide extends Device8060Fragment<CarbonDioxideBean, CarbonAlertBean, CarbonDioxideBean, CarbonDioxideBean> implements I_CarbonDioxide,
        I_DeviceDetailManageLink<Device_Detail_AddBean>, I_UpdateRealView<CarbonDioxideBean> {

    //二氧化碳的 Presenter
    private CarbonDioxidePresenter presenter;

    //二氧化碳数据
    private List<CarbonDioxideBean> carbonBean = new ArrayList<>();
    //设备管理
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, CarbonDioxideBean> manageAdapter;

    //精密空调实时数据列表
    private UltimateRecyclerView carbonRecycler;

    private int ehmIDFirst;

    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    //设备实时数据工具类
    public RealDataUtil<CarbonDioxideBean> realDataUtil;

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public CarbonDioxide(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Present
        presenter = new CarbonDioxidePresenter();

        //初始化报警面板，由子类来实现
        initAlert(1);
        initDeviceManage(2, "二氧化碳");

        //实时数据列表绑定
//        carbonRecycler = viewList.get(0).findViewById(R.id.carbon_dioxide_realtime_recycler);
//        //设备列表
//        carbanAdapter = new CarbanDioxideAdapter(getActivity(), carbonBean, R.layout.carbon_dioxide_template);
//        carbonRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        carbonRecycler.setAdapter(carbanAdapter);

        //设备管理的列表
        String deleteUrl = LainNewApi.carbonDelete;
        String changeUrl = LainNewApi.carbonChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans, carbonBean, getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl, this, "二氧化碳");
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //只显示实时数据，设备管理
        setToNavShow(true, true, false, true);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

        device8060Present.queryRealData(CarbonDioxideBean.class);

        //初始化实时数据工具类
        initRealDataUtil();

    }

    private void initRealDataUtil() {
        realDataUtil = new RealDataUtil<>();
        realDataUtil.initView(viewList.get(0), getActivity(), carbonBean, this, this);
    }

    /**
     * 一定要重写这个方法 返回 ViewPager 的 View，才能初始化 ViewPager
     *
     * @return ViewPager Layout ID
     */
    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();

        //实时数据
        viewList.add(R.layout.base_real_data);
        //报警数据
        viewList.add(R.layout.temperture_alarm);
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
        carbonBean.clear();
        carbonBean.addAll(getRealData());

        //报警列表数据，只更新一次
        if (alertDeviceBeanList.size() == 0) {

            //刷新数据
            realDataUtil.notifyRealDataSetChanged();
            //设置实时数据
            onItemClickListener(getView(), 0);

            ehmIDFirst = carbonBean.get(0).getEcmId();
            //更新报警面板列表
            initAlertView(carbonBean);
        }

    }

    @Override
    public void onItemClickListener(View v, int position) {

        //点击切换上面的圆形进度条的数据
        //设置相关的属性值
        CarbonDioxideBean b = carbonBean.get(position);
        //设置数据
        realDataUtil.setInnerMax(String.valueOf(b.getEcmAlarmData()));
        realDataUtil.setInnerData(String.valueOf(b.getEcmCurrentData()));
        //设置报警值，必须先设置最大值，再设置当前值，才不会出现问题
        realDataUtil.setOuterMax(String.valueOf(b.getEcmAlarmData()));
        realDataUtil.setOuterData(String.valueOf(b.getEcmAlarmData()));
        //设置文案
        realDataUtil.setInnerTitle("浓度");
        realDataUtil.setOuterTitle("报警值");

    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            CarbonDioxideBean conditionerBean = carbonBean.get(0);
            ///获取设备的ID
            int temEhmId = conditionerBean.getEcmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(CarbonAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(CarbonAlertBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void requestAlert() {
        List<CarbonAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            CarbonAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getEcaInfo());
            alertBeansItem.setEhaTime(alertBean.getEcaTime());
            alertBeansItem.setEcmDeviceName(alertBean.getEcmName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryDeviceManage() {

    }

    @Override
    public void queryHistoryData() {
        //只执行一次
        if (addBeans.size() == 0)
            //因为历史数据的位置是设备管理，因此需要在该地方请求设备管理的数据
            device8060Present.queryDeviceManage(CarbonDioxideBean.class);
    }

    @Override
    public void requestHistory() {

    }

    @Override
    public void requestManage() {

        Log.d("lkjfdsf", "requestManage: run");

//        先清空原有的设备数据
        addBeans.clear();

        List<CarbonDioxideBean> manageData = getManageData();
//        适配添加设备的数据
        for (CarbonDioxideBean b :
                manageData) {

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getEcmName());

            bean.setEhmId(String.valueOf(b.getEcmId()));

            //位置
            bean.setPosition(String.valueOf(b.getEcmAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getEcmStatus()));
            bean.setAlertValue(String.valueOf(b.getEcmAlarmData()));
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();

    }

    //实时数据
    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {

    }

    /**
     * 更新报警面板的设备列表
     *
     * @param alertDevice
     */
    public void initAlertView(List<CarbonDioxideBean> alertDevice) {

        alertDeviceBeanList.clear();
        ehmIDFirst = alertDevice.get(0).getEcmId();
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (CarbonDioxideBean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getEcmName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(carbonBean.get(position).getEcmId());
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
        deviceAlert.setText("报警值：" + data.getAlertValue());
        textViewList.add(deviceAlert);

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
    public List<MaterialEditText> setDeviceAddEdit() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        DynamicMaterialEdit.getInstance().setLayoutInflater(inflater, getActivity());
        List<MaterialEditText> editTexts = new ArrayList<>();

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备名称", "");
        MaterialEditText address = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备地址", "");
        MaterialEditText alert = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "报警值", "");

        editTexts.add(name);
        editTexts.add(address);
        editTexts.add(alert);

        return editTexts;
    }

    @Override
    public void addDeviceAction(List<MaterialEditText> materialEditTexts) {

        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("ecmAddress", materialEditTexts.get(0).getText().toString());

        formBody.put("ecmName", materialEditTexts.get(1).getText().toString());
        //必须获取 ehmID 才能修改
        formBody.put("ecmAlarmData", materialEditTexts.get(2).getText().toString());
        formBody.put("diId", materialEditTexts.get(2).getText().toString());
        formBody.put("gId", materialEditTexts.get(2).getText().toString());
        //解析MAP 为 JSON

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.carbonInsert, MyGson.getInstance().toJson(formBody), this);

    }

    @Override
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {
        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("ecmName", materialEditTexts.get(0).getText().toString());

        formBody.put("ecmAlarmData", materialEditTexts.get(2).getText().toString());
        //必须获取 ehmID 才能修改
        formBody.put("ecmId", String.valueOf(actionID));
        //解析MAP 为 JSON

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.carbonChange, MyGson.getInstance().toJson(formBody), this);
    }

    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        CarbonDioxideBean temp = carbonBean.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();

        deviceManageEditBean.setaDeviceName(temp.getEcmName());
        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getEcmId());

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd(flag);
        deviceManageEditBean.setaDeviceAlert(temp.getEcmAlarmData());


        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }

    @Override
    public void addNewDevice(String flag) {

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();

        deviceManageEditBean.setaDeviceName("");

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd(flag);
        deviceManageEditBean.setaDeviceAlert(0);

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
        formBody.put("ecmAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("ecmName", getTextString(keyArray.get("deviceName")));
        formBody.put("ecmAlarmData", getTextString(keyArray.get("alertValue")));
        formBody.put("diId", getTextString(keyArray.get("diId")));
        formBody.put("gId", getTextString(keyArray.get("gId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.carbonInsert, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 提交修改设备数据时的回调
     *
     * @param keyArray   EditText 的储存集合
     * @param spinnerMap Spinner 的储存集合
     */
    @Override
    public void deterChange(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap) {

        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("ecmId", getTextString(keyArray.get("actionID")));
        formBody.put("ecmName", getTextString(keyArray.get("deviceName")));
        formBody.put("ecmAlarmData", getTextString(keyArray.get("alertValue")));

        //解析MAP 为 JSON
        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.carbonChange, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(CarbonDioxideBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(CarbonDioxideBean.class);
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(CarbonDioxideBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();

        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(CarbonDioxideBean.class);
        }, 1000);


    }

    @Override
    public void updateRealView(TextView textView, CarbonDioxideBean data, int position) {
        //设置列表的数据
        textView.setText(data.getEcmName());
    }
}
