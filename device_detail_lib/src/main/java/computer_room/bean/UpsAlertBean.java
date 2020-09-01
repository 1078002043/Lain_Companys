package computer_room.bean;

/**
 * UPS 报警数据
 */
public class UpsAlertBean {


    /**
     * id : 1
     * upsId : 1
     * name : 1号UPS
     * info : 市电异常
     * time : 2019-12-25 15:14:57
     */

    private int id;
    private int upsId;
    private String name;
    private String info;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpsId() {
        return upsId;
    }

    public void setUpsId(int upsId) {
        this.upsId = upsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
