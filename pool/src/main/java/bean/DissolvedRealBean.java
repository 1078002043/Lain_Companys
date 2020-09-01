package bean;

import base.BaseBean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/15 10:49
 * Description：溶氧仪实时数据
 **/
public class DissolvedRealBean extends BaseBean {


    /**
     * edomId : 1
     * edomAddress : 1
     * edomDeviceName : 1号溶氧仪设备
     * edomO2Value : 9.9
     * edomO2MaxValue : 15.1
     * edomO2MinValue : 5.1
     * edomDoTempName : 1溶氧仪设备温度
     * edomDoTemp : 25.6
     * edomDoTempMax : 24.0
     * edomDoTempMin : 10.0
     * diId : 50
     * gId : 2
     * intervalTime : 35
     * edomStatus : 0
     * group : {"gId":2,"parentId":1,"gName":"莱安","children":null}
     * deviceIp : {"dId":16,"name":null,"isShow":0,"diId":50,"diAddress":"192.168.1.111","diPort":6005,"diIsConnect":0,"diOperate":0,"gId":0}
     */

    private int edomId;
    private int edomAddress;
    private String edomDeviceName;
    private double edomO2Value;
    private double edomO2MaxValue;
    private double edomO2MinValue;
    private String edomDoTempName;
    private double edomDoTemp;
    private double edomDoTempMax;
    private double edomDoTempMin;
    private int diId;
    private int gId;
    private int intervalTime;
    private int edomStatus;
    private GroupBean group;
    private DeviceIpBean deviceIp;

    public int getEdomId() {
        return edomId;
    }

    public void setEdomId(int edomId) {
        this.edomId = edomId;
    }

    public int getEdomAddress() {
        return edomAddress;
    }

    public void setEdomAddress(int edomAddress) {
        this.edomAddress = edomAddress;
    }

    public String getEdomDeviceName() {
        return edomDeviceName;
    }

    public void setEdomDeviceName(String edomDeviceName) {
        this.edomDeviceName = edomDeviceName;
    }

    public double getEdomO2Value() {
        return edomO2Value;
    }

    public void setEdomO2Value(double edomO2Value) {
        this.edomO2Value = edomO2Value;
    }

    public double getEdomO2MaxValue() {
        return edomO2MaxValue;
    }

    public void setEdomO2MaxValue(double edomO2MaxValue) {
        this.edomO2MaxValue = edomO2MaxValue;
    }

    public double getEdomO2MinValue() {
        return edomO2MinValue;
    }

    public void setEdomO2MinValue(double edomO2MinValue) {
        this.edomO2MinValue = edomO2MinValue;
    }

    public String getEdomDoTempName() {
        return edomDoTempName;
    }

    public void setEdomDoTempName(String edomDoTempName) {
        this.edomDoTempName = edomDoTempName;
    }

    public double getEdomDoTemp() {
        return edomDoTemp;
    }

    public void setEdomDoTemp(double edomDoTemp) {
        this.edomDoTemp = edomDoTemp;
    }

    public double getEdomDoTempMax() {
        return edomDoTempMax;
    }

    public void setEdomDoTempMax(double edomDoTempMax) {
        this.edomDoTempMax = edomDoTempMax;
    }

    public double getEdomDoTempMin() {
        return edomDoTempMin;
    }

    public void setEdomDoTempMin(double edomDoTempMin) {
        this.edomDoTempMin = edomDoTempMin;
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

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getEdomStatus() {
        return edomStatus;
    }

    public void setEdomStatus(int edomStatus) {
        this.edomStatus = edomStatus;
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
         * dId : 16
         * name : null
         * isShow : 0
         * diId : 50
         * diAddress : 192.168.1.111
         * diPort : 6005
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
