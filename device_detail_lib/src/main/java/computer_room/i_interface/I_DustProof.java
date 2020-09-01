package computer_room.i_interface;

import java.util.List;

import computer_room.bean.DustProofBean;

/**
 * 粉尘 View Interface
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/17 14 : 59
 */
public interface I_DustProof {
    /**
     * 粉尘列表
     *
     * @param beans 数据列表
     */
//    void setDustProofList(List<DustProofBean> beans);
//
//    void devicesAlert(List<DustProofAlertBean> dataList);

    /**
     * 设备管理
     *
     * @param dataList 设备管理请求的列表数据
     */
    void devicesManage(List<DustProofBean> dataList);
}
