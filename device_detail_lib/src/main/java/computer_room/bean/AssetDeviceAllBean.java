package computer_room.bean;

public class AssetDeviceAllBean {

    /**
     * emdId : 1
     * emdName : 资产管理1
     * address : 1
     * number : 1
     * gId : 2
     * diId : 46
     */

    private int emdId;
    private String emdName;
    private String diAddress;
    private int address;
    private int number;
    private int gId;
    private int diId;

    public String getDiAddress() {
        return diAddress;
    }

    public void setDiAddress(String diAddress) {
        this.diAddress = diAddress;
    }

    public int getEmdId() {
        return emdId;
    }

    public void setEmdId(int emdId) {
        this.emdId = emdId;
    }

    public String getEmdName() {
        return emdName;
    }

    public void setEmdName(String emdName) {
        this.emdName = emdName;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public int getDiId() {
        return diId;
    }

    public void setDiId(int diId) {
        this.diId = diId;
    }
}
