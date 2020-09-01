package util;

import org.litepal.crud.LitePalSupport;

/**
 * APP 的一些配置
 * 保存在数据库中
 */
public class APPConfig extends LitePalSupport {

    private boolean device_listInit;
    //字段标志
    private String flag;
    private int id;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDevice_listInit() {
        return device_listInit;
    }

    public void setDevice_listInit(boolean device_listInit) {
        this.device_listInit = device_listInit;
    }
}
