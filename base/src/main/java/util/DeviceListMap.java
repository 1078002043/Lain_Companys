package util;

import org.litepal.crud.LitePalSupport;

/**
 * 保存单个设备的信息，保存设备列表到数据库中，主要负责设备显示 / 隐藏
 */
public class DeviceListMap extends LitePalSupport {
    //该设备在数据库中的ID
    private int id;
    //是否显示该设备
    private boolean isShow;
    //标题
    private String title;
    //图标地址
    private String imagePath;
    //所属分类
    private String classify;

    public void setId(int id) {
        this.id = id;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public boolean isShow() {
        return isShow;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
