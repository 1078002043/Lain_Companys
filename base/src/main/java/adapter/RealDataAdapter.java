package adapter;

import android.content.Context;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.TemperatureBean;

/**
 * @ClassName: RealDataAdapter
 * @Description: 实时数据的适配器
 * @Author: YIN LUO FEI
 * @Date: 2020/7/31 8:49
 */
public class RealDataAdapter<T> extends BaseRecyclerViewAdapter<T> {

    public RealDataAdapter(Context context, List<T> dataList, int layoutId, I_RealData<T> i_realData) {
        super(context, dataList, layoutId, i_realData);
    }

    @Override
    protected void bindData(BaseViewHolder holder, T data, int position) {
        //通过接口回调给调用者处理
        if (i_realData != null) {
            i_realData.bindData(holder, data, position);
        }
    }

}
