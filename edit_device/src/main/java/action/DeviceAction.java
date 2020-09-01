package action;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Map;

/**
 * @ClassName: DeviceAction
 * @Description: 添加-编辑设备时的 确定，取消，修改按钮的回调，只要是给调用者来实现
 * @Author: YIN LUO FEI
 * @Date: 2020/4/20 17:33
 */
public interface DeviceAction {

    /**
     * 添加设备时的回调
     * 点击确定按钮后回调该方法
     * @param keyArray
     * @param spinnerMap
     */
    public void deterMine(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap);

    /**
     * 修改设备时的回调
     * 点击确定按钮后回调该方法
     * @param keyArray
     * @param spinnerMap
     */
    public void deterChange(Map<String, EditText> keyArray, Map<String, Spinner> spinnerMap);

    /**
     * 点击取消后回调该方法
     */
    public void cancelMine();

}
