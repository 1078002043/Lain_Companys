package fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import com.example.hk_lib.R;

import java.io.File;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.BaseViewHolder;
import bean.RecordingFileBean;

/**
 * 录制视频的文件列表
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/9/9 16 : 47
 */
public class RecordingFileAdapter extends BaseRecyclerViewAdapter<RecordingFileBean> {
    private Context context;

    /**
     * 初始化添加设备列表
     *
     * @param context  Context
     * @param dataList 数据列表
     * @param layoutId 列表 ID
     */
    public RecordingFileAdapter(Context context, List<RecordingFileBean> dataList, int layoutId) {
        super(context, dataList, layoutId);
        this.context = context;
    }

    @Override
    protected void bindData(BaseViewHolder holder, RecordingFileBean data, int position) {
        //文件名
        TextView fileText = holder.getView(R.id.file_name);
        TextView fileTime = holder.getView(R.id.file_time);
        //封面
        ImageView coverImage = holder.getView(R.id.recordingCover);
        //播放按钮
        ImageView playIcon = holder.getView(R.id.recording_play_icon);
        //删除录制的视频
        ImageView removeVideo = holder.getView(R.id.remove_recording_video);

        //解析本地视频封面
        Bitmap bitmap = BitmapFactory.decodeFile(data.getFileCover());

        //设置文件名
        fileText.setText(data.getFileName());
        //设置文件修改时间
        fileTime.setText(data.getFileTime());

        //设置封面
        coverImage.setImageBitmap(bitmap);

        //删除录制文件
        removeVideo.setOnClickListener((v) -> {


            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("删除 " + data.getFileName())
                    .setMessage("是否删除 " + data.getFileName() + " 录像文件")
                    .setNegativeButton("取消", (dia, which) -> {

                    })
                    .setPositiveButton("确定", (dia, which) -> {
                        delFile(data, position);
                    })
                    .show();

        });
        //播放视频
        playIcon.setOnClickListener((v) -> {
            if (itemClickListener != null)
                itemClickListener.onItemClickListener(holder.getRootView(), position);
        });

    }

    /**
     * 删除录制的视频文件
     *
     * @param data     获取视频文件信息
     * @param position 需要删除视频Item的位置
     */
    public void delFile(RecordingFileBean data, int position) {

        //获取文件的路径
        File delFile = new File(data.getFilePath());

        //删除的结果
        String delRes = delFile.delete() ? "成功" : "失败";
        //提示用户操作的结果
        String toastMsg = "删除文件 " + data.getFileName() + " " + delRes;
        Toast.makeText(recyclerContext, toastMsg, Toast.LENGTH_SHORT).show();

        //局部更新适配器
        if (delRes.equals("成功")) {
            //删除列表指定项
            dataList.remove(position);
            //更新数据
            notifyDataSetChanged();
        }

    }

}
