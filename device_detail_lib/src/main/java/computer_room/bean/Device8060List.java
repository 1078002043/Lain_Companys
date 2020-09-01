package computer_room.bean;

/**
 * @ClassName: Device8060List
 * @Description: 8060 所有设备的 IP 和 名称
 * @Author: YIN LUO FEI
 * @Date: 2020/7/13 21:17
 */
public class Device8060List {


    /**
     * name : 新风机
     * isShow : 0
     * deviceIp : {"name":null,"isShow":0,"deviceIp":null,"diId":0,"diAddress":"192.168.1.70","diPort":6003,"diIsConnect":0,"diOperate":0,"gid":2,"did":0}
     * diId : 0
     * diAddress : null
     * diPort : 0
     * diIsConnect : 0
     * diOperate : 0
     * gId : 2
     * id : 15
     * address : 3
     * gallery : 2
     * kId : 2
     * k_name : null
     * g_name : null
     * status : 0
     * gid : 0
     * did : 0
     */

    private String name;
    private int isShow;
    private DeviceIpBean deviceIp;
    private int diId;
    private Object diAddress;
    private int diPort;
    private int diIsConnect;
    private int diOperate;
    private int gId;
    private int id;
    private int address;
    private int gallery;
    private int kId;
    private Object k_name;
    private Object g_name;
    private int status;
    private int gid;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
         * diAddress : 192.168.1.70
         * diPort : 6003
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
