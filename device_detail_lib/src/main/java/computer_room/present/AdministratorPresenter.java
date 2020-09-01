package computer_room.present;

import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.BasePresenter;
import bean.LoginJSONBean;
import computer_room.bean.CharacterManageBean;
import computer_room.bean.NewUserManageBean;
import computer_room.bean.RoleManageBean;
import computer_room.i_interface.I_Administrator;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.LainNewApi;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/12/2 8:25
 * Description：用户管理 Presenter
 **/
public class AdministratorPresenter extends BasePresenter implements OkHttpCallBack {

    private I_Administrator i_administrator;

    public AdministratorPresenter(I_Administrator i_administrator) {
        this.i_administrator = i_administrator;
    }

    /**
     * 更新权限管理的信息
     */
    public void updatePermission(Map<String, String> map) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.updateRole;

        OkHttpUtil.getInstance().sendPutOkHttp("updatePermission", url, OkHttpUtil.getInstance().mapToRow(map), this);

    }

    /**
     * 请求用户列表
     */
    public void getUserRoleInfo() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.userRoleInfo;
//        String url = LainNewApi.NEW_IP + LainNewApi.adminUrl;
        OkHttpUtil.getInstance().sendGetOkHttp("userRoleInfo", url, this);
    }

    /**
     * 请求角色管理
     */
    public void getRolePermission() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.rolePermissions;
//        String url = LainNewApi.NEW_IP + LainNewApi.adminUrl;
        OkHttpUtil.getInstance().sendGetOkHttp("rolePermission", url, this);
    }

    /**
     * 请求权限管理
     */
    public void getRoleManage() {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.fileAllRole;
        OkHttpUtil.getInstance().sendGetOkHttp("allRole", url, this);
    }


    /**
     * 添加新用户
     *
     * @param userInfo 用户的信息
     */
    public void addNewUser(Map<String, String> userInfo, File file) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.addNewUserIcon;

        String json = OkHttpUtil.getInstance().mapToRow(userInfo);

        //将字符串转成数组，因为角色拥有的权限是以数据传递的
        json = json.replace("\"[", "[");
        json = json.replace("]\"", "]");

        Log.d("poipryfg", "addNewUser: " + json);

        OkHttpUtil.getInstance().sendRowOkHttp("addNewUser", url, userInfo, this, file);

    }

    /**
     * 删除用户
     */
    public void deleteUserByUid(int userId) {
        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.delUser + userId;
        OkHttpUtil.getInstance().sendDeletedOkHttp("deleteUser", url, this);
    }

    /**
     * 更新用户
     */
    public void updateUserById(String id, String manageCharacter) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.updateUser + "/" + id + "/" + manageCharacter;
        Log.d("opiopirt", "updateUserByUid: " + url);
//        String json = OkHttpUtil.getInstance().mapToRow(userInfo);
//
//        //将字符串转成数组，因为角色拥有的权限是以数据传递的
//        json = json.replace("\"[", "[");
//        json = json.replace("]\"", "]");
//
//        Log.d("kpopofiopg", "updateUserByUid: " + json);

        OkHttpUtil.getInstance().sendPutOkHttp("updateUser", url, "", this);
    }

    /**
     * 网络请求回调
     *
     * @param requestTag  请求的 标识
     * @param requestUrl  请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */
    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
        switch (requestTag) {
            case "updatePermission":

                LoginJSONBean updateBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);

                i_administrator.updatePermission(updateBean.getZt());

                break;
            case "userRoleInfo":
                //更新用户列表
                List<NewUserManageBean> userManageBeans = OkHttpUtil.getInstance().formatResponse(responseStr, NewUserManageBean.class);
                i_administrator.getAllUserInfo(userManageBeans);
                break;
            case "rolePermission":
                //获取角色列表
                List<CharacterManageBean> roleManageBeans = OkHttpUtil.getInstance().formatResponse(responseStr, CharacterManageBean.class);
                i_administrator.getRolePermission(roleManageBeans);
                break;
            case "allRole":
                //获取权限管理
                List<RoleManageBean> roleManageBeanList = OkHttpUtil.getInstance().formatResponse(responseStr, RoleManageBean.class);
                i_administrator.getRoleManage(roleManageBeanList);
                break;
            case "addNewUser":
                //判断是否添加成功

//                if (responseStr.equals("true")) {
//                    //成功
//                    i_administrator.addUserSuccess();
//                } else {
//                    //失败
//                    i_administrator.addUserFailed();
//                }
                Log.d("kljljdfer", "httpRequestSuccess: " + responseStr);
                //成功
                i_administrator.addUserSuccess();

                break;
            case "deleteUser":
                i_administrator.delUserResponse(responseStr);
                break;
            case "updateUser":
                Log.d("opiopirt", "httpRequestSuccess: " + responseStr);
                i_administrator.updateUserResponse(responseStr);
                break;
            case "deleteRole":

                LoginJSONBean deleteBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);
                i_administrator.deleteRole(deleteBean.getZt());

                break;
            case "insertRole":

                LoginJSONBean insertBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);
                i_administrator.insertRole(insertBean.getZt());

                break;
            case "insertCharacter":

                i_administrator.insertCharacter(responseStr);

                break;
            case "deleteCharacter":

                i_administrator.deleteCharacter(responseStr);

                break;
            case "updateCharacter":

                LoginJSONBean updateCharacterBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);
                i_administrator.updateCharacter(updateCharacterBean.getZt());

                break;
            case "updateUserRole":

                try {
                    i_administrator.updateUserRole(Boolean.parseBoolean(responseStr));
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    /**
     * 删除权限
     *
     * @param id 删除权限对应的ID
     */
    public void deleteRole(int id) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deleteRole + id;
        OkHttpUtil.getInstance().sendDeletedOkHttp("deleteRole", url, this);

    }

    /**
     * 添加权限
     *
     * @param map 添加权限的数据
     */
    public void insertPermission(Map<String, String> map) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.insertRole;
        OkHttpUtil.getInstance().sendRowOkHttp("insertRole", url, OkHttpUtil.getInstance().mapToRow(map), this);

    }

    /**
     * 创建角色
     *
     * @param map
     */
    public void insertCharacter(Map<String, String> map) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.insertCharacter;

        String json = OkHttpUtil.getInstance().mapToRow(map);

        //将字符串转成数组，因为角色拥有的权限是以数据传递的
        json = json.replace("\"[", "[");
        json = json.replace("]\"", "]");

        Log.d("pofghgh", "insertCharacter: " + json);

        OkHttpUtil.getInstance().sendRowOkHttp("insertCharacter", url, json, this);

    }

    /**
     * 删除角色
     *
     * @param id 删除角色的ID
     */
    public void deleteCharacter(int id) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.deleteCharacter + id;
        OkHttpUtil.getInstance().sendDeletedOkHttp("deleteCharacter", url, this);

    }

    public void updateCharacter(Map<String, String> map) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.updateCharacter;
        String json = OkHttpUtil.getInstance().mapToRow(map);

        //将字符串转成数组，因为角色拥有的权限是以数据传递的
        json = json.replace("\"[", "[");
        json = json.replace("]\"", "]");

        OkHttpUtil.getInstance().sendPutOkHttp("updateCharacter", url, json, this);

    }

    public void updateUserRole(Map<String, String> userInfo, File selImgFile) {

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.addNewUserIcon;

        OkHttpUtil.getInstance().sendRowOkHttp("updateUserRole", url, userInfo, this, selImgFile);

    }

    /**
     * 更新角色管理中的权限
     *
     * @param userManageId
     * @param roleStr
     */
    public void updateCharacterRole(int userManageId, String roleStr) {

        Map<String, String> map = new HashMap<>();

        String url = LainNewApi.getInstance().getRootPath() + LainNewApi.updateUserRole + userManageId + "/" + roleStr;
        Log.d("kjkljfklgjl", "updateCharacterRole: " + url);

        OkHttpUtil.getInstance().sendPutOkHttp("changeUserRoles", url, OkHttpUtil.getInstance().mapToRow(map), this);

    }
}
