package bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * 报警信息
 */

@SmartTable(name = "报警消息")
public class AlertBeans {


    /**
     * ehaId : 166
     * dId : 0
     * diId : 1
     * ehaInfo : 检测温度27.6°C超过报警阈值20.0°C。
     * ehaTime : 2019-10-15 16:18:21
     */

    private int ehaId;
    private int dId;
    private int diId;
    @SmartColumn(id = 1, name = "设备名称", autoMerge = true)
    private String ecmDeviceName;
    @SmartColumn(id = 2, name = "报警信息")
    private String ehaInfo;
    @SmartColumn(id = 3, name = "报警时间", autoMerge = true)
    private String ehaTime;

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getEcmDeviceName() {
        return ecmDeviceName;
    }

    public void setEcmDeviceName(String ecmDeviceName) {
        this.ecmDeviceName = ecmDeviceName;
    }

    public int getEhaId() {
        return ehaId;
    }

    public void setEhaId(int ehaId) {
        this.ehaId = ehaId;
    }

    public int getDId() {
        return dId;
    }

    public void setDId(int dId) {
        this.dId = dId;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public String getEhaInfo() {
        return ehaInfo;
    }

    public void setEhaInfo(String ehaInfo) {
        this.ehaInfo = ehaInfo;
    }

    public String getEhaTime() {
        return ehaTime;
    }

    public void setEhaTime(String ehaTime) {
        this.ehaTime = ehaTime;
    }
}
