package computer_room.adapter;

import android.content.Context;
import android.widget.TextView;


import com.example.device_detail_lib.R;

import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import computer_room.bean.RecordingFileBean;

/**
 * 录制视频的文件列表
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/9/9 16 : 47
 */
public class RecordingFileAdapter extends BaseRecyclerViewAdapter<RecordingFileBean> {
    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public RecordingFileAdapter(Context context, List<RecordingFileBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, RecordingFileBean data, int position) {
        //文件名
        TextView fileText = holder.getView(R.id.file_name);
        //设置文件名
        fileText.setText(data.getFileName());

        fileText.setOnClickListener((v)->{
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });

    }
}
