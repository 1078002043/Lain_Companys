package computer_room.bean;

/**
 * 系统日志
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/16 11 : 02
 */
public class LogTableBean {

    /**
     * name : 删除温湿度
     * time : 2019-02-1 09:23:20
     * status : 1
     */

    private String name;
    private String time;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
