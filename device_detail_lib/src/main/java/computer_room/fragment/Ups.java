package computer_room.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
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
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.adapter.UpsAdapter;
import computer_room.bean.UpsAlertBean;
import computer_room.bean.UpsBean;
import computer_room.i_interface.I_PrecisionAir;
import computer_room.present.UpsPresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;

/**
 * UPS 设备列表
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/14 09 : 55
 */
public class Ups extends Device8060Fragment<UpsBean, UpsAlertBean,UpsBean, UpsBean> implements I_PrecisionAir, View.OnClickListener,
        I_DeviceDetailManageLink<Device_Detail_AddBean> {
    //UPS 数据
    List<UpsBean> upsBeans = new ArrayList<>();
    //Presenter
    private UpsPresenter presenter;
    //UPS适配器
    private UpsAdapter upsAdapter;

    private int ehmIDFirst;
    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, UpsBean> manageAdapter;

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public Ups(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        presenter = new UpsPresenter();

        //初始化报警面板，由子类来实现
        initAlert(1);

        //实时数据列表
        //UPS 列表
        UltimateRecyclerView ups_real_time = viewList.get(0).findViewById(R.id.ups_realtime_recycler);
        //UPS
        upsAdapter = new UpsAdapter(getActivity(), upsBeans, R.layout.ups_template);
        ups_real_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        ups_real_time.setAdapter(upsAdapter);

        //初始化报警面板，由子类来实现
        initAlert(1);
        initDeviceManage(2, "UPS");

        //设备管理的列表
        String deleteUrl = LainNewApi.upsDelete;
        String changeUrl = LainNewApi.upsChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans,upsBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl,this,"UPS");
//        precisionAirAdd = new PrecisionAirAdd(addBeans,precisionBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl);
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //只显示实时数据，设备管理
        setToNavShow(true,true,false,true);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

        device8060Present.queryRealData(UpsBean.class);
    }

    /**
     * ViewPager View
     *
     * @return Views
     */
    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据
        viewList.add(R.layout.ups_realtime);
        //报警数据
        viewList.add(R.layout.temperture_alarm);
        //设备管理
        viewList.add(R.layout.device_detail_manage);
        return viewList;
    }

    @Override
    public void requestReal() {
        upsBeans.clear();
        upsBeans.addAll(getRealData());
        upsAdapter.notifyDataSetChanged();

        //报警数据
        initAlertView(upsBeans);
    }

    /**
     * 更新报警面板的设备列表
     *
     * @param alertDevice
     */
    public void initAlertView(List<UpsBean> alertDevice) {

        alertDeviceBeanList.clear();
        ehmIDFirst = alertDevice.get(0).getId();
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (UpsBean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    @Override
    public void requestAlert() {
        List<UpsAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            UpsAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getInfo());
            alertBeansItem.setEhaTime(alertBean.getTime());
            alertBeansItem.setEcmDeviceName(alertBean.getName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();
    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            UpsBean conditionerBean = upsBeans.get(0);
            ///获取设备的ID
            int temEhmId = conditionerBean.getId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(UpsAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }
    }

    @Override
    public void queryHistoryData() {
        //只执行一次
        if (addBeans.size() == 0)
            //因为历史数据的位置是设备管理，因此需要在该地方请求设备管理的数据
            device8060Present.queryDeviceManage(UpsBean.class);
    }

    @Override
    public void requestHistory() {

    }
    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(UpsAlertBean.class,ehmID,startTime,endTime);
    }
    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();

        List<UpsBean> manageData = getManageData();

//        适配添加设备的数据
        for (UpsBean b :
                manageData) {

            Device_Detail_AddBean bean = new Device_Detail_AddBean();

            bean.setDeviceName(b.getName());

            bean.setEhmId(String.valueOf(b.getId()));

            //位置
            bean.setPosition(String.valueOf(b.getAddress()));
            bean.setIp("");
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();
    }

    //实时数据

    @Override
    public void onClick(View v) {

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
        String ehmId = String.valueOf(upsBeans.get(position).getId());
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
        deviceManageEditBean.setActionID(upsBeans.get(position).getId());

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

        editTexts.add(name);
        editTexts.add(address);

        return editTexts;
    }

    @Override
    public void addDeviceAction(List<MaterialEditText> materialEditTexts) {

        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("name", materialEditTexts.get(0).getText().toString());
        formBody.put("address", materialEditTexts.get(1).getText().toString());
        //解析MAP 为 JSON

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.upsInsert, MyGson.getInstance().toJson(formBody), this);

    }

    @Override
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {

        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("name", materialEditTexts.get(0).getText().toString());
        //必须获取 ehmID 才能修改
        formBody.put("id", String.valueOf(actionID));
        //解析MAP 为 JSON

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.upsChange, MyGson.getInstance().toJson(formBody), this);
    }
}
