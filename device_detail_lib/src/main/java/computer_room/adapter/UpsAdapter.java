package computer_room.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.UpsBean;
import computer_room.fragment.UPS_Item_Detail;
import util.LainNewApi;

/**
 * UPS 设备列表
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/14 09 : 57
 */
public class UpsAdapter extends BaseRecyclerViewAdapter<UpsBean> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public UpsAdapter(Context context, List<UpsBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, UpsBean data, int position) {
        //ItemView
        LinearLayout upsLayout = holder.getView(R.id.ups_layout);
        //图标
        ImageView upsImage = holder.getView(R.id.ups_image);
        //名称
        TextView upsName = holder.getView(R.id.ups_name);

        //设置相关参数
        //UPS名称
        upsName.setText(data.getName());
        //设置图标
//        upsImage.setImageResource(R.drawable.ups_connect);
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(upsImage);
        upsLayout.setOnClickListener((v) -> {
            //点击跳转到详情页
            Intent intent = new Intent(recyclerContext, UPS_Item_Detail.class);
            intent.putExtra("ups", data);
            recyclerContext.startActivity(intent);
        });
    }
}
