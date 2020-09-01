package view.i_view;

import base.BaseViewHolder;
import bean.WaterPressureRealBean;

/**
 * 所有实时数据设置都回调到 View 类进行操作
 */
public interface I_RealAdapterListen<T> {

    public void realItemAdapter(BaseViewHolder holder, T data, int position);

}
