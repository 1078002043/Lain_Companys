package computer_room.bean;

import java.io.Serializable;

/**
 * 分体空调 BEAN
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/11 20 : 58
 */
public class SplitAirBean implements Serializable {


    /**
     * efmId : 1
     * efmAddress : 1
     * efmName : 分体空调
     * efmStudyOnOrder : S01001
     * efmStudyOffOrder : S01002
     * efmOnOrder : F01001
     * efmOffOrder : F01002
     * efmStatus : 2
     * diId : 33
     * gId : 2
     */

    private int efmId;
    private int efmAddress;
    private String efmName;
    private String efmStudyOnOrder;
    private String efmStudyOffOrder;
    private String efmOnOrder;
    private String efmOffOrder;
    private int efmStatus;
    private int diId;
    private int gId;

    public int getEfmId() {
        return efmId;
    }

    public void setEfmId(int efmId) {
        this.efmId = efmId;
    }

    public int getEfmAddress() {
        return efmAddress;
    }

    public void setEfmAddress(int efmAddress) {
        this.efmAddress = efmAddress;
    }

    public String getEfmName() {
        return efmName;
    }

    public void setEfmName(String efmName) {
        this.efmName = efmName;
    }

    public String getEfmStudyOnOrder() {
        return efmStudyOnOrder;
    }

    public void setEfmStudyOnOrder(String efmStudyOnOrder) {
        this.efmStudyOnOrder = efmStudyOnOrder;
    }

    public String getEfmStudyOffOrder() {
        return efmStudyOffOrder;
    }

    public void setEfmStudyOffOrder(String efmStudyOffOrder) {
        this.efmStudyOffOrder = efmStudyOffOrder;
    }

    public String getEfmOnOrder() {
        return efmOnOrder;
    }

    public void setEfmOnOrder(String efmOnOrder) {
        this.efmOnOrder = efmOnOrder;
    }

    public String getEfmOffOrder() {
        return efmOffOrder;
    }

    public void setEfmOffOrder(String efmOffOrder) {
        this.efmOffOrder = efmOffOrder;
    }

    public int getEfmStatus() {
        return efmStatus;
    }

    public void setEfmStatus(int efmStatus) {
        this.efmStatus = efmStatus;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }
}
