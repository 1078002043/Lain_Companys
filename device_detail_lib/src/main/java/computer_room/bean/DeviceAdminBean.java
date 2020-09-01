package computer_room.bean;

import java.util.List;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/20 16:46
 * Description：设备管理-管理员
 **/
public class DeviceAdminBean {


    /**
     * uId : 1
     * username : admin
     * password : 21232f297a57a5a743894a0e4a801fc3
     * vsername : 韩梅梅
     * telephone : 19868577355
     * email : imchile@163.com
     * weekday : 7F
     * rId : 3
     * rName : ROLE_USER
     * enabled : true
     * gId : 1
     * authorities : [{"authority":"ROLE_USER"}]
     * accountNonExpired : true
     * accountNonLocked : true
     * credentialsNonExpired : true
     */

    private int uId;
    private String username;
    private String password;
    private String vsername;
    private String telephone;
    private String email;
    private String weekday;
    private int rId;
    private String rName;
    private boolean enabled;
    private int gId;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private List<AuthoritiesBean> authorities;

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
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

    public String getVsername() {
        return vsername;
    }

    public void setVsername(String vsername) {
        this.vsername = vsername;
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

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public String getRName() {
        return rName;
    }

    public void setRName(String rName) {
        this.rName = rName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public List<AuthoritiesBean> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthoritiesBean> authorities) {
        this.authorities = authorities;
    }

    public static class AuthoritiesBean {
        /**
         * authority : ROLE_USER
         */

        private String authority;

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
    }
}
