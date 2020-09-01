package view_interface;

import android.widget.TextView;

/**
 * @ClassName: I_UpdateRealView
 * @Description: 更新实时数据的View
 * @Author: YIN LUO FEI
 * @Date: 2020/7/31 10:16
 */
public interface I_UpdateRealView<T> {

    /**
     * 更新实时数据的View
     *
     * @param textView 更新的View
     * @param position 更新的View所在位置
     */
    void updateRealView(TextView textView, T data, int position);

}
