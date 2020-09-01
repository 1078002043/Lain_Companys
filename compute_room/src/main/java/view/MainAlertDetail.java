package view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.compute_room.R;
import com.example.compute_room.R2;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import adapter.MainAlertAdapter;
import base.BaseRecyclerViewAdapter;
import base.Lain_Application;
import base.Lain_Base_Activity;
import bean.AlertQueryBean;
import bean.MainMonthAlert;
import butterknife.BindView;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import present.MainAlertPresenter;
import util.DragFloatingButton;
import util.LainNewApi;
import util.TimeUtil;
import util.ToastManage;
import v_interface.I_MainAlert;

/**
 * 主页的报警消息页面
 */
public class MainAlertDetail extends Lain_Base_Activity implements I_MainAlert, BaseRecyclerViewAdapter.OnItemClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    //月报警数据列表
    @BindView(R2.id.month_alert_recycler)
    UltimateRecyclerView monthAlertRecycler;
    @BindView(R2.id.query_alert)
    DragFloatingButton queryAlert;
    //Presenter
    private MainAlertPresenter presenter;
    //报警列表
    private MainAlertAdapter monthAdapter;
    //报警数据
    private List<MainMonthAlert> monthBeans = new ArrayList<>();

    //报警编辑设置面板
    public BottomSheetDialog alertBottomSheet;

    //设置面板的行为
    private BottomSheetBehavior mDialogBehavior;

    //日期选择器
    public DatePickerDialog datePickerDialog = null;
    //时间选择器
    public TimePickerDialog timePickerDialog = null;

    //记录开始时间的选择
    StringBuffer startTime = new StringBuffer();
    //记录结束时间的选择
    StringBuffer endTime = new StringBuffer();

    //选择已读或者未读
    private int queryStatus = 0;
    //保存查询方式的消息
    AlertQueryBean alertQueryBean = new AlertQueryBean();

    //开始，结束时间显示
    private EditText panelStartTime;
    private EditText panelEndTime;

    @Override
    protected String getToolbarTitle() {

        //获取报警标题，由调用者传入
        String title = getIntent().getStringExtra("title");
        //如果未传，默认是 报警信息
        return title == null || title.isEmpty() ? "报警信息" : title;

    }

    @Override
    public int setLayoutView() {
        return R.layout.main_alert_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化Presenter
        presenter = new MainAlertPresenter(this);
        //初始化列表
        monthAdapter = new MainAlertAdapter(MainAlertDetail.this, monthBeans, R.layout.main_month_alert);
        monthAlertRecycler.setLayoutManager(new LinearLayoutManager(MainAlertDetail.this));
        monthAdapter.setItemClickListener(this);
        monthAlertRecycler.setAdapter(monthAdapter);

        //显示报警数据查询面板
        queryAlert.setOnClickListener((v) -> {

            alertBottomSheet.show();

        });
        //初始化时间选择面板
        initAlertTime();
        //初始化查询面板
        initAlertSheet();

        //默认全部报警数据
        queryAlertMessage(getToolbarTitle());

    }

    /**
     * 权限不同模块查询不同时间段的报警数据
     * 指定查询时间，到当前时间段
     *
     * @param toolbarTitle 模块的名称
     */
    private void queryAlertMessage(String toolbarTitle) {

        //时间段查询
        String start = null;
        String end = null;

        switch (toolbarTitle) {

            case "报警信息":
                //默认查询报警信息的话，直接查当月的数据
            case "当月报警":

                start = TimeUtil.getInstance().getTimeOfMonthStart();
                end = TimeUtil.getInstance().getTimeCurrent();

                presenter.getMonthAlert(0, start, end);

                break;
            case "今日报警":

                start = TimeUtil.getInstance().getTimeOfDayStart();
                end = TimeUtil.getInstance().getTimeCurrent();

                presenter.getMonthAlert(0, start, end);
                break;
            case "当周报警":

                start = TimeUtil.getInstance().getTimeOfWeekStart();
                end = TimeUtil.getInstance().getTimeCurrent();

                presenter.getMonthAlert(0, start, end);
                break;
            case "已读消息":

                start = TimeUtil.getInstance().getTimeOfMonthStart();
                end = TimeUtil.getInstance().getTimeCurrent();
                //默认查询一个月
                presenter.getMonthAlert(1, start, end);

                break;
            case "未读消息":

                start = TimeUtil.getInstance().getTimeOfMonthStart();
                end = TimeUtil.getInstance().getTimeCurrent();
                //默认查询一个月
                presenter.getMonthAlert(2, start, end);
                break;
        }

        //设置查询的时间
        panelStartTime.setText(start);
        panelEndTime.setText(end);

    }

    //请求成功后，回到该方法中进行更新列表
    @Override
    public void monthAlert(List<MainMonthAlert> alerts) {

        monthBeans.clear();
        monthBeans.addAll(alerts);
        monthAdapter.notifyDataSetChanged();

        //查询成功后，再隐藏
        alertBottomSheet.cancel();

    }

    @Override
    public void onItemClickListener(View v, int position) {
        //更新报警状态
        String upUri = LainNewApi.getInstance().getRootPath() + LainNewApi.updateAlertStatus + monthBeans.get(position).getId();
        OkHttpUtil.getInstance().sendPutOkHttp("up_alert", upUri, "", new OkHttpCallBack() {
            @Override
            public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

                //更新状态请求成功后
                if (Boolean.parseBoolean(responseStr)) {
                    Button readBtn = (Button) v;
                    readBtn.setText("已读");
                    readBtn.setBackground(getDrawable(R.drawable.circle_close_bg));
                    readBtn.setEnabled(false);
                    //更新报警列表数据中的数据
                    monthBeans.get(position).setIsRead(1);
                }

            }

            @Override
            public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

            }
        });

    }

    /**
     * 初始化报警查询面板
     */
    private void initAlertSheet() {

        View v = LayoutInflater.from(this).inflate(R.layout.main_alert_sheet, null, false);

        EditText panelYear = findViewById(R.id.panel_year);
        panelStartTime = v.findViewById(R.id.panel_start_time);
        panelEndTime = v.findViewById(R.id.panel_end_time);
        //选择已读
        Button selRel = v.findViewById(R.id.sel_rel);
        //查询全部
        Button selAll = v.findViewById(R.id.sel_all);
        //选择未读
        Button selUnRel = v.findViewById(R.id.sel_un_rel);
        //确定查询
        Button trueCommit = v.findViewById(R.id.true_commit);
        //取消查询
        Button cancelCommit = v.findViewById(R.id.cancel_commit);

        //默认选中第一个
        alertQueryBean.setActionBtn(selAll);
        alertQueryBean.setQueryStatus(queryStatus);

        //查询全部
        selAll.setOnClickListener((btnV) -> {

            queryStatus = 0;

            changeBtnBg();

            alertQueryBean.setActionBtn(btnV);
            alertQueryBean.setQueryStatus(queryStatus);

            selBtn(btnV);

        });
        //查询已读
        selRel.setOnClickListener((btnV) -> {
            queryStatus = 1;
            changeBtnBg();
            alertQueryBean.setActionBtn(btnV);
            alertQueryBean.setQueryStatus(queryStatus);

            selBtn(btnV);

        });
        //查询未读
        selUnRel.setOnClickListener((btnV) -> {

            queryStatus = 2;
            changeBtnBg();
            alertQueryBean.setActionBtn(btnV);
            alertQueryBean.setQueryStatus(queryStatus);

            selBtn(btnV);

        });

        //开始查询
        trueCommit.setOnClickListener((btnV) -> {

            switch (queryStatus) {
                case 0:

                    //如果未选择时间，则不进行查询
                    if (startTime.toString().isEmpty() || endTime.toString().isEmpty()) {
                        ToastManage.getInstance().toastShortShow("请先选择时间");
                        datePickerDialog.show(getSupportFragmentManager(), "alert_start_date");
                        return;
                    }

                    presenter.getMonthAlert(0, startTime.toString(), endTime.toString());
                    break;
                case 1:
                    //已读
                    presenter.getMonthAlert(1, "2020-7-14 16:57:0", "2020-7-23 16:57:0");
                    break;
                case 2:
                    //未读
                    presenter.getMonthAlert(2, "2020-7-14 16:57:0", "2020-7-23 16:57:0");
                    break;
            }

        });

        //取消查询
        cancelCommit.setOnClickListener((btnV) -> {
            alertBottomSheet.cancel();
        });

        //显示开始时间选择面板
        panelStartTime.setOnClickListener((btnV) -> {
            datePickerDialog.show(getSupportFragmentManager(), "alert_start_date");
        });

        //显示结束时间选择面板
        panelEndTime.setOnClickListener((btnV) -> {
            datePickerDialog.show(getSupportFragmentManager(), "alert_end_date");
        });

        //初始化Sheet
        alertBottomSheet = new BottomSheetDialog(this);
        alertBottomSheet.setContentView(v);
        //必须是在 setContentView之后再获取 behavior
        mDialogBehavior = BottomSheetBehavior.from((View) v.getParent());
        //设置展开的状态
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    /**
     * 选择 查询条件后，改变按钮的选中背景颜色
     *
     * @param btnV 按钮的实例
     */
    private void selBtn(View btnV) {
        Button btn = (Button) btnV;
        btn.setBackground(getResources().getDrawable(R.drawable.blue_btn_bg));
        btn.setTextColor(Color.WHITE);
    }

    /**
     * 未选择的 查询条件 按钮默认背景
     */
    private void changeBtnBg() {

        if (alertQueryBean.getActionBtn() != null) {
            Button btn = (Button) alertQueryBean.getActionBtn();
            btn.setBackground(getResources().getDrawable(R.drawable.btn_circle_bg));
            btn.setTextColor(Color.BLACK);
        }
    }

    /**
     * 报警查询时间
     */
    public void initAlertTime() {
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
        //取消按钮
        timePickerDialog.setCancelText("取消");
        //确认按钮
        timePickerDialog.setOkText("查询");
        //时间选择器的样式
        timePickerDialog.setVersion(com.wdullaer.materialdatetimepicker.time.TimePickerDialog.Version.VERSION_2);

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

        //时间选择器的颜色
        datePickerDialog.setAccentColor(Lain_Application.getThemeColor());
        //时间选择器的样式
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);
    }

    //选择日期
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        switch (view.getTag()) {
            case "alert_start_date":
                //每次选择时，都先清空
                startTime.replace(0, startTime.length(), "");
                startTime.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                timePickerDialog.show(getSupportFragmentManager(), "alert_start_time");

                break;
            case "alert_end_date":
                //每次选择时，都先清空
                endTime.replace(0, endTime.length(), "");
                endTime.append(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                timePickerDialog.show(getSupportFragmentManager(), "alert_end_time");
                break;
        }


    }

    //选择时间
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        switch (view.getTag()) {
            case "alert_start_time":
                startTime.append(" ");
                startTime.append(hourOfDay + ":" + minute + ":" + second);

                //设置时间
                panelStartTime.setText(startTime.toString());

                break;
            case "alert_end_time":
                endTime.append(" ");
                endTime.append(hourOfDay + ":" + minute + ":" + second);

                //设置时间
                panelEndTime.setText(endTime.toString());

                break;
        }

    }
}
