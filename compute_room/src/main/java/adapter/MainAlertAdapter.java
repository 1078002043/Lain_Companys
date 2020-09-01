package adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.compute_room.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.MainMonthAlert;

/**
 * 进行报警消息详情页，查询所有的报警信息，根据条件进行筛选数据
 */
public class MainAlertAdapter extends BaseRecyclerViewAdapter<MainMonthAlert> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public MainAlertAdapter(Context context, List<MainMonthAlert> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, MainMonthAlert data, int position) {

        TextView alertName = holder.getView(R.id.main_alert_name);
        TextView alertTime = holder.getView(R.id.main_alert_time);
        Button readBtn = holder.getView(R.id.main_alert_btn);

        alertName.setText(data.getAlarmName());
        alertTime.setText(data.getTime());

        //两个状态都必须设置背景，不然渲染结果不一致
        if (data.getIsRead() == 0) {
            readBtn.setText("未读");
            readBtn.setBackground(recyclerContext.getDrawable(R.drawable.circle_alert_bg));
        } else if (data.getIsRead() == 1){
            readBtn.setText("已读");
            readBtn.setBackground(recyclerContext.getDrawable(R.drawable.circle_close_bg));
            readBtn.setEnabled(false);
        }

        //标识已经的按钮，由调用者处理
        readBtn.setOnClickListener((v) -> {

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });

    }
}
