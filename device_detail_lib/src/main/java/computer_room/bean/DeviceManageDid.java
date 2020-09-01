package computer_room.bean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/4 11:04
 * Description：添加设备时的Did
 **/
public class DeviceManageDid {

    /**
     * dId : 1
     * name : 温湿度
     * isShow : 1
     */

    private int dId;
    private String name;
    private int isShow;

    public int getDId() {
        return dId;
    }

    public void setDId(int dId) {
        this.dId = dId;
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
