package computer_room.bean;

/**
 * 8052的报警数据
 */
public class Alert8052All {


    /**
     * id : 33
     * name : 非定位漏水
     * time : 2019-09-18 19:53:56
     * gallery : DI1
     */

    private int id;
    private String name;
    private String time;
    private String gallery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }
}
