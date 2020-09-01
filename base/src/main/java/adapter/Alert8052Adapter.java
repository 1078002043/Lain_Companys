package adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.base.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.Alert8052Bean;

/**
 * 8052报警数据列表
 */
public class Alert8052Adapter extends BaseRecyclerViewAdapter<Alert8052Bean> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public Alert8052Adapter(Context context, List<Alert8052Bean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, Alert8052Bean data, int position) {


        //报警设备名称
        TextView mAlertName = holder.getView(R.id.alert_8052_name);
        //报警时间
        TextView mAlertTime = holder.getView(R.id.alert_8052_time);
        //设备的通道
        TextView mAlertGallery = holder.getView(R.id.alert_8052_gallery);

        //设置相关数据
        mAlertGallery.setText(data.getGallery());
        mAlertName.setText(data.getEkmName());
        mAlertTime.setText(data.getEkaTime());

    }
}
