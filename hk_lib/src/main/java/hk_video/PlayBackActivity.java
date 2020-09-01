package hk_video;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hk_lib.R;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_TIME;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener;

import java.util.ArrayList;
import java.util.Calendar;

import base.Lain_Application;
import base.Lain_Base_Activity;
import bean.VideoDateBean;
import util.ActivityUtil;
import util.ToastManage;

/**
 * Created by Administrator on 2017/7/14.
 */

public class PlayBackActivity extends Lain_Base_Activity implements View.OnClickListener, OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private static final String TAG = "PlayBackActivity";
    //海康SDK 流
    private NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = null;
    //开始通道
    private int m_iStartChan = 0; // start channel no

    //文件
    public static final String ROOT_DIR = "/mnt/sdcard/mythroad";

    //请求的代码
    private static final int REQUEST_CODE = 1000;
    //动态加载的SO库
    private final String sdcardLibDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/libs";
    //动态加载需要使用到的Context
    private Context context;

    public int loginId;
    public PlayAssistant assistant;  //HKSDK控制方法类
    FrameLayout.LayoutParams lp;
    FrameLayout.LayoutParams lp2;
    LinearLayout linearLayout;
    ImageView iV;
    ImageView iV2;
    String start = "";
    NET_DVR_TIME time;  //查询时间类
    String end = "";
    NET_DVR_TIME time2;
    String showTime = "";
    SeekBar seekBar;
    //开始时间
    StringBuffer startTimeBuf = new StringBuffer();
    //结束时间
    StringBuffer endTimeBuf = new StringBuffer();

    //时间List
    ArrayList<String> timeList = new ArrayList<>();

    private TextView mStartTime;
    private TextView mEndTime;

    private TextView mConcert;
    private SurfaceView mSurface;
    private long startStand;
    private long endStand;
    private boolean flag = true;
    private boolean isPlay = false;
    private boolean isFull = false;
    private Thread mProgressThread; //进度条线程
    private int isStop = -1;   //-1表示没有点击的状态，0表示继续，1停止
    private long progressMillns; //进度条对应的时间
    private float mProgress;
    //时间选择器
    private TimePickerDialog timePickerDialog = null;

    //开始日期时间BEAN
    private VideoDateBean startVideoDate;
    //结束日期时间BEAN
    private VideoDateBean stopVideoDate;
    //时间选择面板
    private ImageView timeSelect;

    //更新进度条
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {
                mProgress = (float) msg.obj;
                seekBar.setProgress((int) mProgress);
            }

        }
    };

    //回放视频时间选择
    //日期选择器
    private DatePickerDialog datePickerDialog = null;

    //点击的TAG
    private String clickTag = "";
    private AlertDialog backBuilder;
    private LinearLayout startTime;
    private LinearLayout endTime;
    private ImageView backImg;

    @Override
    protected String getToolbarTitle() {
        return "";
    }

    @Override
    public int setLayoutView() {
        return R.layout.video_playback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //默认横屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        super.onCreate(savedInstanceState);

        //设置开始时间，结果时间的日期保存对象
        startVideoDate = new VideoDateBean();
        stopVideoDate = new VideoDateBean();

        //初始化VIEW
        assignView();
        addViews();



        assistant = new PlayAssistant(mSurface);
        //设置当前的回放通道，才能正常播放
        assistant.m_iStartChan = Config.getInt("channel");

        //获取传过来的时间和登陆ID
        startTimeBuf.append(getIntent().getStringExtra("start_time"));  //开始时间
        endTimeBuf.append(getIntent().getStringExtra("end_time"));  //结束时间
//        2020-6-18 9:24:0---2020-6-19 9:24:0

        loginId = getIntent().getIntExtra("loginId", -1);
        //初始化日期时间选择器
        initDatePicker();
        initTimePicker();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //必须是延迟1秒以上，否则无法播放
        new Handler().postDelayed(() -> {
            //开始回放
            runOnUiThread(this::startPlayBack);
        }, 1000);

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
        timePickerDialog.setVersion(TimePickerDialog.Version.VERSION_2);

    }

    public void getShowTime(long startStand, long endStand, int progress) {

        long show = (long) ((endStand - startStand) * progress * 1.0 / 100);
        showTime = TimeUtil.getTime(show + startStand, TimeUtil.DEFAULT_DATE_FORMAT);

    }


    private void addViews() {

        iV = new ImageView(this);
        iV.setMinimumHeight(100);
        iV.setMinimumWidth(100);

        FrameLayout.LayoutParams lp1 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp1.gravity = Gravity.CENTER;
        lp1.setMargins(0, 60, 0, 0);
        addContentView(iV, lp1);

        iV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                assistant.poausPlayBack();

                if (checkDate()) {

                    if (!isPlay) {

                        assistant.goAheadPlayBack();
                        isPlay = true;
                        isStop = 0;
                        progressThreadNotify();
                    } else {

                        isPlay = false;
                        assistant.poausPlayBack();
                        isStop = 1;

                    }
                }

            }
        });


        iV2 = new ImageView(this);
        iV2.setMinimumHeight(100);
        iV2.setMinimumWidth(100);

        lp2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp2.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        lp2.setMargins(0, 0, 0, 320);
        addContentView(iV2, lp2);

        iV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //全屏
                if (!isFull) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    isFull = true;
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    isFull = false;
                }
            }
        });


        seekBar = findViewById(R.id.video_back_seekbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //在这里获取我们的进度，最终开始播放

                getShowTime(startStand, endStand, seekBar.getProgress());
                assistant.stopPlayBack();
                TimePhrase phrase2 = getTimePhrase(showTime);
                final NET_DVR_TIME time3 = new NET_DVR_TIME();
                time3.dwYear = phrase2.year;
                time3.dwMonth = phrase2.month;
                time3.dwDay = phrase2.day;
                time3.dwHour = phrase2.hour;
                time3.dwMinute = phrase2.min;
                time3.dwSecond = phrase2.second;
                assistant.seekPlayBack(time3, time2, loginId);
                //赋值，继续开启
                progressMillns = (long) ((endStand - startStand) * seekBar.getProgress() * 1.0 / 100);
                isStop = -1;
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.start_time_ed) {

            //日期
            clickTag = ActivityUtil.getInstance().TextViewToString(mStartTime);
            datePickerDialog.show(getSupportFragmentManager(), "dialog");

        }
        if (view.getId() == R.id.end_time_ed) {
//            showEndDate();
            clickTag = ActivityUtil.getInstance().TextViewToString(mEndTime);
            datePickerDialog.show(getSupportFragmentManager(), "dialog");
        }
        if (view.getId() == R.id.concern_tv) {

            //开始回放
            startPlayBack();

        }

        if (view.getId() == R.id.video_back_panel) {
            //显示面板
            historyTimeSelect();
        }

        //返回上一页
        if (view.getId() == R.id.video_playback_back)
            finish();

    }

    private void startPlayBack() {

        if (checkDate()) {
            //将开启我们回放
            assistant.stopPlayBack();
            setData();
            seekBar.setProgress(0);
            createProgessThread();
            mProgressThread.start();
            progressMillns = 0;
        }
    }

    public TimePhrase getTimePhrase(String date) {
        Log.d(TAG, "getTimePhrase: " + date);
        TimePhrase timePhrase = new TimePhrase();

        String[] str = date.split(" ");
        Log.e("strs", str[0].toString() + " | " + str[1].toString());
        String[] s1 = str[0].split("-");
        for (int j = 0; j < s1.length; j++) {
            timePhrase.year = Integer.parseInt(s1[0]);
            timePhrase.month = Integer.parseInt(s1[1]);
            timePhrase.day = Integer.parseInt(s1[2]);
        }
        String[] s2 = str[1].split(":");
        for (int j = 0; j < s1.length; j++) {
            timePhrase.hour = Integer.parseInt(s2[0]);
            timePhrase.min = Integer.parseInt(s2[1]);
            timePhrase.second = Integer.parseInt(s2[2]);

        }

        return timePhrase;
    }

    //控制进度条继续
    public synchronized void progressThreadNotify() {

        synchronized (mProgressThread) {

            if (isStop == 0) {
                mProgressThread.notify();
            } else {

            }
        }

    }


    //创建控制进度条线程
    public void createProgessThread() {
        mProgressThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        progressMillns = progressMillns + 1 * 1000;
                        mProgress = progressMillns * 100 / (endStand - startStand);

                        Message message = new Message();
                        message.what = 1;
                        message.obj = mProgress;
                        mHandler.sendMessage(message);
                        //线程锁，等待唤醒
                        synchronized (mProgressThread) {
                            //控制暂停
                            if (isStop == 1) {
                                mProgressThread.wait();
                            }
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void setData() {

        /*start = "2019-09-08 00:00:00";
        end = "2019-09-09 00:00:00";*/

        TimePhrase phrase1 = getTimePhrase(startTimeBuf.toString());

        time = new NET_DVR_TIME();
        time.dwYear = phrase1.year;
        time.dwMonth = phrase1.month;
        time.dwDay = phrase1.day;
        time.dwHour = phrase1.hour;
        time.dwMinute = phrase1.min;
        time.dwSecond = phrase1.second;

        TimePhrase phrase2 = getTimePhrase(endTimeBuf.toString());
        time2 = new NET_DVR_TIME();
        time2.dwYear = phrase2.year;
        time2.dwMonth = phrase2.month;
        time2.dwDay = phrase2.day;
        time2.dwHour = phrase2.hour;
        time2.dwMinute = phrase2.min;
        time2.dwSecond = phrase2.second;

        assistant.playback(assistant.setPlayBack(time, time2), loginId);

    }


    //核对我们的时间是否正确
    private boolean checkDate() {

        startStand = TimeUtil.getTimeMills(startTimeBuf.toString());

        endStand = TimeUtil.getTimeMills(endTimeBuf.toString());

        Log.d(TAG, "checkDatsdfae: " + startStand + "---" + endStand);

        if (endStand <= startStand) {

            return false;
        }

        return true;
    }


    private void assignView() {

        timeSelect = findViewById(R.id.video_back_panel);
        timeSelect.setOnClickListener(this);

        backImg = findViewById(R.id.video_playback_back);
        backImg.setOnClickListener(this);

        mConcert = (TextView) findViewById(R.id.concern_tv);
        mSurface = (SurfaceView) findViewById(R.id.surfaceviewId);
        mSurface.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (assistant != null && mProgressThread != null) {
            //停止播放，不清理SDK的初始化
            assistant.playBackCleanUp();
            //停止线程
            mProgressThread.interrupt();
        }

    }

    public void initDatePicker() {
        //日期初始化
        Calendar now = Calendar.getInstance();

        if (datePickerDialog == null) {

            datePickerDialog = DatePickerDialog.newInstance(PlayBackActivity.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));

        } else {

            datePickerDialog.initialize(PlayBackActivity.this,
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
//        2019-09-08 00:00:00
        switch (clickTag) {
            case "开始时间":

                startVideoDate.setYear(year);
                startVideoDate.setMonth(monthOfYear);
                startVideoDate.setDay(dayOfMonth + 1);

                //必须清空
                startTimeBuf.delete(0, startTimeBuf.length());
                startTimeBuf.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ");

                TextView startSubDate = startTime.findViewById(R.id.start_sub_date);
                startSubDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                break;
            case "结束时间":
                stopVideoDate.setYear(year);
                stopVideoDate.setMonth(monthOfYear);
                stopVideoDate.setDay(dayOfMonth + 1);

                //必须清空
                endTimeBuf.delete(0, endTimeBuf.length());

                endTimeBuf.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ");

                TextView endSubDate = endTime.findViewById(R.id.end_sub_date);
                endSubDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth + " ");

                break;
        }


        timePickerDialog.show(getSupportFragmentManager(), "timedialog");
    }

    //时间选择
    public void historyTimeSelect() {
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

            startPlayBack();

            backBuilder.cancel();
        });
        //取消回放
        cancelBtn.setOnClickListener((v) -> {
            ToastManage.getInstance().toastLongShow("取消");
            backBuilder.cancel();
        });

    }
    
}
