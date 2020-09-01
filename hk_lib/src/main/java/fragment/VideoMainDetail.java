package fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.example.hk_lib.R;
import com.example.hk_lib.R2;
import com.github.ybq.android.spinkit.SpinKitView;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.MediaPlayer.PlayM4.Player;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import base.Base_DevicesDetail_ViewPager;
import base.Lain_Application;
import base.Lain_Base_Activity;
import bean.VideoAccountBean;
import bean.VideoDateBean;
import butterknife.BindView;
import butterknife.OnClick;
import hk_video.Config;
import hk_video.PlayAssistant;
import hk_video.PlayBackActivity;
import util.GetViewPager_View;
import util.ToastManage;

/**
 * 实时预览视频监控页面
 */
public class VideoMainDetail extends Lain_Base_Activity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, PlaySuccessCallBack {

    //加载
    @BindView(R2.id.video_loading)
    public SpinKitView videoLoading;
    //设备详情中的 ViewPager
    @BindView(R2.id.video_main_viewpager)
    public ViewPager viewPager;
    //保存 ViewPager 的 View
    protected List<View> viewList;
    //缩小
    @BindView(R2.id.fullscreen_exit)
    ImageView fullscreenExit;
    //返回按钮
    @BindView(R2.id.video_back)
    ImageView videoBack;
    //历史回话按钮
    @BindView(R2.id.video_history)
    ImageView videoHistory;
    //录制按钮
    @BindView(R2.id.video_recording)
    ImageView videoRecording;
    //录制的视频文件查看按钮
    @BindView(R2.id.recording_file)
    ImageView recordingFile;
    //视频监控的声音开关
    @BindView(R2.id.video_sound_switch)
    ImageView soundSwitch;

    //播放辅助哦对
    private PlayAssistant assistant;
    //播放视频监控中的视频控件
    private SurfaceView surfaceView;

    //登陆标识
    private int mLoginId = -1;
    //日志标识
    private String TAG = "HKNetDvrDeviceInfoV30";
    //录制的文件名称
    EditText fileName;
    //录制文件提示
    AlertDialog alert;
    //图标的 开 与 关 的切换标志
    private boolean flag;
    private boolean flag2;
    //时间选择器
    private TimePickerDialog timePickerDialog = null;
    //日期选择器
    private DatePickerDialog datePickerDialog = null;
    //点击的TAG
    private String clickTag = "";
    //开始时间
    StringBuffer startTimeBuf = new StringBuffer();
    //结束时间
    StringBuffer endTimeBuf = new StringBuffer();
    //开始日期时间BEAN

    private VideoDateBean startVideoDate;
    //结束日期时间BEAN
    private VideoDateBean stopVideoDate;

    //设置选择的时间
    private LinearLayout startTime;
    private LinearLayout endTime;
    private AlertDialog backBuilder;

    //获取通道的数量
    private int ipChanNum = 0;
    //获取开始通道的位置
    private int byStartDChan = 0;

    //是否开启视频监控的声音播放
    private volatile boolean isOpenSound = false;
    //视频播放成功后，会在回调方法中发送回来 播放端口，使用这个播放端口才能进行某些操作
    private volatile int playPort = 0;
    //所有摄像头的通道
    ArrayList<String> channels;
    //保存登陆信息
    private VideoAccountBean accountBean;

    //当前播放位置
    int startVideoPos = 0;
    //所有摄像头总数量
    int videoSize = 0;

    @Override
    protected String getToolbarTitle() {
        return "";
    }

    @Override
    public int setLayoutView() {
        return R.layout.video_main_detail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        //默认横屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);

        //获取整个对象
        channels = getIntent().getStringArrayListExtra("video_channel");

        //获取当前播放的位置
        startVideoPos = getIntent().getIntExtra("video_position", 0);
        //获取当前有多少视频
        videoSize = getIntent().getIntExtra("video_size", 0);
        //获取通道的数量
        ipChanNum = getIntent().getIntExtra("ipChanNum", 0);
        //获取开始通道的位置
        byStartDChan = getIntent().getIntExtra("byStartDChan", 0);
        //接收当前的摄像头通道
        int myChannel = getIntent().getIntExtra("channel", 0);

        //获取登陆ID
        mLoginId = getIntent().getIntExtra("loginId", -1);
        //获取登陆信息
        accountBean = (VideoAccountBean) getIntent().getSerializableExtra("loginInfo");

        //获取 ViewPager 中的 View
        viewList = GetViewPager_View.getInstance().getViewViewPager(getVideoList(videoSize), getLayoutInflater());

        //绑定 ViewPager
        Base_DevicesDetail_ViewPager pager = new Base_DevicesDetail_ViewPager(viewList);
        //设置设备详情ViewPager
        viewPager.setAdapter(pager);
        //ViewPager 视频列表滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //滑动之后进行播放
            @Override
            public void onPageSelected(int position) {
                //记录当前播放的位置
                Log.d(TAG, "onPageSelected: " + position);

                //滑动时，绑定每个ViewPager中的视频播放控件
                surfaceView = viewList.get(position).findViewById(R.id.surface_view);
                //停止上一次的播放
                if (assistant != null)
                    assistant.stop();
                //开启下一次的播放
                play_video(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                Log.d(TAG, "onPageScrolled: ScrollStateChanged");

            }
        });

/*
        该IP段内的通道数量 byIPChanNum :16 开始通道的位置 byStartDChan :33
        有时候只能截取几个视频监控的画面，不知道能不能进行查看视频
        Log.d("kljflkgrty", "byChanNum : " + m_oNetDvrDeviceInfoV30.byChanNum + "---" + "byStartChan : " + m_oNetDvrDeviceInfoV30.byStartChan + "----" + "byIPChanNum : " + m_oNetDvrDeviceInfoV30.byIPChanNum + "----" + "byZeroChanNum : " + m_oNetDvrDeviceInfoV30.byZeroChanNum + "----" + "byStartDChan : " + m_oNetDvrDeviceInfoV30.byStartDChan + "----" + "byHighDChanNum : " + m_oNetDvrDeviceInfoV30.byHighDChanNum);
*/

        //录制文件时的提示
        fileName = new EditText(this);
        alert = new AlertDialog.Builder(this)
                .setTitle("录制文件名")
                .setView(fileName)
                .setPositiveButton("录制", ((dialog, which) -> {
                    videoRecording.setImageResource(R.drawable.stop_recording);
                    flag2 = true;
                    assistant.startRecord(fileName.getText().toString());

                }))
                .setNegativeButton("取消", ((dialog, which) -> {

                    ToastManage.getInstance().toastShortShow("取消");

                }))
                .create();

        //初始化回放的 日期，时间控件
        initDatePicker();
        initTimePicker();

        //初始化时间回放Bean类
        startVideoDate = new VideoDateBean();
        stopVideoDate = new VideoDateBean();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //延迟播放
        new Handler().postDelayed(() -> {
            runOnUiThread(this::startRealPlay);
        }, 1000);

    }

    /**
     * 开始播放 实时预览
     */
    private void startRealPlay() {

        if (startVideoPos == 0) {
            //滑动时，绑定每个ViewPager中的视频播放控件
            surfaceView = viewList.get(0).findViewById(R.id.surface_view);
            //开启下一次的播放
            play_video(0);
        } else viewPager.setCurrentItem(startVideoPos);

    }

    private void initTimePicker() {

        //时间初始化
        Calendar timeCalendar = Calendar.getInstance();

        if (timePickerDialog == null) {
            timePickerDialog = TimePickerDialog.newInstance(
                    this, timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE),
                    true);
        } else {
            timePickerDialog.initialize(this,
                    timeCalendar.get(Calendar.HOUR_OF_DAY),
                    timeCalendar.get(Calendar.MINUTE),
                    timeCalendar.get(Calendar.SECOND),
                    true);
        }
        //时间选择器的颜色
        timePickerDialog.setAccentColor(Lain_Application.getThemeColor());
        //时间选择器的样式
        timePickerDialog.setVersion(com.wdullaer.materialdatetimepicker.time.TimePickerDialog.Version.VERSION_2);


    }

    /**
     * 视频监控实时预览 ViewPager
     *
     * @return 根据多个视频监控来确定ViewPager的页数
     */
    public ArrayList<Integer> getVideoList(int countVideo) {

        ArrayList<Integer> viewList = new ArrayList<>();
        //遍历并添加View
        for (int i = 0; i < countVideo; i++)
            //实时数据
            viewList.add(R.layout.video_surveillance_template);

        return viewList;

    }

    /**
     * 播放视频
     *
     * @param position 播放哪个位置上的视频
     */
    public void play_video(int position) {

        assistant = new PlayAssistant(surfaceView, this, this);
        //点击监听
        Config.putInt("channel", Integer.parseInt(channels.get(position)));
        //播放
        play();

    }

    /**
     * 播放前，需要进行登陆才能获取视频资源
     */
    private void play() {
        assistant.play(accountBean.getIpAddress(), accountBean.getIpPort(), accountBean.getUsername(), accountBean.getPassword(), Config.getInt("channel"));
        flag = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        try {
            //只停止播放，不会清理SDK的初始化
            assistant.playBackCleanUp();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 全屏 和 小屏 所显示的控件
     *
     * @param view
     */
    @OnClick({R2.id.video_history, R2.id.video_recording, R2.id.recording_file, R2.id.video_back, R2.id.video_sound_switch})
    public void onViewClicked(View view) {

        //获取点击的ID
        int id = view.getId();

        //查看视频监控的回话
        if (id == R.id.video_history) {

            View back_alert = getLayoutInflater().inflate(R.layout.video_playback_alert, null);
            backBuilder = new AlertDialog.Builder(this)
                    .setView(back_alert)
                    .show();
            startTime = back_alert.findViewById(R.id.start_time_ed);
            endTime = back_alert.findViewById(R.id.end_time_ed);

            startTime.setOnClickListener((v) -> {
                clickTag = "开始时间";
                datePickerDialog.show(getSupportFragmentManager(), "dialog");
            });

            endTime.setOnClickListener((v) -> {
                clickTag = "结束时间";
                datePickerDialog.show(getSupportFragmentManager(), "dialog");
            });

            Button rightBtn = back_alert.findViewById(R.id.concern_tv);
            Button cancelBtn = back_alert.findViewById(R.id.back_play_cancel);
            //确定回放
            rightBtn.setOnClickListener((v) -> {
                ToastManage.getInstance().toastLongShow("确定");
                //日期

                Intent intent = new Intent(this, PlayBackActivity.class);
                intent.putExtra("start_time", startTimeBuf.toString());
                intent.putExtra("end_time", endTimeBuf.toString());
                intent.putExtra("loginId", mLoginId);
                startActivity(intent);
                backBuilder.cancel();
            });
            //取消回放
            cancelBtn.setOnClickListener((v) -> {
                ToastManage.getInstance().toastLongShow("取消");
                backBuilder.cancel();
            });

        } else if (id == R.id.video_recording) {

            //录制视频

            if (!flag2) {
                if (flag) {
                    alert.show();
                } else {
                    ToastManage.getInstance().toastShortShow("请先预览！");
                }

            } else {
                //保存录制的视频，
                videoRecording.setImageResource(R.drawable.start_recording);
                //停止录制
                assistant.stopRecord(fileName.getText().toString());
                fileName.setText("");
                flag2 = false;
            }

        } else if (id == R.id.recording_file) {

            //跳转到录制的文件页

            Intent intent = new Intent(this, RecordingFile.class);
            intent.putExtra("loginID", mLoginId);
            startActivity(intent);

        } else if (id == R.id.video_back) {

            //回到主页面
            finish();

        } else if (id == R.id.video_sound_switch) {

            //是否开启声音，默认是关
            if (isOpenSound) {
                //关闭
                soundSwitch(false);
            } else {
                //开启
                soundSwitch(true);
            }

        }
    }

    @Override
    public void onBackPressed() {
    }


    public void initDatePicker() {
        //日期初始化
        Calendar now = Calendar.getInstance();

        if (datePickerDialog == null) {

            datePickerDialog = DatePickerDialog.newInstance(this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));

        } else {

            datePickerDialog.initialize(this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_WEEK));

        }

        //日期选择器的颜色
        datePickerDialog.setAccentColor(Lain_Application.getThemeColor());
        //日期选择器的样式
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);
    }

    /**
     * 时间选择后回调
     *
     * @param view      PickerView
     * @param hourOfDay 小时
     * @param minute    分钟
     * @param second    秒
     */
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        switch (clickTag) {
            case "开始时间":

                startTimeBuf.append(hourOfDay + ":" + minute + ":" + second);

                startVideoDate.setHour(hourOfDay);
                startVideoDate.setMin(minute);
                startVideoDate.setSecond(second);

                TextView startSubTime = startTime.findViewById(R.id.start_sub_time);
                startSubTime.setText(hourOfDay + ":" + minute + ":" + second);

                break;
            case "结束时间":

                endTimeBuf.append(hourOfDay + ":" + minute + ":" + second);

                stopVideoDate.setHour(hourOfDay);
                stopVideoDate.setMin(minute);
                stopVideoDate.setSecond(second);

                TextView endSubTime = endTime.findViewById(R.id.end_sub_time);
                endSubTime.setText(hourOfDay + ":" + minute + ":" + second);
                break;
        }

    }

    /**
     * 日期选择
     *
     * @param view        PickView
     * @param year        年份
     * @param monthOfYear 月份
     * @param dayOfMonth  天
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        switch (clickTag) {
            case "开始时间":

                //如果上一次还有数据，就先将数据清空
                startTimeBuf.delete(0, startTimeBuf.length());
                startVideoDate.setYear(year);
                startVideoDate.setMonth(monthOfYear);
                startVideoDate.setDay(dayOfMonth + 1);

                startTimeBuf.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ");

                TextView startSubDate = startTime.findViewById(R.id.start_sub_date);
                startSubDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                break;
            case "结束时间":

                //如果上一次还有数据，就先将数据清空

                endTimeBuf.delete(0, endTimeBuf.length());

                stopVideoDate.setYear(year);
                stopVideoDate.setMonth(monthOfYear);
                stopVideoDate.setDay(dayOfMonth + 1);
                endTimeBuf.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ");

                TextView endSubDate = endTime.findViewById(R.id.end_sub_date);
                endSubDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ");

                break;
        }


        timePickerDialog.show(getSupportFragmentManager(), "timedialog");
    }

    /**
     * 播放成功后，会回调该方法
     *
     * @param port 接收播放端口
     */
    @Override
    public void playSuccessCallBack(int port) {
        //保存播放端口
        playPort = port;

        runOnUiThread(() -> {

            //隐藏加载条
            showLoading();

            //默认是关
            if (isOpenSound) {

                // 是否开启声音播放
                boolean openSoundResult = Player.getInstance().playSound(port);
                soundOpenResult(openSoundResult, "open");

            }

        });
    }

    /**
     * 隐藏加载条
     */
    private void showLoading() {

        if (videoLoading.getVisibility() == View.VISIBLE)
            videoLoading.setVisibility(View.GONE);

    }

    /**
     * 由用户控制声音的开关
     *
     * @param isOpenSound true 开 / false 关
     */
    public void soundSwitch(boolean isOpenSound) {


        if (isOpenSound) {

            // 开启声音播放
            boolean openSoundResult = Player.getInstance().playSound(playPort);

            // 开启成功后保存操作结果，并切换图标
            if (openSoundResult) {
                this.isOpenSound = true;
                soundSwitch.setImageResource(R.drawable.open_sound);
            }

            soundOpenResult(openSoundResult, "open");

        } else {

            // 关闭声音播放
            boolean closeSoundResult = Player.getInstance().stopSound();

            //关闭成功后，都会进行保存结果，并切换图标
            if (closeSoundResult) {
                this.isOpenSound = false;
                soundSwitch.setImageResource(R.drawable.colse_sound);
            }

            soundOpenResult(closeSoundResult, "close");

        }

    }

    /**
     * 声音操作的结果
     *
     * @param openSoundResult 接收声音开启/关闭的结果
     * @param tag             声音的 开启/关闭 标识
     */
    private void soundOpenResult(boolean openSoundResult, String tag) {

        //只有成功才保存当前操作的结果
        if (tag.equals("open")) {
            ToastManage.getInstance().toastShortShow(openSoundResult ? "声音开启成功" : "声音开启失败");
        } else {
            ToastManage.getInstance().toastShortShow(openSoundResult ? "声音关闭成功" : "声音关闭失败");
        }

    }
}
