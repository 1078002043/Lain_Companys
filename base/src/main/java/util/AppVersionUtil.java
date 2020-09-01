package util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * APP 更新工具类
 */
public class AppVersionUtil {
    //单例实例
    private static AppVersionUtil appVersionUtil;
    //版本号
    public long versionCode = 0;
    //版本名称
    public String versionName = "0";

    //单例
    public static AppVersionUtil getInstance() {
        if (appVersionUtil == null)
            synchronized (AppVersionUtil.class) {
                if (appVersionUtil == null)
                    appVersionUtil = new AppVersionUtil();
                return appVersionUtil;
            }
        return appVersionUtil;
    }

    /**
     * 获取VersionName
     *
     * @param context 上下文
     * @return 版本名称
     */
    @SuppressLint("WrongConstant")
    public String getVersionName(Context context) {

        try {
            versionName = context.getPackageManager().getPackageInfo("cn.com.lain", 1).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * 获取VersionCode
     *
     * @param context 使用上下文来获取版本号
     * @return 版本号
     */
    @SuppressLint("WrongConstant")
    public long getVersionCode(Context context) {
        try {
            versionCode = context.getPackageManager().getPackageInfo("cn.com.lain", 1).getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

}
