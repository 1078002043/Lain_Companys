package computer_room.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.DeviceSwitchBean;
import util.LainNewApi;

public class DeviceSwitchAdapter extends BaseRecyclerViewAdapter<DeviceSwitchBean> {

    private int[] statusImg;
    //记录开关状态
    private int switchStatus = 2;

    public DeviceSwitchAdapter(Context context, List<DeviceSwitchBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    public DeviceSwitchAdapter(Context context, List<DeviceSwitchBean> dataList, int layoutId, int[] statusImg) {
        super(context, dataList, layoutId);
        this.statusImg = statusImg;
    }

    @Override
    protected void bindData(BaseViewHolder holder, DeviceSwitchBean data, int position) {
        //绑定
        //名称
        TextView name = holder.getView(R.id.device_switch_name);
        //通道
        TextView gallery = holder.getView(R.id.device_switch_gallery);
        //开关
        SwitchCompat switchCompat = holder.getView(R.id.device_switch_control);
        ImageView deviceStatus = holder.getView(R.id.device_switch_image);

        //设置图片
//        deviceStatus.setImageResource(deviceStatusImg(data.getStatus(), statusImg));
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(deviceStatus);

        //设置相关参数
        name.setText(data.getName());
        gallery.setText("通道：" + data.getGallery());
        //开关状态
        if (this.switchStatus != data.getStatus()) {
            switchCompat.setChecked(deviceSwitch(data.getStatus()));
            //保存当前的设备状态，下次如果没有变化，则不需要改变
            this.switchStatus = data.getStatus();
        }
    }

}
