package present;

import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import p_interface.I_ComputerRoom_P;
import v_interface.I_ComputerRoom;

/**
 * 主页中 机房监控内设备 Presenter
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/4 22 : 30
 */
public class ComputerRoom_Presenter implements I_ComputerRoom_P {
    //机房监控的 View
    private I_ComputerRoom computerRoom;


    /**
     * 初始化 机房监控 的出视图实例
     *
     * @param computerRoom ComputerRoom 视图引用
     */
    public ComputerRoom_Presenter(I_ComputerRoom computerRoom) {
        this.computerRoom = computerRoom;

    }

    @Override
    public List<View> getViewPager(LayoutInflater inflater) {
        return null;
    }

//    /**
//     * 初始化 ViewPager
//     *
//     * @param inflater 加载Layout的引用
//     * @return ViewPager View 的集合
//     */
//    public List<View> getViewPager(LayoutInflater inflater) {
//        //保存ViewPager View的集合
//        List<View> viewList = new ArrayList<>();
//        //首页
//        viewList.add(inflater.inflate(R.layout.main_viewpager, null));
//        //环境监控
//        viewList.add(inflater.inflate(R.layout.smart_device_detail, null));
//        //动力监控
//        viewList.add(inflater.inflate(R.layout.power_monitoring, null));
//        //安防监控
//        viewList.add(inflater.inflate(R.layout.sercurity_monitoring, null));
//        //运维监控
//        viewList.add(inflater.inflate(R.layout.operations_monitoring, null));
//        //系统管理
//        viewList.add(inflater.inflate(R.layout.system_management, null));
//        //水质系统
//        viewList.add(inflater.inflate(R.layout.water_quality, null));
//        //楼宇自控
//        viewList.add(inflater.inflate(R.layout.building_control, null));
//        //返回View集合
//        return viewList;
//    }

}
