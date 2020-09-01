package computer_room.i_interface;

/**
 * @ClassName: I_AlertMethod
 * @Description: 报警设置的报警方式回调接口
 * @Author: YIN LUO FEI
 * @Date: 2020/7/21 9:46
 */
public interface I_AlertMethod {

    void updateAlertMethod(String s, boolean isChecked, int position);

}
