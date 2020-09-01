package computer_room.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.device_detail_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import base.Base_Devices_Detail;
import computer_room.bean.DehumidifierBean;

/**
 * 除湿机，没有 Presenter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/12 09 : 35
 */
public class Dehumidifier extends Base_Devices_Detail {

    //列表数据
    private List<DehumidifierBean> dehumidifierList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //实时数据中的 列表
        //除湿器列表
        UltimateRecyclerView dehumidifierRecycler = viewList.get(0).findViewById(R.id.dehumidifier_realtime_recycler);

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
        viewList.add(R.layout.dehumidifier_realtime);
        //报警数据
        viewList.add(R.layout.temperture_alarm);

        return viewList;
    }

    /**
     * 实时获取数据
     */
    @Override
    public void run() {
        //初始化实时数据列表

    }
}
