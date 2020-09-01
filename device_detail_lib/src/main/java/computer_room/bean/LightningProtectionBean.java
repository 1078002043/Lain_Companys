package computer_room.bean;

/**
 * 防雷 BEAN
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/9 13 : 35
 */
public class LightningProtectionBean {

    /**
     * address : 1
     * deviceLocationPojo : null
     * deviceName : 防雷
     * gallery : RL1
     * id : 1
     * ip : 192.168.1.23
     * ipPort : 8090
     * name : 1号防雷
     * number : 4
     * sn : 0
     * status : 1
     */

    private int address;
    private Object deviceLocationPojo;
    private String deviceName;
    private String gallery;
    private int id;
    private String ip;
    private String ipPort;
    private String name;
    private int number;
    private int sn;
    private int status;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public Object getDeviceLocationPojo() {
        return deviceLocationPojo;
    }

    public void setDeviceLocationPojo(Object deviceLocationPojo) {
        this.deviceLocationPojo = deviceLocationPojo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
