package computer_room.bean;

import java.util.List;

/**
 * @ClassName: NewRoleManageBean
 * @Description: 角色管理和角色拥有的权限
 * @Author: YIN LUO FEI
 * @Date: 2020/7/14 22:58
 */
public class CharacterManageBean {


    /**
     * id : 2
     * name : admin
     * desc_ : 普通管理员
     * permissions : [{"id":10,"name":"insertUser","desc_":"增加用户信息","url":"/config/insertUser","roleList":null},{"id":7,"name":"deleteUser","desc_":"删除用户","url":"/config/deleteUserById","roleList":null},{"id":5,"name":"findUserAll","desc_":"查看用户列表","url":"/config/findUserAll","roleList":null}]
     * permissionIds : null
     */

    private int id;
    private String name;
    private String desc_;
    private Object permissionIds;
    private List<PermissionsBean> permissions;

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

    public Object getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(Object permissionIds) {
        this.permissionIds = permissionIds;
    }

    public List<PermissionsBean> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionsBean> permissions) {
        this.permissions = permissions;
    }

    public static class PermissionsBean {
        /**
         * id : 10
         * name : insertUser
         * desc_ : 增加用户信息
         * url : /config/insertUser
         * roleList : null
         */

        private int id;
        private String name;
        private String desc_;
        private String url;
        private Object roleList;

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

        public Object getRoleList() {
            return roleList;
        }

        public void setRoleList(Object roleList) {
            this.roleList = roleList;
        }
    }
}
