package computer_room.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.EelectricityBean;
import util.LainNewApi;

/**
 * 电量仪 RotationChartAdapter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/13 16 : 17
 */
public class ElectricityAdapter extends BaseRecyclerViewAdapter<EelectricityBean> {

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public ElectricityAdapter(Context context, List<EelectricityBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, EelectricityBean data, int position) {
        //电量仪 图标
        ImageView elecImg = holder.getView(R.id.elec_img);
        //电量仪的 名称
        TextView elecName = holder.getView(R.id.elec_name);
        //图标与名称的 布局容器，用于点击
        LinearLayout elecItem = holder.getView(R.id.elec_item);
        //设置图片
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(elecImg);
        //设置名称
        elecName.setText(data.getPemName());
        //点击事件
        elecItem.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });

    }
}
