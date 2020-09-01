package bean;

import java.io.Serializable;

/**
 * 机房监控 设备详情添加设备列表数据 Bean
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/7 10 : 20
 */
public class Device_Detail_AddBean implements Serializable {
    //位置
    private int upPosition;
    //设置按钮字符串
    private String setBtnStr;
    //温度上下限
    private String temperatureMax;
    private String temperatureMin;
    //湿度上下限
    private String humidityMax;
    private String humidityMin;
    //端口
    private String port;
    //位置
    private String position;
    //ip
    private String ip;
    //设备名称
    private String deviceName;
    //保存间隔
    private String saveInterval;
    //图标
    private String imageUrl;
    //设备ID
    private String ehmId;
    //更新间隔
    private String updateTime;
    //报警值
    private String alertValue;
    //范围区间
    private String dataRange;
    //设备通道
    private String gallery;
    //线长度
    private String lineLength;

    //A向
    private String aCur;
    private String aVol;
    //B向
    private String bCur;
    private String bVol;
    //C向
    private String cCur;
    private String cVol;

    public String getaCur() {
        return aCur;
    }

    public void setaCur(String aCur) {
        this.aCur = aCur;
    }

    public String getaVol() {
        return aVol;
    }

    public void setaVol(String aVol) {
        this.aVol = aVol;
    }

    public String getbCur() {
        return bCur;
    }

    public void setbCur(String bCur) {
        this.bCur = bCur;
    }

    public String getbVol() {
        return bVol;
    }

    public void setbVol(String bVol) {
        this.bVol = bVol;
    }

    public String getcCur() {
        return cCur;
    }

    public void setcCur(String cCur) {
        this.cCur = cCur;
    }

    public String getcVol() {
        return cVol;
    }

    public void setcVol(String cVol) {
        this.cVol = cVol;
    }

    public String getLineLength() {
        return lineLength;
    }

    public void setLineLength(String lineLength) {
        this.lineLength = lineLength;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getDataRange() {
        return dataRange;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }

    public String getAlertValue() {
        return alertValue;
    }

    public void setAlertValue(String alertValue) {
        this.alertValue = alertValue;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getEhmId() {
        return ehmId;
    }

    public void setEhmId(String ehmId) {
        this.ehmId = ehmId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSetBtnStr() {
        return setBtnStr;
    }

    public void setSetBtnStr(String setBtnStr) {
        this.setBtnStr = setBtnStr;
    }

    public int getUpPosition() {
        return upPosition;
    }

    public void setUpPosition(int upPosition) {
        this.upPosition = upPosition;
    }

    public String getSaveInterval() {
        return saveInterval;
    }

    public void setSaveInterval(String saveInterval) {
        this.saveInterval = saveInterval;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(String humidityMax) {
        this.humidityMax = humidityMax;
    }

    public String getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(String humidityMin) {
        this.humidityMin = humidityMin;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String floor) {
        this.position = floor;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Device_Detail_AddBean{" +
                "upPosition=" + upPosition +
                ", setBtnStr='" + setBtnStr + '\'' +
                ", temperatureMax='" + temperatureMax + '\'' +
                ", temperatureMin='" + temperatureMin + '\'' +
                ", humidityMax='" + humidityMax + '\'' +
                ", humidityMin='" + humidityMin + '\'' +
                ", port='" + port + '\'' +
                ", position='" + position + '\'' +
                ", ip='" + ip + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", saveInterval='" + saveInterval + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
