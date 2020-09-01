package util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import bean.ConfigBean;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;

/**
 * 读取配置文件的工具类
 */
public class MyPropertiesUtil {

    private static Properties urlProps;
    private static MyPropertiesUtil myPropertiesUtil;

    public static MyPropertiesUtil getInstance() {
        if (myPropertiesUtil == null)
            synchronized (MyPropertiesUtil.class) {
                if (myPropertiesUtil == null)
                    myPropertiesUtil = new MyPropertiesUtil();
            }

        return myPropertiesUtil;
    }

    public List<ConfigBean> getProperties(Context context, String configName, String key) {
        Properties properties = null;
        try {
            properties = new Properties();
            InputStream in = context.getAssets().open(configName);
            //不要直接 load，不然读取中文时会乱码
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            properties.load(bufferedReader);
            //如果读取成功，直接返回
            return getPropertiesList(properties, key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        urlProps = properties;
        //读取失败，返回一个空集合
        return new ArrayList<>();
    }

    /**
     * 获取配置中的 List 集合
     * @param properties
     * @return
     */
    private List<ConfigBean> getPropertiesList(Properties properties, String key) {

        if (properties == null)
            throw new NullPointerException("properties is null");

        //此处的空格不能删
        String environment = properties.get(key).toString();

        List<ConfigBean> configBeanList = OkHttpUtil.getInstance().formatResponse(environment, ConfigBean.class);

        return configBeanList;
    }

}
