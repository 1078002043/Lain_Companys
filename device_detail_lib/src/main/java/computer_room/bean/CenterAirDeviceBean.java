package computer_room.bean;

import java.io.Serializable;

/**
 * @ClassName: CenterAirDeviceBean
 * @Description: 中央空调设备Bean
 * @Author: YIN LUO FEI
 * @Date: 2020/8/26 1:57
 */
public class CenterAirDeviceBean implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
