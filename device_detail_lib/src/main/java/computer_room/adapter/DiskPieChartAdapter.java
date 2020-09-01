package computer_room.adapter;

import android.content.Context;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.DiskPieChartBean;

/**
 * @ClassName: DiskPieChartAdapter
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/7/11 16:13
 */
public class DiskPieChartAdapter extends BaseRecyclerViewAdapter<DiskPieChartBean> {
    public DiskPieChartAdapter(Context context, List<DiskPieChartBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, DiskPieChartBean data, int position) {

    }
}
