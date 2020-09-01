package device_adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
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
 * 环境监控 适配器
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/1 15 : 38
 */
public class EnvironmentalMonitoring extends BaseRecyclerViewAdapter<ConfigBean> {

    private Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public EnvironmentalMonitoring(Context context, List<ConfigBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    /**
     * 系统列表的逻辑操作
     *
     * @param holder   视图的引用
     * @param data     列表数据
     * @param position 列表的位置
     */
    @Override
    protected void bindData(BaseViewHolder holder, ConfigBean data, int position) {



        //卡片
        LinearLayout cardView = holder.getView(R.id.environmental_card);
        //设备图标
        ImageView miniIcon = holder.getView(R.id.monitor_img);

        //如果使用Glide设置会报异常，但也可以正常加载出图片
        Uri uri = Uri.parse(data.getImage());
        miniIcon.setImageURI(uri);



        //设置名称
        TextView monitorName = holder.getView(R.id.monitor_name);

        //设置设备名称
        monitorName.setText(data.getTitle());

        //保存点击项的名称
        LainNewApi.INTENT_TAG = data.getTitle();
        //设置Item点击事件
        cardView.setOnClickListener((v) -> {
            //设置监听，点击跳转到对应设备的页面
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });

    }
}
