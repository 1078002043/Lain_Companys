package computer_room.bean;

/**
 * 8052设备的报警Bean类
 */
public class Alert8052Bean {


    /**
     * ekaId : 33
     * ekmId : 25
     * ekmName : 非定位漏水
     * ekaTime : 2019-10-18 19:53:56
     * gallery : DI1
     * diId : 29
     */

    private int ekaId;
    private int ekmId;
    private String ekmName;
    private String ekaTime;
    private String gallery;
    private int diId;

    public int getEkaId() {
        return ekaId;
    }

    public void setEkaId(int ekaId) {
        this.ekaId = ekaId;
    }

    public int getEkmId() {
        return ekmId;
    }

    public void setEkmId(int ekmId) {
        this.ekmId = ekmId;
    }

    public String getEkmName() {
        return ekmName;
    }

    public void setEkmName(String ekmName) {
        this.ekmName = ekmName;
    }

    public String getEkaTime() {
        return ekaTime;
    }

    public void setEkaTime(String ekaTime) {
        this.ekaTime = ekaTime;
    }

    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }
}
