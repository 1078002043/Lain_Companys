package computer_room.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import base.Base_Devices_Detail;
import computer_room.adapter.DeviceSwitchAdapter;
import computer_room.bean.DeviceSwitchBean;
import computer_room.i_interface.I_DeviceSwitch;
import computer_room.present.DeviceSwitchPresent;
import util.LainNewApi;

public class DeviceSwitchFragment extends Base_Devices_Detail implements I_DeviceSwitch {

    private DeviceSwitchPresent deviceSwitchPresent;
    private DeviceSwitchAdapter switchAdapter;
    private List<DeviceSwitchBean> deviceSwitchBeans = new ArrayList<>();
    //实时数据请求链接
    private String realLink = "";

    public DeviceSwitchFragment(String realLink) {
        this.realLink = realLink;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Presenter
        deviceSwitchPresent = new DeviceSwitchPresent(this);

        //因为只有实时数据，就不用显示顶部导航栏
        setHeadPanelShow(false);

        //新风机数据列表
        UltimateRecyclerView deviceSwitchRecycler = viewList.get(0).findViewById(R.id.device_switch_recycler);
        //实时适配器
        switchAdapter = new DeviceSwitchAdapter(getActivity(), deviceSwitchBeans, R.layout.device_switch_temp, new int[]{R.drawable.alarm_bg, R.drawable.alarm_bg});
        deviceSwitchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        deviceSwitchRecycler.setAdapter(switchAdapter);

        //初始化实时数据列表
        deviceSwitchPresent.queryRealData();

        //实时更新
        infoHandler.postDelayed(this, LainNewApi.SECOND);
    }

    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        viewList.add(R.layout.device_switch_layout);
        return viewList;
    }

    @Override
    public void run() {
        deviceSwitchPresent.queryRealData();
        infoHandler.postDelayed(this, LainNewApi.SECOND);
    }

    @Override
    public String getRealLink() {
        return realLink;
    }

    @Override
    public void realDatResponse(List<DeviceSwitchBean> deviceSwitchBeans) {

        this.deviceSwitchBeans.clear();
        this.deviceSwitchBeans.addAll(deviceSwitchBeans);
        switchAdapter.notifyDataSetChanged();


    }

}
