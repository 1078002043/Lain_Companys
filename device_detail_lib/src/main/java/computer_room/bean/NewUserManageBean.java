package computer_room.bean;

import java.util.List;

/**
 * @ClassName: NewUserManageBean
 * @Description: 用户信息和拥有的权限
 * @Author: YIN LUO FEI
 * @Date: 2020/7/14 22:52
 */
public class NewUserManageBean {


    /**
     * id : 26
     * salt : null
     * icon : null
     * username : imchile
     * password : 58bce1a79fa476d39d8c53e6a0659a44
     * telephone : 12345677312
     * email : imchile@163.com
     * roleList : [{"id":3,"name":"user","desc_":"普通用户","permissions":null,"permissionIds":null},{"id":2,"name":"admin","desc_":"普通管理员","permissions":null,"permissionIds":null}]
     * gid : 2
     */

    private int id;
    private Object salt;
    private Object icon;
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
