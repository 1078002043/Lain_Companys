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
import computer_room.bean.Precision_Air_ConditionerBean;
import computer_room.fragment.PrecisionDetail;
import util.LainNewApi;

/**
 * 精密空调
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/9 08 : 44
 */
public class PrecisionConditionerAdapter extends BaseRecyclerViewAdapter<Precision_Air_ConditionerBean> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public PrecisionConditionerAdapter(Context context, List<Precision_Air_ConditionerBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, Precision_Air_ConditionerBean data, int position) {

        //绑定
        LinearLayout linearLayout = holder.getView(R.id.precision_item);
        ImageView icon = holder.getView(R.id.precision_image);
        TextView precisionTemperatureName = holder.getView(R.id.precision_temperature_name);

        //设置相关参数
        precisionTemperatureName.setText(data.getEcmDeviceName());
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(icon);

        //点击事件
        linearLayout.setOnClickListener((v)->{
            Intent intent = new Intent(v.getContext(), PrecisionDetail.class);
            intent.putExtra("precision", data);
            v.getContext().startActivity(intent);
        });

    }
}
