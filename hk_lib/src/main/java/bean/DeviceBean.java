package bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 设备监控账号Bean 主要用户保存通道和抓取的视频监控封面图片Bitmap
 * Created by Administrator on 2017/6/21.
 */

public class DeviceBean {

    public String strIP;  //Ip
    public int nPort;     //ip端口号
    public String strUser;  //登录名
    public String strPsd;   //密码

    //通道号
    public int channel;
    //保存视频监控的封面图片
    public Bitmap bitmap;

    public DeviceBean() {

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public DeviceBean(int position) {
        channel = position;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getStrIP() {
        return strIP;
    }

    public void setStrIP(String strIP) {
        this.strIP = strIP;
    }

    public int getnPort() {
        return nPort;
    }

    public void setnPort(int nPort) {
        this.nPort = nPort;
    }

    public String getStrUser() {
        return strUser;
    }

    public void setStrUser(String strUser) {
        this.strUser = strUser;
    }

    public String getStrPsd() {
        return strPsd;
    }

    public void setStrPsd(String strPsd) {
        this.strPsd = strPsd;
    }

}
