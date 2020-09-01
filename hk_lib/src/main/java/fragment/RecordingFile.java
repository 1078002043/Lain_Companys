package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ffmeger_com.FfmegerControl;
import com.example.hk_lib.R;
import com.example.hk_lib.R2;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import base.BaseRecyclerViewAdapter;
import base.Lain_Application;
import base.Lain_Base_Activity;
import bean.RecordingFileBean;
import butterknife.BindView;

/**
 * 视频监控录制的文件，只支持查看录制的视频列表，目前暂不支持播放
 */
public class RecordingFile extends Lain_Base_Activity implements BaseRecyclerViewAdapter.OnItemClickListener {
    //录制文件名集合
    ArrayList<RecordingFileBean> fileBeans = new ArrayList<>();
    //文件列表
    @BindView(R2.id.recording_file_recycler)
    UltimateRecyclerView recordingFileRecycler;

    @Override
    protected String getToolbarTitle() {
        return "录制视频";
    }

    @Override
    public int setLayoutView() {
        return R.layout.recording_file;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //录制保存的路径
        File file = new File("/mnt/sdcard/surface/recording/");
        //获取时间格式化实例
        SimpleDateFormat simpleFormat = new SimpleDateFormat("y-M-d H:m:s E");
        //文件创建时间
        String fileTime;

        //文件夹是否存在
        if (!file.exists())
            return;
        //遍历目录
        File[] files = file.listFiles();

        for (File fileName :
                files) {

            //获取文件名称
            String name = fileName.getName();
            //获取后缀
            String suffix = name.substring(name.lastIndexOf(".") + 1);

            //只遍历MP4
            if (!suffix.equals("mp4"))
                continue;

            //将遍历到的文件名保存下来
            RecordingFileBean bean = new RecordingFileBean();
            //不显示文件的后缀名
            bean.setFileName(name.split(".mp4")[0]);
            //创建时间
            fileTime = simpleFormat.format(new Date(fileName.lastModified()));
            bean.setFileTime(fileTime);

            //获取文件的绝对路径
            String path =  fileName.getAbsolutePath();
            //将文件路径保存到BEAN类中
            bean.setFilePath(path);
            //将文件封面保存到BEAN类中
            bean.setFileCover(path.replace("mp4", "png"));
            //保存录制文件的信息
            fileBeans.add(bean);

        }

        //视频列表适配器
        RecordingFileAdapter adapter = new RecordingFileAdapter(this, fileBeans, R.layout.recording_file_template);
        recordingFileRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter.setItemClickListener(this);
        recordingFileRecycler.setAdapter(adapter);

    }

    @Override
    public void onItemClickListener(View v, int position) {

        //文件
        File file = new File(fileBeans.get(position).getFilePath());
        //文件路径
        String pa = "/mnt/sdcard/surface/recording/";

        //播放录制的视频
        myStartActivity(this, RecordingPlay.class, "recordingPath",fileBeans.get(position).getFilePath());

    }
}
