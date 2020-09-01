package computer_room.bean;

/**
 * @ClassName: DeviceAssetsList
 * @Description: 资产管理 IP 名称 解析类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/14 17:45
 */
public class DeviceAssetsList {


    /**
     * emdId : 0
     * emdName : 资产管理1
     * address : 1
     * number : 1
     * gId : 2
     * diId : 0
     * deviceIp : {"name":null,"isShow":0,"deviceIp":null,"diId":0,"diAddress":"192.168.1.89","diPort":6002,"diIsConnect":0,"diOperate":0,"did":0,"gid":2}
     */

    private int emdId;
    private String emdName;
    private int address;
    private int number;
    private int gId;
    private int diId;
    private DeviceIpBean deviceIp;

    public int getEmdId() {
        return emdId;
    }

    public void setEmdId(int emdId) {
        this.emdId = emdId;
    }

    public String getEmdName() {
        return emdName;
    }

    public void setEmdName(String emdName) {
        this.emdName = emdName;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public DeviceIpBean getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(DeviceIpBean deviceIp) {
        this.deviceIp = deviceIp;
    }

    public static class DeviceIpBean {
        /**
         * name : null
         * isShow : 0
         * deviceIp : null
         * diId : 0
         * diAddress : 192.168.1.89
         * diPort : 6002
         * diIsConnect : 0
         * diOperate : 0
         * did : 0
         * gid : 2
         */

        private Object name;
        private int isShow;
        private Object deviceIp;
        private int diId;
        private String diAddress;
        private int diPort;
        private int diIsConnect;
        private int diOperate;
        private int did;
        private int gid;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }
    }
}
