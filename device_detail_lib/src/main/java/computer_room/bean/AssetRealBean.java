package computer_room.bean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/4 14:43
 * Description：资产管理实时数据
 **/
public class AssetRealBean {


    /**
     * mId : 11
     * name : 1号设备
     * status : 0
     * gallery : 1
     * emdId : 1
     */

    private int mId;
    private String name;
    private int status;
    private int gallery;
    private int emdId;

    public int getMId() {
        return mId;
    }

    public void setMId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGallery() {
        return gallery;
    }

    public void setGallery(int gallery) {
        this.gallery = gallery;
    }

    public int getEmdId() {
        return emdId;
    }

    public void setEmdId(int emdId) {
        this.emdId = emdId;
    }
}
