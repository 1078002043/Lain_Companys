package computer_room.adapter;

import android.content.Context;
import android.widget.TextView;


import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.AddEventBean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 23:24
 * Description：日志-添加事件-列表
 **/
public class AddEventAdapter extends BaseRecyclerViewAdapter<AddEventBean.SysLogBean.ListBean> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public AddEventAdapter(Context context, List<AddEventBean.SysLogBean.ListBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, AddEventBean.SysLogBean.ListBean data, int position) {
        TextView executor = holder.getView(R.id.executor);

        TextView message = holder.getView(R.id.log_message);
        TextView time = holder.getView(R.id.log_time);
        //触发的IP
        TextView pos = holder.getView(R.id.device_manage_pos);
        //日志触发人，可能为空，所以需要判断;
        if (data.getUsername() == null)
            executor.setText("未知");
        else
            executor.setText(data.getUsername());

        //日志的信息
        message.setText(data.getOperationName());
        //触发的时间
        time.setText(data.getCreateDate());
        //设置触发的IP，可能为空，所以需要判断;
        if (data.getIp() == null)
            pos.setText("未知");
        else
            pos.setText(data.getIp());

    }
}
