package bean;

import java.util.List;

/**
 * @ClassName: SmartMainBean
 * @Description: 主页所有的数据 * @Author: YIN LUO FEI
 * @Date: 2020/7/16 17:29
 */
public class SmartMainBean {

    /**
     * AvgTemperature : 19.099999999999998
     * EquipmentTotal : 12
     * TotalAlarmIsNotRead : 286
     * TotalAlarmIsRead : 35
     * TotalAlarmLimit : [{"alarmName":"温湿度3","id":334,"isRead":1,"time":"2020-07-06 08:33:38"},{"alarmName":"温湿度3","id":333,"isRead":1,"time":"2020-07-06 08:03:36"},{"alarmName":"温湿度3","id":332,"isRead":0,"time":"2020-07-06 07:33:35"},{"alarmName":"温湿度3","id":331,"isRead":0,"time":"2020-07-06 07:03:34"},{"alarmName":"温湿度3","id":330,"isRead":0,"time":"2020-07-06 06:33:34"}]
     * TotalAlarmMonthCount : 71
     * TotalAlarmTodayCount : 0
     * TotalAlarmWeekCount : 0
     */

    private double AvgTemperature;
    private int EquipmentTotal;
    private int TotalAlarmIsNotRead;
    private int TotalAlarmIsRead;
    private int TotalAlarmMonthCount;
    private int TotalAlarmTodayCount;
    private int TotalAlarmWeekCount;
    private List<TotalAlarmLimitBean> TotalAlarmLimit;

    public double getAvgTemperature() {
        return AvgTemperature;
    }

    public void setAvgTemperature(double AvgTemperature) {
        this.AvgTemperature = AvgTemperature;
    }

    public int getEquipmentTotal() {
        return EquipmentTotal;
    }

    public void setEquipmentTotal(int EquipmentTotal) {
        this.EquipmentTotal = EquipmentTotal;
    }

    public int getTotalAlarmIsNotRead() {
        return TotalAlarmIsNotRead;
    }

    public void setTotalAlarmIsNotRead(int TotalAlarmIsNotRead) {
        this.TotalAlarmIsNotRead = TotalAlarmIsNotRead;
    }

    public int getTotalAlarmIsRead() {
        return TotalAlarmIsRead;
    }

    public void setTotalAlarmIsRead(int TotalAlarmIsRead) {
        this.TotalAlarmIsRead = TotalAlarmIsRead;
    }

    public int getTotalAlarmMonthCount() {
        return TotalAlarmMonthCount;
    }

    public void setTotalAlarmMonthCount(int TotalAlarmMonthCount) {
        this.TotalAlarmMonthCount = TotalAlarmMonthCount;
    }

    public int getTotalAlarmTodayCount() {
        return TotalAlarmTodayCount;
    }

    public void setTotalAlarmTodayCount(int TotalAlarmTodayCount) {
        this.TotalAlarmTodayCount = TotalAlarmTodayCount;
    }

    public int getTotalAlarmWeekCount() {
        return TotalAlarmWeekCount;
    }

    public void setTotalAlarmWeekCount(int TotalAlarmWeekCount) {
        this.TotalAlarmWeekCount = TotalAlarmWeekCount;
    }

    public List<TotalAlarmLimitBean> getTotalAlarmLimit() {
        return TotalAlarmLimit;
    }

    public void setTotalAlarmLimit(List<TotalAlarmLimitBean> TotalAlarmLimit) {
        this.TotalAlarmLimit = TotalAlarmLimit;
    }

    public static class TotalAlarmLimitBean {
        /**
         * alarmName : 温湿度3
         * id : 334
         * isRead : 1
         * time : 2020-07-06 08:33:38
         */

        private String alarmName;
        private int id;
        private int isRead;
        private String time;

        public String getAlarmName() {
            return alarmName;
        }

        public void setAlarmName(String alarmName) {
            this.alarmName = alarmName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
