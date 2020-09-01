package computer_room.present;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BasePresenter;
import bean.AddEventBean;
import computer_room.i_interface.I_LogManagement;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * 日志管理
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/17 16 : 22
 */
public class LogManagementPresenter extends BasePresenter {

    private I_LogManagement i_logManagement;

    public LogManagementPresenter(I_LogManagement i_logManagement) {
        this.i_logManagement = i_logManagement;
    }

    /**
     * 查询所有日志
     */
    public void findAllLog(String startTime, String endTime) {

        String logUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.selectSysLog + "?" + "startTime=" + startTime + "&" + "endTime=" + endTime;
        OkHttpUtil.getInstance().sendGetOkHttp("findAllLog", logUrl, this);

    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        switch (requestTag) {
            //查询所有
            case "findAllLog":
                //查询所有日志数据
                i_logManagement.logExport(responseStr);
            case "time_select":
                logSelectSuccess(responseStr);
                break;
        }
    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {
        i_logManagement.httpFailed(errorMsg);
    }

    private void logSelectSuccess(String responseStr) {
        //更新View
        i_logManagement.logDataParser(responseStr);
    }

    /**
     * 根据时间查询日志
     *

     * @param startTime 查询的开始时间
     * @param endTime   查询的结束时间
     */
    public void timeSelectLog(String startTime, String endTime, String curUser, String curTab, int pageNum) {

        Map<String, String> optionMap = new HashMap<>();
        if (curUser != null)
            optionMap.put("username", curUser);
        optionMap.put("operationType", curTab);
        optionMap.put("startTime", startTime);
        optionMap.put("endTime", endTime);

        optionMap.put("pageNum", String.valueOf(pageNum));

        String logUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.selectSysLog;

        Log.d("lkjler", "timeSelectLog: " + OkHttpUtil.getInstance().mapToRow(optionMap));

        OkHttpUtil.getInstance().sendRowOkHttp("time_select", logUrl, OkHttpUtil.getInstance().mapToRow(optionMap), this);

    }

    /**
     * 根据时间查询日志
     *
     * @param timeStartSelect
     * @param startTime 查询的开始时间
     * @param endTime   查询的结束时间
     */
    public void timeSelectLogAll(String startTime, String endTime) {

        Map<String, String> optionMap = new HashMap<>();
        optionMap.put("startTime", startTime);
        optionMap.put("endTime", endTime);

        String logUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.selectSysLog;

        OkHttpUtil.getInstance().sendRowOkHttp("findAllLog", logUrl, OkHttpUtil.getInstance().mapToRow(optionMap), this);

    }

}
