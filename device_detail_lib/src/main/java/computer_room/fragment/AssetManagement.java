package computer_room.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import action.ActionControl;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import computer_room.adapter.AssetRealAdapter;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.bean.AssetAlertBean;
import computer_room.bean.AssetRealBean;
import computer_room.i_interface.I_AssetManage;
import computer_room.present.AssetPresenter;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.DynamicMaterialEdit;
import util.LainNewApi;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/4 13:51
 * Description：资产管理
 **/
public class AssetManagement extends Device8060Fragment<AssetRealBean, AssetAlertBean,AssetRealBean,AssetRealBean>
        implements I_AssetManage, I_DeviceDetailManageLink<Device_Detail_AddBean>{
    //资产管理集合
    private List<AssetRealBean> assetRealBeans = new ArrayList<>();
    //资产管理适配器
    private AssetRealAdapter adapter;
    //资产管理 Presenter
    private AssetPresenter assetPresenter;



    //设备管理
    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, AssetRealBean> manageAdapter;

    //精密空调实时数据列表
    private UltimateRecyclerView assetRecycler;

    private int ehmIDFirst;

    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public AssetManagement(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        assetPresenter = new AssetPresenter();

        //初始化报警面板，由子类来实现
        initAlert(1);
        initDeviceManage(2, "资产管理");



        //实时数据列表绑定
        assetRecycler = viewList.get(0).findViewById(R.id.asset_realtime_recycler);
        //设备列表
        adapter = new AssetRealAdapter(getActivity(), assetRealBeans, R.layout.asset__real_temp);
        assetRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        assetRecycler.setAdapter(adapter);

        //设备管理的列表
        String deleteUrl = LainNewApi.assetDelete;
        String changeUrl = LainNewApi.assetChange;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans,assetRealBeans,getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl,this,"资产管理");
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);
        manageAdapter.setHasStableIds(true);
        //只显示实时数据，设备管理
        setToNavShow(true,true,false,true);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

        device8060Present.queryRealData(AssetRealBean.class);



    }

    /**
     * 获取ViewPager的列表
     *
     * @return View 的集合
     */
    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据
        viewList.add(R.layout.asset_manage_realtime);
        //报警数据
        viewList.add(R.layout.temperture_alarm);
        //设备管理
        viewList.add(R.layout.device_detail_manage);

        return viewList;
    }

    //实时获取数据
    @Override
    public void run() {

    }

    @Override
    public void requestReal() {
        assetRealBeans.clear();
        assetRealBeans.addAll(getRealData());
        adapter.notifyDataSetChanged();

        //报警数据
        //报警列表数据，只更新一次
        if (alertDeviceBeanList.size() == 0) {
            ehmIDFirst = assetRealBeans.get(0).getMId();
            //更新报警面板列表
            initAlertView(assetRealBeans);
        }
    }

    /**
     * 更新报警面板的设备列表
     * @param alertDevice
     */
    public void initAlertView(List<AssetRealBean> alertDevice) {

        alertDeviceBeanList.clear();
        ehmIDFirst = alertDevice.get(0).getMId();
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (AssetRealBean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }
    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(AssetAlertBean.class,ehmID,startTime,endTime);
    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            AssetRealBean conditionerBean = assetRealBeans.get(0);
            ///获取设备的ID
            int temEhmId = conditionerBean.getMId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(AssetAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }
    }

    @Override
    public void requestAlert() {
        List<AssetAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            AssetAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo("");
            alertBeansItem.setEhaTime(alertBean.getEmaTime());
            alertBeansItem.setEcmDeviceName("");
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
        device8060Present.queryDeviceManage(AssetRealBean.class);
    }

    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();
        List<AssetRealBean> manageData = getManageData();
//        适配添加设备的数据
        for (AssetRealBean b :
                manageData) {

            Device_Detail_AddBean bean = new Device_Detail_AddBean();
            bean.setDeviceName(b.getName());

//            bean.setEhmId(String.valueOf(b.getMId()));
//
//            //位置
//            bean.setPosition(String.valueOf(b.getGallery()));
//            //更新时间
//            bean.setUpdateTime(String.valueOf(b.getStatus()));
            addBeans.add(bean);

        }

        for (Device_Detail_AddBean y:
                addBeans) {
            Log.d("dsfsdf", "requestManage: "+y.getDeviceName());
        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();
    }

    //处理实时数据
    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {
//        assetRealBeans.clear();
//        assetRealBeans.addAll(OkHttpUtil.getInstance().formatResponse(responseStr, AssetRealBean.class));
//        adapter.notifyDataSetChanged();
    }


    @Override
    public void deleteDevice(int position, BaseDeviceDetailManageAdapter.SVHolder svHolder) {
        String ehmId = String.valueOf(assetRealBeans.get(position).getMId());
        svHolder.deleteDevice(ehmId, position);
    }


    @Override
    public void changeDevice(String flag, BaseDeviceDetailManageAdapter.SVHolder svHolder) {

        //获取当前需要修改设备的位置
        int position = svHolder.getAdapterPosition();

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setTempRangeMin(0);
        deviceManageEditBean.setHumRangeMin(0);
        deviceManageEditBean.setaDeviceInterval(0);
        //如果是修改，就必须设置设备的ID
        deviceManageEditBean.setActionID(assetRealBeans.get(position).getEmdId());

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

    @Override
    public void addNewDevice(String flag) {

        Intent intent = new Intent(getActivity(), ActionControl.class);

        action.DeviceManageEditBean deviceManageEditBean = new action.DeviceManageEditBean();
        deviceManageEditBean.setaDeviceName("");
        deviceManageEditBean.setTempRangeMin(0);
        deviceManageEditBean.setHumRangeMin(0);
        deviceManageEditBean.setaDeviceInterval(0);

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

    @Override
    public void withBindDeviceManage(BaseDeviceDetailManageAdapter.SVHolder svHolder, Device_Detail_AddBean data, int position) {
        svHolder.textView.setText(data.getDeviceName());
        svHolder.address.setText("地址：" + data.getPosition());
        //设置图标
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(svHolder.imageView);
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


}
