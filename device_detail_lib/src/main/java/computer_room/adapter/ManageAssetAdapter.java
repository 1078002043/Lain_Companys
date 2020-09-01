package computer_room.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.DeviceAssetsList;
import computer_room.bean.DeviceAssetsList;

/**
 * 资产管理的设备列表适配器
 */
public class ManageAssetAdapter extends BaseRecyclerViewAdapter<DeviceAssetsList> {
    public ManageAssetAdapter(Context context, List<DeviceAssetsList> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, DeviceAssetsList data, int position) {
        Button editBtn = holder.getView(R.id.device_change);
        Button startBtn = holder.getView(R.id.device_start);
        Button deleteBtn = holder.getView(R.id.device_delete);
        TextView moduleName = holder.getView(R.id.module_name);
        TextView deviceManageIp = holder.getView(R.id.device_manage_ip);

        moduleName.setText(data.getEmdName());
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
