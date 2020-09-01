package view;

import java.util.ArrayList;
import java.util.List;

import top.defaults.view.PickerView;

/**
 * @ClassName: IpItem
 * @Description: 自动获取IP后，如果有多个IP，则通过 PickerView 显示给用户选择IP
 * @Author: YIN LUO FEI
 * @Date: 2020/7/9 12:56
 */
public class IpItem implements PickerView.PickerItem{


    private String ip;
    private String port;

    public IpItem(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String getText() {
        return ip + " : " + port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

}
