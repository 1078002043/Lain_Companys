package computer_room.bean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/19 22:02
 * Description：设备管理-正在运行
 **/
public class DeviceRunningBean {


    /**
     * dId : 1
     * name : 温湿度
     * isShow : 1
     * diId : 28
     * diAddress : 192.168.1.20
     * diPort : 6001
     * diIsConnect : 0
     * diOperate : 0
     * gId : 3
     */

    private int dId;
    private String name;
    private int isShow;
    private int diId;
    private String diAddress;
    private int diPort;
    private int diIsConnect;
    private int diOperate;
    private int gId;

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

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public String getDiAddress() {
        return diAddress;
    }

    public void setDiAddress(String diAddress) {
        this.diAddress = diAddress;
    }

    public int getDiPort() {
        return diPort;
    }

    public void setDiPort(int diPort) {
        this.diPort = diPort;
    }

    public int getDiIsConnect() {
        return diIsConnect;
    }

    public void setDiIsConnect(int diIsConnect) {
        this.diIsConnect = diIsConnect;
    }

    public int getDiOperate() {
        return diOperate;
    }

    public void setDiOperate(int diOperate) {
        this.diOperate = diOperate;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }
}
