package adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.example.base.R;

import java.util.List;

import bean.AlertDeviceBean;
import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;

/**
 * 选择设备进行查询报警数据的列表甜酸器
 */
public class AlertDeviceAdapter extends BaseRecyclerViewAdapter<AlertDeviceBean> {

    private Context context;

    //记录当前的点击位置
    private int itemClickPosition = -1;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public AlertDeviceAdapter(Context context, List<AlertDeviceBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, AlertDeviceBean data, int position) {

        //显示设备列表Item
        Button btn = holder.getView(R.id.btn_panel_device);
        btn.setText(data.getName());

        //默认选择 第一个，如果已经选中其它的按钮，则不进行设置
        selectedItem(position, data, btn);

        //设备的点击
        btn.setOnClickListener((v) -> {

            //如果 != -1 代表点击过，通过记录到的位置，将数据源中的元素设为未选中
            if (itemClickPosition != -1) {
                getDataList().get(itemClickPosition).setCheck(false);
            }

            //设置当前的点击位置
            itemClickPosition = position;
            //更新当前选中元素的选中状态
            data.setCheck(true);

            //更新列表，解决视图不更新的问题
            notifyDataSetChanged();

            //处理基本操作后，再回调给调用者进行后续操作
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });

    }

    /**
     * 选择后改变Item的属性
     *
     * @param position        点击的Item 位置
     * @param data            点击Item对应的数据
     * @param temperatureName 点击的按钮实例
     */
    private void selectedItem(int position, AlertDeviceBean data, Button temperatureName) {

        //从数据源中获取该元素是否选中
        if (data.isCheck()) {
            temperatureName.setBackground(context.getDrawable(R.drawable.real_sel_item));
            temperatureName.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            //不选中时，设置相关属性
            temperatureName.setBackground(context.getDrawable(R.drawable.real_no_sel_item));
            temperatureName.setTextColor(Color.parseColor("#1EABDD"));
        }
    }

    /**
     * 刷新实时数据时，如果未选中Item，则默认选择第一个，否则选择上次选中的Item
     */
    public void notifyRealDataSetChanged() {

        //如果不为空，刷新时，默认选中第一个，再进行刷新列表
        if (itemClickPosition == -1 && !dataList.isEmpty()) {

            //记录选中的位置
            itemClickPosition = 0;
            //更新列表
            dataList.get(0).setCheck(true);
            notifyDataSetChanged();

        } else if (!dataList.isEmpty()) {

            //如果刷新时，已经选择过其它的ITEM，则刷新时，也选中所点击的Item
            dataList.get(itemClickPosition).setCheck(true);
            notifyDataSetChanged();

        }

    }

}
