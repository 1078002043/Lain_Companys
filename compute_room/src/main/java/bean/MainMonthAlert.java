package bean;

public class MainMonthAlert {


    /**
     * id : 3
     * alarmName : 温湿度3
     * time : 2019-10-08 16:15:21
     * isRead : 1
     */

    private int id;
    private String alarmName;
    private String time;
    private int isRead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
