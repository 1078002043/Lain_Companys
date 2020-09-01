package fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hk_lib.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_JPEGPARA;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.Lain_Base_Fragment;
import bean.DeviceBean;
import bean.VideoAccountBean;
import hk_video.Config;
import hk_video.PlayAssistant;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * 视频监控主页
 */
public class VideoMainPage extends Lain_Base_Fragment implements BaseRecyclerViewAdapter.OnItemClickListener,
        I_CaptureImg {

    //请求的代码
    private static final int REQUEST_CODE = 1000;

    //视频列表
    private UltimateRecyclerView videoRecyclerView;
    private View view;

    //海康SDK 流
    private NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = null;
    //开始通道
    private int m_iStartChan = 0; // start channel no
    //登陆标识
    private int mLoginId = -1;
    //当前通道
    private int channel = 1;
    //日志标识
    private String TAG = "HKNetDvrDeviceInfoV30";

    //视频配置
    private ArrayList<DeviceBean> videoDeviceList;
    //加载截图等待
    private SpinKitView loading;
    //视频列表适配器
    private VideoMainAdapter adapter = null;
    //抓取图片完成
    private I_CaptureImg i_captureImg;
    //加载视频时等待的View
    private LinearLayout videoLoadingView;
    //获取通道的数量
    private int ipChanNum = 0;
    //获取开始通道的位置
    private int byStartDChan = 0;

    private String videoName;
    private String videoPas;
    private String videoIP;
    private String videoPort;

    private String directory;

    //保存所有摄像头的通道
    private ArrayList<String> deviceChannel = new ArrayList<>();

    //保存登陆信息
    private VideoAccountBean accountBean;

    //视频监控的操作类
    private PlayAssistant assistant;
    //加载中文字
    private TextView loadingText;
    //加载中进度
    private int loadingProgress = 1;

    //接收截图加载进度
    private Handler loadingHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            loadingText.setText("加载中 " + loadingProgress + " \\ " + m_oNetDvrDeviceInfoV30.byIPChanNum);
            //+1
            loadingProgress++;

        }
    };

    //保存用户列表
    private List<VideoAccountBean> videoAccountBeanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //请求需要的权限
        applyForPermissions();

        //初始化图片抓取接口
        i_captureImg = this;

        //获取文件路径
        directory = Environment.getExternalStorageDirectory().getAbsolutePath();

        File file = new File(directory + "/surface/images/");
        if (!file.exists()) {
            file.mkdirs();
        }


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //视频列表
        videoRecyclerView = view.findViewById(R.id.video_main_recycler);
        //视频的加载等待图标
        videoLoadingView = view.findViewById(R.id.video_loading_layout);

        //加载动画
        loading = view.findViewById(R.id.video_main_loading);
        //加载进度
        loadingText = view.findViewById(R.id.loading_progress);

        //视频数据
        videoDeviceList = new ArrayList<>();

        //创建视频监控的操作类
        assistant = new PlayAssistant();

        //注册海康SDK配置文件
        Config.register(getActivity());
        //初始化SDK
        if (!initeSdk()) {
            getActivity().finish();
            return;
        }

        //如果第一次进行则设置适配器，否则直接更新
        adapter = new VideoMainAdapter(getActivity(), videoDeviceList, R.layout.video_temperature);
        //设置点击监控
        adapter.setItemClickListener(this);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        videoRecyclerView.setAdapter(adapter);

        //获取视频的账号相关信息
        OkHttpUtil.getInstance().sendGetOkHttp("video_account", LainNewApi.getInstance().getRootPath() + LainNewApi.videoAccount, new OkHttpCallBack() {
            @Override
            public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

                //解析账号数据
                videoAccountBeanList = OkHttpUtil.getInstance().formatResponse(responseStr, VideoAccountBean.class);

                //如果未获取到账号，直接返回
                if (videoAccountBeanList.isEmpty())
                    return;

                //保存登陆信息
                accountBean = videoAccountBeanList.get(0);

                Log.d(TAG, "httpRequestSuccess: " + responseStr + "---" + Thread.currentThread().getName());

                //保存获取到的视频账号信息
                try {
                    //账号名称
                    videoName = videoAccountBeanList.get(0).getUsername();
                    //账号的密码
                    videoPas = videoAccountBeanList.get(0).getPassword();
                    //账号IP
                    videoIP = videoAccountBeanList.get(0).getIpAddress();
                    //账号端口
                    videoPort = videoAccountBeanList.get(0).getIpPort() + "";

                    //请求成功后，再对视频监控初始化
                    initSDK(videoIP, Integer.parseInt(videoPort), videoName, videoPas);
                    //登陆成功后，请求获取摄像头信息，截图
                    initVideo();

                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

            }
        });

    }

    /**
     * 初始化SDK与登陆视频监控，动态加载视频监控的 SDK 包
     */
    private void initSDK(String strIP, int nPort, String strUser, String strPsd) {

        //用户登陆
        mLoginId = loginNormalDevice(strIP, nPort, strUser, strPsd, channel + 32);

//        该IP段内的通道数量 byIPChanNum : 16  开始通道的位置 byStartDChan : 33
//         有时候只能截取几个视频监控的画面，不知道能不能进行查看视频

        try {
            //获取开始通道 因为是 33 通道是 16 从 1 开始，因为要想确保通道数量正确，就必须 -1
            byStartDChan = m_oNetDvrDeviceInfoV30.byStartDChan;

            //获取最大的IP通道数量
            //获取通道的数量 因为开始通道不固定，因为抓取封面时，需要 现有通道 + 开始通道 确定循环的范围
            ipChanNum = (m_oNetDvrDeviceInfoV30.byIPChanNum - 1) + byStartDChan;
            //循环使用默认的封面，进入页面后，再通过获取视频监控本身的截图来作为封面

        } catch (NullPointerException e) {
            Log.d(TAG, "onActivityCreated000: 无法获取 海康视频SDK");
        }

    }

    //获取需要的权限
    private void applyForPermissions() {//申请sdcard读写权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 抓取封面
     */
    private void initVideo() {
        captureImage();
    }

    @Override
    protected View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.video_main_page, container, false);
    }

    /**
     * 登录设备
     */
    private int loginNormalDevice(String strIP, int nPort, String strUser, String strPsd, int channel) {
        // get instance
        m_oNetDvrDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
        if (null == m_oNetDvrDeviceInfoV30) {
            Log.e(TAG, "HKNetDvrDeviceInfoV30 new is failed!");
            return -1;
        }
        // call NET_DVR_Login_v30 to login on, port 8000 as default
        int iLogID = HCNetSDK.getInstance().NET_DVR_Login_V30(strIP, nPort, strUser, strPsd,
                m_oNetDvrDeviceInfoV30);

        Log.d(TAG, "loginNormalDevice: " + iLogID);

        if (iLogID < 0) {
            Log.e(TAG, "NET_DVR_Login is failed!Err:" + HCNetSDK.getInstance().NET_DVR_GetLastError());
            return -1;
        }

        if (m_oNetDvrDeviceInfoV30.byChanNum > 0) {
            // m_oNetDvrDeviceInfoV30.byStartChan
            m_iStartChan = channel;
            //开始通道
        } else if (m_oNetDvrDeviceInfoV30.byIPChanNum > 0) {
            // m_oNetDvrDeviceInfoV30.byStartDChan
            m_iStartChan = channel + 32;
            Log.e("m_iStartChan", m_iStartChan + "");
        }

        Log.i(TAG, "NET_DVR_Login is Successful!");

        return iLogID;
    }

    //初始化SDK
    private boolean initeSdk() {
        // init net sdk
        if (!HCNetSDK.getInstance().NET_DVR_Init()) {
            Log.e("lzc", "HCNetSDK init is failed!");
            return false;
        }
        HCNetSDK.getInstance().NET_DVR_SetLogToFile(3, "/mnt/sdcard/sdklog/",
                true);

        return true;
    }

    /**
     * 抓取图片
     */
    private void captureImage() {
        //显示等待图标
        videoLoadingView.setVisibility(View.VISIBLE);

        //如果登陆成功，才会爬图
        if (mLoginId >= 0) {

            //在子线程中执行
            new Thread(() -> {

                //保存抓取的图片
                ArrayList<DeviceBean> videoDeviceList = new ArrayList<>();
                NET_DVR_JPEGPARA net_dvr_jpegpara = new NET_DVR_JPEGPARA();

                //只有 0xff 是超清图，不设置这个则是非常模糊的截图
                net_dvr_jpegpara.wPicSize = 0xff;
                net_dvr_jpegpara.wPicQuality = 0;

                //组装数据，  I 是通道
                for (int i = byStartDChan; i < ipChanNum; i++) {

                    Log.d(TAG, "onCreate: " + i + " byStartDChan：" + byStartDChan + " byStartDChan： " + ipChanNum);


                    File file = new File(directory + "/surface/images/");
                    Log.d(TAG, "captureImage: " + file.getAbsolutePath());
                    if (!file.exists()) {
                        file.mkdir();
                        Log.e("mkdir", "make success " + file.mkdir());
                    }

                    //截图设置到recycleView中
                    Log.d(TAG, "captureImage: " + net_dvr_jpegpara.wPicSize);
                    if (HCNetSDK.getInstance().NET_DVR_CaptureJPEGPicture(mLoginId, i, net_dvr_jpegpara, directory + "/surface/images/" + channel + ".jpg")) {
                        Log.e(TAG, "capture success-----" + i);
                        String myJpgPath = directory + "/surface/images/" + channel + ".jpg";
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 2;
                        //获取图片的 BitMap
                        Bitmap bm = BitmapFactory.decodeFile(myJpgPath, options);
                        //保存 Bitmap
                        DeviceBean bean = new DeviceBean();
                        bean.bitmap = bm;
                        //记录获取成功的通道号
                        bean.channel = i;

                        videoDeviceList.add(bean);
                        deviceChannel.add(i + "");

                        channel++;

                    } else {
                        Log.e(TAG, "capture failed!");
                    }

                    //每张截图完成后，发送完成消息
                    loadingHandler.sendEmptyMessage(1);

                }

                //全部抓取完成后，回调接口更新列表
                i_captureImg.captureComplete(videoDeviceList);

            }).start();

        }

    }

    /**
     * 点击后，跳转到对应的视频详情页中播放
     *
     * @param v        View
     * @param position 点击的视频ITEM所属位置
     */
    @Override
    public void onItemClickListener(View v, int position) {

        Intent intent = new Intent(getActivity(), VideoMainDetail.class);
        //视频所在位置，相当于 通道
        intent.putExtra("video_position", position);
        //视频列表的长度，用于 ViewPager 的滑动
        intent.putExtra("video_size", videoDeviceList.size());
        intent.putExtra("channel", videoDeviceList.get(position).getChannel());
        //视频的开始通道
        intent.putExtra("byStartDChan", byStartDChan);
        //视频的所有通道
        intent.putExtra("ipChanNum", ipChanNum);

        //登陆ID
        intent.putExtra("loginId", mLoginId);

        //用户名
        intent.putExtra("loginInfo", accountBean);

        //直接将所有的摄像头信息传递到详情页
        intent.putStringArrayListExtra("video_channel", deviceChannel);

        startActivity(intent);

    }

    /**
     * 重启APP
     */
    private void restartApplication() {

        //重新打开app启动页
        final Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        //杀掉主进程
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    /**
     * 抓取完成后，回调该方法设置列表数据
     *
     * @param videoDeviceList 抓取的数据
     */
    @Override
    public void captureComplete(ArrayList<DeviceBean> videoDeviceList) {

        //设置视频列表
        getActivity().runOnUiThread(() -> {

            //保存抓取图片，在详情中才能统计视频的数量，实现ViewPager切换视频
            this.videoDeviceList.addAll(videoDeviceList);

            //直接更新列表
            adapter.notifyDataSetChanged();

            if (adapter == null) {
                //如果第一次进行则设置适配器，否则直接更新
                adapter = new VideoMainAdapter(getActivity(), videoDeviceList, R.layout.video_temperature);
                //设置点击监控
                adapter.setItemClickListener(this);
                videoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                videoRecyclerView.setAdapter(adapter);
            } else {

            }

            videoLoadingView.setVisibility(View.GONE);
        });


    }

    /**
     * 进入视频监控的用户管理页面
     */
    public void intoVideoUserManage() {

        Intent intent = new Intent(getActivity(), VideoUserManage.class);
        startActivity(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //退出视频监控后，将所有的资源释放
        if (assistant != null) {
            assistant.Cleanup();
        }
    }
}
