package bean;

import base.BaseBean;

/**
 * 设备报警 Bean
 */
public class AlertDeviceBean extends BaseBean {
    //报警设备名称
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
