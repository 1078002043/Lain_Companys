package bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * 日志管理中的Excel表格
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/16 13 : 14
 */
@SmartTable(name = "日志管理")
public class LogManageTable {
    // autoMerge = true  有相同数据时，自动合并
    @SmartColumn(id = 3, name = "日志类型")
    private String logType;
    @SmartColumn(id = 2, name = "日志模块")
    private String modual;
    @SmartColumn(id = 3, name = "日志内容")
    private String description;
    @SmartColumn(id = 1, name = "操作人员", autoMerge = true)
    private String username;
    @SmartColumn(id = 5, name = "登入IP")
    private String logIp;
    @SmartColumn(id = 6, name = "操作时间")
    private String createTime;

    public LogManageTable(String logType, String modual, String description, String username, String logIp, String createTime) {
        this.logType = logType;
        this.modual = modual;
        this.description = description;
        this.username = username;
        this.logIp = logIp;
        this.createTime = createTime;
    }

    public String getLogType() {
        return logType;
    }

    public String getModual() {
        return modual;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    public String getLogIp() {
        return logIp;
    }

    public String getCreateTime() {
        return createTime;
    }
}
