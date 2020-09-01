package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/14 9:22
 * Description：WIFI 管理器
 **/
public class MyWiFiManage extends ConnectivityManager.NetworkCallback {
    //断开中
    public static final int WIFI_AP_STATE_DISABLING = 10;
    //已断开
    public static final int WIFI_AP_STATE_DISABLED = 11;
    //开启中
    public static final int WIFI_AP_STATE_ENABLING = 12;
    //已连接
    public static final int WIFI_AP_STATE_ENABLED = 13;
    //失败状态
    public static final int WIFI_AP_STATE_FAILED = 14;
    //LOG TAG
    private static final String WIFI_TAG = "WIFI_MANAGE";
    //系统保存当前连接WIFI信息的文件路径
    private static String ARP_PATH = "/proc/net/arp";
    //WIFI 的单例
    private static MyWiFiManage myWiFiManage;
    //WIFI管理器
    private WifiManager wifiManager;
    //WIFI连接结果回调接口
    private WiFiConnect wiFiConnect;

    //单例
    public static MyWiFiManage getInstance() {
        if (myWiFiManage == null)
            synchronized (MyWiFiManage.class) {
                if (myWiFiManage == null) {
                    myWiFiManage = new MyWiFiManage();
                }
            }
        return myWiFiManage;
    }

    /**
     * 获取 WIFI 的状态
     *
     * @param mContext 获取 WIFI 状态需要的 上下文
     * @return 返回WIFI的当前状态
     */
    public int getWifiApState(Context mContext) {


        try {
            //通过反射获取WIFI的状态
            Method method = wifiManager.getClass().getMethod("getWifiApState");
            //通过返回得到状态的 getWifiApState() 返回的数据
            int i = (Integer) method.invoke(wifiManager);
            //打印WIFI的状态
            Log.i(WIFI_TAG, "wifi state: " + i);
            //返回WIFI 状态
            return i;
        } catch (Exception e) {
            Log.e(WIFI_TAG, "Cannot get WiFi AP state" + e);
            return WIFI_AP_STATE_FAILED;
        }
    }

    /**
     * 判断 WIFI 是否可用
     *
     * @param mContext 判断时需要使用到的 上下文
     * @return WIFI 是否可用的状态
     */
    public boolean isApEnabled(Context mContext) {
        int state = getWifiApState(mContext);
        //如果 WIFI 可用，直接返回 可用状态，否则直接将 当前状态返回
        return WIFI_AP_STATE_ENABLING == state || WIFI_AP_STATE_ENABLED == state;
    }

    /**
     * 获取保存WIFI信息文件中的内容
     *
     * @return 返回 IP 内容
     */
    private String getConnectedHostIP() {
        //保存IP
//        ArrayList<String> connectIP = new ArrayList<>();

        //如果 WIFI Manage 未初始化，不进行获取操作，默认返回 192.168.1.1
        if (wifiManager == null)
            return "192.168.1.1";

        //获取当前连接的WIFI信息
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        //获取当前的IP，需要进行转码
        int currentIP = wifiInfo.getIpAddress();

        //对获取到的IP进行转码
        return (currentIP & 0xff) + "." + (currentIP >> 8 & 0xff) + "." + (currentIP >> 16 & 0xff) + "." + (currentIP >> 24 & 0xff);
    }

    /**
     * 得到 解析后 的IP
     */
    public String getWIFI_HostIp(Context mContext) {

        if (wifiManager == null)
            //获取 WIFI管理器实例
            wifiManager = getWiFiManage(mContext);

        // 取出 IP
        return getConnectedHostIP();

    }

    /**
     * 获取 WIFI Manage 实例
     *
     * @return WIFI 管理器实例
     */
    public WifiManager getWiFiManage(Context context) {
        //如果 未获取 WIFI管理器，则先获取再返回
        if (wifiManager == null)
            wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        return wifiManager;
    }


    /**
     * 网络连接时调用
     *
     * @param network
     */
    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        Log.d(WIFI_TAG, "onAvailable: avalilable");
    }

    /**
     * 网络功能更改但任满足需求时调用
     *
     * @param network
     * @param maxMsToLive
     */
    @Override
    public void onLosing(@NonNull Network network, int maxMsToLive) {
        super.onLosing(network, maxMsToLive);
        Log.d(WIFI_TAG, "onLosing: onLosing");
    }

    /**
     * 网络连接断开时回调
     *
     * @param network
     */
    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        Log.d(WIFI_TAG, "onAvailable: onLost");
        ToastManage.getInstance().toastLongShow("莱安：网络断开");
    }

    /**
     * 网络即将断开时调用
     */
    @Override
    public void onUnavailable() {
        super.onUnavailable();
        Log.d(WIFI_TAG, "onAvailable: onUnavailable");
    }

    /**
     * 网络状态改变时会调用，可能会执行多次，一般在WIFI连接上时会回调
     *
     * @param network
     * @param networkCapabilities
     */
    @Override
    public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);

        // 表明此网络连接成功验证
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)) {

            // 使用WI-FI
            if (wiFiConnect != null)
                wiFiConnect.wifiIsConnect(true);
//            ToastManage.getInstance().toastLongShow("WIFI 已连接");
        /*if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {

            } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ) {
                // 使用蜂窝网络

            } else{
                // 未知网络，包括蓝牙、VPN、LoWPAN

            }*/
        } else {
            if (wiFiConnect != null)
                wiFiConnect.wifiIsConnect(false);
//            ToastManage.getInstance().toastLongShow("WIFI 未连接");
        }
    }

    /**
     * 网络缺失network时调用
     *
     * @param network
     * @param linkProperties
     */
    @Override
    public void onLinkPropertiesChanged(@NonNull Network network, @NonNull LinkProperties linkProperties) {
        super.onLinkPropertiesChanged(network, linkProperties);
        Log.d(WIFI_TAG, "onAvailable: onLinkPropertiesChanged");
    }

    @Override
    public void onBlockedStatusChanged(@NonNull Network network, boolean blocked) {
        super.onBlockedStatusChanged(network, blocked);
        Log.d(WIFI_TAG, "onAvailable: onBlockedStatusChanged");
    }

    /**
     * WIFI连接状态接口
     */
    public interface WiFiConnect {
        /**
         * WIFI 是否连接
         *
         * @param isConnect WIFI 连接的结果
         */
        void wifiIsConnect(boolean isConnect);
    }

    /**
     * 设置 WIFI 状态的回调接口
     *
     * @param wiFiConnect WIFI 回调接口实例
     */
    public void setWiFiConnect(WiFiConnect wiFiConnect) {
        this.wiFiConnect = wiFiConnect;
    }

    /**
     * 保存用户输入的网络配置
     *
     * @param ip     服务器IP
     * @param domain 域名
     * @param port   端口
     */
    public void saveAPPNetwork(String ip, String domain, String port, SharedPreferences preferences) {

        //保存已运行过的配置
        SharedPreferences.Editor edit = preferences.edit();
        //每次保存时，如果不设置 IP ,则使用自动获取的 IP
        edit.putString("ip", ip.isEmpty() ? LainNewApi.getInstance().AutoIp : ip);
        edit.putString("doMain", domain);
        edit.putString("port", domain.isEmpty() ? "8080" : port);
        edit.apply();

        ToastManage.getInstance().toastLongShow("保存成功");

        //更新API接口文件的请求链接
        LainNewApi.getInstance().PORT = port;

        //如果 域名不为空，则优先使用 域名 请求
        if (!domain.isEmpty())
            //设置IP，必须设置完整的链接
            LainNewApi.getInstance().IP = domain;
        else if (!ip.isEmpty())
            //设置域名，必须设置完整的链接
            LainNewApi.getInstance().IP = ip;

    }

    /**
     * 获取用户输入的网络配置
     *
     * @param preferences 获取网络配置的实例
     * @return 将 端口，IP，域名 组成 MAP 返回
     */
    public Map<String, String> getAPPNetwork(SharedPreferences preferences) {

        //保存已运行过的配置
        String ip = preferences.getString("ip", "");
        String doMain = preferences.getString("doMain", "");
        String port = preferences.getString("port", "");

        Map<String, String> mapNetwork = new HashMap<>();
        //端口
        mapNetwork.put("port", doMain.isEmpty() ? "8080" : port);
        //域名
        mapNetwork.put("doMain", doMain);
        //IP
        mapNetwork.put("ip", ip);

        //如果 域名不为空，则优先使用 域名 请求
        if (!doMain.isEmpty()) {

            //设置IP，必须设置完整的链接
            LainNewApi.getInstance().IP = mapNetwork.get("doMain");

        } else if (!ip.isEmpty())
            //设置域名，必须设置完整的链接
            LainNewApi.getInstance().IP = mapNetwork.get("ip");

        //更新API接口文件的请求链接
        LainNewApi.getInstance().PORT = mapNetwork.get("port");

        //返回结果
        return mapNetwork;

    }

}
