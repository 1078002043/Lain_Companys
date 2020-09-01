package util;

import android.widget.EditText;
import android.widget.TextView;

/**
 * ActivityUI 的工具类
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/31 10 : 52
 */
public class ActivityUtil {
    //单例实例
    private volatile static ActivityUtil activityUtil;

    /**
     * 双重检查 单例模式
     *
     * @return UI 的工具类 实例
     */
    public static ActivityUtil getInstance() {

        if (activityUtil == null)
            synchronized (ActivityUtil.class) {
                if (activityUtil == null) {
                    activityUtil = new ActivityUtil();
                    return activityUtil;
                }
            }

        return activityUtil;
    }

    /**
     * 获取 TextView 中的值，并转成String
     *
     * @param textView 需要转换的 TextView
     * @return 转换之后的 String 值
     */
    public String TextViewToString(TextView textView) {

        if (textView == null)
            throw new NullPointerException("转换的 TextView 是 NULL");

        return textView.getText().toString();

    }

    /**
     * 获取 EditText 中的值，并转成String
     *
     * @param editText 需要转换的 EditView
     * @return 转换之后的 String 值
     */
    public String EditTextToString(EditText editText) {

        if (editText == null)
            throw new NullPointerException("转换的 EditText 是 NULL");

        return editText.getText().toString();

    }

    /**
     * 判断EditText实例，内容 是否为空
     *
     * @param editText 需要检查的控件
     * @return 检查的结果
     */
    public boolean EditTextIsNull(EditText editText) {

        if (editText == null)
            return true;

        return editText.getText().toString().isEmpty();

    }

}
