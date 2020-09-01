package view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.pool.R;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.Base_Devices_Detail;
import bean.SamplingRealBean;
import present.SamplingPresent;
import util.Pool_API;
import view.i_view.I_Sampling;

/**
 * 取样泵
 */
public class SamplingFragment extends Base_Devices_Detail implements BaseRecyclerViewAdapter.OnItemClickListener, I_Sampling {

    //初始化 present
    private SamplingPresent samplingPresent;
    //设备列表数据
    private UltimateRecyclerView samplingRecycler;

    //实时数据
    private List<SamplingRealBean> samplingRealBeans;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Present
        samplingPresent = new SamplingPresent(this);
        //设备列表
        samplingRecycler = viewList.get(0).findViewById(R.id.oxygen_real_recycler);
        //初始化适配器
        samplingRealBeans = new ArrayList<>();

        //请求数据
        samplingPresent.dealRealData(Pool_API.Pool_IP + Pool_API.samplingPump, "sampling_real");

    }

    @Override
    public void onItemClickListener(View v, int position) {

    }

    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        viewList.add(R.layout.oxygen_real_layout);
        return viewList;
    }

    @Override
    public void run() {

    }

    @Override
    public void samplingRealComplete(List<SamplingRealBean> samplingRealBeans) {
        //更新数据
        this.samplingRealBeans.clear();
        this.samplingRealBeans.addAll(samplingRealBeans);

    }
}
