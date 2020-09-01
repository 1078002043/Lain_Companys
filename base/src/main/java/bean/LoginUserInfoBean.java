package bean;

import java.util.List;

/**
 * @ClassName: LoginUserInfoBean
 * @Description: 登陆成功后，获取用户的相关信息
 * @Author: YIN LUO FEI
 * @Date: 2020/7/28 21:41
 */
public class LoginUserInfoBean {


    /**
     * id : 36
     * salt : null
     * icon : Cheerful.jpg
     * username : admin
     * password : 3c0e2bd0833e7db6b2899f3fb3a37a5d
     * telephone : 11036636996
     * email : imchile@163.com
     * roleList : [{"id":3,"name":"user","desc_":"普通用户","permissions":null,"permissionIds":null},{"id":34,"name":"测试角陆","desc_":"测试是否","permissions":null,"permissionIds":null},{"id":35,"name":"于心无愧政","desc_":"起去好友了","permissions":null,"permissionIds":null},{"id":38,"name":"最开心","desc_":"卖队友","permissions":null,"permissionIds":null}]
     * gid : 2
     */

    private int id;
    private Object salt;
    private String icon;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private int gid;
    private List<RoleListBean> roleList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getSalt() {
        return salt;
    }

    public void setSalt(Object salt) {
        this.salt = salt;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
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

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public List<RoleListBean> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleListBean> roleList) {
        this.roleList = roleList;
    }

    public static class RoleListBean {
        /**
         * id : 3
         * name : user
         * desc_ : 普通用户
         * permissions : null
         * permissionIds : null
         */

        private int id;
        private String name;
        private String desc_;
        private Object permissions;
        private Object permissionIds;

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

        public Object getPermissions() {
            return permissions;
        }

        public void setPermissions(Object permissions) {
            this.permissions = permissions;
        }

        public Object getPermissionIds() {
            return permissionIds;
        }

        public void setPermissionIds(Object permissionIds) {
            this.permissionIds = permissionIds;
        }
    }
}
