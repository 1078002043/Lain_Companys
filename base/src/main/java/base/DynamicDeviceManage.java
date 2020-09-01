package base;

import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @ClassName: DynamicDeviceManage
 * @Description: 动态添加设备管理中的修改选项
 * @Author: YIN LUO FEI
 * @Date: 2020/4/3 9:46
 */
public class DynamicDeviceManage {

    public static DynamicDeviceManage deviceManage;

    public static DynamicDeviceManage getInstance() {

        if (deviceManage == null)
            synchronized (DynamicDeviceManage.class) {
                if (deviceManage == null)
                    deviceManage = new DynamicDeviceManage();
            }

        return deviceManage;
    }

    /**
     * 动态添加设备管理中的设备信息选项
     * @param textViewList TextView数据集合
     * @param linearLayout 动态添加View的容器
     */
    public void addManageItem(List<TextView> textViewList, LinearLayout linearLayout) {

        //如果修改，添加设备时，如果不将所有之前添加的View移除，那么就会一直存在，导致数据重复
        linearLayout.removeAllViews();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 0);
        //遍历动态添加
        for (TextView textView :
                textViewList) {
            linearLayout.addView(textView, layoutParams);
        }

    }


}
