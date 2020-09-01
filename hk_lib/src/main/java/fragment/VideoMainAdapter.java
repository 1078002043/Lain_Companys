package fragment;//package compute_room.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.request.RequestOptions;
import com.example.hk_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import base.GlideApp;
import bean.DeviceBean;

/**
 * 视频监控主列表，显示视频监控的封面
 */
public class VideoMainAdapter extends BaseRecyclerViewAdapter<DeviceBean> {

    Context context;
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public VideoMainAdapter(Context context, List<DeviceBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, DeviceBean videoBean, int position) {
        //如果传递过来的数据是空的，则不进行数据设置

        //ITEM VIEW
        CardView videoLayout = holder.getView(R.id.video_main_temp);
        //视频监控的封面
        ImageView videoCover = holder.getView(R.id.video_bg);

        //加载保存下来的视频封面

        GlideApp.with(context).asBitmap().load(videoBean.bitmap)
                .apply(RequestOptions.centerCropTransform())
                .into(videoCover);

        //点击跳转到视频监控的播放页面
        videoLayout.setOnClickListener((v)->{
            if (itemClickListener != null) {
                itemClickListener.onItemClickListener(holder.getRootView(), position);
            }
        });

    }
}
