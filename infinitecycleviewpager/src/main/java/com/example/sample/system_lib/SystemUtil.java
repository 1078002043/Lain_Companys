package com.example.sample.system_lib;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统的工具类
 * 保存各系统的
 */
public class SystemUtil {

    public static List<SystemInfoBean> systemInfoBeanList = new ArrayList<>();

    private static SystemUtil systemUtil = null;

    public static SystemUtil getInstance() {
        if (systemUtil == null)
            synchronized (SystemUtil.class) {
                if (systemUtil == null) {
                    systemUtil = new SystemUtil();
                    //初始化系统列表
                    setSystemInfoBeanList();
                }
            }
        return systemUtil;
    }

    /**
     * 获取系统信息
     * @return
     */
    public List<SystemInfoBean> getSystemInfo() {
        return systemInfoBeanList;
    }

    /**
     * 设置系统信息
     */
    private static void setSystemInfoBeanList() {
        systemInfoBeanList = SystemApi.getSystemList();
    }
}
