package fragment;//package compute_room.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.hk_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.VideoAccountBean;
import http.OkHttpCallBack;
import util.ToastManage;

/**
 * 视频账号的适配器
 */
public class VideoUserAdapter extends BaseRecyclerViewAdapter<VideoAccountBean> {

    //视频用户的名称
    private TextView videoUserName;
    //视频用户的IP 和 端口
    private TextView videoUserIp;

    private Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public VideoUserAdapter(Context context, List<VideoAccountBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, VideoAccountBean data, int position) {

        //账号名称
        videoUserName = holder.getView(R.id.video_user_name);
        //视频监控的IP
        videoUserIp = holder.getView(R.id.video_user_ip);

        //设置相关数据
        videoUserName.setText(data.getUsername());
        videoUserIp.setText(data.getIpAddress() + " : " + data.getIpPort());

    }

}
