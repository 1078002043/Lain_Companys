package bean;

/**
 * 增氧泵 实时 数据
 */
public class OxygenIncreasingRealBean {


    /**
     * id : 2
     * status : 1
     * gallery : RL2
     * name : 1号增氧泵
     */

    private int id;
    private int status;
    private String gallery;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
