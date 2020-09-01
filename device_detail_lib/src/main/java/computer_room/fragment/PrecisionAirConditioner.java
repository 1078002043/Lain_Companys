package computer_room.fragment;

import android.app.AlertDialog;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import computer_room.adapter.PrecisionConditionerAdapter;
import computer_room.bean.PrecisionAirAlertBean;
import computer_room.bean.Precision_Air_ConditionerBean;
import computer_room.i_interface.I_PrecisionAir;
import computer_room.present.PrecisionAirPresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;

/**
 * 精密空调
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/9 08 : 34
 */
public class PrecisionAirConditioner extends Device8060Fragment<Precision_Air_ConditionerBean, PrecisionAirAlertBean,Precision_Air_ConditionerBean, Precision_Air_ConditionerBean> implements I_PrecisionAir, View.OnClickListener,
        I_DeviceDetailManageLink<Device_Detail_AddBean> {
    //精密空调实时数据列表
    private UltimateRecyclerView precisionConditioner;
    //实时数据列表在数据
    private List<Precision_Air_ConditionerBean> precisionBeans = new ArrayList<>();
    //Presenter
    private PrecisionAirPresenter presenter;
    //设备列表适合器
    private PrecisionConditionerAdapter precisionAdapter;
    private int ehmIDFirst;

    //分体空调的设备管理列表
//    public PrecisionAirAdd precisionAirAdd;
    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    private AlertDialog changeDialog;

    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, Precision_Air_ConditionerBean> manageAdapter;
    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public PrecisionAirConditioner(String realLink, String alertLink, String historyLink, String manageLink) {
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
    public PrecisionAirConditioner(String realLink, String alertLink, String historyLink, String manageLink, String tempDeleted, String tempChange) {
        super(realLink, alertLink, historyLink, manageLink);
        this.deleteUrl = tempDeleted;
        this.changeUrl = tempChange;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Present
        presenter = new PrecisionAirPresenter();

        //初始化报警面板，由子类来实现
        initAlert(1);
        initDeviceManage(2, "精密空调");

        //实时数据列表绑定
        precisionConditioner = viewList.get(0).findViewById(R.id.precision_realtime_recycler);
        //设备列表
        precisionAdapter = new PrecisionConditionerAdapter(getActivity(), precisionBeans, R.layout.precision_air_conditioner_temperature);
        precisionConditioner.setLayoutManager(new LinearLayoutManager(getActivity()));
        precisionConditioner.setAdapter(precisionAdapter);

        //设备管理的列表
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans,precisionBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl,this,"精密空调");
//        precisionAirAdd = new PrecisionAirAdd(addBeans,precisionBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl);
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //只显示实时数据，设备管理
        setToNavShow(true,true,false,true);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

        device8060Present.queryRealData(Precision_Air_ConditionerBean.class);

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
        viewList.add(R.layout.precision_real_time);
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

        precisionBeans.clear();
        precisionBeans.addAll(getRealData());
        precisionAdapter.notifyDataSetChanged();

        //报警数据
        initAlertView(precisionBeans);
    }

    @Override
    public void requestAlert() {
        List<PrecisionAirAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            PrecisionAirAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getEcaInfo());
            alertBeansItem.setEhaTime(alertBean.getEcaTime());
            alertBeansItem.setEcmDeviceName(alertBean.getEcmDeviceName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();

    }


    @Override
    public void queryDeviceManage() {

    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            Precision_Air_ConditionerBean conditionerBean = precisionBeans.get(0);
            ///获取设备的ID
            int temEhmId = conditionerBean.getEcmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(PrecisionAirAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }
    }

    @Override
    public void queryHistoryData() {
        //只执行一次
        if (addBeans.size() == 0)
            //因为历史数据的位置是设备管理，因此需要在该地方请求设备管理的数据
            device8060Present.queryDeviceManage(Precision_Air_ConditionerBean.class);
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(PrecisionAirAlertBean.class,ehmID,startTime,endTime);
    }

    @Override
    public void requestHistory() {

    }

    @Override
    public void requestManage() {

        Log.d("kljldfdf", "requestManage: run");

//        先清空原有的设备数据
        addBeans.clear();

        List<Precision_Air_ConditionerBean> manageData = getManageData();

//        适配添加设备的数据
        for (Precision_Air_ConditionerBean b :
                manageData) {

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getEcmDeviceName());

            bean.setEhmId(String.valueOf(b.getEcmId()));

            //位置
            bean.setPosition(String.valueOf(b.getEcmDeviceAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getEcmIntervalTime()));
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {

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
    public void initAlertView(List<Precision_Air_ConditionerBean> alertDevice) {

        alertDeviceBeanList.clear();
        ehmIDFirst = alertDevice.get(0).getEcmId();
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (Precision_Air_ConditionerBean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getEcmDeviceName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    /**
     * 获取设备ID，查询报警，历史
     * @return
     */
    @Override
    public int getEhmID() {
        return this.ehmIDFirst;
    }

    /**
     * 设备管理-删除设备
     * @param position 需要删除设备所有的的位置
     * @param svHolder 设备的VIEW
     */
    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(precisionBeans.get(position).getEcmId());
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

        DynamicDeviceManage.getInstance().addManageItem(textViewList, svHolder.linearLayout);
    }

    @Override
    public List<MaterialEditText> setDeviceAddEdit() {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        DynamicMaterialEdit.getInstance().setLayoutInflater(inflater, getActivity());
        List<MaterialEditText> editTexts = new ArrayList<>();

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备名称","");
        MaterialEditText address = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备地址","");
        MaterialEditText ip = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "IP地址","");

        editTexts.add(name);
        editTexts.add(address);
        editTexts.add(ip);

        return editTexts;
    }



    @Override
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {

        //进行解析
        Map<String, String> formBody = new HashMap();
//        formBody.put("ecmDeviceAddress", materialEditTexts.get(1).getText().toString());
        formBody.put("ecmDeviceName", materialEditTexts.get(0).getText().toString());
        //必须获取 ehmID 才能修改
        formBody.put("ecmId", String.valueOf(actionID));
        //解析MAP 为 JSON

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.precisionAirChange, MyGson.getInstance().toJson(formBody), this);
    }

    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        Precision_Air_ConditionerBean temp = precisionBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();

        deviceManageEditBean.setaDeviceName(temp.getEcmDeviceName());
        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getEcmId());

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd(flag);

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
        formBody.put("ecmDeviceAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("ecmDeviceName", getTextString(keyArray.get("deviceName")));
        formBody.put("ecmIntervalTime", getTextString(keyArray.get("interval")));
        formBody.put("diId", getTextString(keyArray.get("diId")));
        formBody.put("gId", getTextString(keyArray.get("gId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.precisionAirAdd, MyGson.getInstance().toJson(formBody), this);

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
        formBody.put("ecmId", getTextString(keyArray.get("actionID")));
        formBody.put("ecmDeviceName", getTextString(keyArray.get("deviceName")));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + changeUrl, MyGson.getInstance().toJson(formBody), this);

    }


    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(Precision_Air_ConditionerBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(Precision_Air_ConditionerBean.class);
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(Precision_Air_ConditionerBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();

        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(Precision_Air_ConditionerBean.class);
        }, 1000);


    }

}
