package bean;

import base.BaseBean;

/**
 * 酸碱仪  PH
 */
public class AcidBaseRealBean extends BaseBean {


    /**
     * ephmId : 1
     * ephmDeviceName : 酸碱仪
     * ephmAddress : 1
     * ephmPhValue : 5.0
     * ephmPhMax : 30.0
     * ephmPhMin : 20.1
     * intervalTime : 20
     * ephmStatus : 0
     * diId : 54
     * gId : 2
     * group : {"gId":2,"parentId":1,"gName":"莱安","children":null}
     * deviceIp : {"dId":18,"name":null,"isShow":0,"diId":54,"diAddress":"192.168.1.228","diPort":5000,"diIsConnect":0,"diOperate":0,"gId":0}
     */

    private int ephmId;
    private String ephmDeviceName;
    private int ephmAddress;
    private double ephmPhValue;
    private double ephmPhMax;
    private double ephmPhMin;
    private int intervalTime;
    private int ephmStatus;
    private int diId;
    private int gId;
    private GroupBean group;
    private DeviceIpBean deviceIp;

    public int getEphmId() {
        return ephmId;
    }

    public void setEphmId(int ephmId) {
        this.ephmId = ephmId;
    }

    public String getEphmDeviceName() {
        return ephmDeviceName;
    }

    public void setEphmDeviceName(String ephmDeviceName) {
        this.ephmDeviceName = ephmDeviceName;
    }

    public int getEphmAddress() {
        return ephmAddress;
    }

    public void setEphmAddress(int ephmAddress) {
        this.ephmAddress = ephmAddress;
    }

    public double getEphmPhValue() {
        return ephmPhValue;
    }

    public void setEphmPhValue(double ephmPhValue) {
        this.ephmPhValue = ephmPhValue;
    }

    public double getEphmPhMax() {
        return ephmPhMax;
    }

    public void setEphmPhMax(double ephmPhMax) {
        this.ephmPhMax = ephmPhMax;
    }

    public double getEphmPhMin() {
        return ephmPhMin;
    }

    public void setEphmPhMin(double ephmPhMin) {
        this.ephmPhMin = ephmPhMin;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getEphmStatus() {
        return ephmStatus;
    }

    public void setEphmStatus(int ephmStatus) {
        this.ephmStatus = ephmStatus;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public DeviceIpBean getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(DeviceIpBean deviceIp) {
        this.deviceIp = deviceIp;
    }

    public static class GroupBean {
        /**
         * gId : 2
         * parentId : 1
         * gName : 莱安
         * children : null
         */

        private int gId;
        private int parentId;
        private String gName;
        private Object children;

        public int getGId() {
            return gId;
        }

        public void setGId(int gId) {
            this.gId = gId;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }
    }

    public static class DeviceIpBean {
        /**
         * dId : 18
         * name : null
         * isShow : 0
         * diId : 54
         * diAddress : 192.168.1.228
         * diPort : 5000
         * diIsConnect : 0
         * diOperate : 0
         * gId : 0
         */

        private int dId;
        private Object name;
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
}
