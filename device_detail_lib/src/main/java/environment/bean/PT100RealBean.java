package environment.bean;

import base.BaseBean;

/**
 * @ClassName: PT100RealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 9:40
 */
public class PT100RealBean extends BaseBean {


    /**
     * ehmpId : 1
     * ehmpDeviceAddress : 0
     * gallery : 0
     * ehmpDeviceName : 1号PT100
     * ehmpTemp : -22.3
     * ehmpHum : 0.0
     * ehmpMaxTemp : 0.0
     * ehmpMinTemp : 0.0
     * ehmpMaxHum : 0.0
     * ehmpMinHum : 0.0
     * ehmpStatus : 2
     * intervalTime : 0
     * wireLength : 0
     * diId : 0
     * gId : 0
     * group : {"gId":0,"parentId":0,"gName":null,"children":null}
     * deviceIp : {"dId":0,"name":null,"isShow":0,"diId":0,"diAddress":null,"diPort":0,"diIsConnect":0,"diOperate":0,"gId":0}
     */

    private int ehmpId;
    private int ehmpDeviceAddress;
    private int gallery;
    private String ehmpDeviceName;
    private double ehmpTemp;
    private double ehmpHum;
    private double ehmpMaxTemp;
    private double ehmpMinTemp;
    private double ehmpMaxHum;
    private double ehmpMinHum;
    private int ehmpStatus;
    private int intervalTime;
    private int wireLength;
    private int diId;
    private int gId;
    private GroupBean group;
    private DeviceIpBean deviceIp;

    public int getEhmpId() {
        return ehmpId;
    }

    public void setEhmpId(int ehmpId) {
        this.ehmpId = ehmpId;
    }

    public int getEhmpDeviceAddress() {
        return ehmpDeviceAddress;
    }

    public void setEhmpDeviceAddress(int ehmpDeviceAddress) {
        this.ehmpDeviceAddress = ehmpDeviceAddress;
    }

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public String getEhmpDeviceName() {
        return ehmpDeviceName;
    }

    public void setEhmpDeviceName(String ehmpDeviceName) {
        this.ehmpDeviceName = ehmpDeviceName;
    }

    public double getEhmpTemp() {
        return ehmpTemp;
    }

    public void setEhmpTemp(double ehmpTemp) {
        this.ehmpTemp = ehmpTemp;
    }

    public double getEhmpHum() {
        return ehmpHum;
    }

    public void setEhmpHum(double ehmpHum) {
        this.ehmpHum = ehmpHum;
    }

    public double getEhmpMaxTemp() {
        return ehmpMaxTemp;
    }

    public void setEhmpMaxTemp(double ehmpMaxTemp) {
        this.ehmpMaxTemp = ehmpMaxTemp;
    }

    public double getEhmpMinTemp() {
        return ehmpMinTemp;
    }

    public void setEhmpMinTemp(double ehmpMinTemp) {
        this.ehmpMinTemp = ehmpMinTemp;
    }

    public double getEhmpMaxHum() {
        return ehmpMaxHum;
    }

    public void setEhmpMaxHum(double ehmpMaxHum) {
        this.ehmpMaxHum = ehmpMaxHum;
    }

    public double getEhmpMinHum() {
        return ehmpMinHum;
    }

    public void setEhmpMinHum(double ehmpMinHum) {
        this.ehmpMinHum = ehmpMinHum;
    }

    public int getEhmpStatus() {
        return ehmpStatus;
    }

    public void setEhmpStatus(int ehmpStatus) {
        this.ehmpStatus = ehmpStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getWireLength() {
        return wireLength;
    }

    public void setWireLength(int wireLength) {
        this.wireLength = wireLength;
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
         * gId : 0
         * parentId : 0
         * gName : null
         * children : null
         */

        private int gId;
        private int parentId;
        private Object gName;
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

        public Object getGName() {
            return gName;
        }

        public void setGName(Object gName) {
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
         * dId : 0
         * name : null
         * isShow : 0
         * diId : 0
         * diAddress : null
         * diPort : 0
         * diIsConnect : 0
         * diOperate : 0
         * gId : 0
         */

        private int dId;
        private Object name;
        private int isShow;
        private int diId;
        private Object diAddress;
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
    }
}
