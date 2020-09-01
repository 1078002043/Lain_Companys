package computer_room.bean;

import java.io.Serializable;

/**
 * 精密空调 BEAN
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/9 08 : 44
 */
public class Precision_Air_ConditionerBean implements Serializable {

    /**
     * ecmId : 1
     * ecmDeviceAddress : 1
     * ecmDeviceName : 1号精密空调
     * ecmTemp : 23
     * ecmHum : 22.2
     * ecmOnoroffStatus : 0
     * ecmComprStatus : 0
     * ecmFanStatus : 0
     * ecmRefrigerationStatus : 0
     * ecmHeatingStatus : 0
     * ecmDehumidifyStatus : 0
     * ecmHumidificationStatus : 0
     * ecmRunningStatus : 0
     * ecmHighPressureAlarm : 0
     * ecmLowPressureAlarm : 0
     * ecmHighTempAlarm : 0
     * ecmLowTempAlarm : 0
     * ecmHighHumAlarm : 0
     * ecmLowHumAlarm : 0
     * ecmPowerFailureAlarm : 0
     * ecmHotGasByPassValveAlarm : 0
     * ecmIntervalTime : 30
     * gId : 2
     * diId : 23
     * group : {"gId":0,"parentId":0,"gName":null,"children":null}
     */

    private int ecmId;
    private int ecmDeviceAddress;
    private String ecmDeviceName;
    private int ecmTemp;
    private double ecmHum;
    private int ecmOnoroffStatus;
    private int ecmComprStatus;
    private int ecmFanStatus;
    private int ecmRefrigerationStatus;
    private int ecmHeatingStatus;
    private int ecmDehumidifyStatus;
    private int ecmHumidificationStatus;
    private int ecmRunningStatus;
    private int ecmHighPressureAlarm;
    private int ecmLowPressureAlarm;
    private int ecmHighTempAlarm;
    private int ecmLowTempAlarm;
    private int ecmHighHumAlarm;
    private int ecmLowHumAlarm;
    private int ecmPowerFailureAlarm;
    private int ecmHotGasByPassValveAlarm;
    private int ecmIntervalTime;
    private int gId;
    private int diId;
    private GroupBean group;

    public int getEcmId() {
        return ecmId;
    }

    public void setEcmId(int ecmId) {
        this.ecmId = ecmId;
    }

    public int getEcmDeviceAddress() {
        return ecmDeviceAddress;
    }

    public void setEcmDeviceAddress(int ecmDeviceAddress) {
        this.ecmDeviceAddress = ecmDeviceAddress;
    }

    public String getEcmDeviceName() {
        return ecmDeviceName;
    }

    public void setEcmDeviceName(String ecmDeviceName) {
        this.ecmDeviceName = ecmDeviceName;
    }

    public int getEcmTemp() {
        return ecmTemp;
    }

    public void setEcmTemp(int ecmTemp) {
        this.ecmTemp = ecmTemp;
    }

    public double getEcmHum() {
        return ecmHum;
    }

    public void setEcmHum(double ecmHum) {
        this.ecmHum = ecmHum;
    }

    public int getEcmOnoroffStatus() {
        return ecmOnoroffStatus;
    }

    public void setEcmOnoroffStatus(int ecmOnoroffStatus) {
        this.ecmOnoroffStatus = ecmOnoroffStatus;
    }

    public int getEcmComprStatus() {
        return ecmComprStatus;
    }

    public void setEcmComprStatus(int ecmComprStatus) {
        this.ecmComprStatus = ecmComprStatus;
    }

    public int getEcmFanStatus() {
        return ecmFanStatus;
    }

    public void setEcmFanStatus(int ecmFanStatus) {
        this.ecmFanStatus = ecmFanStatus;
    }

    public int getEcmRefrigerationStatus() {
        return ecmRefrigerationStatus;
    }

    public void setEcmRefrigerationStatus(int ecmRefrigerationStatus) {
        this.ecmRefrigerationStatus = ecmRefrigerationStatus;
    }

    public int getEcmHeatingStatus() {
        return ecmHeatingStatus;
    }

    public void setEcmHeatingStatus(int ecmHeatingStatus) {
        this.ecmHeatingStatus = ecmHeatingStatus;
    }

    public int getEcmDehumidifyStatus() {
        return ecmDehumidifyStatus;
    }

    public void setEcmDehumidifyStatus(int ecmDehumidifyStatus) {
        this.ecmDehumidifyStatus = ecmDehumidifyStatus;
    }

    public int getEcmHumidificationStatus() {
        return ecmHumidificationStatus;
    }

    public void setEcmHumidificationStatus(int ecmHumidificationStatus) {
        this.ecmHumidificationStatus = ecmHumidificationStatus;
    }

    public int getEcmRunningStatus() {
        return ecmRunningStatus;
    }

    public void setEcmRunningStatus(int ecmRunningStatus) {
        this.ecmRunningStatus = ecmRunningStatus;
    }

    public int getEcmHighPressureAlarm() {
        return ecmHighPressureAlarm;
    }

    public void setEcmHighPressureAlarm(int ecmHighPressureAlarm) {
        this.ecmHighPressureAlarm = ecmHighPressureAlarm;
    }

    public int getEcmLowPressureAlarm() {
        return ecmLowPressureAlarm;
    }

    public void setEcmLowPressureAlarm(int ecmLowPressureAlarm) {
        this.ecmLowPressureAlarm = ecmLowPressureAlarm;
    }

    public int getEcmHighTempAlarm() {
        return ecmHighTempAlarm;
    }

    public void setEcmHighTempAlarm(int ecmHighTempAlarm) {
        this.ecmHighTempAlarm = ecmHighTempAlarm;
    }

    public int getEcmLowTempAlarm() {
        return ecmLowTempAlarm;
    }

    public void setEcmLowTempAlarm(int ecmLowTempAlarm) {
        this.ecmLowTempAlarm = ecmLowTempAlarm;
    }

    public int getEcmHighHumAlarm() {
        return ecmHighHumAlarm;
    }

    public void setEcmHighHumAlarm(int ecmHighHumAlarm) {
        this.ecmHighHumAlarm = ecmHighHumAlarm;
    }

    public int getEcmLowHumAlarm() {
        return ecmLowHumAlarm;
    }

    public void setEcmLowHumAlarm(int ecmLowHumAlarm) {
        this.ecmLowHumAlarm = ecmLowHumAlarm;
    }

    public int getEcmPowerFailureAlarm() {
        return ecmPowerFailureAlarm;
    }

    public void setEcmPowerFailureAlarm(int ecmPowerFailureAlarm) {
        this.ecmPowerFailureAlarm = ecmPowerFailureAlarm;
    }

    public int getEcmHotGasByPassValveAlarm() {
        return ecmHotGasByPassValveAlarm;
    }

    public void setEcmHotGasByPassValveAlarm(int ecmHotGasByPassValveAlarm) {
        this.ecmHotGasByPassValveAlarm = ecmHotGasByPassValveAlarm;
    }

    public int getEcmIntervalTime() {
        return ecmIntervalTime;
    }

    public void setEcmIntervalTime(int ecmIntervalTime) {
        this.ecmIntervalTime = ecmIntervalTime;
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

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public static class GroupBean implements Serializable{
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
}
