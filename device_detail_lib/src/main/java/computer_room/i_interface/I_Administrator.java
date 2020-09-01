package computer_room.i_interface;

import java.util.List;

import computer_room.bean.CharacterManageBean;
import computer_room.bean.NewUserManageBean;
import computer_room.bean.RoleManageBean;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/2 8:26
 * Description：用户管理VIEW 接口
 **/
public interface I_Administrator {
    /**
     * 设置管理员
     * @param newUserManageBeans 管理员列表
     */
    public void getAllUserInfo(List<NewUserManageBean> newUserManageBeans);

    //获取所有的角色信息
    public void getRolePermission(List<CharacterManageBean> newRoleManageBeanList);

    //获取所有权限
    public void getRoleManage(List<RoleManageBean> roleManageBeans);

    /**
     * 更新权限的结果
     * @param response
     */
    public void updatePermission(boolean response);

    /**
     * 添加用户成功
     */
    public void addUserSuccess();

    /**
     * 添加用户失败
     */
    public void addUserFailed();

    /**
     * 删除用户结果
     * @param delResult 删除用户的结果
     */
    public void delUserResponse(String delResult);

    /**
     * 更新用户结果
     * @param updateResult 更新用户的结果
     */
    public void updateUserResponse(String updateResult);

    public void deleteRole(boolean result);

    /**
     * 插入权限
     * @param zt 插入的权限
     */
    void insertRole(boolean zt);

    /**
     * 插入角色
     * @param responseStr 插入的结果
     */
    void insertCharacter(String responseStr);

    /**
     * 删除角色
     * @param responseStr 删除角色的结果
     */
    void deleteCharacter(String responseStr);

    /**
     * 更新角色
     * @param responseStr 更新角色的结果
     */
    void updateCharacter(boolean responseStr);

    /**
     * 更新用户的权限
     * @param zt 更新的结果
     */
    void updateUserRole(boolean zt);
}
