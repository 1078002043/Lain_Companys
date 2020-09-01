package view_interface;

import base.BaseViewHolder;

/**
 * 历史数据 适配器 的回调接口
 */
public interface I_HistoryAdapter<T> {

    void bindHistoryData(BaseViewHolder holder, T data, int position);

}
