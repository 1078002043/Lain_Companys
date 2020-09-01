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
 * 安防监控 适配器
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 22 : 37
 */
public class SecurityAdapter extends BaseRecyclerViewAdapter<ConfigBean> {
    Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public SecurityAdapter(Context context, List<ConfigBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, ConfigBean data, int position) {

        //卡片
        LinearLayout cardView = holder.getView(R.id.environmental_card3);
        //设备图标
        ImageView miniIcon = holder.getView(R.id.monitor_img);

        //如果使用Glide设置会报异常，但也可以正常加载出图片
        Uri uri = Uri.parse(data.getImage());
        miniIcon.setImageURI(uri);

        //设备名称
        TextView monitorName = holder.getView(R.id.monitor_name);

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
