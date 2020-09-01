package computer_room.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.adapter.NewHistoryAdapter;
import computer_room.bean.TemperatureLineBean;
import computer_room.bean.ToxicAlertBean;
import computer_room.bean.ToxicGasBean;
import computer_room.bean.ToxicHistoryBean;
import computer_room.i_interface.I_HistoryAdapter;
import computer_room.i_interface.I_ToxicQiti;
import computer_room.present.ToxicQitiPresenter;
import http.MyGson;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;
import util.RealDataUtil;
import util.ToastManage;
import view.SingleHistoryActivity;
import view_interface.I_UpdateRealView;

/**
 * 有毒气体
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 21 : 27
 */
public class ToxicQiti extends Device8060Fragment<ToxicGasBean, ToxicAlertBean,
        ToxicHistoryBean, ToxicGasBean> implements I_ToxicQiti, BaseRecyclerViewAdapter.OnItemClickListener,
        I_DeviceDetailManageLink<Device_Detail_AddBean>, I_HistoryAdapter<ToxicHistoryBean>, I_UpdateRealView<ToxicGasBean> {

    //列表数据
    private List<ToxicGasBean> toxicGasBeans = new ArrayList<>();
    //有毒气体的 Presenter
    private ToxicQitiPresenter presenter;


    //温湿度中的 实时数据列表
    private UltimateRecyclerView toxicRecycler;

    //LineChart 的数据
    private List<TemperatureLineBean> lineChartList;

    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    //只在进入页面时，刷新一次设备列表
    private boolean refreshDeviceList = true;

    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, ToxicGasBean> manageAdapter;

    //保存添加的设备列表适配器
//    public Temperature_Add deviceDetail_AddAdapter;
    //温湿度数据展示面板
    private LinearLayout temperature_top_data;
    //记录第一个 ehmID


    //删除URL
    String deleteUrl = "";
    String changeUrl = "";
    NewHistoryAdapter<ToxicHistoryBean> newHistoryAdapter;
    List<ToxicHistoryBean> lineDataList = new ArrayList<>();

    //设备实时数据工具类
    public RealDataUtil<ToxicGasBean> realDataUtil;

    private static LayoutInflater layoutInflater;

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public ToxicQiti(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        presenter = new ToxicQitiPresenter();


        layoutInflater = getActivity().getLayoutInflater();

        //初始报警页面的控件
        initAlert(1);
        //初始化历史数据中的控件
        initHistoryFloatingButton(2);

        initDeviceManage(3, "有毒气体");

        //设备管理的列表
        deleteUrl = LainNewApi.toxicDelete;
        changeUrl = LainNewApi.toxicUpdate;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans, toxicGasBeans, getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl, this, "有毒气体");
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //初始化 实时数据，报警数据，历史曲线，设备管理等适配器列表
        initModelAdapter();
        //设置显示的NAV
        setDeviceNav(true, true, true, true);
        //查询实时数据
        device8060Present.queryRealData(ToxicGasBean.class);
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
        realDataUtil.initView(viewList.get(0), getActivity(), toxicGasBeans, this, this);
    }

    private void initModelAdapter() {

        //温湿度实时数据
//        toxicqitiAdapter = new ToxicqitiAdapter(getActivity(), toxicGasBeans, R.layout.carbon_dioxide_template);
//        toxicqitiAdapter.setItemClickListener(this);
//        toxicRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        toxicRecycler.setAdapter(toxicqitiAdapter);
        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);
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
        try {
            //先清空
            toxicGasBeans.clear();
            //更新所有的数据，必须调用 getRealData()，才能获取到数据，必须使用 addAll 才能更新列表
            toxicGasBeans.addAll(getRealData());

            //数据为空时才更新
            if (alertDeviceBeanList.isEmpty()) {

                //刷新数据
                realDataUtil.notifyRealDataSetChanged();
                //设置实时数据
                onItemClickListener(getView(), 0);

                //每次查询都保存第一个设备ID
                ehmIDFirst = toxicGasBeans.get(0).getEcmId();
                //更新报警查询的设备
                for (ToxicGasBean b :
                        toxicGasBeans) {
                    AlertDeviceBean alertB = new AlertDeviceBean();
                    alertB.setName(b.getEcmName());
                    alertDeviceBeanList.add(alertB);
                }
                alertPanelDevice.notifyRealDataSetChanged();
            }

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            ToxicGasBean temperatureBean = toxicGasBeans.get(0);
            ///获取设备的ID
            int temEhmId = temperatureBean.getEcmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(ToxicAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }

    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(ToxicAlertBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void requestAlert() {


        List<ToxicAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            ToxicAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getEcaInfo());
            alertBeansItem.setEhaTime(alertBean.getEcaTime());
            alertBeansItem.setEcmDeviceName(alertBean.getEcmName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();

    }

    @Override
    public void requestHistory() {
        try {

            lineDataList.clear();

            List<Integer> tempList = new ArrayList<>();
            List<Integer> humList = new ArrayList<>();
            List<String> timeList = new ArrayList<>();

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
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {

        toxicGasBeans.clear();
        toxicGasBeans.addAll(OkHttpUtil.getInstance().formatResponse(responseStr, ToxicGasBean.class));

        //刷新数据
        realDataUtil.notifyRealDataSetChanged();
        //设置实时数据
        onItemClickListener(getView(), 0);


        //报警数据
        initAlertView(toxicGasBeans);
        //获取IP
        getDeviceIP(LainNewApi.poisonousIP);
    }

    /**
     * 更新报警面板的设备列表
     *
     * @param alertDevice
     */
    public void initAlertView(List<ToxicGasBean> alertDevice) {

        alertDeviceBeanList.clear();

        //初始化报警面板的数据
        AlertDeviceBean firstBean = new AlertDeviceBean();
        //查询所有设备
        firstBean.setName("所有设备");
        alertDeviceBeanList.add(0, firstBean);
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (ToxicGasBean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getEcmName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    @Override
    public void onItemClickListener(View v, int position) {
        //历史数据点击查看 曲线图
        if (v.findViewById(com.example.pool.R.id.new_history_name) != null) {

            //必须先查询，才能进入查看曲线图
            if (lineDataList.isEmpty()) {
                historyBottomSheetBehavior.show();
                ToastManage.getInstance().toastShortShow("请先查询历史数据");
                return;
            }

            ArrayList<String> temps = new ArrayList<>();
            ArrayList<String> times = new ArrayList<>();

            for (ToxicHistoryBean temp :
                    lineDataList) {
                temps.add(temp.getEphData() + "");
                times.add(temp.getEphTime() + "");
            }

            Intent intent = new Intent(getActivity(), SingleHistoryActivity.class);
            //传递显示的数据
            intent.putStringArrayListExtra("temps", temps);
            intent.putStringArrayListExtra("times", times);
            intent.putExtra("title", toxicGasBeans.get(position).getEcmName());
            //传递曲线的单位
            ArrayList<String> suffixList = new ArrayList<>();
            suffixList.add(" ppm");
            intent.putStringArrayListExtra("suffixList", suffixList);
            startActivity(intent);
            return;
        }

        //点击切换上面的圆形进度条的数据
        //设置相关的属性值
        ToxicGasBean b = toxicGasBeans.get(position);

        //设置报警值，必须先设置最大值，再设置当前值，才不会出现问题
        realDataUtil.setOuterData(String.valueOf(b.getEcmAlarmData()));
        realDataUtil.setOuterMax(String.valueOf(b.getEcmAlarmData()));
        //设置数据
        realDataUtil.setInnerData(String.valueOf(b.getEcmCurrentData()));
        realDataUtil.setInnerMax(String.valueOf(b.getEcmAlarmData()));

        //设置文案
        realDataUtil.setInnerTitle("浓度");
        realDataUtil.setOuterTitle("报警值");

    }

    @Override
    public void queryHistoryData() {

        try {
            //默认查询第一个设备
            ToxicGasBean toxicGasBean = toxicGasBeans.get(0);
            ///获取设备的ID
            int temEhmId = toxicGasBean.getEcmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(ToxicHistoryBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }

    }

    @Override
    public void queryDeviceManage() {
        //只执行一次
        if (addBeans.size() == 0)
            device8060Present.queryDeviceManage(ToxicGasBean.class);
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(ToxicHistoryBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();

        List<ToxicGasBean> manageData = getManageData();

//        适配添加设备的数据
        for (ToxicGasBean b :
                manageData) {

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getEcmName());

            bean.setEhmId(String.valueOf(b.getEcmId()));
            //位置
            bean.setPosition(String.valueOf(b.getEcmAddress()));
            bean.setIp("");
            bean.setUpdateTime(String.valueOf(b.getIntervalTime()));
            bean.setAlertValue(String.valueOf(b.getEcmAlarmData()));

            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();

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
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(toxicGasBeans.get(position).getEcmId());
        svHolder.deleteDevice(ehmId, position);
    }

    @Override
    public void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {
        //设置图标
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

        TextView deviceInterval = new TextView(getActivity());
        deviceInterval.setText("保存间隔：" + data.getUpdateTime());
        textViewList.add(deviceInterval);

        DynamicDeviceManage.getInstance().addManageItem(textViewList, svHolder.linearLayout);
    }


    @Override
    public List<MaterialEditText> setDeviceAddEdit() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        DynamicMaterialEdit.getInstance().setLayoutInflater(inflater, getActivity());
        List<MaterialEditText> editTexts = new ArrayList<>();

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备名称", "");
        MaterialEditText address = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "设备地址", "");
        MaterialEditText alertData = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "报警值", "");
        MaterialEditText interval = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "保存间隔", "");
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
        formBody.put("ecmName", materialEditTexts.get(0).getText().toString());
        formBody.put("ecmAlarmData", materialEditTexts.get(2).getText().toString());
        formBody.put("intervalTime", materialEditTexts.get(3).getText().toString());
        //必须获取 ehmID 才能修改
        formBody.put("ecmId", String.valueOf(actionID));
        //解析MAP 为 JSON

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.toxicUpdate, MyGson.getInstance().toJson(formBody), this);
    }

    @Override
    public void addDeviceAction(List<MaterialEditText> materialEditTexts) {
        //进行解析
        Map<String, String> formBody = new HashMap();
        formBody.put("ecmName", materialEditTexts.get(0).getText().toString());
        formBody.put("ecmAddress", materialEditTexts.get(1).getText().toString());
        formBody.put("ecmAlarmData", materialEditTexts.get(2).getText().toString());
        //必须获取 ehmID 才能修改
        formBody.put("intervalTime", materialEditTexts.get(3).getText().toString());
        //解析MAP 为 JSON

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.toxicInsert, MyGson.getInstance().toJson(formBody), this);

    }

    @Override
    public void bindHistoryData(BaseViewHolder holder, ToxicHistoryBean data, int position) {
        TextView historyName = holder.getView(R.id.new_history_name);
        TextView historyTemp = holder.getView(R.id.new_history_temp);
        TextView historyHum = holder.getView(R.id.new_history_hum);
        TextView historyTime = holder.getView(R.id.new_history_time);
        ImageView historyImage = holder.getView(R.id.history_image_list);
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(historyImage);
        Button loogQx = holder.getView(R.id.look_qx);

        historyTemp.setText("温度" + data.getEphData());
        historyHum.setText("地址" + data.getEpmAddress());
        historyTime.setText("时间" + data.getEphTime());

        loogQx.setOnClickListener((v) -> {
            onItemClickListener(holder.getRootView(), position);
        });
    }

    /**
     * 点击修改按钮时会跳转到编辑页
     **/
    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        ToxicGasBean temp = toxicGasBeans.get(position);

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName(temp.getEcmName());
        deviceManageEditBean.setaDeviceInterval(temp.getIntervalTime());
        deviceManageEditBean.setaDeviceAlert(temp.getEcmAlarmData());

        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(temp.getEcmId());

        deviceManageEditBean.setDeviceIPBean(deviceIPBeanList);

        deviceManageEditBean.setGroupBeans(SaveInterface.getInstance().getGroupBeanList().get(0));

        deviceManageEditBean.setShowDeviceAddress(true);

        deviceManageEditBean.setChangeOrAdd(flag);

        intent.putExtra("editBean", deviceManageEditBean);

        startActivity(intent);

    }

    /**
     * 点击添加设备按钮时会跳转到编辑页
     **/
    @Override
    public void addNewDevice(String flag) {

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setaDeviceInterval(30);
        deviceManageEditBean.setaDeviceAlert(1);

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
        formBody.put("ecmAddress", getSpinnerString(spinnerMap.get("deviceAddress")));
        formBody.put("ecmName", getTextString(keyArray.get("deviceName")));
        formBody.put("ecmAlarmData", getTextString(keyArray.get("alertValue")));
        formBody.put("intervalTime", getTextString(keyArray.get("interval")));
        formBody.put("gId", getSpinnerString(spinnerMap.get("classify")));
        formBody.put("diId", getTextString(keyArray.get("diId")));

        OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.toxicInsert, MyGson.getInstance().toJson(formBody), this);

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
        formBody.put("ecmAlarmData", getTextString(keyArray.get("alertValue")));
        formBody.put("ecmName", getTextString(keyArray.get("deviceName")));
        formBody.put("intervalTime", getTextString(keyArray.get("interval")));
        formBody.put("ecmId", getTextString(keyArray.get("actionID")));

        OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.toxicUpdate, MyGson.getInstance().toJson(formBody), this);

    }

    /**
     * 修改设备成功后会调用
     */
    @Override
    public void changeDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(ToxicGasBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();
        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(ToxicGasBean.class);
        }, 1000);

    }

    /**
     * 添加设备成功后会调用
     */
    @Override
    public void addDeviceSuccess() {

        //刷新页面
        device8060Present.queryRealData(ToxicGasBean.class);
        refreshDeviceList = true;
        //将报警，设备管理列表重置
        alertDeviceBeanList.clear();

        new Handler().postDelayed(() -> {
            device8060Present.queryDeviceManage(ToxicGasBean.class);
        }, 1000);


    }

    @Override
    public void updateRealView(TextView textView, ToxicGasBean data, int position) {
        //设置列表的数据
        textView.setText(data.getEcmName());
    }
}
