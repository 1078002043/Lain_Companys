package computer_room.adapter;

import android.content.Context;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.DehumidifierBean;

/**
 * 除湿机
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/12 09 : 36
 */
public class DehmidifierAdapter extends BaseRecyclerViewAdapter<DehumidifierBean> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public DehmidifierAdapter(Context context, List<DehumidifierBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, DehumidifierBean data, int position) {

    }
}
