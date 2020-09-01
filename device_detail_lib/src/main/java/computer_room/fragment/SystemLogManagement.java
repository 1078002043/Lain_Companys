package computer_room.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.device_detail_lib.R;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import base.Base_Devices_Detail;
import base.Lain_Application;
import bean.AddEventBean;
import bean.ConfigBean;
import bean.UserAllBean;
import computer_room.adapter.AddEventAdapter;
import computer_room.i_interface.I_LogManagement;
import computer_room.present.LogManagementPresenter;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.DynamicMaterialEdit;
import util.ExcelUtil;
import util.LainNewApi;
import util.ToastManage;

/**
 * 日志管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/15 16 : 52
 */
public class SystemLogManagement extends Base_Devices_Detail implements I_LogManagement, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener,
        MenuItem.OnMenuItemClickListener {

    //日期选择器
    private DatePickerDialog datePickerDialog = null;
    //时间选择器
    private TimePickerDialog timePickerDialog = null;

    //Presenter
    private LogManagementPresenter presenter;

    //Menu 开始时间
    MenuItem item_1;
    //Menu 结束时间
    MenuItem item_2;
    //保存日志
    MenuItem saveLog;
    //选择的日期
    String timeStr;

    //保存选择的时间
    private ArrayList<String> timeList = new ArrayList<>();

    //添加事件
    private List<AddEventBean.SysLogBean.ListBean> addEventBeans;
    private AddEventAdapter addEventAdapter;
    //删除事件
    private List<AddEventBean.SysLogBean.ListBean> delEventBeans;
    private AddEventAdapter addEventAdapter2;
    //修改事件
    private List<AddEventBean.SysLogBean.ListBean> changeEventBeans;
    private AddEventAdapter addEventAdapter3;
    //修改事件
    private List<AddEventBean.SysLogBean.ListBean> queryEventBeans;
    private AddEventAdapter addEventAdapter4;
    //保存所有的日志数据
    private List<AddEventBean.SysLogBean.ListBean> allEventBeans = new ArrayList<>();

    //开始日期
    private TextView startDate;
    //结束日期
    private TextView endDate;
    //结束时间
    private TextView endtime;
    //开始时间
    private TextView startTime;
    //判断点击的是开始时间面板还是结束时间面板
    private String timeSelectPanle = "开始时间";
    //日志查询面板
    private AlertDialog timeSelectLog;

    //用户列表
    private List<String> userListData = new LinkedList<>();
    private NiceSpinner userListSpinner;
    //顶部导航栏Item
    private List<String> topNav = getTopItem();

    //记录当前选择的查询指定用户日志
    private String curUser = "";
    //当前ViewPager的位置
    private String curTab = null;

    //记录开始时间
    private String timeStartSelect = "2019-1-1 00:00:00";
    //记录结束时间
    private String timeEndSelect = "2020-10-1 00:00:00";;
    private UltimateRecyclerView addEventRecycler;

    protected boolean status_progress = false;

    private int curPage = 1;

    //添加操作查询
    List<AddEventBean> eventLog = new ArrayList<>();
    private UltimateRecyclerView addEventRecycler2;
    private UltimateRecyclerView addEventRecycler3;
    private UltimateRecyclerView addEventRecycler4;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化 Presenter
        presenter = new LogManagementPresenter(this);

        magicIndicator2.setVisibility(View.VISIBLE);

        //添加事件 - 假数据
        addEventRecycler = viewList.get(0).findViewById(R.id.add_event_recycler);
        addEventBeans = new ArrayList<>();
        addEventRecycler.setLoadMoreView(R.layout.custom_bottom_progressbar);

        addEventAdapter = new AddEventAdapter(getActivity(), addEventBeans, R.layout.system_log_temp);
        addEventAdapter.enableLoadMore(true);

        addEventRecycler.setOnLoadMoreListener(this);
//        addEventRecycler.setOnLoadMoreListener(((itemsCount, maxLastVisiblePosition) -> {
//
//            //未获取到日志数据 或者，直接返回
//            if (eventLog.isEmpty() || curPage == eventLog.get(0).getPages()) {
//                //加载完闭后，关闭加载更多的功能
//                addEventRecycler.disableLoadmore();
//                return;
//            }
//
//            //获取当前页数并加载下一页
//            curPage = eventLog.get(0).getSysLog().getPageNum() + 1;
//
//            presenter.timeSelectLog(timeStartSelect, timeEndSelect, curUser.equals("") ? null : curUser, curTab, curPage);


//            AddEventBean.SysLogBean.ListBean configBean = new AddEventBean.SysLogBean.ListBean();
//            configBean.setOperationName("admin");
//            configBean.setOperationType("添加操作");
//            configBean.setCreateDate("2020-8-7 17:8:7");
//            configBean.setIp("192.168.1.1");
//
//            addData(addEventAdapter, configBean);

//        }));

        addEventRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        addEventRecycler.setAdapter(addEventAdapter);

        //册除事件 - 假数据
        addEventRecycler2 = viewList.get(1).findViewById(R.id.add_event_recycler);
        addEventRecycler2.setLoadMoreView(R.layout.custom_bottom_progressbar);
        delEventBeans = new ArrayList<>();

        addEventAdapter2 = new AddEventAdapter(getActivity(), delEventBeans, R.layout.system_log_temp);
        addEventRecycler2.setOnLoadMoreListener(this);
        addEventRecycler2.setLayoutManager(new LinearLayoutManager(getActivity()));
        addEventRecycler2.setAdapter(addEventAdapter2);
        //修改事件 - 假数据
        addEventRecycler3 = viewList.get(2).findViewById(R.id.add_event_recycler);
        addEventRecycler3.setLoadMoreView(R.layout.custom_bottom_progressbar);
        changeEventBeans = new ArrayList<>();

        addEventAdapter3 = new AddEventAdapter(getActivity(), changeEventBeans, R.layout.system_log_temp);
        addEventRecycler3.setLayoutManager(new LinearLayoutManager(getActivity()));
        addEventRecycler3.setAdapter(addEventAdapter3);
        addEventRecycler3.setOnLoadMoreListener(this);
        //查询事件
        addEventRecycler4 = viewList.get(3).findViewById(R.id.add_event_recycler);
        addEventRecycler4.setLoadMoreView(R.layout.custom_bottom_progressbar);
        queryEventBeans = new ArrayList<>();

        addEventAdapter4 = new AddEventAdapter(getActivity(), queryEventBeans, R.layout.system_log_temp);
        addEventRecycler4.setOnLoadMoreListener(this);
        addEventRecycler4.setLayoutManager(new LinearLayoutManager(getActivity()));
        addEventRecycler4.setAdapter(addEventAdapter4);

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

        //日期初始化
        Calendar now = Calendar.getInstance();
        if (datePickerDialog == null) {

            datePickerDialog = DatePickerDialog.newInstance(SystemLogManagement.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH));

        } else {

            datePickerDialog.initialize(SystemLogManagement.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_WEEK));

        }
        //在Fragment中添加menu
        setHasOptionsMenu(true);
        //时间选择器的颜色
        datePickerDialog.setAccentColor(Lain_Application.getThemeColor());
        //时间选择器的样式
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);

        //开始请求数据，查询日志接口
//        presenter.timeSelectLogAll(timeStartSelect, timeEndSelect);

        //隐藏所有的顶部导航栏
        head_panel.setVisibility(View.GONE);

        //如果未获取到所有的用户，则进行获取
        if (userListData.isEmpty()) {
            getAllUser();
        }

    }

    /**
     * 日志加载更多功能
     * @param itemsCount ITEM的数量
     * @param maxLastVisiblePosition 可见的ITEM
     */
    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        Log.d("ljkjhkler", "loadMore: run");
        //未获取到日志数据 或者，直接返回
        if (eventLog.isEmpty() || curPage == eventLog.get(0).getPages()) {
            switch (curTab) {
                case "添加操作":
                    //加载完闭后，关闭加载更多的功能
                    addEventRecycler.disableLoadmore();
                    break;
                case "删除操作":
                    //加载完闭后，关闭加载更多的功能
                    addEventRecycler2.disableLoadmore();
                    break;
                case "修改操作":
                    //加载完闭后，关闭加载更多的功能
                    addEventRecycler3.disableLoadmore();
                    break;
                case "查询操作":
                    //加载完闭后，关闭加载更多的功能
                    addEventRecycler4.disableLoadmore();
                    break;
            }

            return;
        }

        Log.d("ljkjhkler", "loadMore: run");

        //获取当前页数并加载下一页
        curPage = eventLog.get(0).getSysLog().getPageNum() + 1;

        presenter.timeSelectLog(timeStartSelect, timeEndSelect, curUser.equals("") ? null : curUser, curTab, curPage);

    }

    private void addData(easyRegularAdapter adapter, AddEventBean.SysLogBean.ListBean configBean) {

        List< AddEventBean.SysLogBean.ListBean> sa = new ArrayList<>();
        sa.add(configBean);
        adapter.insert(sa);

    }
    //查询日志的结果
    @Override
    public void dealWitchReal(String requestTag, String requestUrl, String responseStr) {
        super.dealWitchReal(requestTag, requestUrl, responseStr);
    }

    //获取 ViewPager 的View
    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //实时数据

        //添加事件
        viewList.add(R.layout.add_event);
        //删除事件
        viewList.add(R.layout.add_event);
        //修改事件
        viewList.add(R.layout.add_event);
        //查询事件
        viewList.add(R.layout.add_event);

        return viewList;
    }




    /**
     * 设置所选择的日期
     *
     * @param view        日期选择器的View
     * @param year        选择的年份
     * @param monthOfYear 选择的月份
     * @param dayOfMonth  选择的日
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        timeStr = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
        //判断是开始日期还是结束日期
        if (timeSelectPanle.equals("开始时间"))
            startDate.setText(timeStr);
        else
            endDate.setText(timeStr);
        //显示对话框
        timePickerDialog.show(getFragmentManager(), "timedialog");

    }

    //时间回调
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String str = hourOfDay + ":" + minute;
        //判断是开始时间还是结束时间
        if (timeSelectPanle.equals("开始时间"))
            startTime.setText(str);
        else
            endtime.setText(str);

    }

    @Override
    public void run() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //加载Menu
        inflater.inflate(R.menu.log_query_time, menu);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //绑定 开始时间
        item_1 = menu.findItem(R.id.time_select);
        item_1.setOnMenuItemClickListener(this);
        //保存按钮
        saveLog = menu.findItem(R.id.save_log);
        saveLog.setOnMenuItemClickListener(this);

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.save_log) {//判断是否有查询到具体的数据

            if (timeStartSelect == null && timeEndSelect == null) {
                ToastManage.getInstance().toastShortShow("请先选择时间查询数据后再导出");
                return false;
            }

            //将 添加操作，删除操作，修改操作，查询操作，添加到总集体中
            presenter.timeSelectLogAll(timeStartSelect, timeEndSelect);

            return true;

        } else if (itemId == R.id.time_select) {//选择时间查询
            View timeSelectView = getLayoutInflater().inflate(R.layout.log_option_panel, null);

            //用户查询
            userListSpinner = timeSelectView.findViewById(R.id.user_list_spinner);
            //操作类型查询
            NiceSpinner optionListSpinner = timeSelectView.findViewById(R.id.option_list_spinner);

            List<String> optionListData = new LinkedList<>(Arrays.asList("添加事件", "删除事件", "修改事件", "查询事件"));
            userListSpinner.attachDataSource(userListData);
            optionListSpinner.attachDataSource(optionListData);

            userListSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                @Override
                public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                    if (!userListData.isEmpty())
                        curUser = userListData.get(position);
                }
            });

            optionListSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
                @Override
                public void onItemSelected(NiceSpinner parent, View view, int position, long id) {

                }
            });

            //查询面板
            timeSelectLog = new AlertDialog.Builder(getContext())
                    .setView(timeSelectView)
                    .show();
            //初始化时间查询的面板
            initTimeSelect(timeSelectView, timeSelectLog);
        }

        return true;
    }

    private void getAllUser() {

        //查询所有的用户
        OkHttpUtil.getInstance().sendGetOkHttp("user_all", LainNewApi.getInstance().getRootPath() + LainNewApi.userAll, new OkHttpCallBack() {
            @Override
            public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

                List<UserAllBean> userAllBeanList = null;
                try {
                    userAllBeanList = MyGson.getInstance().fromJson(responseStr, new TypeToken<List<UserAllBean>>() {
                    }.getType());
                } catch (JsonSyntaxException e) {
                    Log.d("getAllUser", "httpRequestSuccess: 获取用户失败");
                }

                //获取所有的用户名称
                for (UserAllBean userAllBean:
                        userAllBeanList) {
                    userListData.add(userAllBean.getUsername());
                }

                //所有用户获取成功后，默认请求第一个用户的添加操作日志
                magicSelected(0);
                //这句必须在最后，不然后面的代码无法执行
                userListSpinner.attachDataSource(userListData);

            }

            @Override
            public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

            }
        });

    }

    /**
     * 根据时间查询日志面板
     */
    private void initTimeSelect(View timeSelectView, AlertDialog timeSelectLog) {

        //开始时间面板
        LinearLayout startTimeLayout = timeSelectView.findViewById(R.id.start_time_ed);
        //结束时间面板
        LinearLayout endTimeLayout = timeSelectView.findViewById(R.id.end_time_ed);
        //开始日期
        startDate = timeSelectView.findViewById(R.id.start_sub_date);
        //结束日期
        endDate = timeSelectView.findViewById(R.id.end_sub_date);
        //结束时间
        endtime = timeSelectView.findViewById(R.id.end_sub_time);
        //开始时间
        startTime = timeSelectView.findViewById(R.id.start_sub_time);
        //确认按钮
        Button rightQuery = timeSelectView.findViewById(R.id.concern_tv);
        //取消按钮
        Button cancelQuery = timeSelectView.findViewById(R.id.back_play_cancel);
        //选择开始日期-开始时间
        startTimeLayout.setOnClickListener((v) -> {
            //保存点击的标识
            timeSelectPanle = "开始时间";
            datePickerDialog.show(getFragmentManager(), "dialog");
        });
        //选择结束日期-结束时间
        endTimeLayout.setOnClickListener((v) -> {
            timeSelectPanle = "结束时间";
            datePickerDialog.show(getFragmentManager(), "dialog");
        });
        //确认查询
        rightQuery.setOnClickListener((v) -> {

            //清空目前所有数据
            addEventBeans.clear();
            delEventBeans.clear();
            queryEventBeans.clear();
            changeEventBeans.clear();
            //清空之后再更新列表
            addEventAdapter.notifyDataSetChanged();
            addEventAdapter2.notifyDataSetChanged();
            addEventAdapter3.notifyDataSetChanged();
            addEventAdapter4.notifyDataSetChanged();

            //如果选择了开始和结束时间，则进行查询操作
            //必须加上 “：00”，不然无法查询
            timeStartSelect = startDate.getText().toString() + " " + startTime.getText().toString() + ":00";
            timeEndSelect = endDate.getText().toString() + " " + endtime.getText().toString() + ":00";
            //开始请求
            presenter.timeSelectLog(timeStartSelect, timeEndSelect, curUser, curTab, curPage);
        });
        //取消查询
        cancelQuery.setOnClickListener((v) -> {
            timeSelectLog.cancel();
        });

    }

    /**
     * 保存文件，导出 Excel
     *
     * @param saveName 文件名
     */
    private void saveLogExcel(String saveName) {

        //文件路径
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/lain_excel/";
        //文件是否存在
        File file = new File(filePath);
        if (!file.exists())
            file.mkdir();
        //excel 列
        String[] title = {"操作人员", "日志类型", "日志内容", "操作IP", "操作时间"};
        //保存文件的完整文件名
        String excelFileName = "/" + saveName + ".xls";
        //保存路径结果
        String resultPath = file.getAbsolutePath() + excelFileName;

        //保存文件
        ExcelUtil<AddEventBean.SysLogBean.ListBean> addEventBeanExcelUtil = new ExcelUtil<>();
        addEventBeanExcelUtil.initExcel(resultPath, title, (projectBean) -> {

            List<String> excelDataList = new ArrayList<>();

            excelDataList.add(projectBean.getUsername());
            excelDataList.add(projectBean.getCreateDate());
            excelDataList.add(projectBean.getOperationType());
            excelDataList.add(projectBean.getOperationName());
            excelDataList.add(projectBean.getIp());
            excelDataList.add(projectBean.getCreateDate());

            return excelDataList;
        });

        //导出功能
        File moduleFile = addEventBeanExcelUtil.writeObjectListToExcel(allEventBeans, resultPath, getActivity());

        //如果文件存在，代表保存成功
        if (moduleFile != null)
            ToastManage.getInstance().toastShortShow("保存成功！ 路径：" + filePath);

    }

    @Override
    public ArrayList<String> getTopItem() {
        ArrayList<String> topItem = new ArrayList<>();
        topItem.add("添加操作");
        topItem.add("删除操作");
        topItem.add("修改操作");
        topItem.add("查询操作");
        return topItem;
    }

    /**
     * 日志查询的结果
     *
     * @param addList    添加事件
     * @param delList    删除事件
     * @param changeList 修改事件
     * @param queryList  查询事件
     * @param allList    所有日志事件的集合
     */
    @Override
    public void setLogManagementList(List<AddEventBean.SysLogBean.ListBean> addList, List<AddEventBean.SysLogBean.ListBean> delList, List<AddEventBean.SysLogBean.ListBean> changeList, List<AddEventBean.SysLogBean.ListBean> queryList, List<AddEventBean.SysLogBean.ListBean> allList) {

        //保存当前查询的所有日志
        this.allEventBeans.clear();
        this.allEventBeans.addAll(allList);

        //添加事件列表更新
        if (addList.size() > 0) {
            addEventBeans.clear();
            addEventBeans.addAll(addList);
            addEventAdapter.notifyDataSetChanged();
        }

        //删除事件列表更新
        if (delList.size() > 0) {
            delEventBeans.clear();
            delEventBeans.addAll(delList);
            addEventAdapter2.notifyDataSetChanged();
        }

        //修改事件列表更新
        if (changeList.size() > 0) {
            changeEventBeans.clear();
            changeEventBeans.addAll(changeList);
            addEventAdapter3.notifyDataSetChanged();
        }

        //查询事件列表更新
        if (queryList.size() > 0) {
            queryEventBeans.clear();
            queryEventBeans.addAll(queryList);
            addEventAdapter4.notifyDataSetChanged();
        }

        //如果时间选择面板正在显示，查询成功后将其隐藏
        if (timeSelectLog.isShowing()) {
            ToastManage.getInstance().toastShortShow("查询日志成功");
            timeSelectLog.cancel();
        }

    }

    /**
     * 日志查询失败时回调
     * @param errorMsg
     */
    @Override
    public void httpFailed(String errorMsg) {
        getActivity().runOnUiThread(()->{
            ToastManage.getInstance().toastShortShow(errorMsg);
        });

    }

    @Override
    public void logExport(String responseStr) {

        Log.d("ljlioer", "logExport: " + responseStr);

        //解析所有的日志数据
        allEventBeans = MyGson.getInstance().fromJson(responseStr, new TypeToken<List<AddEventBean>>() {
        }.getType());

        //动态添加View时必须调用这句
        DynamicMaterialEdit.getInstance().setLayoutInflater(getLayoutInflater(), getContext());
        //如果有数据，则提示输入名称，文件名输入框
        MaterialEditText name = DynamicMaterialEdit.getInstance().setDynamicMaterialEdit(1, "请输入导出Excel文件名", "");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(30, 80, 30, 0);

        LinearLayout saveFileLayout = new LinearLayout(getActivity());

        //添加时倒序的，在操作编辑框时，一个要按照倒序来做，第一个则是在 editTextList 中的最后一个
        saveFileLayout.addView(name, 0, params);

        //用户输入文件名
        AlertDialog alert = new AlertDialog.Builder(getActivity())
                .setView(saveFileLayout)
                .setPositiveButton("确定", (dialog, which) -> {
                    //确定保存
                    saveLogExcel(name.getText().toString());

                })
                .setNegativeButton("取消", (dialog, which) -> {
                    //取消保存
                    dialog.cancel();
                })
                .show();

    }

    //重写ViewPager的监控
    @Override
    public void magicSelected(int position) {

        curTab = topNav.get(position);
        //每次滑动页面时，都要将获取的页数清空
        curPage = 1;

        Log.d("lkjlkdf", "magicSelected: ");
        //把对应的页面数据清空
        switch (curTab) {
            case "添加操作":
                addEventBeans.clear();
                //开启加载更多功能
                addEventAdapter.enableLoadMore(true);
                break;
            case "删除操作":
                delEventBeans.clear();
                //开启加载更多功能
                addEventAdapter2.enableLoadMore(true);
                break;
            case "修改操作":
                changeEventBeans.clear();
                //开启加载更多功能
                addEventAdapter3.enableLoadMore(true);
                break;
            case "查询操作":
                queryEventBeans.clear();
                //开启加载更多功能
                addEventAdapter4.enableLoadMore(true);
                break;
        }


        Map<String, String> optionMap = new HashMap<>();

        //如果选择了指定用户查询，则根据指定用户来查
        if (!curUser.isEmpty())
            optionMap.put("username", curUser);

        optionMap.put("operationType", curTab);

        optionMap.put("startTime", timeStartSelect);
        optionMap.put("endTime", timeEndSelect);
        optionMap.put("pageNum", String.valueOf(curPage));

        Log.d("kjler", "magicSelected: run");

        //请求的URL
        String url;

        if (startTime != null) {
            //如果按时间查询，则每次都通过选择的时间进行查询
            presenter.timeSelectLog(timeStartSelect, timeEndSelect, curUser, curTab, curPage);
        } else {
            //如果不按时间查询，则查询全部
            presenter.timeSelectLog(timeStartSelect, timeEndSelect, null, curTab, curPage);
        }

//
//
//        OkHttpUtil.getInstance().sendrow("log", url, optionMap, new OkHttpCallBack() {
//            @Override
//            public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
//
//                logDataParser(responseStr);
//
//            }
//
//            @Override
//            public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {
//
//            }
//        });

    }

    @Override
    public void logDataParser(String responseStr) {

        //选择查询时间后，滑动后，不再是以时间进行查询，而是查询全部了
        responseStr = "[" + responseStr + "]";


        List<AddEventBean.SysLogBean.ListBean> eventLogList = null;
        try {
            eventLog = MyGson.getInstance().fromJson(responseStr, new TypeToken<List<AddEventBean>>(){}.getType());
        } catch (JsonSyntaxException e) {
            Log.d(getClass().getName(), "logDataParser: 获取日志数据失败");
        }

        eventLogList = eventLog.get(0).getSysLog().getList();

        switch (curTab) {
            case "添加操作":

//                addEventBeans.clear();
                addEventBeans.addAll(eventLogList);
                addEventAdapter.notifyDataSetChanged();
                break;
            case "删除操作":

//                delEventBeans.clear();
                delEventBeans.addAll(eventLogList);
                addEventAdapter2.notifyDataSetChanged();
                break;
            case "修改操作":
//                changeEventBeans.clear();
                changeEventBeans.addAll(eventLogList);
                addEventAdapter3.notifyDataSetChanged();
                break;
            case "查询操作":
//                queryEventBeans.clear();
                queryEventBeans.addAll(eventLogList);
                addEventAdapter4.notifyDataSetChanged();
                break;
        }

        //显示的状态才会隐藏
        if (timeSelectLog.isShowing()) {
            ToastManage.getInstance().toastShortShow("查询日志成功");
            timeSelectLog.cancel();
        }


    }


    protected void enableLoadMore() {
        // StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(simpleRecyclerViewAdapter);
        // ultimateRecyclerView.addItemDecoration(headersDecor);
        addEventRecycler.setLoadMoreView(R.layout.custom_bottom_progressbar);

        addEventRecycler.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, final int maxLastVisiblePosition) {
                status_progress = true;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        onLoadmore();
                        status_progress = false;
                    }
                }, 500);
            }
        });

    }

    protected void onLoadmore() {

    }

}
