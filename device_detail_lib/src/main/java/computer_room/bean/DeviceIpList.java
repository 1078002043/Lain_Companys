package computer_room.bean;

import java.util.List;

/**
 * @ClassName: DeviceIpList
 * @Description: IP 设置列表请求解析类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/13 16:59
 */
public class DeviceIpList {


    /**
     * diId : 0
     * diAddress : 192.168.1.251
     * diPort : 6001
     * diIsConnect : 0
     * diOperate : 0
     * dId : 0
     * gId : 0
     * deviceList : [{"name":"温湿度","isShow":1,"deviceIp":null,"did":1}]
     */

    private int diId;
    private String diAddress;
    private int diPort;
    private int diIsConnect;
    private int diOperate;
    private int dId;
    private int gId;
    private List<DeviceListBean> deviceList;

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

    public int getDId() {
        return dId;
    }

    public void setDId(int dId) {
        this.dId = dId;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public List<DeviceListBean> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<DeviceListBean> deviceList) {
        this.deviceList = deviceList;
    }

    public static class DeviceListBean {
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
}
