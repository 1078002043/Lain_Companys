package computer_room.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.List;

import base.Base_Devices_Detail;
import bean.Alert8052Bean;
import computer_room.bean.Device8052Bean;
import computer_room.i_interface.I_Base8052Fragment;
import computer_room.present.Base8052Present;
import util.LainNewApi;

public abstract class Base8052Fragment<R, A> extends Base_Devices_Detail implements I_Base8052Fragment<R, A> {

    //8052的实时数据请求链接
    private String linkReal8052 = "";
    private String linkAlert8052 = "";
    //保存实时数据
    private List<R> realList;
    //保存报警数据
    private List<A> alertList;

    public Base8052Present<R,A> base8052Present;

    public Base8052Fragment() {
    }

    public Base8052Fragment(String linkReal8052, String linkAlert8052) {
        this.linkReal8052 = linkReal8052;
        this.linkAlert8052 = linkAlert8052;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        base8052Present = new Base8052Present<>(this);

    }

    /**
     * 是否开启自动更新
     * @param isStart
     */
    public void isStartAutoUp(boolean isStart) {
        if (isStart)
            //实时更新
            infoHandler.postDelayed(this, LainNewApi.SECOND);
    }

    /**
     * 自动更新逻辑由子类来实现
     */
    @Override
    public void run() {

    }

    /**
     * 处理报警数据
     * @param alert8052Beans
     */
    @Override
    public void dealAlertData8052(List<Alert8052Bean> alert8052Beans) {

    }

    /**
     * 处理实时数据
     * @param device8052Bean
     */
    @Override
    public void dealRealData8052(List<Device8052Bean> device8052Bean) {



    }

    public <E> void deal8052Real(List<E> device8052Bean) {
        this.realList = (List<R>) device8052Bean;
        //数据保存后，将数据传回子类
        responseReal8052();
    }

    public <E> void deal8052Alert(List<E> alert8052Bean) {
        this.alertList = (List<A>) alert8052Bean;
        responseAlert8052();
    }

    /**
     * 请求报警数据完成后，回调该方法，由子类实现
     */
    protected abstract void responseAlert8052();

    /**
     * 数据请求成功后，会回调到该方法
     */
    protected abstract void responseReal8052();

    /**
     * 获取实时数据
     * @return
     */
    public List<R> getRealList() {
        return realList;
    }

    /**
     * 获取报警数据
     * @return
     */
    public List<A> getAlertList() {
        return alertList;
    }

    @Override
    public void querySelectDeviceAlert(int ehmID, String startTime, String endTime) {

    }

    @Override
    public void queryAlertData() {


    }

    /**
     * 获取实时数据
     * @return
     */
    @Override
    public String getLinkReal() {
        return linkReal8052;
    }

    /**
     * 获取报警数据
     * @return
     */
    @Override
    public String getLinkAlert() {
        return linkAlert8052;
    }

    @Override
    public void loadMore(int itemsCount, int maxLastVisiblePosition) {
        Log.d("ljler", "loadMore: 8052 more");
    }
}
