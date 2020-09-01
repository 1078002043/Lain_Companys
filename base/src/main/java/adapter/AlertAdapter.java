package adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.base.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.AlertBeans;

/**
 * 普通设备的报警数据列表
 */
public class AlertAdapter extends BaseRecyclerViewAdapter<AlertBeans> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public AlertAdapter(Context context, List<AlertBeans> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, AlertBeans data, int position) {

        //报警名称
        TextView mAlertName = holder.getView(R.id.alert_name);

        //报警时间
        TextView mAlertTime = holder.getView(R.id.alert_time);

        //报警信息，每行不超过16个字，显示两行，字数超过24个字自动省略
        TextView mAlertInfo = holder.getView(R.id.alert_info);

        //设置相应的数据
        mAlertName.setText(data.getEcmDeviceName());
        mAlertTime.setText(data.getEhaTime());
        mAlertInfo.setText(data.getEhaInfo());

    }
}
