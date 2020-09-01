package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.example.compute_room.R;
import com.example.compute_room.R2;
import com.github.ybq.android.spinkit.SpinKitView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import base.BaseRecyclerViewAdapter;
import base.Lain_Base_Activity;
import base.Lain_Base_Fragment;
import bean.SmartMainBean;
import butterknife.BindView;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import present.SmartDevice_Presenter;
import util.LainNewApi;
import v_interface.I_SmartDevice;
import view.ComputerRoom;
import view.Device_Detail;
import view.MainAlertDetail;

import static solid.ren.skinlibrary.utils.SkinResourcesUtils.getDrawable;

/**
 * 添加的系统 Fragment
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/31 14 : 43
 */
public class SmartDevice extends Lain_Base_Fragment implements I_SmartDevice, BaseRecyclerViewAdapter.OnItemClickListener {

    private ArrayList<Integer> arrayList;

    @BindView(R2.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R2.id.view_pager)
    ViewPager viewPager;
    @BindView(R2.id.menu_config_loading)
    SpinKitView menuConfigLoading;

    //保存View，用来绑定Fragment 中的控件
    private View view = null;
    //初始化Presenter
    private static SmartDevice_Presenter presenter;

    public ComputerRoom noActivity;
    //月报警
    TextView monthAlert;
    //天报警
    TextView dayAlert;
    TextView weekAlarm;
    TextView mainReal;
    TextView communication;
    TextView unRead;

    TextView average;
    TextView totalPower;

    UltimateRecyclerView mainAlertRecycler;

    //动态添加报警Item
    LinearLayout mainAlertLinear;

    /**
     * Fragment创建完毕时执行
     *
     * @param savedInstanceState 传递的数据
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //实例化Presenter
        presenter = new SmartDevice_Presenter(this);

        noActivity = new ComputerRoom(getActivity(), viewPager, magicIndicator, menuConfigLoading);

        View mainView = noActivity.viewPagerList.get(0);
        //月报警
        monthAlert = mainView.findViewById(R.id.month_total);
        //天报警
        dayAlert = mainView.findViewById(R.id.current_alarm_count);

        weekAlarm = noActivity.viewPagerList.get(0).findViewById(R.id.week_alarm);
        mainReal = noActivity.viewPagerList.get(0).findViewById(R.id.main_read);
        communication = noActivity.viewPagerList.get(0).findViewById(R.id.communication);
        unRead = noActivity.viewPagerList.get(0).findViewById(R.id.unread);
        average = noActivity.viewPagerList.get(0).findViewById(R.id.average);
        totalPower = noActivity.viewPagerList.get(0).findViewById(R.id.total_power);
        mainAlertRecycler = noActivity.viewPagerList.get(0).findViewById(R.id.main_alert_list);
        //动态添加View
        mainAlertLinear = noActivity.viewPagerList.get(0).findViewById(R.id.main_alert_item);

        //当月报警
        monthAlert.setOnClickListener((v) -> {
            Lain_Base_Activity.myStartActivity(getActivity(), MainAlertDetail.class, "title", "当月报警");
        });

        //今日报警
        dayAlert.setOnClickListener((v) -> {
            Lain_Base_Activity.myStartActivity(getActivity(), MainAlertDetail.class, "title", "今日报警");
        });

        //当周报警
        weekAlarm.setOnClickListener(v -> {
            Lain_Base_Activity.myStartActivity(getActivity(), MainAlertDetail.class, "title", "当周报警");
        });

        //已读
        mainReal.setOnClickListener(v -> {
            Lain_Base_Activity.myStartActivity(getActivity(), MainAlertDetail.class, "title", "已读消息");
        });

        //未读
        unRead.setOnClickListener(v -> {
            Lain_Base_Activity.myStartActivity(getActivity(), MainAlertDetail.class, "title", "未读消息");
        });

        //通讯地址
        communication.setOnClickListener(v -> {

            LainNewApi.INTENT_TAG = "设备管理";
            LainNewApi.DEVICE_TAG = "系统管理";

            Intent intent = new Intent(getActivity(), Device_Detail.class);
            intent.putExtra("device_tag", LainNewApi.DEVICE_TAG);
            intent.putExtra("device_name", LainNewApi.INTENT_TAG);

            startActivity(intent);

        });

        //引导欢迎界面
        arrayList = new ArrayList<>();
        //设置上下文
        presenter.getMainAllData();

    }

    @Override
    public void getSmartAllData(SmartMainBean smartMainBean) {

        //获取到所有的主页数据
        //月报警
        monthAlert.setText(String.valueOf(smartMainBean.getTotalAlarmMonthCount()));
        //天报警
        dayAlert.setText(smartMainBean.getTotalAlarmTodayCount() + "");
        mainReal.setText(smartMainBean.getTotalAlarmIsRead() + "");
        unRead.setText(smartMainBean.getTotalAlarmIsNotRead() + "");

        weekAlarm.setText(smartMainBean.getTotalAlarmWeekCount() + "");

        //保存后两位
        String averageStr = null;
        try {
            averageStr = String.valueOf(smartMainBean.getAvgTemperature());
            averageStr = averageStr.substring(0, 5);
        } catch (StringIndexOutOfBoundsException e) {
            //如果数据没超过5位数字，则直接赋值
            averageStr = String.valueOf(smartMainBean.getAvgTemperature());
        }

        average.setText(averageStr + "℃");
        communication.setText(smartMainBean.getEquipmentTotal() + "");

        //设置最新的报警数据，如果使用 RecyclerView会导致滑动不了的问题，所以暂时只能使用动态添加View
        addMainAlertItem(smartMainBean.getTotalAlarmLimit());


    }

    /**
     * 动态添加主页的报警信息
     *
     * @param totalAlarmLimit 请求到的主页报警信息
     */
    private void addMainAlertItem(List<SmartMainBean.TotalAlarmLimitBean> totalAlarmLimit) {


        for (SmartMainBean.TotalAlarmLimitBean limitBean :
                totalAlarmLimit) {

            View holder = LayoutInflater.from(getActivity()).inflate(R.layout.main_month_alert, null);

            TextView alertName = holder.findViewById(R.id.main_alert_name);
            TextView alertTime = holder.findViewById(R.id.main_alert_time);
            Button readBtn = holder.findViewById(R.id.main_alert_btn);

            alertName.setText(limitBean.getAlarmName());
            alertTime.setText(limitBean.getTime());

            //两个状态都必须设置背景，不然渲染结果不一致
            if (limitBean.getIsRead() == 0) {
                readBtn.setText("未读");
                readBtn.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.circle_alert_bg));
            } else if (limitBean.getIsRead() == 1) {
                readBtn.setText("已读");
                readBtn.setBackground(Objects.requireNonNull(getActivity()).getDrawable(R.drawable.circle_close_bg));
                readBtn.setEnabled(false);
            }

            readBtn.setOnClickListener((v) -> {

                updateAlertStatus((Button) v, limitBean);

            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 310);
            params.setMargins(20, 10, 20, 10);

            mainAlertLinear.addView(holder, params);

        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private boolean isViewActive() {
        return !(getActivity().isFinishing() || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && getActivity().isDestroyed()));
    }

    /**
     * Fragment创建时执行
     *
     * @param savedInstanceState 临时保存数据
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Fragment 准备时执行
     *
     * @param context Context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    /**
     * 添加系统在Layout ID
     *
     * @param inflater           视图加载
     * @param container          容器
     * @param savedInstanceState 临时保存数据
     * @return 添加系统的 Layout View
     */
    @Override
    protected View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.smart_device, container, false);
    }

    public static SmartDevice_Presenter getPresenter() {
        if (presenter != null)
            return presenter;
        return null;
    }

    @Override
    public void onItemClickListener(View v, int position) {

    }

    /**
     * 更新主页的消息
     *
     * @param v         按钮的实例
     * @param limitBean
     */
    private void updateAlertStatus(Button v, SmartMainBean.TotalAlarmLimitBean limitBean) {
        //更新报警状态
        String upUri = LainNewApi.getInstance().getRootPath() + LainNewApi.updateAlertStatus + limitBean.getId();
        OkHttpUtil.getInstance().sendPutOkHttp("up_alert", upUri, "", new OkHttpCallBack() {
            @Override
            public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

                //更新状态请求成功后
                if (Boolean.parseBoolean(responseStr)) {
                    Button readBtn = v;
                    readBtn.setText("已读");
                    readBtn.setBackground(getActivity().getDrawable(R.drawable.circle_close_bg));
                    readBtn.setEnabled(false);
                    //更新报警列表数据中的数据，在重新回到主页时，才不会因为重新渲染导致数据显示出错问题
                    limitBean.setIsRead(1);
                }

            }

            @Override
            public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

            }
        });
    }

}
