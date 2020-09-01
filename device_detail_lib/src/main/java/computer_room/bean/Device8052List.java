package computer_room.bean;

/**
 * @ClassName: Device8052List
 * @Description: 8052 所有设备的 IP 和 名称
 * @Author: YIN LUO FEI
 * @Date: 2020/7/13 21:55
 */
public class Device8052List {


    /**
     * name : null
     * isShow : 0
     * deviceIp : {"name":null,"isShow":0,"deviceIp":null,"diId":0,"diAddress":"192.168.1.89","diPort":6001,"diIsConnect":0,"diOperate":0,"gid":2,"did":0}
     * diId : 0
     * diAddress : null
     * diPort : 0
     * diIsConnect : 0
     * diOperate : 0
     * gId : 2
     * ekmId : 0
     * ekmName : 点式漏水1
     * ekmAddress : 3
     * gallery : DI3
     * status : 2
     * kId : 9
     * k_name : null
     * g_name : null
     * gid : 0
     * did : 0
     */

    private Object name;
    private int isShow;
    private DeviceIpBean deviceIp;
    private int diId;
    private Object diAddress;
    private int diPort;
    private int diIsConnect;
    private int diOperate;
    private int gId;
    private int ekmId;
    private String ekmName;
    private int ekmAddress;
    private String gallery;
    private int status;
    private int kId;
    private Object k_name;
    private Object g_name;
    private int gid;
    private int did;

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

    public DeviceIpBean getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(DeviceIpBean deviceIp) {
        this.deviceIp = deviceIp;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public Object getDiAddress() {
        return diAddress;
    }

    public void setDiAddress(Object diAddress) {
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

    public int getEkmId() {
        return ekmId;
    }

    public void setEkmId(int ekmId) {
        this.ekmId = ekmId;
    }

    public String getEkmName() {
        return ekmName;
    }

    public void setEkmName(String ekmName) {
        this.ekmName = ekmName;
    }

    public int getEkmAddress() {
        return ekmAddress;
    }

    public void setEkmAddress(int ekmAddress) {
        this.ekmAddress = ekmAddress;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getKId() {
        return kId;
    }

    public void setKId(int kId) {
        this.kId = kId;
    }

    public Object getK_name() {
        return k_name;
    }

    public void setK_name(Object k_name) {
        this.k_name = k_name;
    }

    public Object getG_name() {
        return g_name;
    }

    public void setG_name(Object g_name) {
        this.g_name = g_name;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public static class DeviceIpBean {
        /**
         * name : null
         * isShow : 0
         * deviceIp : null
         * diId : 0
         * diAddress : 192.168.1.89
         * diPort : 6001
         * diIsConnect : 0
         * diOperate : 0
         * gid : 2
         * did : 0
         */

        private Object name;
        private int isShow;
        private Object deviceIp;
        private int diId;
        private String diAddress;
        private int diPort;
        private int diIsConnect;
        private int diOperate;
        private int gid;
        private int did;

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

        public int getGid() {
            return gid;
        }

        public void setGid(int gid) {
            this.gid = gid;
        }

        public int getDid() {
            return did;
        }

        public void setDid(int did) {
            this.did = did;
        }
    }
}
