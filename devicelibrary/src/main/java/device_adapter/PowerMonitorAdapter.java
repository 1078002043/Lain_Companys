package device_adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.devicelibrary.R;

import java.io.File;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.ConfigBean;

/**
 * 动力监控
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 01 : 55
 */
public class PowerMonitorAdapter extends BaseRecyclerViewAdapter<ConfigBean> {

    private Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public PowerMonitorAdapter(Context context, List<ConfigBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, ConfigBean data, int position) {

        //卡片
        LinearLayout cardView = holder.getView(R.id.environmental_card2);
        //设备图标
        ImageView miniIcon = holder.getView(R.id.monitor_img);

        //如果使用Glide设置会报异常，但也可以正常加载出图片
        Uri uri = Uri.parse(data.getImage());
        miniIcon.setImageURI(uri);

        //设备名称
        TextView monitorName = holder.getView(R.id.monitor_name);
        //设置设备名称
        monitorName.setText(data.getTitle());

        //设置Item点击事件
        cardView.setOnClickListener((v) -> {
            //设置监听
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });
    }
}
