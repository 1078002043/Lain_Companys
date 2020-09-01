package environment.bean;

import base.BaseBean;

/**
 * @ClassName: DS18B20RealBean
 * @Description:
 * @Author: YIN LUO FEI
 * @Date: 2020/3/28 10:05
 */
public class DS18B20RealBean extends BaseBean {


    /**
     * ehbmId : 28
     * ehbmDeviceAddress : 0
     * gallery : 0
     * ehbmDeviceName : 1号18b20探头
     * ehbmTemp : 7.8
     * ehbmHum : 0.0
     * ehbmMaxTemp : 0.0
     * ehbmMinTemp : 0.0
     * ehbmMaxHum : 0.0
     * ehbmMinHum : 0.0
     * ehbmStatus : 2
     * intervalTime : 0
     * diId : 0
     * gId : 0
     * group : {"gId":0,"parentId":0,"gName":null,"children":null}
     * deviceIp : {"dId":0,"name":null,"isShow":0,"diId":0,"diAddress":null,"diPort":0,"diIsConnect":0,"diOperate":0,"gId":0}
     */

    private int ehbmId;
    private int ehbmDeviceAddress;
    private int gallery;
    private String ehbmDeviceName;
    private double ehbmTemp;
    private double ehbmHum;
    private double ehbmMaxTemp;
    private double ehbmMinTemp;
    private double ehbmMaxHum;
    private double ehbmMinHum;
    private int ehbmStatus;
    private int intervalTime;
    private int diId;
    private int gId;
    private GroupBean group;
    private DeviceIpBean deviceIp;

    public int getEhbmId() {
        return ehbmId;
    }

    public void setEhbmId(int ehbmId) {
        this.ehbmId = ehbmId;
    }

    public int getEhbmDeviceAddress() {
        return ehbmDeviceAddress;
    }

    public void setEhbmDeviceAddress(int ehbmDeviceAddress) {
        this.ehbmDeviceAddress = ehbmDeviceAddress;
    }

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public String getEhbmDeviceName() {
        return ehbmDeviceName;
    }

    public void setEhbmDeviceName(String ehbmDeviceName) {
        this.ehbmDeviceName = ehbmDeviceName;
    }

    public double getEhbmTemp() {
        return ehbmTemp;
    }

    public void setEhbmTemp(double ehbmTemp) {
        this.ehbmTemp = ehbmTemp;
    }

    public double getEhbmHum() {
        return ehbmHum;
    }

    public void setEhbmHum(double ehbmHum) {
        this.ehbmHum = ehbmHum;
    }

    public double getEhbmMaxTemp() {
        return ehbmMaxTemp;
    }

    public void setEhbmMaxTemp(double ehbmMaxTemp) {
        this.ehbmMaxTemp = ehbmMaxTemp;
    }

    public double getEhbmMinTemp() {
        return ehbmMinTemp;
    }

    public void setEhbmMinTemp(double ehbmMinTemp) {
        this.ehbmMinTemp = ehbmMinTemp;
    }

    public double getEhbmMaxHum() {
        return ehbmMaxHum;
    }

    public void setEhbmMaxHum(double ehbmMaxHum) {
        this.ehbmMaxHum = ehbmMaxHum;
    }

    public double getEhbmMinHum() {
        return ehbmMinHum;
    }

    public void setEhbmMinHum(double ehbmMinHum) {
        this.ehbmMinHum = ehbmMinHum;
    }

    public int getEhbmStatus() {
        return ehbmStatus;
    }

    public void setEhbmStatus(int ehbmStatus) {
        this.ehbmStatus = ehbmStatus;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
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
