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
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.adapter.LocatingAlertAdapter;
import computer_room.adapter.Locating_RealTime_Adapter;
import computer_room.bean.LocatingDetailAlertBean;
import computer_room.bean.Locating_RealTime_Bean;
import computer_room.i_interface.I_Locating;
import computer_room.present.LocatingPresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;

/**
 * 定位漏水
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/8 21 : 22
 */
public class Locating_Detail extends Base8052Fragment<Locating_RealTime_Bean, LocatingDetailAlertBean> implements I_Locating, I_DeviceDetailManageLink<Device_Detail_AddBean> {

    //列表数据
    private List<Locating_RealTime_Bean> locatingRealTimeBeans = new ArrayList<>();

    //实时数据列表
    private UltimateRecyclerView locating_real_time;
    //报警数据
    private UltimateRecyclerView locating_alert_time;

    //Presenter
    private LocatingPresenter presenter;
    //实时数据适配器
    private Locating_RealTime_Adapter locatingAdapter;
    //报警适配器
    private LocatingAlertAdapter locatingAlertAdapter;
    //实时数据
    private List<Locating_RealTime_Bean> locatingBeans = new ArrayList<>();
    private List<LocatingDetailAlertBean> alertBeans = new ArrayList<>();
    //保存设备的ID
    private int elmID = 0;

    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, Locating_RealTime_Bean> manageAdapter;

    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    public Locating_Detail(String linkReal8052, String linkAlert8052) {
        super(linkReal8052, linkAlert8052);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Presenter
        presenter = new LocatingPresenter();

        //初始化报警面板，由子类来实现
        initAlert(1);

        //实时数据列表
        locating_real_time = viewList.get(0).findViewById(R.id.locating_realtime_recycler);
        //报警数据列表
        locating_alert_time = viewList.get(1).findViewById(R.id.alert_recycler);

        initDeviceManage(2, "定位漏水");

        //设备管理
        String deleteUrl = LainNewApi.locationDelete;
        changeUrl = LainNewApi.locationChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans, locatingBeans, getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl, this, "定式漏水");
//        deviceSplitAir_AddAdapter = new SplitAir_Add(addBeans,splitAirBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl);
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //实时数据适配器
        locatingAdapter = new Locating_RealTime_Adapter(getActivity(), locatingBeans, R.layout.locating_real_time_template);
        locating_real_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        locating_real_time.setAdapter(locatingAdapter);

        locatingAlertAdapter = new LocatingAlertAdapter(getActivity(), alertBeans, com.example.base.R.layout.device_8052_alert_temp);
        locatingAlertAdapter.setItemClickListener(this);
        locating_alert_time.setLayoutManager(new LinearLayoutManager(getActivity()));
        locating_alert_time.setAdapter(locatingAlertAdapter);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

        //只显示实时数据，设备管理
        setToNavShow(true, true, false, true);

        //实时数据
        base8052Present.queryRealData(Locating_RealTime_Bean.class);
        isStartAutoUp(true);
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
        viewList.add(R.layout.locating_real_time);
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
        base8052Present.queryRealData();
        infoHandler.postDelayed(this, LainNewApi.SECOND);
    }

    @Override
    protected void responseAlert8052() {
        this.alertBeans.clear();
        this.alertBeans.addAll(getAlertList());
        locatingAlertAdapter.notifyDataSetChanged();
    }

    @Override
    protected void responseReal8052() {
        try {
            locatingBeans.clear();
            locatingBeans.addAll(getRealList());
            locatingAdapter.notifyDataSetChanged();
            //报警列表数据，只更新一次
            if (alertDeviceBeanList.size() == 0) {
                elmID = locatingBeans.get(0).getElmId();
                initAlertView(locatingBeans);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        //设备管理
        devicesManage(locatingBeans);

    }

    public void devicesManage(List<Locating_RealTime_Bean> dataList) {
        //先清空原有的设备数据
        addBeans.clear();
        //适配添加设备的数据
        for (Locating_RealTime_Bean b :
                dataList) {

            String ip = String.valueOf(b.getElmAddress());

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getElmName());
            bean.setIp(ip);
            bean.setPosition(String.valueOf(b.getElmAddress()));
            bean.setEhmId(String.valueOf(b.getElmId()));

//            bean.setImageUrl(LainNewApi.getUserIP() + "/resources/icon/72x72/environment/localtion.png");
            addBeans.add(bean);
        }
        //设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();
    }

    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {

    }

    @Override
    public void dealWitchAlert(String requestTag, String requestUrl, String responseStr) {

        super.dealWitchAlert(requestTag, requestUrl, responseStr);
    }


    /**
     * 更新报警面板的设备列表
     *
     * @param alertDevice
     */
    public void initAlertView(List<Locating_RealTime_Bean> alertDevice) {

        alertDeviceBeanList.clear();

        //初始化报警面板的数据
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (Locating_RealTime_Bean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getElmName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        //开始查询
        base8052Present.queryAlertData(LocatingDetailAlertBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void queryAlertData() {

        try {
            //默认查询第一个设备
            Locating_RealTime_Bean bean8052 = locatingBeans.get(0);
            ///获取设备的ID
            int ekmId = bean8052.getElmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            base8052Present.queryAlertData(LocatingDetailAlertBean.class, ekmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }

    }

    @Override
    public void onItemClickListener(View v, int position) {

        try {
            //报警数据中选择设备查询时的回调
            if (v.findViewById(com.example.base.R.id.btn_panel_device) != null) {
                //先保存数据，点击确定后在进行查询
                setEhmID(locatingBeans.get(position).getElmId());
            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getEhmID() {
        return elmID;
    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(locatingBeans.get(position).getElmId());
        svHolder.deleteDevice(ehmId, position);
    }

    @Override
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {
        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("elmName", materialEditTexts.get(0).getText().toString());
        formBody.put("elmId", String.valueOf(actionID));
        //解析MAP 为 JSON
        String json = MyGson.getInstance().toJson(formBody);
        //发送请求
        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.locationChange, json, this);
    }


    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        Locating_RealTime_Bean temp = locatingBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();

        deviceManageEditBean.setaDeviceName(temp.getElmName());
        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getElmId());

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);
        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);
    }

    @Override
    public void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {
        //在这里设置视图

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

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备名称", "");
        MaterialEditText address = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备地址", "");

        editTexts.add(address);
        editTexts.add(name);


        return editTexts;
    }

    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        base8052Present.queryRealData(Locating_RealTime_Bean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            base8052Present.queryDeviceManage();
        }, 1000);

    }

    /**
     * 点击添加设备按钮时会跳转到编辑页
     **/
    @Override
    public void addNewDevice(String flag) {

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);

        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd("添加");

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
        formBody.put("elmAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("elmName", getTextString(keyArray.get("deviceName")));
        formBody.put("diId", getTextString(keyArray.get("diId")));
        formBody.put("gId", getTextString(keyArray.get("gId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.locationInsert, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        base8052Present.queryRealData(Locating_RealTime_Bean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            base8052Present.queryDeviceManage();
        }, 1000);

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
        formBody.put("elmId", getTextString(keyArray.get("actionID")));
        formBody.put("elmName", getTextString(keyArray.get("deviceName")));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + changeUrl, MyGson.getInstance().toJson(formBody), this);

    }

}
