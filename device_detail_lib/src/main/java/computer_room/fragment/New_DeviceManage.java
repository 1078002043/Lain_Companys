package computer_room.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.device_detail_lib.R;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.rengwuxian.materialedittext.MaterialEditText;


import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import base.BaseRecyclerViewAdapter;
import base.Base_Devices_Detail;
import computer_room.adapter.AlertSettingAdapter;
import computer_room.adapter.DeviceAlertAdapter;
import computer_room.adapter.DeviceRunningAdapter;
import computer_room.adapter.ManageAssetAdapter;
import computer_room.adapter.MicrostartAdapter;
import computer_room.bean.AlertSettingBean;
import computer_room.bean.AssetDeviceAllBean;
import computer_room.bean.Device8052List;
import computer_room.bean.Device8060AllBean;
import computer_room.bean.Device8060List;
import computer_room.bean.DeviceAll8052Bean;
import computer_room.bean.DeviceAllBean;
import computer_room.bean.DeviceAssetsList;
import computer_room.bean.DeviceIPAllBean;
import computer_room.bean.DeviceIpList;
import computer_room.bean.DeviceManage8052Bean;
import computer_room.bean.DeviceManage8060Bean;
import computer_room.bean.DeviceManageDid;
import computer_room.bean.DeviceRunningBean;
import computer_room.i_interface.I_AlertMethod;
import computer_room.i_interface.I_DeviceManage;
import computer_room.i_interface.I_NewDeviceManage;
import computer_room.present.NewDevicePresenter;
import util.DynamicMaterialEdit;
import util.LainNewApi;
import util.ToastManage;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 17:06
 * Description：新的设备管理
 **/
public class New_DeviceManage extends Base_Devices_Detail implements I_DeviceManage,
        BaseRecyclerViewAdapter.OnItemClickListener, OnSpinnerItemSelectedListener, I_NewDeviceManage, I_AlertMethod {

    //环境监控
    private List<String> environmental = new ArrayList<>();
    //运维监控
    private List<String> pperational = Arrays.asList("UPS", "电量仪", "配电监控", "市电监控");
    //安防监控
    private List<String> security = Arrays.asList("红外监控", "烟感监控", "消防监控", "玻璃监控", "门磁监控", "门禁监控");
    //顶部导航栏
    private static final String[] topList = new String[]{"正在运行", "设备告警", "未启动"};
    //添加设备时设备的模块
    private List<DeviceManageDid> deviceManageDids = new ArrayList<>();

    //编辑设置面板
    public BottomSheetDialog bottomSheetBehavior;
    //设置面板的展开状态
    public BottomSheetBehavior mDialogBehavior;


    //报警设置编辑设置面板
    public BottomSheetDialog alertSettingSheet;
    //报警设置设置面板的展开状态
    public BottomSheetBehavior alertSettingBehavior;

    //IP设置编辑设置面板
    public BottomSheetDialog ipSettingSheet;
    //IP设置设置面板的展开状态
    public BottomSheetBehavior ipSettingBehavior;

    //8060设备编辑设置面板
    public BottomSheetDialog device8060Sheet;
    //8060设备设置面板的展开状态
    public BottomSheetBehavior device8060Behavior;

    //8052设备编辑设置面板
    public BottomSheetDialog device8052Sheet;
    //8052设备设置面板的展开状态
    public BottomSheetBehavior device8052Behavior;

    //8052设备编辑设置面板
    public BottomSheetDialog deviceAssetsSheet;
    //8052设备设置面板的展开状态
    public BottomSheetBehavior deviceAssetsBehavior;
    
    //保存点击的报警对应的daId
    public String alertDaId = "0";

    //报警面板的控件
    private MaterialEditText alertEditWeek;
    private MaterialEditText alertTime1;
    private MaterialEditText alertTime2;
    private MaterialEditText alertTime3;
    private MaterialEditText alertTimeInterval;
    private Button alertBtnClr;
    private Button alertBtnDone;

    //IP设置面板
    public NiceSpinner ipSettingDevice;
    private MaterialEditText ipSettingAddress;
    public MaterialEditText ipSettingDeviceName;
    private MaterialEditText ipSettingPort;
    public Button addNewDevice;

    //8060设置面板控件
    public NiceSpinner device8060Ip;
    public NiceSpinner device8060Address;
    public NiceSpinner device8060Gallery;
    public NiceSpinner device8060Device;
    public MaterialEditText device8060Name;
    public Button device8060Btn;


    //8052设置面板控件
    public NiceSpinner device8052Ip;
    public NiceSpinner device8052Address;
    public NiceSpinner device8052Gallery;
    public NiceSpinner device8052Device;
    public MaterialEditText device8052Name;
    public Button device8052Btn;
    private List<String> spinner8052GalleryList = new ArrayList<>();
    private List<DeviceAll8052Bean> deviceAll8052Beans = new ArrayList<>();
    
    //资产管理面板控件
    public NiceSpinner deviceAssetsIp;
    public NiceSpinner deviceAssetsAddress;
    public MaterialEditText deviceAssetsName;
    public MaterialEditText deviceAssetsNum;
    public Button deviceAssetsBtn;

    //IP设置
    private View deviceRunning;
    //8060
    private View deviceAlert;
    //8052
    private View microstart;
    //资产管理
    private View assetView;
    //报警设置
    private View alertSettingView;
    //设备管理中添加设备的模块设备
    private NiceSpinner niceSpinner2;

    //正在运行适配器
    private DeviceRunningAdapter runningAdapter;
    //设备管理的Presenter
    private NewDevicePresenter devicePresenter;
    //设备未启动
    private MicrostartAdapter microstartAdapter;
    //报警设置适配器
    private AlertSettingAdapter alertSettingAdapter;
    //资产管理
    private ManageAssetAdapter assetAdapter;

    //设备未启动数据源
    private List<DeviceRunningBean> microstartBeans;
    //记录需要添加的设备位置，用来获取 Did
    private int deviceDid = 0;
    //设备IP
    private EditText deviceIp;
    //设备端口
    private EditText devicePort;
    //取消添加
    private Button cancelAdd;
    //确认添加
    private Button rightAdd;
    //判断是更新还是添加
    public String upOrAdd = "add_device";
    //保存当前点击的 Item 位置
    private int currentDevicePos = 0;
    //记录顶部导航栏的位置
    public int topPos = 0;

    public NiceSpinner niceSpinner;
    List<String> deviceSelectSet;

    
    View editView;
    //报警设置编辑面板
    View alertView;
    //IP设置编辑面板
    View ipView;
    //8060编辑面板
    View view8060;
    //8052编辑面板
    View view8052;
    //资产管理编辑面板
    View viewAssets;

    List<MaterialEditText> editTexts;
    LinearLayout linearLayout;


    int itemClickPos = 0;

    //设备告警的假数据
    List<DeviceManage8060Bean> deviceAlertBeans;

    private DeviceAlertAdapter deviceAlertAdapter;

    List<DeviceManage8060Bean> manage8060Beans = new ArrayList<>();
    List<DeviceManage8052Bean> manage8052Beans = new ArrayList<>();
    List<AssetDeviceAllBean> assetDeviceAllBeans = new ArrayList<>();

    //保存8060，8052的 IP
    String device8060IP = "";
    String device8052IP = "";
    String deviceAssetIP = "";

    int spinnerIndex = 0;

    List<DeviceIPAllBean> device8060IPList = new ArrayList<>();
    List<DeviceIPAllBean> device8052IPList = new ArrayList<>();
    List<DeviceIPAllBean> deviceAssetIPList = new ArrayList<>();

    //获取8060所有设备
    private List<Device8060AllBean> device8060AllBeans = new ArrayList<>();
    //获取所有的设备
    private List<DeviceAllBean> deviceAllBeans = new ArrayList<>();
    //IP设置中的 所有IP 和 设备名称
    private List<DeviceIpList> deviceIpList = new ArrayList<>();
    //IP设置中的 所有8060 IP 和 设备名称
    private List<Device8060List> device8060List = new ArrayList<>();
    //IP设置中的 所有80652IP 和 设备名称
    private List<Device8052List> device8052List = new ArrayList<>();
    //所有的 报警设置 数据
    private List<AlertSettingBean> deviceAlertList = new ArrayList<>();
    //所有的 资产管理 数据
    private List<DeviceAssetsList> deviceAssetsList = new ArrayList<>();

    //保存8060的IP列表
    private List<DeviceIPAllBean> device8060IPBeans = new ArrayList<>();
    //保存8052的IP列表
    private List<DeviceIPAllBean> device8052IPBeans = new ArrayList<>();
    //保存资产管理的IP列表
    private List<DeviceIPAllBean> deviceAssetsIPBeans = new ArrayList<>();


    //报警方法的点击接口
    private Map<String, String> alertMap = new HashMap<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化Presenter
        devicePresenter = new NewDevicePresenter(this);

//        magicIndicator2.setVisibility(View.VISIBLE);

        //获取三个ViewPager 的 View
        deviceRunning = viewList.get(0);
        deviceAlert = viewList.get(1);
        microstart = viewList.get(2);
        assetView = viewList.get(3);
        alertSettingView = viewList.get(4);
        //绑定三个ViewPager中的View
        //正在运行 列表
        UltimateRecyclerView runningRecycler = deviceRunning.findViewById(R.id.device_run_recycler);
        //设备告警 列表
        UltimateRecyclerView deviceAlertRecycler = deviceAlert.findViewById(R.id.device_alert_recycler);
        //未启动 列表
        UltimateRecyclerView microstartRecycler = microstart.findViewById(R.id.microstart_recycler);
        UltimateRecyclerView assetRecycler = assetView.findViewById(R.id.manage_asset_recycler);
        //报警设置
        UltimateRecyclerView alertSettingRecycler = alertSettingView.findViewById(R.id.alert_setting_list);


      /*  DeviceRunningBean b = new DeviceRunningBean();
        b.setTest("hell");
        runningBeans.add(b);*/
        //设备告警的假数据
        deviceAlertBeans = new ArrayList<>();
        //未启动的假数据
        microstartBeans = new ArrayList<>();
        //报警设置
        deviceAlertList = new ArrayList<>();
       /* MicrostartBean b2 = new MicrostartBean();
        microstartBeans.add(b2);*/

        //初始化适配器
        //正在运行适配器
        runningAdapter = new DeviceRunningAdapter(getActivity(), deviceIpList, R.layout.device_template);
        runningAdapter.setItemClickListener(this);
        runningRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        runningRecycler.setAdapter(runningAdapter);
        //设备告警适配器
        deviceAlertAdapter = new DeviceAlertAdapter(getActivity(), device8060List, R.layout.device_template);
        deviceAlertAdapter.setItemClickListener(this);
        deviceAlertRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        deviceAlertRecycler.setAdapter(deviceAlertAdapter);
        //未启动适配器
        microstartAdapter = new MicrostartAdapter(getActivity(), device8052List, R.layout.device_template);
        microstartAdapter.setItemClickListener(this);
        microstartRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        microstartRecycler.setAdapter(microstartAdapter);

        //报警设置
        alertSettingAdapter = new AlertSettingAdapter(getActivity(), deviceAlertList, R.layout.alert_setting_list_temp, this);
        alertSettingAdapter.setItemClickListener(this);
        alertSettingRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        alertSettingRecycler.setAdapter(alertSettingAdapter);

        //资产管理
        assetAdapter = new ManageAssetAdapter(getActivity(), deviceAssetsList, R.layout.device_template);
        assetAdapter.setItemClickListener(this);
        assetRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        assetRecycler.setAdapter(assetAdapter);

        //初始化顶部导航栏
        magicIndicator2.setVisibility(View.VISIBLE);

        //初始化界面
        initMagicIndicator();
        //编辑面板
        editView = LayoutInflater.from(getActivity()).inflate(R.layout.device_detail_add_devie, null, false);
        alertView = LayoutInflater.from(getActivity()).inflate(R.layout.alert_setting_panel, null, false);

        linearLayout = editView.findViewById(R.id.add_edit_layout);

        Button addBtn = editView.findViewById(R.id.add_new_device);

        //初始化 编辑面板中的控件
        initBottomSheet(linearLayout);
        //初始化报警设置面板的控件
        initAlertBottomSheet();


        bottomSheetBehavior = new BottomSheetDialog(getActivity());
        bottomSheetBehavior.setContentView(editView);
        //必须是在 setContentView之后再获取 behavior
        mDialogBehavior = BottomSheetBehavior.from((View) editView.getParent());
        //设置展开的状态
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //报警设置底部面板
        alertSettingSheet = new BottomSheetDialog(getActivity());
        alertSettingSheet.setContentView(alertView);
        //必须是在 setContentView之后再获取 behavior
        alertSettingBehavior = BottomSheetBehavior.from((View) alertView.getParent());
        //设置展开的状态
        alertSettingBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //IP设置底部面板
        ipView = LayoutInflater.from(getActivity()).inflate(R.layout.ip_setting_sheet, null, false);

        //初始化IP设置面板的控件
        initIpBottomSheet();

        ipSettingSheet = new BottomSheetDialog(getActivity());
        ipSettingSheet.setContentView(ipView);
        //必须是在 setContentView之后再获取 behavior
        ipSettingBehavior = BottomSheetBehavior.from((View) ipView.getParent());
        //设置展开的状态
        ipSettingBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //8060设置底部面板
        view8060 = LayoutInflater.from(getActivity()).inflate(R.layout.device_8060_sheet, null, false);

        //初始化8060设置面板的控件
        init8060BottomSheet();

        device8060Sheet = new BottomSheetDialog(getActivity());
        device8060Sheet.setContentView(view8060);
        //必须是在 setContentView之后再获取 behavior
        device8060Behavior = BottomSheetBehavior.from((View) view8060.getParent());
        //设置展开的状态
        device8060Behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //8052设置底部面板
        view8052 = LayoutInflater.from(getActivity()).inflate(R.layout.device_8052_sheet, null, false);

        //初始化8060设置面板的控件
        init8052BottomSheet();

        device8052Sheet = new BottomSheetDialog(getActivity());
        device8052Sheet.setContentView(view8052);
        //必须是在 setContentView之后再获取 behavior
        device8052Behavior = BottomSheetBehavior.from((View) view8052.getParent());
        //设置展开的状态
        device8052Behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //资产管理设置底部面板
        viewAssets = LayoutInflater.from(getActivity()).inflate(R.layout.device_assets_sheet, null, false);

        //初始化资产管理设置面板的控件
        initAssetsBottomSheet();

        deviceAssetsSheet = new BottomSheetDialog(getActivity());
        deviceAssetsSheet.setContentView(viewAssets);
        //必须是在 setContentView之后再获取 behavior
        deviceAssetsBehavior = BottomSheetBehavior.from((View) viewAssets.getParent());
        //设置展开的状态
        deviceAssetsBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                
        bottomSheetBehavior.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                manageName.setText("");
                manageIP.setText("");
                managePort.setText("");
                manageClassify.setText("");
            }
        });


        //获取所有设备的 名称
        if (deviceAllBeans.isEmpty())
            devicePresenter.getDeviceAll();

        addBtn.setOnClickListener((v) -> {

            switch (topPos) {
                case 0:


                    Map<String, String> deviceInfos = new HashMap<>();
                    //设备IP
                    deviceInfos.put("diAddress", manageIP.getText().toString());
                    //设备端口
                    deviceInfos.put("diPort", managePort.getText().toString());

                    if (upOrAdd.equals("up")) {
                        //更新设备，就必须添加设备标识 diId
                        deviceInfos.put("diId", String.valueOf(deviceIpList.get(itemClickPos).getDiId()));
                        devicePresenter.updateDevice(deviceInfos);
                    } else {
                        //添加设备，就必须添加设备标识 dId
                        deviceInfos.put("did", String.valueOf(deviceAllBeans.get(itemClickPos).getDid()));

                        devicePresenter.insertDevice(deviceInfos);
                    }
                    break;
                case 1:

                    if (upOrAdd.equals("up")) {
                        Map<String, String> device8060Infos = new HashMap<>();

                        //设备IP
                        device8060Infos.put("name", manageName.getText().toString());

                        //更新设备，就必须添加设备标识 diId
                        device8060Infos.put("id", String.valueOf(device8060List.get(itemClickPos).getId()));
                        devicePresenter.update8060Device(device8060Infos);
                    } else {
                        Map<String, String> device8060Infos = new HashMap<>();
                        //设备IP
                        device8060Infos.put("name", manageName.getText().toString());

                        device8060Infos.put("kId", String.valueOf(device8060AllBeans.get(spinner8060Device.getSelectedIndex()).getKId()));
                        device8060Infos.put("gallery", String.valueOf(spinner8060Channel.getSelectedIndex()));
                        device8060Infos.put("address", String.valueOf(spinner8060AddressList.get(spinner8060Address.getSelectedIndex())));
                        device8060Infos.put("diId", String.valueOf(device8060IPBeans.get(spinner8060Address.getSelectedIndex()).getDiId()));
                        //添加设备，就必须添加设备标识 dId
                        devicePresenter.insert8060Device(device8060Infos);
                    }
                    break;
                case 2:
                    if (upOrAdd.equals("up")) {
                        Map<String, String> device8052Infos = new HashMap<>();
                        //设备IP
                        device8052Infos.put("ekmName", manageName.getText().toString());

                        //更新设备，就必须添加设备标识 diId
                        device8052Infos.put("ekmId", String.valueOf(device8052List.get(itemClickPos).getEkmId()));

                        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.update8052;
                        devicePresenter.update8060Device(device8052Infos, url);
                    } else {
                        Map<String, String> device8060Infos = new HashMap<>();
                        //设备IP
                        device8060Infos.put("ekmName", manageName.getText().toString());
                        device8060Infos.put("kId", String.valueOf(device8052List.get(spinner8060Device.getSelectedIndex()).getKId()));
                        device8060Infos.put("gallery", spinner8060ChannelList.get(spinner8060Channel.getSelectedIndex()));

                        Log.d("dsfgdrg", "onActivityCreated: " + spinner8060AddressList.size());
                        device8060Infos.put("ekmAddress", String.valueOf(spinner8060AddressList.get(spinner8060Address.getSelectedIndex())));
                        device8060Infos.put("diId", String.valueOf(device8052IPBeans.get(spinner8052IP.getSelectedIndex()).getDiId()));
                        //添加设备，就必须添加设备标识 dId
                        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.insert8052;
                        devicePresenter.insert8060Device(device8060Infos, url);
                    }
                    break;
                case 3:
                    if (upOrAdd.equals("up")) {
                        Map<String, String> device8052Infos = new HashMap<>();
                        //设备IP
                        device8052Infos.put("emdName", manageName.getText().toString());
                        //更新设备，就必须添加设备标识 diId
                        device8052Infos.put("emdId", String.valueOf(deviceAssetsList.get(itemClickPos).getEmdId()));
                        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.assetUpdateManage;
                        devicePresenter.update8060Device(device8052Infos, url);
                    } else {
                        Map<String, String> device8060Infos = new HashMap<>();
                        //设备IP
                        device8060Infos.put("emdName", manageName.getText().toString());
                        device8060Infos.put("number", quantityCount.getText().toString());
                        device8060Infos.put("address", String.valueOf(spinner8060AddressList.get(spinner8060Address.getSelectedIndex())));
                        device8060Infos.put("diId", String.valueOf(deviceAssetsIPBeans.get(itemClickPos).getDiId()));
                        //添加设备，就必须添加设备标识 dId
                        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.assetInsertManage;
                        devicePresenter.insert8060Device(device8060Infos, url);
                    }
                    break;
            }


        });
        //请求设备管理中的列表
        devicePresenter.getDeviceIPList();
        //隐藏顶部面板
        head_panel.setVisibility(View.GONE);
    }
    
    //初始化资产管理的面板
    private void initAssetsBottomSheet() {

        

        deviceAssetsIp = viewAssets.findViewById(R.id.device_assets_ip);
        deviceAssetsIp.setOnSpinnerItemSelectedListener(this);
        deviceAssetsAddress = viewAssets.findViewById(R.id.device_assets_Address);
        deviceAssetsAddress.setOnSpinnerItemSelectedListener(this);
        deviceAssetsName = viewAssets.findViewById(R.id.device_assets_name);
        deviceAssetsNum = viewAssets.findViewById(R.id.device_assets_num);
        deviceAssetsBtn = viewAssets.findViewById(R.id.device_assets_btn);

        //初始化Assets设置地址
        deviceAssetsAddress.attachDataSource(spinner8060AddressList);

        deviceAssetsBtn.setOnClickListener(v -> {

            if (upOrAdd.equals("up")) {
                Map<String, String> device8052Infos = new HashMap<>();
                //设备IP
                device8052Infos.put("emdName", deviceAssetsName.getText().toString());
                //更新设备，就必须添加设备标识 diId
                device8052Infos.put("emdId", String.valueOf(deviceAssetsList.get(itemClickPos).getEmdId()));
                String url = LainNewApi.getInstance().getRootPath() + LainNewApi.assetUpdateManage;
                devicePresenter.update8060Device(device8052Infos, url);
            } else {
                Map<String, String> device8060Infos = new HashMap<>();
                //设备IP

                device8060Infos.put("emdName", deviceAssetsName.getText().toString());
                device8060Infos.put("number", deviceAssetsNum.getText().toString());

                Log.d("kjlkerer", "initAssetsBottomSheet: " + spinner8060AddressList.get(spinnerIndex));
                device8060Infos.put("address", spinner8060AddressList.get(spinnerIndex));
                device8060Infos.put("diId", String.valueOf(deviceAssetsIPBeans.get(deviceAssetsIp.getSelectedIndex()).getDiId()));


                //添加设备，就必须添加设备标识 dId
                String url = LainNewApi.getInstance().getRootPath() + LainNewApi.assetInsertManage;
                devicePresenter.insert8060Device(device8060Infos, url);
            }

        });
        
    }
    //初始化8052的面板
    private void init8052BottomSheet() {

        device8052Ip = view8052.findViewById(R.id.device_8052_ip);
        device8052Ip.setOnSpinnerItemSelectedListener(this);
        device8052Address = view8052.findViewById(R.id.device_8052_Address);
        device8052Address.setOnSpinnerItemSelectedListener(this);
        device8052Gallery = view8052.findViewById(R.id.device_8052_gallery);
        device8052Gallery.setOnSpinnerItemSelectedListener(this);
        device8052Device = view8052.findViewById(R.id.device_8052_device);
        device8052Device.setOnSpinnerItemSelectedListener(this);
        device8052Name = view8052.findViewById(R.id.device8052_name);
        device8052Btn = view8052.findViewById(R.id.device_8052_btn);

        //初始化8052设置地址
        device8052Address.attachDataSource(spinner8060AddressList);
        //初始化8052通道
        for (int i = 1; i < 10; i++) {
            spinner8052GalleryList.add("DI" + i);
        }

        device8052Gallery.attachDataSource(spinner8052GalleryList);

        device8052Btn.setOnClickListener(v -> {

            if (upOrAdd.equals("up")) {
                
                Map<String, String> device8052Infos = new HashMap<>();
                //设备IP
                device8052Infos.put("ekmName", device8052Name.getText().toString());

                //更新设备，就必须添加设备标识 diId
                device8052Infos.put("ekmId", String.valueOf(device8052List.get(itemClickPos).getEkmId()));

                String url = LainNewApi.getInstance().getRootPath() + LainNewApi.update8052;
                devicePresenter.update8060Device(device8052Infos, url);
                
            } else {

                Map<String, String> device8060Infos = new HashMap<>();
                //设备IP
                device8060Infos.put("ekmName", device8052Name.getText().toString());
                device8060Infos.put("kId", String.valueOf(deviceAll8052Beans.get(device8052Device.getSelectedIndex()).getKId()));
                device8060Infos.put("gallery", spinner8052GalleryList.get(device8052Gallery.getSelectedIndex()));

                Log.d("kljoer", "init8052BottomSheet: " + spinner8060AddressList.get(spinnerIndex) + "---" + spinnerIndex);
                device8060Infos.put("ekmAddress", spinner8060AddressList.get(spinnerIndex));

                device8060Infos.put("diId", String.valueOf(device8052IPBeans.get(device8052Ip.getSelectedIndex()).getDiId()));
                //添加设备，就必须添加设备标识 dId
                String url = LainNewApi.getInstance().getRootPath() + LainNewApi.insert8052;
                devicePresenter.insert8060Device(device8060Infos, url);
            }

        });
    }
    
    //初始化 8060 底部面板
    private void init8060BottomSheet() {


        device8060Ip = view8060.findViewById(R.id.device_8060_ip);
        device8060Ip.setOnSpinnerItemSelectedListener(this);
        device8060Address = view8060.findViewById(R.id.device_8060_Address);
        device8060Address.setOnSpinnerItemSelectedListener(this);
        device8060Gallery = view8060.findViewById(R.id.device_8060_gallery);
        device8060Gallery.setOnSpinnerItemSelectedListener(this);
        device8060Device = view8060.findViewById(R.id.device_8060_device);
        device8060Device.setOnSpinnerItemSelectedListener(this);
        device8060Name = view8060.findViewById(R.id.device8060_name);
        device8060Btn = view8060.findViewById(R.id.device_8060_btn);

        //初始化8060设置地址
        for (int i = 1; i < 21; i++) {
            spinner8060AddressList.add(i + "");
        }
        device8060Address.attachDataSource(spinner8060AddressList);
        //初始化8060通道
        for (int i = 1; i < 10; i++) {
            spinner8060ChannelList.add("RL" + i);
        }

        device8060Gallery.attachDataSource(spinner8060ChannelList);

        device8060Btn.setOnClickListener(v -> {

            if (upOrAdd.equals("up")) {
                Map<String, String> device8060Infos = new HashMap<>();

                //设备IP
                device8060Infos.put("name", device8060Name.getText().toString());

                //更新设备，就必须添加设备标识 diId
                device8060Infos.put("id", String.valueOf(device8060List.get(itemClickPos).getId()));
                devicePresenter.update8060Device(device8060Infos);
            } else {
                Map<String, String> device8060Infos = new HashMap<>();
                //设备IP
                device8060Infos.put("name", device8060Name.getText().toString());

                device8060Infos.put("kId", String.valueOf(device8060AllBeans.get(device8060Device.getSelectedIndex()).getKId()));
                device8060Infos.put("gallery", String.valueOf(device8060Gallery.getSelectedIndex() + 1));
//                if (device8060Address.getSelectedIndex() == 0) {
//
//                    device8060Infos.put("address", String.valueOf(spinner8060AddressList.get(0)));
//
//                } else {
//
//                    device8060Infos.put("address", String.valueOf(spinner8060AddressList.get(device8060Address.getSelectedIndex() - 1)));
//
//                }

                device8060Infos.put("address", spinner8060AddressList.get(spinnerIndex));
                device8060Infos.put("diId", String.valueOf(device8060IPBeans.get(device8060Ip.getSelectedIndex()).getDiId()));
                //添加设备，就必须添加设备标识 dId
                devicePresenter.insert8060Device(device8060Infos);
            }

        });

    }

    //初始化底部面板
    private void initIpBottomSheet() {

        ipSettingDevice = ipView.findViewById(R.id.ip_setting_device);
        ipSettingDevice.setOnSpinnerItemSelectedListener(this);

        ipSettingAddress = ipView.findViewById(R.id.ip_setting_address);
        ipSettingDeviceName = ipView.findViewById(R.id.ip_setting_device_name);
        ipSettingPort = ipView.findViewById(R.id.ip_setting_port);
        addNewDevice = ipView.findViewById(R.id.add_new_device);

        addNewDevice.setOnClickListener(v -> {


            Map<String, String> deviceInfos = new HashMap<>();
            //设备IP
            deviceInfos.put("diAddress", ipSettingAddress.getText().toString());
            //设备端口
            deviceInfos.put("diPort", ipSettingPort.getText().toString());

            if (upOrAdd.equals("up")) {
                //更新设备，就必须添加设备标识 diId
                deviceInfos.put("diId", String.valueOf(deviceIpList.get(itemClickPos).getDiId()));
                devicePresenter.updateDevice(deviceInfos);
            } else {

                //如果为0，则获取数据源中的第一个设备
                if (deviceDid == 0)
                    deviceDid = deviceAllBeans.get(0).getDid();

                //添加设备，就必须添加设备标识 dId
                deviceInfos.put("did", deviceDid + "");

                devicePresenter.insertDevice(deviceInfos);
            }

            //隐藏Sheet
            ipSettingSheet.cancel();

        });

    }
    //初始化报警设置面板
    private void initAlertBottomSheet() {


        alertEditWeek = alertView.findViewById(R.id.alert_edit_week);
        alertTime1 = alertView.findViewById(R.id.alert_time1);
        alertTime2 = alertView.findViewById(R.id.alert_time2);
        alertTime3 = alertView.findViewById(R.id.alert_time3);
        alertTimeInterval = alertView.findViewById(R.id.alert_time_interval);
        alertBtnClr = alertView.findViewById(R.id.alert_btn_clr);
        alertBtnDone = alertView.findViewById(R.id.alert_btn_done);

        alertBtnClr.setOnClickListener(v -> {
            alertSettingSheet.cancel();
        });

        alertBtnDone.setOnClickListener(v -> {

            String week = alertEditWeek.getText().toString();
            String time1 = alertTime1.getText().toString();
            String time2 = alertTime2.getText().toString();
            String time3 = alertTime3.getText().toString();
            String interval = alertTimeInterval.getText().toString();

            Map<String, String> mapJson = new HashMap<>();
            mapJson.put("daId", alertDaId);
            mapJson.put("timeQuantumOne", time1);
            mapJson.put("timeQuantumTwo", time2);
            mapJson.put("timeQuantumThree", time3);
            mapJson.put("intervalTime", interval);
            //报警方式
            mapJson.put("emailStatus", alertMap.get("email"));
            mapJson.put("smsStatus", alertMap.get("sms"));
            mapJson.put("soundLightStatus", alertMap.get("sound"));
            mapJson.put("phoneStatus", alertMap.get("phone"));

            devicePresenter.updateAlertInfo(mapJson);

        });

    }

    public MaterialEditText manageName;
    public MaterialEditText manageIP;
    public MaterialEditText managePort;
    public MaterialEditText manageClassify;
    //排插数量
    public MaterialEditText quantityCount;

    //设备地址
    public NiceSpinner spinner8060Address;
    List<String> spinner8060AddressList = new ArrayList<>();
    //设备通道
    public NiceSpinner spinner8060Channel;
    List<String> spinner8060ChannelList = new ArrayList<>();
    //设备IP
    public NiceSpinner spinner8060IP;
    List<String> spinner8060IPList = new ArrayList<>();

    //设备8052 IP
    public NiceSpinner spinner8052IP;
    List<String> spinner8052IPList = new ArrayList<>();

    //设备资产管理 IP
    public NiceSpinner spinnerAssetsIP;
    List<String> spinnerAssetsIPList = new ArrayList<>();

    //设备列表
    public NiceSpinner spinner8060Device;
    List<String> spinner8060DeviceList = new ArrayList<>();

    //设备列表
    public NiceSpinner spinner8052Device;
    List<String> spinner8052DeviceList = new ArrayList<>();

    /**
     * 初始化编辑面板的 控件
     */
    private void initBottomSheet(LinearLayout linearLayout) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        DynamicMaterialEdit.getInstance().setLayoutInflater(inflater, getActivity());
        editTexts = new ArrayList<>();

        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(R.id.device_manage_name, "设备名称", "");
        MaterialEditText ip = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(R.id.device_address, "IP地址", "");
        MaterialEditText port = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(R.id.device_port, "IP端口", "");
        MaterialEditText classify = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(R.id.device_classify, "所属分组", "");
        //资产管理-排插数量
        MaterialEditText quantity = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(R.id.quantityCount, "排插数量", "");
        editTexts.add(name);
        editTexts.add(ip);
        editTexts.add(port);
        editTexts.add(classify);
        editTexts.add(quantity);

        for (int i = 0; i < editTexts.size(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(30, 20, 30, 20);
            //添加时倒序的，在操作编辑框时，一个要按照倒序来做，第一个则是在 editTextList 中的最后一个
            linearLayout.addView(editTexts.get(i), 0, params);
        }

        manageName = linearLayout.findViewById(R.id.device_manage_name);
        manageIP = linearLayout.findViewById(R.id.device_address);
        managePort = linearLayout.findViewById(R.id.device_port);
        manageClassify = linearLayout.findViewById(R.id.device_classify);
        quantityCount = linearLayout.findViewById(R.id.quantityCount);

        //动态添加选择设备的 Spinner
        niceSpinner = new NiceSpinner(getActivity());
        niceSpinner.setOnSpinnerItemSelectedListener(this);
        deviceSelectSet = new LinkedList<>();
        for (int i = 0; i < deviceAllBeans.size(); i++) {
            deviceSelectSet.add(deviceAllBeans.get(i).getName());
        }
        niceSpinner.attachDataSource(deviceSelectSet);
        niceSpinner.setId(R.id.device_spinner);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(30, 20, 30, 20);
        linearLayout.addView(niceSpinner, 0, params);

        //8060模块的添加设备
        spinner8060Address = new NiceSpinner(getActivity());
        spinner8060Address.setOnSpinnerItemSelectedListener(this);

        //设备通道
        //8060模块的添加设备
        spinner8060Channel = new NiceSpinner(getActivity());
        spinner8060Channel.setOnSpinnerItemSelectedListener(this);
        spinner8060ChannelList = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            spinner8060ChannelList.add("RL" + i);
        }
        spinner8060Channel.attachDataSource(spinner8060ChannelList);
        spinner8060Channel.setId(R.id.spinner8060Channel);


        //设备IP
        spinner8060IP = new NiceSpinner(getActivity());
        spinner8060IP.setOnSpinnerItemSelectedListener(this);
        spinner8060IPList = new LinkedList<>();
        spinner8060IP.attachDataSource(spinner8060IPList);
        spinner8060IP.setId(R.id.spinner8060IP);

        //设备8052 IP
        spinner8052IP = new NiceSpinner(getActivity());
        spinner8052IP.setOnSpinnerItemSelectedListener(this);
        spinner8052IPList = new LinkedList<>();

        spinner8052IP.attachDataSource(spinner8052IPList);
        spinner8052IP.setId(R.id.spinner8052IP);

        //设备资产管理 IP
        spinnerAssetsIP = new NiceSpinner(getActivity());
        spinnerAssetsIP.setOnSpinnerItemSelectedListener(this);
        spinnerAssetsIPList = new LinkedList<>();

        spinnerAssetsIP.attachDataSource(spinnerAssetsIPList);
        spinnerAssetsIP.setId(R.id.spinnerAssetsIP);

        //选择设备
        spinner8060Device = new NiceSpinner(getActivity());
        spinner8060Device.setOnSpinnerItemSelectedListener(this);
        spinner8060DeviceList = new LinkedList<>();
        spinner8060Device.attachDataSource(spinner8060DeviceList);
        spinner8060Device.setId(R.id.spinner8060Device);

        spinner8052Device = new NiceSpinner(getActivity());
        spinner8052Device.setOnSpinnerItemSelectedListener(this);
        spinner8052DeviceList = new LinkedList<>();
        spinner8052Device.attachDataSource(spinner8052DeviceList);
        spinner8052Device.setId(R.id.spinner8052Device);

        linearLayout.addView(spinner8060Address, 0, params);
        linearLayout.addView(spinner8060Channel, 0, params);
        linearLayout.addView(spinner8060IP, 0, params);
        linearLayout.addView(spinner8060Device, 0, params);


        linearLayout.addView(spinner8052IP, 0, params);
        linearLayout.addView(spinner8052Device, 0, params);


        linearLayout.addView(spinnerAssetsIP, 0, params);
//        //模块
//        NiceSpinner niceSpinner = view.findViewById(R.id.nice_spinner);
//        niceSpinner.setOnSpinnerItemSelectedListener(this);
//        List<String> dataset = new LinkedList<>(Arrays.asList("环境监控", "动力监控", "安防监控"));
//        niceSpinner.attachDataSource(dataset);
//        //设备
//        niceSpinner2 = view.findViewById(R.id.nice_spinner2);
//        niceSpinner2.attachDataSource(environmental);
//        //下拉框的选择监听
//        niceSpinner2.setOnSpinnerItemSelectedListener((parent, parent_view, position, id) -> {
//            deviceDid = deviceManageDids.get(position).getDId();
//        });
//
//        //设备的协议
//        NiceSpinner niceSpinner3 = view.findViewById(R.id.nice_spinner3);
//        List<String> dataset3 = new LinkedList<>(Arrays.asList("A 协议", "B 协议", "C 协议"));
//        niceSpinner3.attachDataSource(dataset3);
//
//        deviceIp = view.findViewById(R.id.new_device_manage_ip);
//        devicePort = view.findViewById(R.id.new_device_manage_port);
//        //确认添加
//        cancelAdd = view.findViewById(R.id.cancel_commit);
//        //取消添加
//        rightAdd = view.findViewById(R.id.true_commit);
//        //取消添加
//        cancelAdd.setOnClickListener((v) -> {
//            bottomSheetBehavior.cancel();
//        });
//        rightAdd.setOnClickListener((v) -> {
//            Map<String, String> deviceInfos = new HashMap<>();
//            //设备IP
//            deviceInfos.put("diAddress", deviceIp.getText().toString());
//            //设备端口
//            deviceInfos.put("diPort", devicePort.getText().toString());
//            //设备的ID
//            deviceInfos.put("dId", deviceDid + "");
//            //设备组ID
//            deviceInfos.put("gId", "2");
//            //如果是更新，就必须添加设备标识 diId
//            if (upOrAdd.equals("update")) {
//                //更新设备
//                deviceInfos.put("diId", microstartBeans.get(currentDevicePos).getDiId() + "");
//                devicePresenter.updateDevice(deviceInfos);
//            } else {
//                //插入设备
//                devicePresenter.insertDevice(deviceInfos);
//            }
//
//        });


    }

    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();


        viewList.add(R.layout.device_running);

        viewList.add(R.layout.device_alert);

        viewList.add(R.layout.microstart);
        //资产管理
        viewList.add(R.layout.manage_asset);
        //报警设置
        viewList.add(R.layout.manage_alert_setting);

        return viewList;
    }

    @Override
    public void run() {

    }

    @Override
    public void onItemClickListener(View v, int position) {
        int id = v.getId();//编辑用户
        //保存点击的位置
        itemClickPos = position;

        if (id == R.id.device_change) {//更新设备
            upOrAdd = "up";
            switch (topPos) {
                case 0:

                    addNewDevice.setText("修改");
                    //隐藏设置选择
                    ipSettingDevice.setVisibility(View.GONE);
                    //获取数据并设置到编辑框中
                    ipSettingDeviceName.setText(deviceIpList.get(position).getDeviceList().get(0).getName());
                    ipSettingDeviceName.setEnabled(false);
                    ipSettingAddress.setText(deviceIpList.get(position).getDiAddress());
                    ipSettingPort.setText(String.valueOf(deviceIpList.get(position).getDiPort()));

                    ipSettingSheet.show();

                    break;
                case 2:

                    device8052Btn.setText("修改");
                    device8052Ip.setVisibility(View.GONE);
                    device8052Device.setVisibility(View.GONE);

//                    int gallery = Integer.parseInt(device8052List.get(position).getGallery().split("DI")[1]);

                    if (device8052List.get(position).getGallery().equals("DI0")) {

                        device8052Address.setSelectedIndex(0);
                        device8052Gallery.setSelectedIndex(0);

                    } else {
                        device8052Gallery.setSelectedIndex(spinner8052GalleryList.indexOf(device8052List.get(position).getGallery()));
                        Log.d("kljoer", "onItemClickListener: " + device8052List.get(position).getEkmAddress());
                        device8052Address.setSelectedIndex(device8052List.get(position).getEkmAddress() - 1);
                    }

                    //禁用
                    device8052Address.setEnabled(false);
                    device8052Address.setTextColor(Color.GRAY);
                    device8052Gallery.setEnabled(false);
                    device8052Gallery.setTextColor(Color.GRAY);

                    device8052Name.setText(device8052List.get(position).getEkmName());
                    device8052Name.setEnabled(true);

                    //显示sheet
                    device8052Sheet.show();
                    
                    break;
                case 1:

                    device8060Btn.setText("修改");
                    device8060Ip.setVisibility(View.GONE);
                    device8060Device.setVisibility(View.GONE);

                    if (device8060List.get(position).getGallery() == 0) {
                        device8060Address.setSelectedIndex(0);
                        device8060Gallery.setSelectedIndex(0);

                    } else {
                        device8060Gallery.setSelectedIndex(spinner8060ChannelList.indexOf("RL" + device8060List.get(position).getGallery()));
                        device8060Address.setSelectedIndex(device8060List.get(position).getAddress() - 1);
                    }

                    //禁用
                    device8060Address.setEnabled(false);
                    device8060Address.setTextColor(Color.GRAY);
                    device8060Gallery.setEnabled(false);
                    device8060Gallery.setTextColor(Color.GRAY);

                    device8060Name.setText(device8060List.get(position).getName());
                    device8060Name.setEnabled(true);

                    //显示sheet
                    device8060Sheet.show();

                    break;
                case 3:

                    deviceAssetsBtn.setText("修改");
                    deviceAssetsIp.setVisibility(View.GONE);

                    if (deviceAssetsList.get(position).getAddress() == 0) {
                        deviceAssetsAddress.setSelectedIndex(0);
                    } else {
                        deviceAssetsAddress.setSelectedIndex(deviceAssetsList.get(position).getAddress() - 1);
                    }

                    //禁用
                    deviceAssetsAddress.setEnabled(false);
                    deviceAssetsAddress.setTextColor(Color.GRAY);
                    deviceAssetsNum.setEnabled(false);
                    deviceAssetsNum.setTextColor(Color.GRAY);
                    deviceAssetsNum.setText(deviceAssetsList.get(position).getNumber() + "");

                    deviceAssetsName.setText(deviceAssetsList.get(position).getEmdName());

                    //显示sheet
                    deviceAssetsSheet.show();
                    
                    break;
            }

//            bottomSheetBehavior.show();


            //开启通信
        } else if (id == R.id.device_start) {//必须传入map，不然无法使用 PUT
            Map<String, String> mapDevice = new HashMap<>();
            mapDevice.put("", "");

            if (topPos == 0) {
                if (deviceIpList.get(position).getDiIsConnect() == 1)
                    //关闭通信
                    devicePresenter.deviceCommunication(mapDevice, deviceIpList.get(position).getDiId() + "", 0 + "");
                else
                    //开启通信
                    devicePresenter.deviceCommunication(mapDevice, deviceIpList.get(position).getDiId() + "", 1 + "");
                return;

            }
            //删除设备
        } else if (id == R.id.device_delete) {
            switch (topPos) {
                case 0:
                    devicePresenter.deleteDevice(deviceIpList.get(position).getDiId() + "");
                    break;
                case 1:
                    devicePresenter.delete8060Device(device8060List.get(position).getId() + "");
                    break;
                case 2:
                    String url = LainNewApi.getInstance().getRootPath() + LainNewApi.delete8052 + device8052List.get(position).getEkmId();
                    devicePresenter.delete8060Device(device8052List.get(position).getEkmId() + "", url);
                    break;
                case 3:
                    String assetDelUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.assetDeleteManage + deviceAssetsList.get(position).getEmdId();
                    devicePresenter.delete8060Device(deviceAssetsList.get(position).getEmdId() + "", assetDelUrl);
                    break;
            }

        } else if (id == R.id.alert_setting_edit) {
            //编辑 报警设置

            //保存DaId
            alertDaId = deviceAlertList.get(position).getDaId() + "";
            alertEditWeek.setText(deviceAlertList.get(position).getWeek());
            alertTime1.setText(deviceAlertList.get(position).getTimeQuantumOne());
            alertTime2.setText(deviceAlertList.get(position).getTimeQuantumTwo());
            alertTime3.setText(deviceAlertList.get(position).getTimeQuantumThree());
            alertTimeInterval.setText(deviceAlertList.get(position).getIntervalTime() + "");

            alertSettingSheet.show();

        }


    }


    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {
        super.dealWitchReal(requestTag, requestUrl, responseStr);
    }

    /**
     * 设备管理中添加设备时选择的协议
     *
     * @param parent   NiceSpinner View
     * @param view     View
     * @param position 点击的位置
     * @param id       item 的 ID
     */
    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
        //保存选中设备的ID
        spinnerIndex = position;
        Log.d("kjoioer", "onItemSelected: " + spinnerIndex);
        deviceDid = deviceAllBeans.get(position).getDid();
    }

    /**
     * 获取 设备
     */
//    @Override
//    public void getAllIPDevice(List<DeviceIPAllBean> runningBeans) {
//
//        switch (topPos) {
//            case 0:
//                if (runningBeans.size() > 0) {
//                    //更新正在运行的设备
//                    this.deviceIpList.clear();
//                    this.deviceIpList.addAll(runningBeans);
//                    runningAdapter.notifyDataSetChanged();
//                    //获取设备的 名称
//                    devicePresenter.getDeviceAll();
//                }
//                break;
//            case 1:
//
//                if (runningBeans.size() > 0) {
//                    device8060IPList.clear();
//                    device8060IPList.addAll(runningBeans);
//                    device8060IP = runningBeans.get(0).getDiAddress() + ":" + runningBeans.get(0).getDiPort();
//
//                    //更新添加设备时的信息
//                    spinner8060IPList.clear();
//                    spinner8060IPList.add(device8060IP);
//                    //如果只有一个 Spinner Item，就必须使用 setSelectedIndex(0) 来设置默认选中第一个，不然是无法显示数据的
//                    spinner8060IP.setSelectedIndex(0);
//                    spinner8060IP.attachDataSource(spinner8060IPList);
//
//                    for (int i = 0; i < deviceAlertBeans.size(); i++) {
//                        deviceAlertBeans.get(i).setDiAddress(device8060IP);
//                    }
//
//                    //获取设备的 名称
//                    devicePresenter.getDeviceAll8060();
//                }
//                deviceAlertAdapter.notifyDataSetChanged();
//                break;
//            case 2:
//                if (runningBeans.size() > 0) {
//                    device8052IPList.clear();
//                    device8052IPList.addAll(runningBeans);
//                    device8052IP = runningBeans.get(0).getDiAddress() + ":" + runningBeans.get(0).getDiPort();
//                    Log.d("dfasdf", "getAllIPDevice: " + device8052IP);
//                    //更新添加设备时的信息
//                    spinner8060IPList.clear();
//                    spinner8060IPList.add(device8052IP);
//                    //如果只有一个 Spinner Item，就必须使用 setSelectedIndex(0) 来设置默认选中第一个，不然是无法显示数据的
//                    spinner8060IP.setSelectedIndex(0);
//                    spinner8060IP.attachDataSource(spinner8060IPList);
//
//                    for (int i = 0; i < manage8052Beans.size(); i++) {
//                        manage8052Beans.get(i).setDiAddress(device8052IP);
//                    }
//
//                    //获取设备的 名称
//                    devicePresenter.getDeviceAll8052();
//                }
//                microstartAdapter.notifyDataSetChanged();
//                break;
//            case 3:
//                if (runningBeans.size() > 0) {
//                    deviceAssetIPList.clear();
//                    deviceAssetIPList.addAll(runningBeans);
//                    deviceAssetIP = runningBeans.get(0).getDiAddress() + ":" + runningBeans.get(0).getDiPort();
//                    Log.d("dfasdf", "getAllIPDevice: " + deviceAssetIP);
//                    //更新添加设备时的信息
//                    spinner8060IPList.clear();
//                    spinner8060IPList.add(deviceAssetIP);
//                    //如果只有一个 Spinner Item，就必须使用 setSelectedIndex(0) 来设置默认选中第一个，不然是无法显示数据的
//                    spinner8060IP.setSelectedIndex(0);
//                    spinner8060IP.attachDataSource(spinner8060IPList);
//
//                    for (int i = 0; i < assetDeviceAllBeans.size(); i++) {
//                        assetDeviceAllBeans.get(i).setDiAddress(deviceAssetIP);
//                    }
//
//                    //获取设备的 名称
////                    devicePresenter.getAssetDeviceAll();
//                }
//                assetAdapter.notifyDataSetChanged();
//                break;
//        }
//
//
//        //获取设备的 Did
////        devicePresenter.getDeviceDid();
//    }
    @Override
    public void findAllDevice8060(List<Device8060AllBean> device8060AllBeans) {

        spinner8060DeviceList.clear();
        for (int i = 0; i < device8060AllBeans.size(); i++) {
            spinner8060DeviceList.add(device8060AllBeans.get(i).getKName());
        }

        this.device8060AllBeans.addAll(device8060AllBeans);
        device8060Device.attachDataSource(spinner8060DeviceList);


    }

    @Override
    public void findAllDeviceAsset(List<AssetDeviceAllBean> assetDeviceAllBeans) {

        this.assetDeviceAllBeans.addAll(assetDeviceAllBeans);

        if (assetDeviceAllBeans.size() > 0) {
            //更新正在运行的设备
            this.assetDeviceAllBeans.clear();
            this.assetDeviceAllBeans.addAll(assetDeviceAllBeans);
            assetAdapter.notifyDataSetChanged();

        }

    }

    //获取IP和设备名称
    @Override
    public void findDeviceIPList(List<DeviceIpList> deviceIpLists) {

        this.deviceIpList.clear();
        this.deviceIpList.addAll(deviceIpLists);

        //更新名称后，再进行刷新
        runningAdapter.notifyDataSetChanged();

    }

    //接收8060所有设备名称和IP
    @Override
    public void findDevice8060List(List<Device8060List> assetDeviceAllBeans) {

        this.device8060List.clear();
        this.device8060List.addAll(assetDeviceAllBeans);
        deviceAlertAdapter.notifyDataSetChanged();

        //请求8060数据成功后，再请求设备列表
        devicePresenter.getDeviceAll8060();

        if (device8060IPBeans.isEmpty())
            devicePresenter.get8060DeviceIP();

    }

    //接收8052所有设备名称和IP
    @Override
    public void findDevice8052List(List<Device8052List> device8052Lists) {
        this.device8052List.clear();
        this.device8052List.addAll(device8052Lists);
        microstartAdapter.notifyDataSetChanged();



        //获取设备的 名称
        if (device8052IPBeans.isEmpty()) {
            //请求8060数据成功后，再请求设备列表
            devicePresenter.getDeviceAll8052();
            devicePresenter.get8052DeviceIP();
        }

    }

    @Override
    public void findAlertSettingList(List<AlertSettingBean> deviceAlertList) {

        this.deviceAlertList.clear();
        this.deviceAlertList.addAll(deviceAlertList);
        alertSettingAdapter.notifyDataSetChanged();

    }

    @Override
    public void findAssetsList(List<DeviceAssetsList> deviceAssetsLists) {

        if (deviceAssetsLists.isEmpty())
            return;

        this.deviceAssetsList.clear();
        this.deviceAssetsList.addAll(deviceAssetsLists);
        assetAdapter.notifyDataSetChanged();


        //获取设备的 名称
        if (deviceAssetsIPBeans.isEmpty())
            devicePresenter.getAssetDeviceIP();

    }

    @Override
    public void find8060Ip(List<DeviceIPAllBean> ip8060) {


        if (ip8060.isEmpty())
            return;

        this.device8060IPBeans.clear();

        for (DeviceIPAllBean ip :
                ip8060) {

            spinner8060IPList.add(ip.getDiAddress());

        }

        this.device8060IPBeans.addAll(ip8060);
        this.device8060Ip.attachDataSource(spinner8060IPList);
        //如果只有一条数据，就必须设置默认选择第一条，不然是无法选择到数据的
        this.device8060Ip.setSelectedIndex(0);
    }

    @Override
    public void find8052Ip(List<DeviceIPAllBean> ip8052) {

        if (ip8052.isEmpty())
            return;

        this.device8052IPBeans.clear();

        for (DeviceIPAllBean ip :
                ip8052) {

            spinner8052IPList.add(ip.getDiAddress());

        }

        this.device8052Ip.attachDataSource(spinner8052IPList);
        this.device8052IPBeans.addAll(ip8052);
        //如果只有一条数据，就必须设置默认选择第一条，不然是无法选择到数据的
        this.device8052Ip.setSelectedIndex(0);

    }

    @Override
    public void findAssetsIp(List<DeviceIPAllBean> ipAssets) {

        //如果获取不到IP，直接返回
        if (ipAssets.isEmpty())
            return;

        this.deviceAssetsIPBeans.clear();

        for (DeviceIPAllBean ip :
                ipAssets) {

            spinnerAssetsIPList.add(ip.getDiAddress());

        }

        this.deviceAssetsIp.attachDataSource(spinnerAssetsIPList);
        this.deviceAssetsIPBeans.addAll(ipAssets);
        //如果只有一条数据，就必须设置默认选择第一条，不然是无法选择到数据的
        this.deviceAssetsIp.setSelectedIndex(0);

    }

    @Override
    public void findDevice8060(List<DeviceManage8060Bean> manage8060Beans) {

        this.manage8060Beans = manage8060Beans;

        if (deviceIpList.size() > 0) {
            //更新正在运行的设备
            this.deviceAlertBeans.clear();
            this.deviceAlertBeans.addAll(manage8060Beans);
            deviceAlertAdapter.notifyDataSetChanged();
            //获取设备的 名称
            devicePresenter.get8060DeviceIP();
        }

        //更新名称后，再进行刷新
        deviceAlertAdapter.notifyDataSetChanged();

    }

    @Override
    public void findDevice8052(List<DeviceManage8052Bean> manage8060Beans) {
        if (manage8060Beans.size() > 0) {
            this.manage8052Beans.clear();
            this.manage8052Beans.addAll(manage8060Beans);
            microstartAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void findDeviceAll8052(List<DeviceAll8052Bean> manage8052Beans) {

        spinner8060DeviceList.clear();
        deviceAll8052Beans.clear();

        for (int i = 0; i < manage8052Beans.size(); i++) {
            spinner8052DeviceList.add(manage8052Beans.get(i).getKName());
        }

        device8052Device.attachDataSource(spinner8052DeviceList);
        //保存8052设备数据
        deviceAll8052Beans.addAll(manage8052Beans);

    }

    @Override
    public void updateAlertSetting(String responseStr) {

        //接收更新的结果
        switch (responseStr) {
            case "true":

                //提示
                ToastManage.getInstance().toastLongShow("更新报警信息成功");
                //关闭编辑面板
                alertSettingSheet.cancel();
                //更新成功后，刷新页面
                devicePresenter.getAlertSetting();

                break;
            case "false":
                //插入失败的提示
                ToastManage.getInstance().toastShortShow("更新报警信息失败");
                break;
        }

    }

    /**
     * 获取设备名称
     *
     * @param deviceAllBeans
     */
    @Override
    public void getAllDevice(List<DeviceAllBean> deviceAllBeans) {

        this.deviceAllBeans = deviceAllBeans;

        deviceSelectSet.clear();
        //只有这样才能取到正确的数据
        for (DeviceAllBean deviceAllBean :
                deviceAllBeans) {
            deviceSelectSet.add(deviceAllBean.getName());
        }

        //更新名称后，再进行刷新
        ipSettingDevice.attachDataSource(deviceSelectSet);
        ipSettingDevice.setSelectedIndex(0);

    }

    /**
     * 获取插入设备的结果
     *
     * @param insertResult 插入设备的结果
     */
    @Override
    public void insertDevice(String insertResult) {

        switch (insertResult) {
            case "true":
                switch (topPos) {
                    case 0:
                        //刷新 设备页面
                        devicePresenter.getDeviceIPList();
                        ipSettingSheet.cancel();
                        break;
                    case 1:
                        devicePresenter.getDevice8060List();
                        device8060Sheet.cancel();
                        break;
                    case 2:
                        devicePresenter.getDevice8052List();
                        device8052Sheet.cancel();
                        break;
                    case 3:
                        //资产管理
                        devicePresenter.getAssetsList();
                        deviceAssetsSheet.cancel();
                        break;
                }

                //提示
                ToastManage.getInstance().toastLongShow("插入设备成功");
                //关闭编辑面板
                bottomSheetBehavior.cancel();

                break;
            case "false":
                //插入失败的提示
                ToastManage.getInstance().toastShortShow("插入设备失败");
                break;
        }
    }

    /**
     * 获取 设备的Did
     *
     * @param deviceManageDids 设备的Did
     */
    @Override
    public void getDeviceDid(List<DeviceManageDid> deviceManageDids) {
        environmental.clear();
        this.deviceManageDids.clear();
        this.deviceManageDids.addAll(deviceManageDids);
        //将名称遍历
        for (DeviceManageDid did :
                deviceManageDids) {
            environmental.add(did.getName());
        }
        //环境监控 Spinner
        niceSpinner2.attachDataSource(environmental);
    }

    /**
     * 通信的结果
     *
     * @param communicationResult 通信处理的结果
     */
    @Override
    public void deviceCommunication(String communicationResult) {

        switch (communicationResult) {
            case "true":

                ToastManage.getInstance().toastShortShow("通信成功");
//
//                if (topPos == 0) {
//
//                    //更新所有的设备
//                    devicePresenter.getDeviceIPAll();
//                    return;
//                }
//
//                ToastManage.getInstance().toastShortShow("通信成功");
//                //更新所有的设备
//                devicePresenter.getDeviceIPAll();
                break;
            default:
                ToastManage.getInstance().toastShortShow("设备通信失败");
                break;
        }
    }

    /**
     * 删除设备
     *
     * @param deleteResult 删除设备的结果
     */
    @Override
    public void deleteDevice(String deleteResult) {

        switch (deleteResult) {
            case "true":
                switch (topPos) {
                    case 0:
                        //刷新页面
                        devicePresenter.getDeviceIPList();
                        break;
                    case 1:
                        devicePresenter.getDevice8060List();
                        break;
                    case 2:
                        devicePresenter.getDevice8052List();
                        break;
                    case 3:
                        devicePresenter.getAssetsList();
                        break;
                }
                ToastManage.getInstance().toastShortShow("删除设备成功");

                break;
            case "false":
                ToastManage.getInstance().toastShortShow("删除设备失败");
                break;
        }
    }

    /**
     * 更新设备信息
     *
     * @param updateResult 更新设备的结果
     */
    @Override
    public void updateDevice(String updateResult) {
        switch (updateResult) {
            case "true":
                if (topPos == 0) {
                    //更新成功，刷新页面
                    devicePresenter.getDeviceIPList();
                    ipSettingSheet.cancel();
                } else if (topPos == 1) {
                    devicePresenter.getDevice8060List();
                    device8060Sheet.cancel();
                } else if (topPos == 2) {
                    devicePresenter.getDevice8052List();
                    device8052Sheet.cancel();
                } else if (topPos == 3) {
                    devicePresenter.getAssetsList();
                    deviceAssetsSheet.cancel();
                }
                ToastManage.getInstance().toastShortShow("更新成功");

                //取消底部面板
//                bottomSheetBehavior.cancel();
                break;
            case "false":
                ToastManage.getInstance().toastShortShow("更新失败");
                break;
        }
    }

    @Override
    public ArrayList<String> getTopItem() {

        ArrayList<String> topItem = new ArrayList<>();
        topItem.add("IP设置");
        topItem.add("8060模块");
        topItem.add("8052模块");
        topItem.add("资产管理");
        topItem.add("报警设置");
        return topItem;
    }

    @Override
    public void magicSelected(int position) {

        topPos = position;
        switch (position) {
            case 1:
                spinner8060ChannelList.clear();
                for (int i = 1; i < 10; i++) {
                    spinner8060ChannelList.add("RL" + i);
                }
                spinner8060Channel.attachDataSource(spinner8060ChannelList);

                //如果为空时才会进行刷新
                if (device8060List.isEmpty())
                    devicePresenter.getDevice8060List();

                break;
            case 2:
                spinner8060ChannelList.clear();
                for (int i = 0; i < 10; i++) {
                    spinner8060ChannelList.add("DI" + i);
                }
                spinner8060Channel.attachDataSource(spinner8060ChannelList);

                //如果为空时才会进行刷新
                if (device8052List.isEmpty())
                    devicePresenter.getDevice8052List();

                break;
            case 3:
                //如果为空时才会进行刷新
                if (deviceAssetsList.isEmpty())
                    devicePresenter.getAssetsList();

                break;
            case 4:
                //如果为空时才会进行刷新
                if (deviceAlertList.isEmpty())
                    devicePresenter.getAlertSetting();
                break;
        }

    }

    @Override
    public void updateAlertMethod(String s, boolean isChecked, int position) {

        //保存是否选中的结果  0 关 1 开
        int isCheckAlert = isChecked ? 1 : 0;
        String isCheckAlertStr = isChecked ? "1" : "0";

        //保存 DaId
        int daId = deviceAlertList.get(position).getDaId();

        switch (s) {
            case "语音":
                alertMap.put("phone", isCheckAlertStr);
                deviceAlertList.get(position).setPhoneStatus(isCheckAlert);
                break;
            case "短信":
                alertMap.put("sms", isCheckAlertStr);
                deviceAlertList.get(position).setSmsStatus(isCheckAlert);
                break;
            case "声光":
                alertMap.put("sound", isCheckAlertStr);
                deviceAlertList.get(position).setSoundLightStatus(isCheckAlert);
                break;
            case "邮箱":
                alertMap.put("email", isCheckAlertStr);
                deviceAlertList.get(position).setEmailStatus(isCheckAlert);
                break;
        }

        devicePresenter.updateAlertAway(deviceAlertList.get(position));


    }
}
