package computer_room.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.Device8052Bean;
import util.LainNewApi;

public class Device8052Adapter extends BaseRecyclerViewAdapter<Device8052Bean> {
    public Device8052Adapter(Context context, List<Device8052Bean> dataList, int layoutId, int[] statusImg) {
        super(context, dataList, layoutId, statusImg);
    }

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */


    @Override
    protected void bindData(BaseViewHolder holder, Device8052Bean data, int position) {
        //绑定
        //名称
        TextView name = holder.getView(R.id.name_8052);
        //地址
        TextView address = holder.getView(R.id.address_8052);
        //状态
        TextView status = holder.getView(R.id.status_8052);
        //图标
        ImageView imageView = holder.getView(R.id.img_8052);
        //设置相关参数
        name.setText(data.getEkmName());
        String addressStr = "通道：" + data.getGallery();
        address.setText(addressStr);
        //设备状态
        status.setText(deviceStatus(data.getStatus()));
        //图标状态
        //设置图标
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(imageView);
//        deviceStatusImg(data.getStatus(), imageView, statusImg);
    }
}
