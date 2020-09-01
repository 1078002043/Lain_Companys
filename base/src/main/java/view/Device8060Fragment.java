package view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import base.Base_Devices_Detail;
import view_interface.I_Device8060;

/**
 * 普通设备的封装类
 *
 * @param <T> 实时数据
 * @param <A> 报警数据
 * @param <H> 历史数据
 * @param <M> 设备管理
 */
public abstract class Device8060Fragment<T, A, H, M> extends Base_Devices_Detail implements I_Device8060<T> {

    //实时数据请求链接
    private String realLink;
    //报警数据请求链接
    private String alertLink;
    //历史数据请求链接
    private String historyLink;
    //设备管理请求链接
    private String manageLink;
    //present
    public Device8060Present<T, A, H, M> device8060Present;
    //数据解析类
    private Class<T> parseClass;
    //当前请求到的实时数据
    public List<T> realData;
    //当前请求到的报警数据
    private List<A> alertData;
    //当前请求到的历史数据
    private List<H> historyData;
    //当前请求到的设备管理数据
    private List<M> manageData;
    public static final String REAL_DATA = "real_data";
    public static final String ALERT_DATA = "alert_data";
    public static final String HISTORY_DATA = "history_data";
    public static final String MANAGE_DATA = "manage_data";

    /**
     * @param realLink    实时数据请求链接
     * @param alertLink   报警数据请求链接
     * @param historyLink 历史数据请求链接
     * @param manageLink  设备管理数据请求链接
     */
    public Device8060Fragment(String realLink, String alertLink, String historyLink, String manageLink) {
        this.realLink = realLink;
        this.alertLink = alertLink;
        this.historyLink = historyLink;
        this.manageLink = manageLink;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化Present
        device8060Present = new Device8060Present<>(this);

    }

    /**
     * 开启实时查询 实时数据
     */
    public void startRealUpdate() {
        //定时更新
        infoHandler.postDelayed(this, 3000);
    }

    /**
     * 设置设备详情中显示那些Nav
     *
     * @param real    实时
     * @param alert   报警
     * @param history 历史
     * @param manage  管理
     */
    public void setDeviceNav(boolean real, boolean alert, boolean history, boolean manage) {
        setToNavShow(real, alert, history, manage);
    }

    /**
     * ViewPager 的 View，由子类来实现
     *
     * @return
     */
    @Override
    public List<Integer> getViewPagerView() {
        return null;
    }

    /**
     * 实时更新 实时数据
     */
    @Override
    public void run() {
        device8060Present.queryRealData(getParseClass());
        infoHandler.postDelayed(this, 3000);
    }

    //获取实时数据链接
    @Override
    public String getRealLink() {
        return realLink;
    }

    //获取报警数据链接
    @Override
    public String getAlertLink() {
        return alertLink;
    }

    //获取历史数据链接
    @Override
    public String getHistoryLink() {
        return historyLink;
    }

    //获取设备管理数据链接
    @Override
    public String getManageLink() {
        return manageLink;
    }

    /**
     * 请求数据完成后，会回调到这个方法，并将数据保存，执行 requestReal(), 子类重写 requestReal()
     * 就能拿到实时数据
     *
     * @param listClass 解析到的数据
     */
    @Override
    public synchronized <E> void jsonParseResponse(List<E> listClass) {
        //需要强制转换才能保存
        this.realData = (List<T>) listClass;
        //传递数据给子类
        requestReal();
    }


    /**
     * 解析报警数据
     *
     * @param alertList 获取到的报警数据
     * @param <E>
     */
    @Override
    public <E> void jsonParseAlert(List<E> alertList) {
        try {

            this.alertData = (List<A>) alertList;
            requestAlert();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析历史数据
     *
     * @param historyList 获取到历史数据
     * @param <E>
     */
    @Override
    public <E> void jsonParseHistory(List<E> historyList) {
        try {
            this.historyData = (List<H>) historyList;
            requestHistory();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析设备管理数据
     *
     * @param manageList 获取到的设备管理数据
     * @param <E>
     */
    @Override
    public <E> void jsonParseManage(List<E> manageList) {
        try {
            this.manageData = (List<M>) manageList;
            requestManage();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * 请求的实时数据回调，由子类来重写
     */
    public abstract void requestReal();

    /**
     * 请求的报警数据回调，由子类来重写
     */
    public abstract void requestAlert();

    /**
     * 请求的历史数据回调，由子类来重写
     */
    public abstract void requestHistory();

    /**
     * 请求的设备管理数据回调，由子类来重写
     */
    public abstract void requestManage();

    /**
     * 获取请求数据解析的类型
     *
     * @return
     */
    @Override
    public Class<T> getParseClass() {

        return parseClass;

    }

    /**
     * 返回获取到的实时数据
     *
     * @return 实时数据
     */
    public List<T> getRealData() {
        return realData;
    }

    /**
     * 返回获取到的报警数据
     *
     * @return 报警数据
     */
    public List<A> getAlertData() {
        return alertData;
    }

    /**
     * 返回获取到的历史数据
     *
     * @return 报警数据
     */
    public List<H> getHistoryData() {
        return historyData;
    }

    /**
     * 返回获取到的设备管理数据
     *
     * @return 报警数据
     */
    public List<M> getManageData() {
        return manageData;
    }

    /**
     * 设置请求数据的类型
     *
     * @param parseClass
     */
    public void setParseClass(Class<T> parseClass) {
        this.parseClass = parseClass;
    }


}
