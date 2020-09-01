package action;


import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: DeviceManageEditBean
 * @Description: 添加-修改设备时的输入框数据
 * @Author: YIN LUO FEI
 * @Date: 2020/4/12 18:43
 */
public class DeviceManageEditBean implements Serializable {

    //修改 或 添加
    private String changeOrAdd = "添加";

    public String getChangeOrAdd() {
        return changeOrAdd;
    }

    public void setChangeOrAdd(String changeOrAdd) {
        this.changeOrAdd = changeOrAdd;
    }

    //设备名称
    private String aDeviceName;
    //更新时间
    private String aDeviceInterval;
    //报警值
    private String aDeviceAlert;
    //线长度
    private String aDeviceLine;
    //温度最小值
    private String tempRangeMin;
    //温度最大值
    private String tempRangeMax;
    //氨气最小值
    private String ammoniaRangeMin;
    //氨气最大值
    private String ammoniaRangeMax;
    //氮气最小值
    private String nitrogenRangeMin;
    //氮气最大值
    private String nitrogenRangeMax;
    //浓度最小值
    private String concentrationRangeMin;
    //浓度最大值
    private String concentrationRangeMax;
    //湿度最小值
    private String humRangeMin;
    //湿度最大值
    private String humRangeMax;
    //噪音最小值
    private String noiseRangeMin;
    //噪音最大值
    private String noiseRangeMax;
    //范围区间最小值
    private String rangeMin;
    //范围区间最大值
    private String rangeMax;
    //剩于电流最小值
    private String electricMin;
    //剩于电流最大值
    private String electricMax;
    //A相电流最小值
    private String phaseAMin;
    //A相电流最大值
    private String phaseAMax;
    //A相电压最小值
    private String voltageAMin;
    //A相电压最大值
    private String voltageAMax;
    //B相电流最小值
    private String phaseBMin;
    //B相电流最大值
    private String phaseBMax;
    //B相电压最小值
    private String voltageBMin;
    //B相电压最小值
    private String voltageBMax;
    //C相电流最小值
    private String phaseCMin;
    //C相电流最大值
    private String phaseCMax;
    //C相电压最小值
    private String voltageCMin;
    //C相电压最大值
    private String voltageCMax;
    //含氧量最小值
    private String oxygenRangeMin;
    //含氧量最大值
    private String oxygenRangeMax;
    //设备修改时的操作ID
    private String actionID;
    //设置设备的DIid
    private int diId;

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    //所属分组
    private List<String> classifyArr;
    //IP 地址
    private List<String> ipArr;

    //是否显示设备地址，默认不显示
    private boolean isShowDeviceAddress = false;
    //是否显示设备通道，默认不显示
    private boolean isShowDeviceGallery = false;
    //是否允许修改设备分组，默认不可以修改
    private boolean isShowDeviceClassify = false;

    //是否显示功能码，默认不显示
    private boolean isShowFunctionCode = false;
    //是否显示IP，默认不显示
    private boolean isShowIP = false;

    //保存设备的 IP
    private List<DeviceIPBean> deviceIPBean;
    //保存设备的 分组
    private DeviceGroupBean groupBeans;

    public boolean isShowDeviceClassify() {
        return isShowDeviceClassify;
    }

    public void setShowDeviceClassify(boolean showDeviceClassify) {
        isShowDeviceClassify = showDeviceClassify;
    }

    public boolean isShowDeviceGallery() {
        return isShowDeviceGallery;
    }

    public void setShowDeviceGallery(boolean showDeviceGallery) {
        isShowDeviceGallery = showDeviceGallery;
    }

    /**
     * 获取所有设备的IP
     * @return
     */
    public List<DeviceIPBean> getDeviceIPBean() {
        return deviceIPBean;
    }
    /**
     * 获取所有设备的分组
     * @return
     */
    public void setDeviceIPBean(List<DeviceIPBean> deviceIPBean) {
        this.deviceIPBean = deviceIPBean;
    }

    /**
     * 获取所有设备的分组
     * @return
     */
    public DeviceGroupBean getGroupBeans() {
        return groupBeans;
    }

    public void setGroupBeans(DeviceGroupBean groupBeans) {
        this.groupBeans = groupBeans;
    }

    public String getActionID() {
        return actionID;
    }

    public void setActionID(int actionID) {
        this.actionID = String.valueOf(actionID);
    }



    public boolean isShowIP() {
        return isShowIP;
    }

    public void setShowIP(boolean showIP) {
        isShowIP = showIP;
    }

    public boolean isShowDeviceAddress() {
        return isShowDeviceAddress;
    }

    public void setShowDeviceAddress(boolean showDeviceAddress) {
        isShowDeviceAddress = showDeviceAddress;
    }

    public boolean isShowFunctionCode() {
        return isShowFunctionCode;
    }

    public void setShowFunctionCode(boolean showFunctionCode) {
        isShowFunctionCode = showFunctionCode;
    }

    public List<String> getClassifyArr() {
        return classifyArr;
    }

    public void setClassifyArr(List<String> classifyArr) {
        this.classifyArr = classifyArr;
    }

    public List<String> getIpArr() {
        return ipArr;
    }

    public void setIpArr(List<String> ipArr) {
        this.ipArr = ipArr;
    }

    public String getaDeviceName() {
        return aDeviceName;
    }

    public void setaDeviceName(String aDeviceName) {
        this.aDeviceName = aDeviceName;
    }

    public String getaDeviceInterval() {
        return aDeviceInterval;
    }

    public void setaDeviceInterval(int aDeviceInterval) {
        this.aDeviceInterval = String.valueOf(aDeviceInterval);
    }

    public String getaDeviceAlert() {
        return aDeviceAlert;
    }

    public void setaDeviceAlert(double aDeviceAlert) {
        this.aDeviceAlert = String.valueOf(aDeviceAlert);
    }

    public String getaDeviceLine() {
        return aDeviceLine;
    }

    public void setaDeviceLine(int aDeviceLine) {
        this.aDeviceLine = String.valueOf(aDeviceLine);
    }

    public String getTempRangeMin() {
        return tempRangeMin;
    }

    public void setTempRangeMin(double tempRangeMin) {
        this.tempRangeMin = String.valueOf(tempRangeMin);
    }

    public String getTempRangeMax() {
        return tempRangeMax;
    }

    public void setTempRangeMax(double tempRangeMax) {
        this.tempRangeMax = String.valueOf(tempRangeMax);
    }

    public String getAmmoniaRangeMin() {
        return ammoniaRangeMin;
    }

    public void setAmmoniaRangeMin(double ammoniaRangeMin) {
        this.ammoniaRangeMin = String.valueOf(ammoniaRangeMin);
    }

    public String getAmmoniaRangeMax() {
        return ammoniaRangeMax;
    }

    public void setAmmoniaRangeMax(double ammoniaRangeMax) {
        this.ammoniaRangeMax = String.valueOf(ammoniaRangeMax);
    }

    public String getNitrogenRangeMin() {
        return nitrogenRangeMin;
    }

    public void setNitrogenRangeMin(double nitrogenRangeMin) {
        this.nitrogenRangeMin = String.valueOf(nitrogenRangeMin);
    }

    public String getNitrogenRangeMax() {
        return nitrogenRangeMax;
    }

    public void setNitrogenRangeMax(double nitrogenRangeMax) {
        this.nitrogenRangeMax = String.valueOf(nitrogenRangeMax);
    }

    public String getConcentrationRangeMin() {
        return concentrationRangeMin;
    }

    public void setConcentrationRangeMin(double concentrationRangeMin) {
        this.concentrationRangeMin = String.valueOf(concentrationRangeMin);
    }

    public String getConcentrationRangeMax() {
        return concentrationRangeMax;
    }

    public void setConcentrationRangeMax(double concentrationRangeMax) {
        this.concentrationRangeMax = String.valueOf(concentrationRangeMax);
    }

    public String getHumRangeMin() {
        return humRangeMin;
    }

    public void setHumRangeMin(double humRangeMin) {
        this.humRangeMin = String.valueOf(humRangeMin);
    }

    public String getHumRangeMax() {
        return humRangeMax;
    }

    public void setHumRangeMax(double humRangeMax) {
        this.humRangeMax = String.valueOf(humRangeMax);
    }

    public String getNoiseRangeMin() {
        return noiseRangeMin;
    }

    public void setNoiseRangeMin(double noiseRangeMin) {
        this.noiseRangeMin = String.valueOf(noiseRangeMin);
    }

    public String getNoiseRangeMax() {
        return noiseRangeMax;
    }

    public void setNoiseRangeMax(double noiseRangeMax) {
        this.noiseRangeMax = String.valueOf(noiseRangeMax);
    }

    public String getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(double rangeMin) {
        this.rangeMin = String.valueOf(rangeMin);
    }

    public String getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(double rangeMax) {
        this.rangeMax = String.valueOf(rangeMax);
    }

    public String getElectricMin() {
        return electricMin;
    }

    public void setElectricMin(double electricMin) {
        this.electricMin = String.valueOf(electricMin);
    }

    public String getElectricMax() {
        return electricMax;
    }

    public void setElectricMax(double electricMax) {
        this.electricMax = String.valueOf(electricMax);
    }

    public String getPhaseAMin() {
        return phaseAMin;
    }

    public void setPhaseAMin(String phaseAMin) {
        this.phaseAMin = phaseAMin;
    }

    public String getPhaseAMax() {
        return phaseAMax;
    }

    public void setPhaseAMax(String phaseAMax) {
        this.phaseAMax = phaseAMax;
    }

    public String getVoltageAMin() {
        return voltageAMin;
    }

    public void setVoltageAMin(String voltageAMin) {
        this.voltageAMin = voltageAMin;
    }

    public String getVoltageAMax() {
        return voltageAMax;
    }

    public void setVoltageAMax(String voltageAMax) {
        this.voltageAMax = voltageAMax;
    }

    public String getPhaseBMin() {
        return phaseBMin;
    }

    public void setPhaseBMin(String phaseBMin) {
        this.phaseBMin = phaseBMin;
    }

    public String getPhaseBMax() {
        return phaseBMax;
    }

    public void setPhaseBMax(String phaseBMax) {
        this.phaseBMax = phaseBMax;
    }

    public String getVoltageBMin() {
        return voltageBMin;
    }

    public void setVoltageBMin(String voltageBMin) {
        this.voltageBMin = voltageBMin;
    }

    public String getVoltageBMax() {
        return voltageBMax;
    }

    public void setVoltageBMax(String voltageBMax) {
        this.voltageBMax = voltageBMax;
    }

    public String getPhaseCMin() {
        return phaseCMin;
    }

    public void setPhaseCMin(String phaseCMin) {
        this.phaseCMin = phaseCMin;
    }

    public String getPhaseCMax() {
        return phaseCMax;
    }

    public void setPhaseCMax(String phaseCMax) {
        this.phaseCMax = phaseCMax;
    }

    public String getVoltageCMin() {
        return voltageCMin;
    }

    public void setVoltageCMin(String voltageCMin) {
        this.voltageCMin = voltageCMin;
    }

    public String getVoltageCMax() {
        return voltageCMax;
    }

    public void setVoltageCMax(String voltageCMax) {
        this.voltageCMax = voltageCMax;
    }

    public String getOxygenRangeMin() {
        return oxygenRangeMin;
    }

    public void setOxygenRangeMin(double oxygenRangeMin) {
        this.oxygenRangeMin = String.valueOf(oxygenRangeMin);
    }

    public String getOxygenRangeMax() {
        return oxygenRangeMax;
    }

    public void setOxygenRangeMax(double oxygenRangeMax) {
        this.oxygenRangeMax = String.valueOf(oxygenRangeMax);
    }
}
