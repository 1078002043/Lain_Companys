package view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import base.Base_Devices_Detail;
import bean.Alert8052Bean;
import bean.Device8052Bean;
import util.LainNewApi;
import view_interface.I_Base8052Fragment;

/**
 * 8052 所有设备的封装
 * @param <R> 实时数据
 * @param <A> 报警数据
 */
public abstract class Base8052Fragment<R, A> extends Base_Devices_Detail implements I_Base8052Fragment<R, A> {

    //8052的实时数据请求链接
    private String linkReal8052 = "";
    private String linkAlert8052 = "";
    //保存实时数据
    private List<R> realList;
    //保存报警数据
    private List<A> alertList;

    //8052 的Present
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

        //初始化Present
        base8052Present = new Base8052Present<>(this);

    }

    /**
     * 是否开启自动更新
     * @param isStart 是否自动更新
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
     * 处理 8052 实时数据
     * @param device8052Bean 接收到的8052设备数据
     * @param <E> 设备的解析类
     */
    public <E> void deal8052Real(List<E> device8052Bean) {
        this.realList = (List<R>) device8052Bean;
        //数据保存后，将数据传回子类
        responseReal8052();
    }

    /**
     * 处理8052的报警数据
     * @param alert8052Bean 接收8052报警数据
     * @param <E> 8052的报警数据解析类
     */
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


}
