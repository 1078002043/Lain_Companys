package computer_room.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.DeviceAlertBean;
import computer_room.bean.DeviceIPAllBean;
import computer_room.bean.Device8060List;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 21:15
 * Description：设备告警
 **/
public class DeviceAlertAdapter extends BaseRecyclerViewAdapter<Device8060List> {


    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public DeviceAlertAdapter(Context context, List<Device8060List> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, Device8060List data, int position) {
        Button editBtn = holder.getView(R.id.device_change);
        Button startBtn = holder.getView(R.id.device_start);
        Button deleteBtn = holder.getView(R.id.device_delete);
        TextView moduleName = holder.getView(R.id.module_name);
        TextView deviceManageIp = holder.getView(R.id.device_manage_ip);

        moduleName.setText(data.getName());
        deviceManageIp.setText(data.getDeviceIp().getDiAddress());

        startBtn.setVisibility(View.GONE);
        editBtn.setOnClickListener((v)->{

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });

        deleteBtn.setOnClickListener((v)->{
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });

    }
}
