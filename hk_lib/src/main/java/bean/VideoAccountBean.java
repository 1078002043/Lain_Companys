package bean;

import java.io.Serializable;

/**
 * 视频监控账号 BEAN 类
 */
public class VideoAccountBean implements Serializable {
    /**
     * vId : 1
     * ipAddress : 192.168.1.65
     * ipPort : 8000
     * username : admin
     * password : lain123456
     */

    private int vId;
    private String ipAddress;
    private int ipPort;
    private String username;
    private String password;

    public int getVId() {
        return vId;
    }

    public void setVId(int vId) {
        this.vId = vId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getIpPort() {
        return ipPort;
    }

    public void setIpPort(int ipPort) {
        this.ipPort = ipPort;
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
}
