package i_v;

/**
 * @ClassName: UserUpdateLisener
 * @Description: 用户更新数据成功后，刷新数据
 * @Author: YIN LUO FEI
 * @Date: 2020/7/29 14:03
 */
public interface UserUpdateListener {

    /**
     * 刷新用户数据
     * @param s 删除的权限内容
     * @param pos 删除权限所处的位置
     */
    void refreshUserInfo(String s, int pos);

}
