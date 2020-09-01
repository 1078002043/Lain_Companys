package adapter;

import base.BaseViewHolder;

/**
 * @ClassName: I_RealData
 * @Description: 实时数据适配器回调
 * @Author: YIN LUO FEI
 * @Date: 2020/7/31 8:53
 */
public interface I_RealData<T> {

    void bindData(BaseViewHolder holder, T data, int position);

}
