package network;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;

import com.stealthcopter.networktools.SubnetDevices;
import com.stealthcopter.networktools.subnet.Device;

import java.util.ArrayList;
import java.util.List;

import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * @ClassName: NetWorkSubDevice
 * @Description: 获取局域网下所有的IP，并且确定后台的IP地址
 * @Author: YIN LUO FEI
 * @Date: 2020/7/8 14:06
 */
public class NetWorkSubDevice {

    private static NetWorkSubDevice subDevice;
    List<String> serverIPList = new ArrayList<>();

    public static NetWorkSubDevice getInstance() {

        if (subDevice == null)
            synchronized (NetWorkSubDevice.class) {
                if (subDevice == null)
                    subDevice = new NetWorkSubDevice();
            }

        return subDevice;
    }

    //封装获取局域网设备
    public void getWifiSubDevice(final OkHttpCallBack callBack, final Activity activity) {

        SubnetDevices.fromLocalAddress().findDevices(new SubnetDevices.OnSubnetDeviceFound() {
            @Override
            public void onDeviceFound(Device device) {

            }

            @Override
            public void onFinished(final ArrayList<Device> devicesFound) {

                //加入 prepare() 才能在子线程中创建 Handler

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //保存获取到的IP数量
                        LainNewApi.ipCount = devicesFound.size();

                        //获取所有 在线IP 完成后，通过发送请求来获取局域网内的后台服务器地址
                        getServerIPAddress(devicesFound, callBack);
                    }
                });


            }
        });

    }

    /**
     *
     * @param devicesFound 获取到的局域网下所有的IP服务器
     * @param callBack 请求的接口回调
     */
    private void getServerIPAddress(ArrayList<Device> devicesFound, OkHttpCallBack callBack) {

        //将获取到的所有IP进行一条空的请求
        for (Device d :
                devicesFound) {
            Log.d("lkjlpioer", "getServerIPAddress: " + d.ip);
            //如果请求成功，代表获取到了后台的IP地址
            requestServer(d.ip, callBack);

        }

    }

    // 请求后台，如果请求成功则返回当前的请求IP
    private void requestServer(final String ip, OkHttpCallBack callBack) {

        //请求的地址
        String url = "http://" + ip + ":"+ LainNewApi.getInstance().PORT + "/" + LainNewApi.getInstance().PATH + "/" + LainNewApi.serverIP;
        //发起请求
        OkHttpUtil.getInstance().sendGetOkHttp("server_ip", url, callBack);

    }


}
