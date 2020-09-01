package computer_room.bean;

/**
 * @ClassName: RoleManageBean
 * @Description: 权限管理
 * @Author: YIN LUO FEI
 * @Date: 2020/7/15 15:56
 */
public class RoleManageBean {


    /**
     * id : 10
     * name : insertUser
     * desc_ : 增加用户信息
     * url : /config/insertUser
     */

    private int id;
    private String name;
    private String desc_;
    private String url;

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

    public String getDesc_() {
        return desc_;
    }

    public void setDesc_(String desc_) {
        this.desc_ = desc_;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
