package bean;

/**
 * @ClassName: UserAllBean
 * @Description: 查询所有的用户Bean解析类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/13 10:18
 */
public class UserAllBean {


    /**
     * id : 1
     * salt : cFD8u4bdfvS/zc1yayixWA==
     * icon : null
     * username : admin
     * password : 3c0e2bd0833e7db6b2899f3fb3a37a5d
     * telephone : 19868577355
     * email : 1084998063@qq.com
     * gId : 1
     */

    private int id;
    private String salt;
    private Object icon;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private int gId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }
}
