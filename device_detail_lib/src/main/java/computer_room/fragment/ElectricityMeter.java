package computer_room.fragment;

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
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.swipe.SwipeItemManagerInterface;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.ActionControl;
import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import base.DynamicDeviceManage;
import bean.AlertBeans;
import bean.AlertDeviceBean;
import bean.Device_Detail_AddBean;
import bean.EelectricityBean;
import computer_room.adapter.BaseDeviceDetailManageAdapter;
import computer_room.adapter.ElectricityAdapter;
import computer_room.adapter.NewHistoryAdapter;
import computer_room.bean.ElectricityAlertBean;
import computer_room.bean.ElectricityHistoryBean;
import computer_room.i_interface.I_ElectricityMeter;
import computer_room.i_interface.I_HistoryAdapter;
import computer_room.present.ElectricityMeterPresenter;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import i_v.I_DeviceDetailManageLink;
import util.DateUtil;
import util.LainNewApi;
import util.ToastManage;

/**
 * 电量仪
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/13 16 : 11
 */
public class ElectricityMeter extends Device8060Fragment<EelectricityBean, ElectricityAlertBean,
        ElectricityHistoryBean, EelectricityBean> implements I_ElectricityMeter, BaseRecyclerViewAdapter.OnItemClickListener, OkHttpCallBack, I_DeviceDetailManageLink<Device_Detail_AddBean>,
        I_HistoryAdapter<ElectricityHistoryBean.PageInfoBean.ListBean> {

    //实时数据列表
    private UltimateRecyclerView electricity_real_time;
    //实时数据

    private List<EelectricityBean> electricityBeans = new ArrayList<>();
    //初始化 Presenter
    private ElectricityMeterPresenter presenter;
    //实时数据列表
    private ElectricityAdapter elecAdapter;
    //设备管理
    private UltimateRecyclerView deviceManage;


    //添加设备按钮
    private FloatingActionButton electricityAddDevice;
    //添加设备，修改信息
    private AlertDialog electricityDialog;

    //温湿度中的 实时数据列表
    private UltimateRecyclerView electricityRecycler;

    //设备管理的数据
    private ArrayList<Device_Detail_AddBean> addBeans = new ArrayList<>();

    private BaseDeviceDetailManageAdapter<Device_Detail_AddBean, EelectricityBean> manageAdapter;

    //保存添加的设备列表适配器
//    public Temperature_Add deviceDetail_AddAdapter;
    //温湿度数据展示面板
    private LinearLayout temperature_top_data;
    //记录第一个 ehmID


    //删除URL
    String tempDeleted = "";
    String tempChange = "";
    NewHistoryAdapter<ElectricityHistoryBean.PageInfoBean.ListBean> newHistoryAdapter;
    List<ElectricityHistoryBean> lineDataList = new ArrayList<>();

    private static LayoutInflater layoutInflater;


    MaterialEditText mNewDeviceName;
    MaterialEditText mElectricityAddress;
    MaterialEditText mDeviceGallery;
    MaterialEditText mPemMaxAvol;
    MaterialEditText mPemMinAvol;
    MaterialEditText mPemMaxBvol;
    MaterialEditText mPemMinBvol;
    MaterialEditText mPemMaxCvol;
    MaterialEditText mPemMinCvol;
    MaterialEditText mPemMaxAcur;
    MaterialEditText mPemMinAcur;
    MaterialEditText mPemMaxBcur;
    MaterialEditText mPemMinBcur;
    MaterialEditText mPemMaxCcur;
    MaterialEditText mPemMinCcur;
    MaterialEditText mPemMaxABvol;
    MaterialEditText mPemMinABvol;
    MaterialEditText mPemMaxBCvol;
    MaterialEditText mPemMinBCvol;
    MaterialEditText mPemMaxCAvol;
    MaterialEditText mPemMinCAvol;
    MaterialEditText mPemMaxApap;
    MaterialEditText mPemMinApap;
    MaterialEditText mPemMaxBpap;
    MaterialEditText mPemMinBpap;
    MaterialEditText mPemMaxCpap;
    MaterialEditText mPemMinCpap;
    MaterialEditText mPemMaxAprp;
    MaterialEditText mPemMinAprp;
    MaterialEditText mPemMaxBprp;
    MaterialEditText mPemMinBprp;
    MaterialEditText mPemMaxCprp;
    MaterialEditText mPemMinCprp;
    MaterialEditText mPemMaxAppf;
    MaterialEditText mPemMinAppf;
    MaterialEditText mPemMaxBppf;
    MaterialEditText mPemMinBppf;
    MaterialEditText mPemMaxCppf;
    MaterialEditText mPemMinCppf;
    MaterialEditText mPemMaxTpap;
    MaterialEditText mPemMinTpap;
    MaterialEditText mPemMaxTprp;
    MaterialEditText mPemMinTprp;
    MaterialEditText mPemMaxTppf;
    MaterialEditText mPemMinTppf;
    MaterialEditText mElecIntervalTime;
    Button mAddNewDevice;


    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public ElectricityMeter(String realLink, String alertLink, String historyLink, String manageLink) {
        super(realLink, alertLink, historyLink, manageLink);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化Presenter
        presenter = new ElectricityMeterPresenter();
        //绑定数据展示面板
        electricityRecycler = viewList.get(0).findViewById(R.id.electricity_realtime_recycler);



        //实时数据
        elecAdapter = new ElectricityAdapter(getActivity(), electricityBeans, R.layout.electricity_template);
        elecAdapter.setItemClickListener(this);
        electricityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        electricityRecycler.setAdapter(elecAdapter);

        layoutInflater = getActivity().getLayoutInflater();

        //初始报警页面的控件
        initAlert(1);
        //初始化历史数据中的控件
        initHistoryFloatingButton(2);

        initDeviceManage(3, "电量仪");

        //显示电费计算面板，必须是在 initHistoryFloatingButton() 后调用，不然会报空
        cardViewCalc.setVisibility(View.VISIBLE);


        //设备管理的列表
        String deleteUrl = LainNewApi.deleteElectricity;
        String changeUrl = LainNewApi.electricityDeviceUpdate;
        manageAdapter = new BaseDeviceDetailManageAdapter<>(addBeans, electricityBeans, getActivity(), R.layout.split_air_manage_swiper, deleteUrl, changeUrl, this, "电量仪");
        manageAdapter.setMode(SwipeItemManagerInterface.Mode.Single);

        //设置显示的NAV
        setDeviceNav(true, true, true, true);
        //查询实时数据
        device8060Present.queryRealData(EelectricityBean.class);

        //设置设备的信息
        newDeviceRecycler.setAdapter(manageAdapter);

        //一开始就绑定编辑框
//        View view = getActivity().getLayoutInflater().inflate(com.example.base.R.layout.electricity_add_device, null, false);

        //自定义添加设备导航
        if (device_addAccent != null)
            device_addAccent.setOnClickListener((v) -> {

                createAddAlert();

            });

    }

    /**
     * 添加电量仪面板的初始化
     */
    private void initAddDevicePanel(View view) {

        mElectricityAddress = view.findViewById(com.example.base.R.id.electricity_address);
        mNewDeviceName = view.findViewById(com.example.base.R.id.new_device_name);
        mPemMaxAvol = view.findViewById(com.example.base.R.id.pemMaxAvol);
        mPemMinAvol = view.findViewById(com.example.base.R.id.pemMinAvol);
        mPemMaxBvol = view.findViewById(com.example.base.R.id.pemMaxBvol);
        mPemMinBvol = view.findViewById(com.example.base.R.id.pemMinBvol);
        mPemMaxCvol = view.findViewById(com.example.base.R.id.pemMaxCvol);
        mPemMinCvol = view.findViewById(com.example.base.R.id.pemMinCvol);
        mPemMaxAcur = view.findViewById(com.example.base.R.id.pemMaxAcur);
        mPemMinAcur = view.findViewById(com.example.base.R.id.pemMinAcur);
        mPemMaxBcur = view.findViewById(com.example.base.R.id.pemMaxBcur);
        mPemMinBcur = view.findViewById(com.example.base.R.id.pemMinBcur);
        mPemMaxCcur = view.findViewById(com.example.base.R.id.pemMaxCcur);
        mPemMinCcur = view.findViewById(com.example.base.R.id.pemMinCcur);
        mPemMaxABvol = view.findViewById(com.example.base.R.id.pemMaxABvol);
        mPemMinABvol = view.findViewById(com.example.base.R.id.pemMinABvol);
        mPemMaxBCvol = view.findViewById(com.example.base.R.id.pemMaxBCvol);
        mPemMinBCvol = view.findViewById(com.example.base.R.id.pemMinBCvol);
        mPemMaxCAvol = view.findViewById(com.example.base.R.id.pemMaxCAvol);
        mPemMinCAvol = view.findViewById(com.example.base.R.id.pemMinCAvol);
        mPemMaxApap = view.findViewById(com.example.base.R.id.pemMaxApap);
        mPemMinApap = view.findViewById(com.example.base.R.id.pemMinApap);
        mPemMaxBpap = view.findViewById(com.example.base.R.id.pemMaxBpap);
        mPemMinBpap = view.findViewById(com.example.base.R.id.pemMinBpap);
        mPemMaxCpap = view.findViewById(com.example.base.R.id.pemMaxCpap);
        mPemMinCpap = view.findViewById(com.example.base.R.id.pemMinCpap);
        mPemMaxAprp = view.findViewById(com.example.base.R.id.pemMaxAprp);
        mPemMinAprp = view.findViewById(com.example.base.R.id.pemMinAprp);
        mPemMaxBprp = view.findViewById(com.example.base.R.id.pemMaxBprp);
        mPemMinBprp = view.findViewById(com.example.base.R.id.pemMinBprp);
        mPemMaxCprp = view.findViewById(com.example.base.R.id.pemMaxCprp);
        mPemMinCprp = view.findViewById(com.example.base.R.id.pemMinCprp);
        mPemMaxAppf = view.findViewById(com.example.base.R.id.pemMaxAppf);
        mPemMinAppf = view.findViewById(com.example.base.R.id.pemMinAppf);
        mPemMaxBppf = view.findViewById(com.example.base.R.id.pemMaxBppf);
        mPemMinBppf = view.findViewById(com.example.base.R.id.pemMinBppf);
        mPemMaxCppf = view.findViewById(com.example.base.R.id.pemMaxCppf);
        mPemMinCppf = view.findViewById(com.example.base.R.id.pemMinCppf);
        mPemMaxTpap = view.findViewById(com.example.base.R.id.pemMaxTpap);
        mPemMinTpap = view.findViewById(com.example.base.R.id.pemMinTpap);
        mPemMaxTprp = view.findViewById(com.example.base.R.id.pemMaxTprp);
        mPemMinTprp = view.findViewById(com.example.base.R.id.pemMinTprp);
        mPemMaxTppf = view.findViewById(com.example.base.R.id.pemMaxTppf);
        mPemMinTppf = view.findViewById(com.example.base.R.id.pemMinTppf);
        mElecIntervalTime = view.findViewById(com.example.base.R.id.elec_intervalTime);

        mAddNewDevice = view.findViewById(com.example.base.R.id.add_new_device);

        mAddNewDevice.setOnClickListener((v) -> {

            Map<String, String> electricityMap = new HashMap<>();

            electricityMap.put("pemName", mNewDeviceName.getText().toString());
            electricityMap.put("pemAddress", mElectricityAddress.getText().toString());
            electricityMap.put("pemMaxAvol", mPemMaxAvol.getText().toString());
            electricityMap.put("pemMinAvol", mPemMinAvol.getText().toString());
            electricityMap.put("pemMaxBvol", mPemMaxBvol.getText().toString());
            electricityMap.put("pemMinBvol", mPemMinBvol.getText().toString());
            electricityMap.put("pemMaxCvol", mPemMaxCvol.getText().toString());
            electricityMap.put("pemMinCvol", mPemMinCvol.getText().toString());
            electricityMap.put("pemMaxAcur", mPemMaxAcur.getText().toString());
            electricityMap.put("pemMinAcur", mPemMinAcur.getText().toString());
            electricityMap.put("pemMaxBcur", mPemMaxBcur.getText().toString());
            electricityMap.put("pemMinBcur", mPemMinBcur.getText().toString());
            electricityMap.put("pemMaxCcur", mPemMaxCcur.getText().toString());
            electricityMap.put("pemMinCcur", mPemMinCcur.getText().toString());
            electricityMap.put("pemMaxTpap", mPemMaxTpap.getText().toString());
            electricityMap.put("pemMaxABvol", mPemMaxABvol.getText().toString());
            electricityMap.put("pemMinABvol", mPemMinABvol.getText().toString());
            electricityMap.put("pemMaxBCvol", mPemMaxBCvol.getText().toString());
            electricityMap.put("pemMinBCvol", mPemMinBCvol.getText().toString());
            electricityMap.put("pemMaxCAvol", mPemMaxCAvol.getText().toString());
            electricityMap.put("pemMinCAvol", mPemMinCAvol.getText().toString());
            electricityMap.put("pemMaxApap", mPemMaxApap.getText().toString());
            electricityMap.put("pemMinApap", mPemMinApap.getText().toString());
            electricityMap.put("pemMaxBpap", mPemMaxBpap.getText().toString());
            electricityMap.put("pemMinBpap", mPemMinBpap.getText().toString());
            electricityMap.put("pemMaxCpap", mPemMaxCpap.getText().toString());
            electricityMap.put("pemMinCpap", mPemMinCpap.getText().toString());
            electricityMap.put("pemMaxAprp", mPemMaxAprp.getText().toString());
            electricityMap.put("pemMinAprp", mPemMinAprp.getText().toString());
            electricityMap.put("pemMaxBprp", mPemMaxBprp.getText().toString());
            electricityMap.put("pemMinBprp", mPemMinBprp.getText().toString());
            electricityMap.put("pemMaxCprp", mPemMaxCprp.getText().toString());
            electricityMap.put("pemMinCprp", mPemMinCprp.getText().toString());

            electricityMap.put("pemMaxAppf", mPemMaxAppf.getText().toString());
            electricityMap.put("pemMinAppf", mPemMinAppf.getText().toString());
            electricityMap.put("pemMaxBppf", mPemMaxBppf.getText().toString());
            electricityMap.put("pemMinBppf", mPemMinBppf.getText().toString());
            electricityMap.put("pemMaxCppf", mPemMaxCppf.getText().toString());
            electricityMap.put("pemMinCppf", mPemMinCppf.getText().toString());
            electricityMap.put("pemMinTpap", mPemMinTpap.getText().toString());
            electricityMap.put("pemMaxTprp", mPemMaxTprp.getText().toString());
            electricityMap.put("pemMinTprp", mPemMinTprp.getText().toString());
            electricityMap.put("pemMaxTppf", mPemMaxTppf.getText().toString());
            electricityMap.put("pemMinTppf", mPemMinTppf.getText().toString());
            electricityMap.put("intervalTime", mElecIntervalTime.getText().toString());

//            必须要添加这三个参数，暂时不做
//            electricityMap.put("pemId",  "6");
//            electricityMap.put("diId",  "5");
//            electricityMap.put("gId",  "5");

            String elecDeviceJson = MyGson.getInstance().toJson(electricityMap);

            OkHttpUtil.getInstance().sendRowOkHttp("add", LainNewApi.getInstance().getRootPath() + LainNewApi.electricityDeviceAdd, elecDeviceJson, this);


        });

    }

    /**
     * 添加电量仪面板的初始化
     */
    private void changeDevicePanel(View view, int position) {

        mNewDeviceName = view.findViewById(com.example.base.R.id.new_device_name);
        mElectricityAddress = view.findViewById(com.example.base.R.id.electricity_address);

        mPemMaxAvol = view.findViewById(com.example.base.R.id.pemMaxAvol);
        mPemMinAvol = view.findViewById(com.example.base.R.id.pemMinAvol);
        mPemMaxBvol = view.findViewById(com.example.base.R.id.pemMaxBvol);
        mPemMinBvol = view.findViewById(com.example.base.R.id.pemMinBvol);
        mPemMaxCvol = view.findViewById(com.example.base.R.id.pemMaxCvol);
        mPemMinCvol = view.findViewById(com.example.base.R.id.pemMinCvol);
        mPemMaxAcur = view.findViewById(com.example.base.R.id.pemMaxAcur);
        mPemMinAcur = view.findViewById(com.example.base.R.id.pemMinAcur);
        mPemMaxBcur = view.findViewById(com.example.base.R.id.pemMaxBcur);
        mPemMinBcur = view.findViewById(com.example.base.R.id.pemMinBcur);
        mPemMaxCcur = view.findViewById(com.example.base.R.id.pemMaxCcur);
        mPemMinCcur = view.findViewById(com.example.base.R.id.pemMinCcur);
        mPemMaxABvol = view.findViewById(com.example.base.R.id.pemMaxABvol);
        mPemMinABvol = view.findViewById(com.example.base.R.id.pemMinABvol);
        mPemMaxBCvol = view.findViewById(com.example.base.R.id.pemMaxBCvol);
        mPemMinBCvol = view.findViewById(com.example.base.R.id.pemMinBCvol);
        mPemMaxCAvol = view.findViewById(com.example.base.R.id.pemMaxCAvol);
        mPemMinCAvol = view.findViewById(com.example.base.R.id.pemMinCAvol);
        mPemMaxApap = view.findViewById(com.example.base.R.id.pemMaxApap);
        mPemMinApap = view.findViewById(com.example.base.R.id.pemMinApap);
        mPemMaxBpap = view.findViewById(com.example.base.R.id.pemMaxBpap);
        mPemMinBpap = view.findViewById(com.example.base.R.id.pemMinBpap);
        mPemMaxCpap = view.findViewById(com.example.base.R.id.pemMaxCpap);
        mPemMinCpap = view.findViewById(com.example.base.R.id.pemMinCpap);
        mPemMaxAprp = view.findViewById(com.example.base.R.id.pemMaxAprp);
        mPemMinAprp = view.findViewById(com.example.base.R.id.pemMinAprp);
        mPemMaxBprp = view.findViewById(com.example.base.R.id.pemMaxBprp);
        mPemMinBprp = view.findViewById(com.example.base.R.id.pemMinBprp);
        mPemMaxCprp = view.findViewById(com.example.base.R.id.pemMaxCprp);
        mPemMinCprp = view.findViewById(com.example.base.R.id.pemMinCprp);
        mPemMaxAppf = view.findViewById(com.example.base.R.id.pemMaxAppf);
        mPemMinAppf = view.findViewById(com.example.base.R.id.pemMinAppf);
        mPemMaxBppf = view.findViewById(com.example.base.R.id.pemMaxBppf);
        mPemMinBppf = view.findViewById(com.example.base.R.id.pemMinBppf);
        mPemMaxCppf = view.findViewById(com.example.base.R.id.pemMaxCppf);
        mPemMinCppf = view.findViewById(com.example.base.R.id.pemMinCppf);
        mPemMaxTpap = view.findViewById(com.example.base.R.id.pemMaxTpap);
        mPemMinTpap = view.findViewById(com.example.base.R.id.pemMinTpap);
        mPemMaxTprp = view.findViewById(com.example.base.R.id.pemMaxTprp);
        mPemMinTprp = view.findViewById(com.example.base.R.id.pemMinTprp);
        mPemMaxTppf = view.findViewById(com.example.base.R.id.pemMaxTppf);
        mPemMinTppf = view.findViewById(com.example.base.R.id.pemMinTppf);
        mElecIntervalTime = view.findViewById(com.example.base.R.id.elec_intervalTime);
        mAddNewDevice = view.findViewById(com.example.base.R.id.add_new_device);


        //设置每个编辑框的数据
        mNewDeviceName.setText(String.valueOf(electricityBeans.get(position).getPemName()));
        mElectricityAddress.setText(String.valueOf(electricityBeans.get(position).getPemAddress()));
        mPemMaxAvol.setText(String.valueOf(electricityBeans.get(position).getPemMaxAvol()));
        mPemMinAvol.setText(String.valueOf(electricityBeans.get(position).getPemMinAvol()));
        mPemMaxBvol.setText(String.valueOf(electricityBeans.get(position).getPemMaxBvol()));
        mPemMinBvol.setText(String.valueOf(electricityBeans.get(position).getPemMinBvol()));
        mPemMaxCvol.setText(String.valueOf(electricityBeans.get(position).getPemMaxCvol()));
        mPemMinCvol.setText(String.valueOf(electricityBeans.get(position).getPemMinCvol()));
        mPemMaxAcur.setText(String.valueOf(electricityBeans.get(position).getPemMaxAcur()));
        mPemMinAcur.setText(String.valueOf(electricityBeans.get(position).getPemMinAcur()));
        mPemMaxBcur.setText(String.valueOf(electricityBeans.get(position).getPemMaxBcur()));
        mPemMinBcur.setText(String.valueOf(electricityBeans.get(position).getPemMinBcur()));
        mPemMaxCcur.setText(String.valueOf(electricityBeans.get(position).getPemMaxCcur()));
        mPemMinCcur.setText(String.valueOf(electricityBeans.get(position).getPemMinCcur()));
        mPemMaxABvol.setText(String.valueOf(electricityBeans.get(position).getPemMaxABvol()));
        mPemMinABvol.setText(String.valueOf(electricityBeans.get(position).getPemMinABvol()));
        mPemMaxBCvol.setText(String.valueOf(electricityBeans.get(position).getPemMaxBCvol()));
        mPemMinBCvol.setText(String.valueOf(electricityBeans.get(position).getPemMinBCvol()));
        mPemMaxCAvol.setText(String.valueOf(electricityBeans.get(position).getPemMaxCAvol()));
        mPemMinCAvol.setText(String.valueOf(electricityBeans.get(position).getPemMinCAvol()));
        mPemMaxApap.setText(String.valueOf(electricityBeans.get(position).getPemMaxApap()));
        mPemMinApap.setText(String.valueOf(electricityBeans.get(position).getPemMinApap()));
        mPemMaxBpap.setText(String.valueOf(electricityBeans.get(position).getPemMaxBpap()));
        mPemMinBpap.setText(String.valueOf(electricityBeans.get(position).getPemMinBpap()));
        mPemMaxCpap.setText(String.valueOf(electricityBeans.get(position).getPemMaxCpap()));
        mPemMinCpap.setText(String.valueOf(electricityBeans.get(position).getPemMinCpap()));
        mPemMaxAprp.setText(String.valueOf(electricityBeans.get(position).getPemMaxAprp()));
        mPemMinAprp.setText(String.valueOf(electricityBeans.get(position).getPemMinAprp()));
        mPemMaxBprp.setText(String.valueOf(electricityBeans.get(position).getPemMaxBprp()));
        mPemMinBprp.setText(String.valueOf(electricityBeans.get(position).getPemMinBprp()));
        mPemMaxCprp.setText(String.valueOf(electricityBeans.get(position).getPemMaxCprp()));
        mPemMinCprp.setText(String.valueOf(electricityBeans.get(position).getPemMinCprp()));
        mPemMaxAppf.setText(String.valueOf(electricityBeans.get(position).getPemMaxAppf()));
        mPemMinAppf.setText(String.valueOf(electricityBeans.get(position).getPemMinAppf()));
        mPemMaxBppf.setText(String.valueOf(electricityBeans.get(position).getPemMaxBppf()));
        mPemMinBppf.setText(String.valueOf(electricityBeans.get(position).getPemMinBppf()));
        mPemMaxCppf.setText(String.valueOf(electricityBeans.get(position).getPemMaxCppf()));
        mPemMinCppf.setText(String.valueOf(electricityBeans.get(position).getPemMinCppf()));
        mPemMaxTpap.setText(String.valueOf(electricityBeans.get(position).getPemMaxTpap()));
        mPemMinTpap.setText(String.valueOf(electricityBeans.get(position).getPemMinTpap()));
        mPemMaxTprp.setText(String.valueOf(electricityBeans.get(position).getPemMaxTprp()));
        mPemMinTprp.setText(String.valueOf(electricityBeans.get(position).getPemMinTprp()));
        mPemMaxTppf.setText(String.valueOf(electricityBeans.get(position).getPemMaxTppf()));
        mPemMinTppf.setText(String.valueOf(electricityBeans.get(position).getPemMinTppf()));
        mElecIntervalTime.setText(String.valueOf(electricityBeans.get(position).getIntervalTime()));


        mAddNewDevice.setOnClickListener((v) -> {

            Map<String, String> electricityMap = new HashMap<>();

            electricityMap.put("pemName", mNewDeviceName.getText().toString());
            electricityMap.put("pemAddress", mElectricityAddress.getText().toString());
            electricityMap.put("pemMaxAvol", mPemMaxAvol.getText().toString());
            electricityMap.put("pemMinAvol", mPemMinAvol.getText().toString());
            electricityMap.put("pemMaxBvol", mPemMaxBvol.getText().toString());
            electricityMap.put("pemMinBvol", mPemMinBvol.getText().toString());
            electricityMap.put("pemMaxCvol", mPemMaxCvol.getText().toString());
            electricityMap.put("pemMinCvol", mPemMinCvol.getText().toString());
            electricityMap.put("pemMaxAcur", mPemMaxAcur.getText().toString());
            electricityMap.put("pemMinAcur", mPemMinAcur.getText().toString());
            electricityMap.put("pemMaxBcur", mPemMaxBcur.getText().toString());
            electricityMap.put("pemMinBcur", mPemMinBcur.getText().toString());
            electricityMap.put("pemMaxCcur", mPemMaxCcur.getText().toString());
            electricityMap.put("pemMinCcur", mPemMinCcur.getText().toString());
            electricityMap.put("pemMaxTpap", mPemMaxTpap.getText().toString());
            electricityMap.put("pemMaxABvol", mPemMaxABvol.getText().toString());
            electricityMap.put("pemMinABvol", mPemMinABvol.getText().toString());
            electricityMap.put("pemMaxBCvol", mPemMaxBCvol.getText().toString());
            electricityMap.put("pemMinBCvol", mPemMinBCvol.getText().toString());
            electricityMap.put("pemMaxCAvol", mPemMaxCAvol.getText().toString());
            electricityMap.put("pemMinCAvol", mPemMinCAvol.getText().toString());
            electricityMap.put("pemMaxApap", mPemMaxApap.getText().toString());
            electricityMap.put("pemMinApap", mPemMinApap.getText().toString());
            electricityMap.put("pemMaxBpap", mPemMaxBpap.getText().toString());
            electricityMap.put("pemMinBpap", mPemMinBpap.getText().toString());
            electricityMap.put("pemMaxCpap", mPemMaxCpap.getText().toString());
            electricityMap.put("pemMinCpap", mPemMinCpap.getText().toString());
            electricityMap.put("pemMaxAprp", mPemMaxAprp.getText().toString());
            electricityMap.put("pemMinAprp", mPemMinAprp.getText().toString());
            electricityMap.put("pemMaxBprp", mPemMaxBprp.getText().toString());
            electricityMap.put("pemMinBprp", mPemMinBprp.getText().toString());
            electricityMap.put("pemMaxCprp", mPemMaxCprp.getText().toString());
            electricityMap.put("pemMinCprp", mPemMinCprp.getText().toString());

            electricityMap.put("pemMaxAppf", mPemMaxAppf.getText().toString());
            electricityMap.put("pemMinAppf", mPemMinAppf.getText().toString());
            electricityMap.put("pemMaxBppf", mPemMaxBppf.getText().toString());
            electricityMap.put("pemMinBppf", mPemMinBppf.getText().toString());
            electricityMap.put("pemMaxCppf", mPemMaxCppf.getText().toString());
            electricityMap.put("pemMinCppf", mPemMinCppf.getText().toString());
            electricityMap.put("pemMinTpap", mPemMinTpap.getText().toString());
            electricityMap.put("pemMaxTprp", mPemMaxTprp.getText().toString());
            electricityMap.put("pemMinTprp", mPemMinTprp.getText().toString());
            electricityMap.put("pemMaxTppf", mPemMaxTppf.getText().toString());
            electricityMap.put("pemMinTppf", mPemMinTppf.getText().toString());
            electricityMap.put("intervalTime", mElecIntervalTime.getText().toString());

            electricityMap.put("pemId", String.valueOf(electricityBeans.get(position).getPemId()));

            String elecDeviceJson = MyGson.getInstance().toJson(electricityMap);
            OkHttpUtil.getInstance().sendPutOkHttp("change", LainNewApi.getInstance().getRootPath() + LainNewApi.electricityDeviceUpdate, elecDeviceJson, this);

        });

    }

    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据
        viewList.add(R.layout.electricity_meter_realtime);
        //报警数据
        viewList.add(R.layout.temperture_alarm);
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

        electricityBeans.clear();
        electricityBeans.addAll(getRealData());
        elecAdapter.notifyDataSetChanged();
        //请求报警数据
        //数据为空时才更新

        if (alertDeviceBeanList.isEmpty() && !electricityBeans.isEmpty()) {

            //每次查询都保存第一个设备ID
            ehmIDFirst = electricityBeans.get(0).getPemId();
            //更新报警查询的设备
            for (EelectricityBean b :
                    electricityBeans) {
                AlertDeviceBean alertB = new AlertDeviceBean();
                alertB.setName(b.getPemName());
                alertDeviceBeanList.add(alertB);
            }
            alertPanelDevice.notifyRealDataSetChanged();
        }
    }

    @Override
    public void queryHistoryData() {
        try {
            //默认查询第一个设备
            EelectricityBean temperatureBean = electricityBeans.get(0);
            ///获取设备的ID
            int temEhmId = temperatureBean.getPemId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryHistoryData(ElectricityHistoryBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }
    }

    @Override
    protected void querySelectDeviceHistory(int ehmID, String startTime, String endTime) {
        device8060Present.queryHistoryData(ElectricityHistoryBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void queryAlertData() {
        try {
            //默认查询第一个设备
            EelectricityBean temperatureBean = electricityBeans.get(0);
            ///获取设备的ID
            int temEhmId = temperatureBean.getPemId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            device8060Present.queryAlertData(ElectricityAlertBean.class, temEhmId, startTime, endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }

    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        device8060Present.queryAlertData(ElectricityAlertBean.class, ehmID, startTime, endTime);
    }

    @Override
    public void requestAlert() {


        List<ElectricityAlertBean> air = getAlertData();
        alertBeans.clear();

        for (int i = 0; i < air.size(); i++) {
            ElectricityAlertBean alertBean = air.get(i);
            AlertBeans alertBeansItem = new AlertBeans();
            alertBeansItem.setEhaInfo(alertBean.getPeaInfo());
            alertBeansItem.setEhaTime(alertBean.getPeaTime());
            alertBeansItem.setEcmDeviceName(alertBean.getPemName());
            alertBeans.add(alertBeansItem);
        }
        alertAdapter.notifyDataSetChanged();

    }

    @Override
    public void requestHistory() {

        try {

            if (lineDataList.isEmpty()) {
                lineDataList.addAll(getHistoryData());
            } else {
                lineDataList.get(0).getPageInfo().getList().clear();
                //更新电量数据
                lineDataList.get(0).setPehElequantity(getHistoryData().get(0).getPehElequantity());
                //更新历史数据
                lineDataList.get(0).getPageInfo().getList().addAll(getHistoryData().get(0).getPageInfo().getList());
            }

            if (newHistoryAdapter == null) {
                //设置历史数据列表
                newHistoryAdapter = new NewHistoryAdapter<>(getActivity(), lineDataList.get(0).getPageInfo().getList(), R.layout.new_history_temp, this);
                newHistoryAdapter.setItemClickListener(this);
                newHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
                newHistoryList.setAdapter(newHistoryAdapter);

            } else {

                newHistoryAdapter.notifyDataSetChanged();
            }

            //如果是点击曲线图，则查询完成后，直接跳转到详情页
            Log.d("kljlert", "requestHistory: " + intoHistoryDetail);
            if (intoHistoryDetail) {
                View v = new View(getActivity());
                v.setId(R.id.new_history_name);
                onItemClickListener(v, historyBottomPos);
            }

        } catch (IndexOutOfBoundsException e) {
            Log.d("history", "initChartTemper: 未获取到历史数据");
        }
    }

    @Override
    public void queryDeviceManage() {
        //只执行一次
        if (addBeans.size() == 0)
            device8060Present.queryDeviceManage(EelectricityBean.class);
    }

    @Override
    public void requestManage() {

//        先清空原有的设备数据
        addBeans.clear();

        List<EelectricityBean> manageData = getManageData();

//        适配添加设备的数据
        for (EelectricityBean b :
                manageData) {

            String ip = b.getPemAddress() + "";
            String port = String.valueOf(b.getPemAddress());

            Device_Detail_AddBean bean = new Device_Detail_AddBean();

            bean.setDeviceName(b.getPemName());

            bean.setEhmId(String.valueOf(b.getPemId()));

            //位置
            bean.setPosition(String.valueOf(b.getPemAddress()));
            //更新时间
            bean.setUpdateTime(String.valueOf(b.getIntervalTime()));
            addBeans.add(bean);

        }

//        设置 设备管理 中的 添加设备 列表
        manageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(View v, int position) {

        //历史数据点击查看 曲线图
        if (v.findViewById(com.example.pool.R.id.new_history_name) != null) {

            //必须先查询，才能进入查看曲线图
            if (lineDataList.isEmpty() || lineDataList.get(0).getPageInfo().getList().isEmpty() ) {
                historyBottomSheetBehavior.show();
                ToastManage.getInstance().toastShortShow("请先查询历史数据");
                return;
            }


            //获取电量仪的数据

            //A电流
            ArrayList<String> aCur = new ArrayList<>();
            //B电流
            ArrayList<String> bCur = new ArrayList<>();
            //C电流
            ArrayList<String> cCur = new ArrayList<>();

            //A电压
            ArrayList<String> aVol = new ArrayList<>();
            //B电压
            ArrayList<String> bVol = new ArrayList<>();
            //C电压
            ArrayList<String> cVol = new ArrayList<>();

            //总功率
            ArrayList<String> totalPower = new ArrayList<>();
            //用电时间
            ArrayList<String> electricityTime = new ArrayList<>();
            //电量
            ArrayList<String> electricity = new ArrayList<>();

            //历史时间
            ArrayList<String> times = new ArrayList<>();

            //获取电压，电流数据
            for (ElectricityHistoryBean.PageInfoBean.ListBean data :
                    lineDataList.get(0).getPageInfo().getList()) {

                //A电流
                aCur.add(data.getPehAcur() + "");
                //B电流
                bCur.add(data.getPehBcur() + "");
                //C电流
                cCur.add(data.getPehCcur() + "");

                //A电压
                aVol.add(data.getPehAvol() + "");
                //B电压
                bVol.add(data.getPehBvol() + "");
                //C电压
                cVol.add(data.getPehCvol() + "");

                //总功率
                totalPower.add(data.getPehTpap() + "");
                //用电时间
                electricityTime.add(data.getPehElecharge() + "");
                //电量
                electricity.add(data.getPehElequantity() + "");

                //历史记录时间
                times.add(data.getPehTime() + "");

            }

            Intent intent = new Intent(getActivity(), ElectricityHistory.class);

            //传递显示的数据
            //电流
            intent.putStringArrayListExtra("aCur", aCur);
            intent.putStringArrayListExtra("bCur", bCur);
            intent.putStringArrayListExtra("cCur", cCur);

            //电压
            intent.putStringArrayListExtra("aVol", aVol);
            intent.putStringArrayListExtra("bVol", bVol);
            intent.putStringArrayListExtra("cVol", cVol);

            //功率电量数据
            intent.putStringArrayListExtra("totalPower", totalPower);
            intent.putStringArrayListExtra("electricityTime", electricityTime);
            intent.putStringArrayListExtra("electricity", electricity);

            //历史时间
            intent.putStringArrayListExtra("times", times);

            //传递曲线的单位
            ArrayList<String> suffixList = new ArrayList<>();
            suffixList.add(" V");
            suffixList.add(" A");
            intent.putStringArrayListExtra("suffixList", suffixList);

            intent.putExtra("title", electricityBeans.get(position).getPemName());
            startActivity(intent);

            //当进入到曲线图后，再重置为false
            intoHistoryDetail = false;
            return;
        }


        //跳转到电量仪的详情页
        Intent intent = new Intent(getActivity(), ElectricityDetail.class);
        intent.putExtra("tool_title", electricityBeans.get(position).getPemName());
        intent.putExtra("ele_id", electricityBeans.get(position).getPemId());
        getActivity().startActivity(intent);
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
        String ehmId = String.valueOf(electricityBeans.get(position).getPemId());
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
        deviceManageEditBean.setActionID(electricityBeans.get(position).getPemId());

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

        TextView deviceInterval = new TextView(getActivity());
        deviceInterval.setText("保存间隔：" + data.getUpdateTime());
        textViewList.add(deviceInterval);

        DynamicDeviceManage.getInstance().addManageItem(textViewList, svHolder.linearLayout);
    }

    @Override
    public void createAddAlert() {

        View vs = getActivity().getLayoutInflater().inflate(com.example.base.R.layout.electricity_add_device, null, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("添加设备");
        builder.setView(vs);
        builder.show();

        //设置按钮监听
        initAddDevicePanel(vs);

    }

    @Override
    public void bindHistoryData(BaseViewHolder holder, ElectricityHistoryBean.PageInfoBean.ListBean data, int position) {

        Log.d("lk;erd", "bindHistoryData: " + data.getPemName());

        TextView historyName = holder.getView(R.id.new_history_name);
        TextView historyTemp = holder.getView(R.id.new_history_temp);
        TextView historyHum = holder.getView(R.id.new_history_hum);
        TextView historyTime = holder.getView(R.id.new_history_time);
        ImageView historyImage = holder.getView(R.id.history_image_list);
        Glide.with(getActivity()).load(LainNewApi.DEVICE_IMAGE).into(historyImage);
        Button loogQx = holder.getView(R.id.look_qx);

        historyTemp.setText("A电流：" + data.getPehAcur());
        historyHum.setText("A电压：" + data.getPehAvol());
        historyTime.setText("时间：" + data.getPehTime());


        loogQx.setOnClickListener((v) -> {
            onItemClickListener(holder.getRootView(), position);
        });

    }


    @Override
    public void electricityUnitPriceMethod(String uniPrice) {

        //未查询历史数据，直接返回
        if (lineDataList.get(0).getPageInfo().getList().isEmpty()) {
            ToastManage.getInstance().toastLongShow("请先查询历史数据");
            return;
        } else if (uniPrice.isEmpty()) {
            ToastManage.getInstance().toastLongShow("请输入单价");
            return;
        }

        double result = lineDataList.get(0).getPehElequantity() * Double.parseDouble(electricityCnitPrice.getText().toString());

        electricityCalcResult.setVisibility(View.VISIBLE);
        electricityCalcValue.setText(String.valueOf(result));
        electricityTotalValue.setText(String.valueOf(lineDataList.get(0).getPehElequantity()));

    }
}
