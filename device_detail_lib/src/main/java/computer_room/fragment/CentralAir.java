package computer_room.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.device_detail_lib.R;
import com.example.device_detail_lib.R2;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import base.Base_Devices_Detail;
import butterknife.BindView;
import computer_room.adapter.CenterAirAdapter;
import computer_room.bean.CenterAirDeviceBean;
import computer_room.i_interface.I_CentralAir;
import computer_room.present.CentralAirPresenter;
import util.LainNewApi;

/**
 * 中央空调
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/12 09 : 20
 */
public class CentralAir extends Base_Devices_Detail implements I_CentralAir, View.OnClickListener {

    UltimateRecyclerView centerAirRecycler;
    //中央空调 Presenter
    private CentralAirPresenter presenter;
    private List<CenterAirDeviceBean> airDeviceBeans = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化Presenter
        presenter = new CentralAirPresenter(this);
        //隐藏顶部导航栏
        setHeadPanelShow(false);

        //绑定控件
        centerAirRecycler = viewList.get(0).findViewById(R.id.center_air_recycler);

        CenterAirDeviceBean centerAirDeviceBean = new CenterAirDeviceBean();
        centerAirDeviceBean.setName("4号中央空调");
        airDeviceBeans.add(centerAirDeviceBean);

        CenterAirAdapter airAdapter = new CenterAirAdapter(getActivity(), airDeviceBeans, R.layout.center_air_temp);
        centerAirRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        centerAirRecycler.setAdapter(airAdapter);

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
        viewList.add(R.layout.new_center_air_real);

        return viewList;
    }

    /**
     * 实时获取数据
     */
    @Override
    public void run() {
        //初始化实时数据列表
        presenter.initCentralAirList();
        infoHandler.postDelayed(this, LainNewApi.SECOND);
    }

    @Override
    public void onClick(View view) {
    }
}
