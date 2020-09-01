package computer_room.bean;

/**
 * 所有的设备
 */
public class DeviceAllBean {


    /**
     * dId : 1
     * name : 温湿度
     * isShow : 1
     */

    private int did;
    private String name;
    private int isShow;

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

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

}
