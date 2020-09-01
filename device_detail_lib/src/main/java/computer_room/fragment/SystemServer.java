package computer_room.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.example.device_detail_lib.R;
import com.github.mikephil.charting.charts.PieChart;
import com.liulishuo.magicprogresswidget.MagicProgressBar;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import base.Base_Devices_Detail;
import base.Lain_Application;
import computer_room.bean.SystemServerBean;
import computer_room.i_interface.I_SystemServer;
import computer_room.present.SystemServerPresenter;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * 服务器
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/15 10 : 06
 */
public class SystemServer extends Base_Devices_Detail implements I_SystemServer {
    //ViewPager position 0
    private View view;
    //总量
    private TextView serverTotal;
    //总使用率
    private TextView totalUsageRate;
    //用户使用率
    private TextView userUsage;
    //系统使用率
    private TextView systemUsage;
    //当前等待率
    private TextView currentWaitingRate;
    //当前错误率
    private TextView currentErrorRate;
    //当前空闲率
    private TextView currentIdleRate;
    //总物理内存
    private TextView totalPhysicalMemory;
    //已使用物理内存
    private TextView remainingPhysicalMemory;
    //剩余物理内存
    private TextView physicalMemoryUsed;
    //交换区总量
    private TextView totalExchangeArea;
    private TextView currentSwapUsage;
    //交换区使用量
    private TextView remainingAmount;

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;


    //交换区进度条
    private MagicProgressBar areaPro;
    //内存进度条
    private MagicProgressBar memoryPro;

    //交换区进度条
    private MagicProgressBar areaPro2;
    //内存进度条
    private MagicProgressBar memoryPro2;
    private UltimateRecyclerView diskCharts;
    private LinearLayout disks;

    private boolean isAnimActive;

    //Presenter
    private SystemServerPresenter presenter;
    //饼状图
    private PieChart serverPieChart;
    private PieChart serverPieChart2;
    //CPU 饼状图
    private PieChart cpuChart;
    //实时更新 CPU 状态
    PieChartUtil cpuChartPie;
    LinearLayout.LayoutParams pa1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 800);

    PieChartUtil pieChartUtil;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        presenter = new SystemServerPresenter();

        //隐藏所有的顶部导航栏
        head_panel.setVisibility(View.GONE);

        //获取View才能绑定对应的控件
        view = viewList.get(0);
        //绑定控件
        initTextView();
        initP();

        //实时更新
        presenter.dealRealData(LainNewApi.getInstance().getRootPath() + LainNewApi.systemServer, Lain_Application.HTTP_REAL, this);
        infoHandler.postDelayed(this, LainNewApi.SECOND);



    }

    /**
     * 绑定服务器的控件
     */
    private void initTextView() {

        areaPro = view.findViewById(R.id.area_pro);
        memoryPro = view.findViewById(R.id.memory_pro);

        areaPro2 = view.findViewById(R.id.area_pro2);
        memoryPro2 = view.findViewById(R.id.memory_pro2);

        diskCharts = view.findViewById(R.id.disk_pie_charts);
        disks = view.findViewById(R.id.disks);

        cpuChart = view.findViewById(R.id.cpu_chart);

        t1 = view.findViewById(R.id.t1);
        t2 = view.findViewById(R.id.t2);
        t3 = view.findViewById(R.id.t3);
        t4 = view.findViewById(R.id.t4);

    }

    public void initP() {

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                // 当前值 / 最大值
                ObjectAnimator.ofFloat(areaPro, "percent", 0, 0 / 100f),
                ObjectAnimator.ofFloat(memoryPro, "percent", 0, 0 / 100f)
        );
        set.setDuration(600);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isAnimActive = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimActive = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setInterpolator(new AccelerateInterpolator());
        set.start();



//        disks.addView(serverPieChart, pa1);
//        disks.addView(serverPieChart2, pa1);
//
//        PieChartUtil pieChartUtil = new PieChartUtil(serverPieChart, Objects.requireNonNull(getActivity()), false, "D盘", p1);
//        PieChartUtil pieChartUtil2 = new PieChartUtil(serverPieChart2, Objects.requireNonNull(getActivity()), false, "C盘", p2);

        float[] cpu = new float[]{50.3f, 60.5f, 40.f, 60.0f, 70.0f, 80.0f};
        //CPU 饼状图
        cpuChartPie = new PieChartUtil(cpuChart, Objects.requireNonNull(getActivity()), true, "CPU状态", cpu);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * ViewPager
     *
     * @return ViewPager View
     */
    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据
        viewList.add(R.layout.server_template);

        return viewList;
    }

    /**
     * 实时获取数据
     */
    @Override
    public void run() {
        //初始化实时数据列表
        presenter.dealRealData(LainNewApi.getInstance().getRootPath() + LainNewApi.systemServer, Lain_Application.HTTP_REAL, this);
        infoHandler.postDelayed(this, LainNewApi.SECOND);
    }

    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {

        SystemServerBean serverBeans = OkHttpUtil.getInstance().formatResponse("[" + responseStr + "]", SystemServerBean.class).get(0);

        SystemServerBean.ComputerMessageBean messageBean = serverBeans.getComputerMessage();
//        "系统使用率", "用户使用率", "总使用率", "当前错误率", "当前等待率", "当前闲置率"
        //更新CPU的状态
        float[] cpu = new float[]{messageBean.getCpuSys() * 1024 *2, messageBean.getCpuUser() * 1024 *2, (float) messageBean.getSwapTotal(), messageBean.getCpuNice()* 1024 *2, messageBean.getCpuWait()* 1024 *2, messageBean.getCpuIdle()* 1024 *2};

        cpuChartPie.setData(cpuChartPie.cpuParties.length, cpu, "CPU状态", cpuChartPie.cpuParties);
        Log.d("sdfdsf", "dealWitchReal: " + messageBean.getSwapFree() / messageBean.getSwapTotal() * 100);
        areaPro.setPercent((float) (messageBean.getSwapUsed() / messageBean.getSwapTotal() * 100 / 100f));
        memoryPro.setPercent((float) (messageBean.getSwapFree() / messageBean.getSwapTotal() * 100 / 100f));

        areaPro2.setPercent((float) (messageBean.getMemUsed() / messageBean.getMemTotal() * 100 / 100f));
        memoryPro2.setPercent((float) (messageBean.getMemFree() / messageBean.getMemTotal() * 100 / 100f));

        t1.setText("已使用  " + messageBean.getSwapUsed());
        t2.setText("未使用  " + messageBean.getSwapFree());

        t3.setText("已使用  " + messageBean.getMemUsed());
        t4.setText("未使用  " + messageBean.getMemFree());

        List<SystemServerBean.DiskListBean> diskList = serverBeans.getDiskList();

        if (pieChartUtil == null)
        for (int i = 0; i < diskList.size(); i++ ) {

            if (diskList.get(i).getDiskTotalSpace() == 0)
                continue;

            if (pieChartUtil == null) {
                serverPieChart = new PieChart(getActivity());
                disks.addView(serverPieChart, pa1);
                float[] data = new float[]{(float) diskList.get(i).getDiskFreeSpace(), (float) diskList.get(i).getDiskUsedSpace()};
                pieChartUtil = new PieChartUtil(serverPieChart, Objects.requireNonNull(getActivity()), false, diskList.get(i).getSpaceName(), data);
            } else {
                float[] data = new float[]{(float) diskList.get(i).getDiskFreeSpace(), (float) diskList.get(i).getDiskUsedSpace()};

                PieChart p = new PieChart(getActivity());

                disks.addView(p, pa1);
                new PieChartUtil(p, Objects.requireNonNull(getActivity()), false, diskList.get(i).getSpaceName(), data);
            }


        }

        Log.d("lkjdf", "dealWitchReal: " + serverBeans.getDiskList());

//        serverTotal.setText("总量：" + messageBean.getCpuMhz());
//        totalUsageRate.setText("总使用率：" + messageBean.getSwapTotal());
//        userUsage.setText("用户使用率：" + messageBean.getCpuUser());
//        systemUsage.setText("系统使用率：" + messageBean.getCpuSys());
//        currentWaitingRate.setText("当前等待率：" + messageBean.getCpuWait());
//        currentErrorRate.setText("当前错误率：" + );
//        currentIdleRate.setText("当前空闲率：" + messageBean.getCpuIdle());
//        totalPhysicalMemory.setText("总物理内存：" + messageBean.getMemTotal());
//        remainingPhysicalMemory.setText("已使用物理内存：" + messageBean.getMemUsed());
//        physicalMemoryUsed.setText("剩余物理内存：" + messageBean.getMemFree());
//        totalExchangeArea.setText("交换区总量：" + messageBean.getSwapTotal());
//        currentSwapUsage.setText("交换区使用量：" + messageBean.getSwapUsed());
//        remainingAmount.setText("交换区剩余量：" + messageBean.getSwapFree());

    }
}
