package bean;

import android.view.View;
import android.widget.Button;

/**
 * @ClassName: AlertQueryBean
 * @Description: 查询报警消息查询方式
 * @Author: YIN LUO FEI
 * @Date: 2020/7/28 9:11
 */
public class AlertQueryBean {

    // 全部，已读，未读
    private View actionBtn;
    // 查询的状态码  0 = 全部， 1 = 已读， 2 = 未读
    private int queryStatus;

    public void setActionBtn(View actionBtn) {
        this.actionBtn = actionBtn;
    }

    public void setQueryStatus(int queryStatus) {
        this.queryStatus = queryStatus;
    }

    public View getActionBtn() {
        return actionBtn;
    }

    public int getQueryStatus() {
        return queryStatus;
    }
}
