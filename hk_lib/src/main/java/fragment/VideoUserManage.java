package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hk_lib.R;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;

import base.BaseRecyclerViewAdapter;
import base.Lain_Base_Activity;
import bean.VideoAccountBean;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * @ClassName: VideoUserManage
 * @Description: 视频监控的用户管理
 * @Author: YIN LUO FEI
 * @Date: 2020/8/1 15:58
 */
public class VideoUserManage extends Lain_Base_Activity implements BaseRecyclerViewAdapter.OnItemClickListener, OkHttpCallBack {

    //视频监控用户列表
    private UltimateRecyclerView videoUserList;

    //用户列表
    private List<VideoAccountBean> videoAccountBeanList = new ArrayList<>();
    private VideoUserAdapter videoUserAdapter;

    @Override
    protected String getToolbarTitle() {
        return "视频用户管理";
    }

    @Override
    public int setLayoutView() {
        return R.layout.video_account_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //绑定视频监控用户列表
        videoUserList = findViewById(R.id.video_user_list);
        //初始化适配器
        videoUserAdapter = new VideoUserAdapter(this, videoAccountBeanList, R.layout.video_user_temp);
        videoUserAdapter.setItemClickListener(this);
        videoUserList.setLayoutManager(new LinearLayoutManager(this));
        videoUserList.setAdapter(videoUserAdapter);
        
        //请求视频监控用户列表
        requestVideoUser();

    }

    /**
     * 请求视频监控的用户列表
     */
    private void requestVideoUser() {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.videoAccount;

        OkHttpUtil.getInstance().sendGetOkHttp("video_user", url, this);

    }

    @Override
    public void onItemClickListener(View v, int position) {

    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        //获取到的用户信息
        userListResponse(responseStr);

    }

    private void userListResponse(String responseStr) {

        //解析用户列表
        videoAccountBeanList.clear();
        videoAccountBeanList.addAll(OkHttpUtil.getInstance().formatResponse(responseStr, VideoAccountBean.class));

        if (videoAccountBeanList.isEmpty())
            return;

        //刷新用户列表
        videoUserAdapter.notifyDataSetChanged();

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }

}
