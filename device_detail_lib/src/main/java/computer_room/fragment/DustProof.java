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
import computer_room.bean.DustAlertBean;
import computer_room.bean.DustProofBean;
import computer_room.i_interface.I_DustProof;
import computer_room.present.DustProofPresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;
import util.RealDataUtil;
import view_interface.I_UpdateRealView;

/**
 * 粉尘
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 21 : 45
 */
public class DustProof  extends Device8060Fragment<DustProofBean, DustAlertBean,DustProofBean,DustProofBean> implements I_DustProof, I_DeviceDetailManageLink<Device_Detail_AddBean>, I_UpdateRealView<DustProofBean> {

    //粉尘 Presenter
    private DustProofPresenter presenter;

    //实时数据
    private List<DustProofBean> dustProofBeans = new ArrayList<>();

    //设备管理
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, DustProofBean> manageAdapter;

    private int ehmIDFirst;

    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    //设备实时数据工具类
    public RealDataUtil<DustProofBean> realDataUtil;

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public DustProof(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        presenter = new DustProofPresenter();

        //初始化报警面板，由子类来实现
        initAlert(1);
        initDeviceManage(2, "粉尘");

        //设备列表
//        dustProofAdapter = new DustProofAdapter(getActivity(), dustProofBeans, R.layout.dust_proof_temp);
//        carbonRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        carbonRecycler.setAdapter(dustProofAdapter);

        //设备管理的列表
        String deleteUrl = LainNewApi.dustDelete;
        String changeUrl = LainNewApi.dustChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans,dustProofBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl,this,"粉尘");
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //只显示实时数据，设备管理
        setToNavShow(true,true,false,true);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

        device8060Present.queryRealData(DustProofBean.class);
        //初始化实时数据工具类
        initRealDataUtil();

    }

    private void initRealDataUtil() {
        realDataUtil = new RealDataUtil<>();
        realDataUtil.initView(viewList.get(0), getActivity(), dustProofBeans, this, this);
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
        dustProofBeans.clear();
        dustProofBeans.addAll(getRealData());

        //报警数据
        //报警列表数据，只更新一次
        if (alertDeviceBeanList.size() == 0) {

            //刷新数据
            realDataUtil.notifyRealDataSetChanged();
            //设置实时数据
            onItemClickListener(getView(), 0);

            ehmIDFirst = dustProofBeans.get(0).getEdmId();
            //更新报警面板列表
            initAlertView(dustProofBeans);

        }

    }

    @Override
    public void onItemClickListener(View v, int position) {

        //点击切换上面的圆形进度条的数据
        //设置相关的属性值
        DustProofBean b = dustProofBeans.get(position);
        //设置数据
        realDataUtil.setInnerData(String.valueOf(b.getEdmCurrentData()));
        realDataUtil.setInnerMax(String.valueOf(b.getEdmAlarmData()));
        //设置报警值，必须先设置最大值，再设置当前值，才不会出现问题
        realDataUtil.setOuterMax(String.valueOf(b.getEdmAlarmData()));
        realDataUtil.setOuterData(String.valueOf(b.getEdmAlarmData()));
        //设置文案
        realDataUtil.setInnerTitle("mg/m3");
        realDataUtil.setOuterTitle("报警值");

    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            DustProofBean conditionerBean = dustProofBeans.get(0);
            ///获取设备的ID
            int temEhmId = conditionerBean.getEdmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(DustAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(DustAlertBean.class,ehmID,startTime,endTime);
    }

    @Override
    public void requestAlert() {
        List<DustAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            DustAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getEdaInfo());
            alertBeansItem.setEhaTime(alertBean.getEdaTime());
            alertBeansItem.setEcmDeviceName(alertBean.getEdmName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestHistory() {

    }

    @Override
    public void queryHistoryData() {
        //因为历史数据的位置是设备管理，因此需要在该地方请求设备管理的数据
        device8060Present.queryDeviceManage(DustProofBean.class);
    }

    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();
        List<DustProofBean> manageData = getManageData();

//        适配添加设备的数据
        for (DustProofBean b :
                manageData) {

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getEdmName());

            bean.setEhmId(String.valueOf(b.getEdmId()));

            //位置
            bean.setPosition(String.valueOf(b.getEdmAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getEdmStatus()));
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
     * @param alertDevice
     */
    public void initAlertView(List<DustProofBean> alertDevice) {

        alertDeviceBeanList.clear();
        ehmIDFirst = alertDevice.get(0).getEdmId();
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (DustProofBean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getEdmName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    @Override
    public void devicesManage(List<DustProofBean> dataList) {
        //先清空原有的设备数据
        addBeans.clear();
        //适配添加设备的数据
        for (DustProofBean b :
                dataList) {

            String ip = String.valueOf(b.getEdmAddress());

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getEdmName());
            bean.setIp(ip);
            bean.setEhmId(String.valueOf(b.getEdmId()));
            addBeans.add(bean);

        }

    }

    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(dustProofBeans.get(position).getEdmId());
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

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备名称","");
        MaterialEditText address = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备地址","");
        MaterialEditText alertData = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "报警值","");
        MaterialEditText interval = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "更新间隔","");

        editTexts.add(name);
        editTexts.add(address);
        editTexts.add(alertData);
        editTexts.add(interval);

        return editTexts;
    }

    @Override
    public void changeDeviceAction(List<MaterialEditText> materialEditTexts, int actionID, int position) {
        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("edmName", materialEditTexts.get(0).getText().toString());
        formBody.put("edmAlarmData", materialEditTexts.get(1).getText().toString());
        formBody.put("intervalTime", materialEditTexts.get(2).getText().toString());
        //必须获取 ehmID 才能修改
        formBody.put("edmId", String.valueOf(actionID));
        //解析MAP 为 JSON
        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.dustChange, MyGson.getInstance().toJson(formBody), this);
    }

    /**
     点击修改按钮时会跳转到编辑页
     **/
    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        DustProofBean temp = dustProofBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName(temp.getEdmName());

        deviceManageEditBean.setaDeviceAlert(temp.getEdmAlarmData());
        deviceManageEditBean.setaDeviceInterval(temp.getIntervalTime());

        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getEdmId());

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
        formBody.put("edmName", getTextString(keyArray.get("deviceName")));
        formBody.put("edmAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("edmAlarmData", getTextString(keyArray.get("alertValue")));
        formBody.put("intervalTime", getTextString(keyArray.get("interval")));
        formBody.put("diId", getTextString(keyArray.get("diId")));
        formBody.put("gId", getTextString(keyArray.get("gId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.dustInsert, MyGson.getInstance().toJson(formBody), this);

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
        formBody.put("edmName", getTextString(keyArray.get("deviceName")));
        formBody.put("edmAlarmData", getTextString(keyArray.get("alertValue")));
        formBody.put("intervalTime", getTextString(keyArray.get("interval")));

        formBody.put("edmId", getTextString(keyArray.get("actionID")));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.dustChange, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(DustProofBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(DustProofBean.class);
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(DustProofBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();

        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(DustProofBean.class);
        }, 1000);


    }

    @Override
    public void updateRealView(TextView textView, DustProofBean data, int position) {
        //设置列表的数据
        textView.setText(data.getEdmName());
    }
}
