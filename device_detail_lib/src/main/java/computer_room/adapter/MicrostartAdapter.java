package computer_room.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.Device8052List;
import computer_room.bean.DeviceManage8052Bean;
import computer_room.bean.DeviceRunningBean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 21:17
 * Description：未启动
 **/
public class MicrostartAdapter extends BaseRecyclerViewAdapter<Device8052List> {
    private Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public MicrostartAdapter(Context context, List<Device8052List> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, Device8052List data, int position) {
        //编辑按钮
        Button editBtn = holder.getView(R.id.device_change);
        TextView moduleName = holder.getView(R.id.module_name);
        TextView deviceManageIp = holder.getView(R.id.device_manage_ip);
        Button deviceSwitch = holder.getView(R.id.device_start);
        Button deviceDelete = holder.getView(R.id.device_delete);
        deviceManageIp.setText(data.getDeviceIp().getDiAddress());
        //设备名称
        moduleName.setText(data.getEkmName());
        deviceSwitch.setVisibility(View.GONE);
        //通信按钮
        deviceSwitch.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });
        //编辑按钮
        editBtn.setOnClickListener((v) -> {

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });
        //删除设备
        deviceDelete.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);
        });

    }
}
