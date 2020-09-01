package adapter;

import android.content.Context;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import view_interface.I_HistoryAdapter;

/**
 * 历史数据 适配器
 */
public class NewHistoryAdapter<T> extends BaseRecyclerViewAdapter<T> {

    //数据的回调接口
    private I_HistoryAdapter i_historyAdapter;

    public NewHistoryAdapter(Context context, List<T> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    /**
     * @param context          上下文
     * @param dataList         列表数据
     * @param layoutId         布局ID
     * @param i_historyAdapter 接收调用者的回调
     */
    public NewHistoryAdapter(Context context, List<T> dataList, int layoutId, I_HistoryAdapter i_historyAdapter) {
        super(context, dataList, layoutId);
        this.i_historyAdapter = i_historyAdapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, T data, int position) {

        //回调给调用者处理
        i_historyAdapter.bindHistoryData(holder, data, position);

    }

}
