package device;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import androidx.recyclerview.widget.GridLayoutManager;

import com.example.devicelibrary.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.layoutmanagers.ScrollSmoothLineaerLayoutManager;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.Lain_Application;
import bean.ConfigBean;
import device_adapter.EnvironmentalMonitoring;
import device_adapter.OperationAdapter;
import device_adapter.PowerMonitorAdapter;
import device_adapter.SecurityAdapter;
import device_adapter.SystemManagementAdapter;
import device_adapter.WaterQualityAdapter;
import util.LainNewApi;

/**
 * 加载所有的设备
 */
public class NewAddDeviceUtil implements BaseRecyclerViewAdapter.OnItemClickListener {

    private static NewAddDeviceUtil newAddDeviceUtil;

    private DeviceInterface deviceInterface;

    public static NewAddDeviceUtil getInstance() {
        if (newAddDeviceUtil == null) {
            synchronized (NewAddDeviceUtil.class) {
                if (newAddDeviceUtil == null)
                    newAddDeviceUtil = new NewAddDeviceUtil();
            }
        }
        return newAddDeviceUtil;
    }

    public void setFace(DeviceInterface deviceInterface) {
        this.deviceInterface = deviceInterface;
    }

    //配置名称
    private String configName = "my_config.properties";

    private List<View> viewPagerList;

    //首页的 Fragment View
    private View mainView;

    //环境监控列表
    private UltimateRecyclerView environmental_list;
    //动力监控列表
    private UltimateRecyclerView power_device_list;
    //安防监控列表
    private UltimateRecyclerView security_list;
    //运维监控列表
    private UltimateRecyclerView operation_list;
    //系统管理列表
    private UltimateRecyclerView systemManage_List;
    //水质监控
    private UltimateRecyclerView water_quality_List;

    //环境监控的设备列表
    public List<ConfigBean> environmentBeanList = new ArrayList<>();
    //动力监控的设备列表
    public List<ConfigBean> powerMonitorList;
    //安防监控
    public List<ConfigBean> securityList;
    //运维监控
    public List<ConfigBean> operationBeanList;
    //系统管理
    public List<ConfigBean> systemManageBeanList;
    //水质监控
    public List<ConfigBean> waterBeanList;

    //环境监控列表适配器
    private EnvironmentalMonitoring adapter;

    /**
     * 水质监控列表数据
     */

    public void getWaterQuality() {

        this.waterBeanList = LitePal.where("classify = ? and isshow = ?", "水质监控", "1").find(ConfigBean.class);

        WaterQualityAdapter adapter = new WaterQualityAdapter(Lain_Application.getContext(), waterBeanList, R.layout.water_quality_layout);
        //网格布局管理器，3列
        GridLayoutManager manager = new GridLayoutManager(Lain_Application.getContext(), 3);
        adapter.setItemClickListener(this);
        //设置布局管理器和适配器
        water_quality_List.setLayoutManager(manager);
        water_quality_List.setAdapter(adapter);
    }


    /**
     * 获取系统管理
     * <p>
     * 系统管理列表数据
     */
    public void getSystemManagement() {

        this.systemManageBeanList = LitePal.where("classify = ? and isshow = ?", "系统管理", "1").find(ConfigBean.class);

        SystemManagementAdapter adapter = new SystemManagementAdapter(Lain_Application.getContext(), systemManageBeanList, R.layout.system_management_layout);
        //网格布局管理器，3列
        GridLayoutManager manager = new GridLayoutManager(Lain_Application.getContext(), 3);
        adapter.setItemClickListener(this);
        //设置布局管理器和适配器
        systemManage_List.setLayoutManager(manager);
        systemManage_List.setAdapter(adapter);

    }

    /**
     * 设置 运维监控 的列表
     */

    public void getOperationMonitor() {

        this.operationBeanList = LitePal.where("classify = ? and isshow = ?", "运维监控", "1").find(ConfigBean.class);

        OperationAdapter adapter = new OperationAdapter(Lain_Application.getContext(), operationBeanList, R.layout.operation_layout);

        //网格布局管理器，2列
        GridLayoutManager manager = new GridLayoutManager(Lain_Application.getContext(), 3);
        adapter.setItemClickListener(this);
        //设置布局管理器和适配器
        operation_list.setLayoutManager(manager);
        operation_list.setAdapter(adapter);

    }

    /**
     * 解析动力监控的数据，并设置动力监控的设备列表
     */
    public UltimateRecyclerView getPowerMonitor() {

        //在这里加载设备列表
        this.powerMonitorList = new ArrayList<>();

        this.powerMonitorList = LitePal.where("classify = ? and isshow = ?", "动力监控", "1").find(ConfigBean.class);

        PowerMonitorAdapter adapter = new PowerMonitorAdapter(Lain_Application.getContext(), powerMonitorList, R.layout.power_monitor_layout);

        //网格布局管理器，2列
        GridLayoutManager manager = new GridLayoutManager(Lain_Application.getContext(), 3);
        adapter.setItemClickListener(this);
        //设置布局管理器和适配器
        power_device_list.setLayoutManager(manager);
        power_device_list.setAdapter(adapter);

        return power_device_list;
    }

    public List<View> getViewPager() {
        return viewPagerList;
    }

    /**
     * 获取安防监控
     */

    public void getSecurityMonitor() {

        //保存安防监控数据列表
        this.securityList = LitePal.where("classify = ? and isshow = ?", "安防监控", "1").find(ConfigBean.class);

        //初始化安防监控
        SecurityAdapter adapter = new SecurityAdapter(Lain_Application.getContext(), securityList, R.layout.security_layout);
        //网格布局管理器，2列
        GridLayoutManager managerSecurity = new GridLayoutManager(Lain_Application.getContext(), 3);
        adapter.setItemClickListener(this);
        //设置布局管理器和适配器
        security_list.setLayoutManager(managerSecurity);
        security_list.setAdapter(adapter);
    }

    /**
     * 解析环境监控的数据，并设置环境监控的设备列表
     */
    public void getEnvironment() {



        //保存环境监控数据列表
        this.environmentBeanList = LitePal.where("classify = ? and isshow = ?", "环境监控", "1").find(ConfigBean.class);


        //初始化环境监控适配器
        adapter = new EnvironmentalMonitoring(Lain_Application.getContext(), environmentBeanList, R.layout.environmental_layout);

        //网格布局管理器，2列
        GridLayoutManager manager = new GridLayoutManager(Lain_Application.getContext(), 3);
        adapter.setItemClickListener(this);
        //设置布局管理器和适配器
        environmental_list.setLayoutManager(manager);
        environmental_list.setAdapter(adapter);

    }

    /**
     * 通过 ViewPager 绑定视图,设备列表
     */
    public List<View> bindViewPager(LayoutInflater inflater) {

        viewPagerList = getViewPager(inflater);

        mainView = viewPagerList.get(0);
        //绑定环境监控
        environmental_list = viewPagerList.get(1).findViewById(R.id.computer_room_recycler);
        //绑定动力监控
        power_device_list = viewPagerList.get(2).findViewById(R.id.power_recycler);
        //绑定安防监控
        security_list = viewPagerList.get(3).findViewById(R.id.security_recycler);
        //绑定运维监控
        operation_list = viewPagerList.get(4).findViewById(R.id.operation_recycler);
        //绑定系统管理
        systemManage_List = viewPagerList.get(5).findViewById(R.id.system_management_recycler);
        //绑定水质系统
        water_quality_List = viewPagerList.get(6).findViewById(R.id.water_quality);

        return viewPagerList;
    }

    /**
     * 初始化 ViewPager
     *
     * @param inflater 加载Layout的引用
     * @return ViewPager View 的集合
     */
    public List<View> getViewPager(LayoutInflater inflater) {

        //保存ViewPager View的集合
        List<View> viewList = new ArrayList<>();
        //首页
        viewList.add(inflater.inflate(R.layout.main_viewpager, null));
        //环境监控
        viewList.add(inflater.inflate(R.layout.smart_device_detail, null));
        //动力监控
        viewList.add(inflater.inflate(R.layout.power_monitoring, null));
        //安防监控
        viewList.add(inflater.inflate(R.layout.sercurity_monitoring, null));
        //运维监控
        viewList.add(inflater.inflate(R.layout.operations_monitoring, null));
        //系统管理
        viewList.add(inflater.inflate(R.layout.system_management, null));
        //水质系统
        viewList.add(inflater.inflate(R.layout.water_quality, null));
        //返回View集合
        return viewList;

    }

    @Override
    public void onItemClickListener(View v, int position) {

        int id = v.getId();
        if (id == R.id.environmental_card) {

            //保存环境监控点击项的名称
            LainNewApi.INTENT_TAG = environmentBeanList.get(position).getTitle();
            LainNewApi.DEVICE_IMAGE = environmentBeanList.get(position).getImage();

        } else if (id == R.id.environmental_card2) {

            //保存动力监控点击项的名称
            LainNewApi.INTENT_TAG = powerMonitorList.get(position).getTitle();
            LainNewApi.DEVICE_IMAGE = powerMonitorList.get(position).getImage();

        } else if (id == R.id.environmental_card3) {

            //保存安防监控点击项的名称
            LainNewApi.INTENT_TAG = securityList.get(position).getTitle();
            LainNewApi.DEVICE_IMAGE = securityList.get(position).getImage();

        } else if (id == R.id.environmental_card4) {

            //保存运维点击项的名称
            LainNewApi.INTENT_TAG = operationBeanList.get(position).getTitle();
            LainNewApi.DEVICE_IMAGE = operationBeanList.get(position).getImage();

        } else if (id == R.id.environmental_card5) {

            //保存系统管理点击项的名称
            LainNewApi.INTENT_TAG = systemManageBeanList.get(position).getTitle();
            LainNewApi.DEVICE_IMAGE = systemManageBeanList.get(position).getImage();

        } else if (id == R.id.environmental_card6) {

            //保存水质监控点击项的名称
            LainNewApi.INTENT_TAG = waterBeanList.get(position).getTitle();
            LainNewApi.DEVICE_IMAGE = waterBeanList.get(position).getImage();

        }

        //跳转到设备的详请页，并传递所点击项的名称作为Toolbar的标题，返回模块名称和设备名称给调用者
        deviceInterface.getClickDevice(LainNewApi.DEVICE_TAG, LainNewApi.INTENT_TAG, LainNewApi.DEVICE_IMAGE);

    }
}
