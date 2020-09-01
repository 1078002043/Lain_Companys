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
import computer_room.bean.CenterAirDeviceBean;
import computer_room.bean.UpsBean;
import computer_room.fragment.CenterAirDetail;
import computer_room.fragment.UPS_Item_Detail;
import util.LainNewApi;

/**
 * @ClassName: CenterAirAdapter
 * @Description: 中央空调的设备列表适配器
 * @Author: YIN LUO FEI
 * @Date: 2020/8/26 1:55
 */
public class CenterAirAdapter extends BaseRecyclerViewAdapter<CenterAirDeviceBean> {

    public CenterAirAdapter(Context context, List<CenterAirDeviceBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, CenterAirDeviceBean data, int position) {
        //ItemView
        LinearLayout centerLayout = holder.getView(R.id.center_air_layout);
        //图标
        ImageView centerImage = holder.getView(R.id.center_air_image);
        //名称
        TextView centerName = holder.getView(R.id.center_air_name);

        //设置相关参数
        //UPS名称
        centerName.setText(data.getName());
        //设置图标
//        upsImage.setImageResource(R.drawable.ups_connect);
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(centerImage);
        centerLayout.setOnClickListener((v) -> {
            //点击跳转到详情页
            Intent intent = new Intent(recyclerContext, CenterAirDetail.class);
            intent.putExtra("title", data.getName());
            intent.putExtra("center_air", data);
            recyclerContext.startActivity(intent);
        });
    }

}
