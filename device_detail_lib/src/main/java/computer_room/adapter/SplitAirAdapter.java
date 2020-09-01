package computer_room.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;


import com.bumptech.glide.Glide;
import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.SplitAirBean;
import util.LainNewApi;

/**
 * 分体空调
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 21 : 01
 */
public class SplitAirAdapter extends BaseRecyclerViewAdapter<SplitAirBean> {
    private TextView address;
    private TextView device_name;

    private BaseViewHolder holder = null;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public SplitAirAdapter(Context context, List<SplitAirBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        //初始化动画的名称
        setLottieAnimationName("precison.json");
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void bindData(BaseViewHolder holder, SplitAirBean data, int position) {

        CardView cardView = holder.getView(R.id.split_card_view);
        //分体空调的开关
        SwitchCompat compat = cardView.findViewById(R.id.split_air_switch);
        //分体空调的地
        address = cardView.findViewById(R.id.split_air_address);
        //分体空调的名称
        device_name = cardView.findViewById(R.id.split_air_name);
        //动画
        ImageView animationView = cardView.findViewById(R.id.split_image_view);
        Glide.with(recyclerContext).load(LainNewApi.DEVICE_IMAGE).into(animationView);
        //设置相关的参数
        device_name.setText(data.getEfmName());
        String addressStr = "地址：" + data.getEfmAddress();
        address.setText(addressStr);

        //设置状态
        compat.setChecked(deviceSwitch(data.getEfmStatus()));

        //直接播放动画
//        LottieUtil.getInstance().showLottieAnimation(lottieName, animationView);
    }

}
