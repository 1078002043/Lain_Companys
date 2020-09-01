package fragment;

import java.util.ArrayList;
import java.util.List;

import bean.DeviceBean;


/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/10 16:20
 * Description：视频监控图片抓取完成后的回调接口
 **/
public interface I_CaptureImg {
    /**
     * 图片抓取完成回调
     * @param videoDeviceList 抓取的数据
     */
    public void captureComplete(ArrayList<DeviceBean> videoDeviceList);

}
