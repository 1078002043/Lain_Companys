package computer_room.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.Locating_RealTime_Bean;
import util.LainNewApi;

/**
 * 定位漏水 实时数据
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/8 22 : 53
 */
public class Locating_RealTime_Adapter extends BaseRecyclerViewAdapter<Locating_RealTime_Bean> {

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public Locating_RealTime_Adapter(Context context, List<Locating_RealTime_Bean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, Locating_RealTime_Bean data, int position) {
        //绑定
        //名称
        TextView locatingName = holder.getView(R.id.locating_name);
        //地址
        TextView locatingAddress = holder.getView(R.id.locating_address);
        //状态
        TextView locatingStatus = holder.getView(R.id.locating_status);
        //图标
        ImageView imageView = holder.getView(R.id.locating_img);
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(imageView);
        //设置相关参数
        locatingName.setText(data.getElmName());
        String address = "地址：" + data.getElmAddress();
        locatingAddress.setText(address);
        //设置状态
        locatingStatus.setText(deviceStatus(data.getElmStatus()));
        //图标状态
        int[] imageID = new int[]{R.drawable.locating_connection, R.drawable.locating_no_connection, R.drawable.locating_no_connection};
//        deviceStatusImg(data.getElmStatus(), imageView, imageID);

    }
}
