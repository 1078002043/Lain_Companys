package bean;

/**
 * @ClassName: MenuConfigBean
 * @Description: 设备列表显示和隐藏的BEAN
 * @Author: YIN LUO FEI
 * @Date: 2020/8/3 13:50
 */
public class MenuConfigBean {


    /**
     * name : 温湿度
     * isShow : 1
     * deviceIp : null
     * did : 1
     */

    private String name;
    private int isShow;
    private Object deviceIp;
    private int did;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public Object getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(Object deviceIp) {
        this.deviceIp = deviceIp;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }
}
