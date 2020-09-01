package computer_room.bean;

/**
 * @ClassName: ServerIpItemBean
 * @Description: 所有服务器的 IP Bean 类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/8 20:57
 */
public class ServerIpItemBean {

    String ip;
    String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
