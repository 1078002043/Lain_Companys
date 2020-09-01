package device_adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.devicelibrary.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.ConfigBean;
import util.LainNewApi;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/14 17:29
 * Description：水质监控设备列表适配器
 **/
public class WaterQualityAdapter extends BaseRecyclerViewAdapter<ConfigBean> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public WaterQualityAdapter(Context context, List<ConfigBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, ConfigBean data, int position) {

        //卡片
        LinearLayout cardView = holder.getView(R.id.environmental_card6);
        //设备名称
        TextView monitorName = holder.getView(R.id.monitor_name);
        //设备图标
        ImageView miniIcon = holder.getView(R.id.monitor_img);

        //如果使用Glide设置会报异常，但也可以正常加载出图片
        Uri uri = Uri.parse(data.getImage());
        miniIcon.setImageURI(uri);

        //设置设备名称
        monitorName.setText(data.getTitle());
        //保存点击项的名称
        LainNewApi.INTENT_TAG = data.getTitle();
        //设置Item点击事件
        cardView.setOnClickListener((v) -> {
            //设置监听
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });
    }
}
