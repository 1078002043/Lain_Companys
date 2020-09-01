package computer_room.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import bean.Alert8052Bean;
import bean.AlertDeviceBean;
import computer_room.adapter.Device8052Adapter;
import computer_room.bean.Device8052Bean;
import computer_room.i_interface.I_Device8052;
import computer_room.present.Device8052Present;
import util.DateUtil;
import util.LainNewApi;

public class Device8052Fragment extends Base8052Fragment<Device8052Bean, Alert8052Bean> implements I_Device8052 {
    //实时列表
    private Device8052Present device8052Present;
    //报警列表
    private Device8052Adapter device8052Adapter;
    //实时数据
    List<Device8052Bean> device8052Beans = new ArrayList<>();

    //设备的ID
    private int elmID;

    public Device8052Fragment(String linkReal8052, String linkAlert8052) {
        super(linkReal8052, linkAlert8052);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        device8052Present = new Device8052Present(this);

        //初始化报警面板，由子类来实现
        init8052Alert(1);

        //列表
        UltimateRecyclerView accessRecycler = viewList.get(0).findViewById(R.id.realtime_8052_recycler);
        //设备的状态图标
        int[] statusImg = new int[]{R.drawable.non_localized, R.drawable.non_localized, R.drawable.non_localized};
        //初始化 Adapter
        device8052Adapter = new Device8052Adapter(getActivity(), device8052Beans, R.layout.device_8052_temp,statusImg);
        accessRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        accessRecycler.setAdapter(device8052Adapter);

        //初始化实时数据列表
        base8052Present.queryRealData(Device8052Bean.class);
        isStartAutoUp(true);


    }

    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据
        viewList.add(R.layout.device_8052_real);
        //报警数据
        viewList.add(R.layout.device_8052_alert);

        return viewList;
    }

    /**
     * 实时更新
     */
    @Override
    public void run() {
        base8052Present.queryRealData();
        infoHandler.postDelayed(this, LainNewApi.SECOND);
    }

    /**
     * 处理实时数据
     * @param device8052Bean
     */
    @Override
    public void dealRealData8052(List<Device8052Bean> device8052Bean) {

        try {
            device8052Beans.clear();
            device8052Beans.addAll(device8052Bean);

            //更新实时列表
            device8052Adapter.notifyDataSetChanged();
            //报警列表数据，只更新一次
            if (alertDeviceBeanList.size() == 0) {
                elmID = device8052Bean.get(0).getEkmId();
                initAlertView(device8052Bean);
            }
        } catch (Exception e) {
            Log.d("TAG", "dealRealData8052: 8052设备 获取数据为空");
        }
    }

    /**
     * 更新报警面板的设备列表
     *
     * @param alertDevice
     */
    public void initAlertView(List<Device8052Bean> alertDevice) {

        alertDeviceBeanList.clear();

        //初始化报警面板的数据
        //遍历实时数据，将实时数据中的设备做查询报警的设备
        for (Device8052Bean bean :
                alertDevice) {
            AlertDeviceBean alertDevices = new AlertDeviceBean();
            alertDevices.setName(bean.getEkmName());
            alertDeviceBeanList.add(alertDevices);
        }
        //更新列表
        alertPanelDevice.notifyRealDataSetChanged();
    }

    /**
     * 实时数据回调
     */
    @Override
    protected void responseAlert8052() {
        dealAlertData8052(getAlertList());
    }

    /**
     * 报警数据回调
     */
    @Override
    protected void responseReal8052() {
        dealRealData8052(getRealList());
    }


    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {
        try {
            //默认查询第一个设备
            Device8052Bean bean = device8052Beans.get(0);
            ///获取设备的ID
            int ekmId = bean.getEkmId();
            //开始查询
            base8052Present.queryAlertData(Alert8052Bean.class,ekmId,startTime,endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryAlertData: 未获取到数据");
        }
    }

    @Override
    public void queryAlertData() {

        try {
            //默认查询第一个设备
            Device8052Bean bean8052 = device8052Beans.get(0);
            ///获取设备的ID
            int ekmId = bean8052.getEkmId();
            //结束时间
            String endTime = DateUtil.getInstance().getCurrentDate();
            //开始时间
            String startTime = DateUtil.getInstance().getStartTime();
            //开始查询
            base8052Present.queryAlertData(Alert8052Bean.class,ekmId,startTime,endTime);
        } catch (IndexOutOfBoundsException e) {
            Log.d("temp", "queryHistoryData: 未获取到数据");
        }

    }

    /**
     * 处理报警数据
     * @param alert8052Beans
     */
    @Override
    public void dealAlertData8052(List<Alert8052Bean> alert8052Beans) {
        this.alert8052Beans.clear();
        this.alert8052Beans.addAll(alert8052Beans);
        alert8052Adapter.notifyDataSetChanged();
    }

    @Override
    public int getEhmID() {
        return elmID;
    }
}
