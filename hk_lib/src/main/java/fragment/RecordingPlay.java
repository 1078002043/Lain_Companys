package fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hk_lib.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.GSYSampleADVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.File;

/**
 * @ClassName: RecordingPlay
 * @Description: 视频录制播放
 * @Author: YIN LUO FEI
 * @Date: 2020/6/16 9:18
 */
public class RecordingPlay extends AppCompatActivity {
    //视频播放控件
    private StandardGSYVideoPlayer recordingGsyVideo;
    private String filePath;
    //屏幕旋转
    OrientationUtils orientationUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recording_video_play);

        //绑定视频控件
        recordingGsyVideo = findViewById(R.id.recording_gsy_video);
        //获取视频的路径
        filePath = getIntent().getStringExtra("recordingPath");

        //开始播放视频
        playVideo();


    }
    //开始播放视频
    private void playVideo() {

        //获取文件名称
        File videoFile = new File(filePath);

        //设置视频控件的相关配置
        recordingGsyVideo.setUp(filePath, true, videoFile.getName());
        recordingGsyVideo.getTitleTextView().setVisibility(View.VISIBLE);
        recordingGsyVideo.getBackButton().setVisibility(View.VISIBLE);

        //旋转
        orientationUtils = new OrientationUtils(this, recordingGsyVideo);
        orientationUtils.resolveByClick();

        //全屏
        recordingGsyVideo.getFullscreenButton().setVisibility(View.GONE);

        recordingGsyVideo.setIsTouchWiget(true);

        //全屏按钮
        recordingGsyVideo.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });

        //设置返回按键功能
        recordingGsyVideo.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //开始播放
        recordingGsyVideo.startPlayLogic();

    }

    @Override
    protected void onPause() {
        super.onPause();
        recordingGsyVideo.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        recordingGsyVideo.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {

        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            recordingGsyVideo.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        recordingGsyVideo.setVideoAllCallBack(null);
        super.onBackPressed();

    }

}
