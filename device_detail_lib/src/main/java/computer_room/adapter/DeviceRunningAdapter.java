package computer_room.adapter;

import android.content.Context;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.DeviceIPAllBean;
import computer_room.bean.DeviceIpList;
import computer_room.bean.DeviceRunningBean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 17:38
 * Description：设备管理-正在运行
 **/
public class DeviceRunningAdapter extends BaseRecyclerViewAdapter<DeviceIpList> {
    private Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public DeviceRunningAdapter(Context context, List<DeviceIpList> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void bindData(BaseViewHolder holder, DeviceIpList data, int position) {

        Button editBtn = holder.getView(R.id.device_change);
        Button deleteBtn = holder.getView(R.id.device_delete);
        Button startBtn = holder.getView(R.id.device_start);
        TextView moduleName = holder.getView(R.id.module_name);
        TextView deviceManageIp = holder.getView(R.id.device_manage_ip);
        Button deviceSwitch = holder.getView(R.id.device_start);

        if (data.getDiIsConnect() == 1) {
            //设置设备的开关状态
            deviceSwitch.setText("启动");
            //获取资源设置背景样式
            deviceSwitch.setBackground(context.getResources().getDrawable(R.drawable.blue_btn_bg, null));
        } else {
            deviceSwitch.setText("关闭");
            //获取资源设置背景样式

            deviceSwitch.setBackground(context.getResources().getDrawable(R.drawable.green_btn_bg, null));
        }

        //设备名称
        moduleName.setText(data.getDeviceList().get(0).getName());
        //设备的IP与端口
        deviceManageIp.setText(data.getDiAddress() + ":" + data.getDiPort());

        startBtn.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });

        editBtn.setOnClickListener((v) -> {

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });

        deleteBtn.setOnClickListener((v)->{

            if (itemClickListener != null)
                itemClickListener.onItemClickListener(v, position);

        });

    }
}
