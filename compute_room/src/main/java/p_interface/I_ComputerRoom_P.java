package p_interface;

import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * 主页中 机房监控内的设备 Presenter Interface
 *
 * @author Create by Yin Luo Fei
 * @version ${NUM}
 * @Time 2019/8/4 22 : 29
 */
public interface I_ComputerRoom_P {

    //初始化ViewPager中的View
    List<View> getViewPager(LayoutInflater inflater);

}
