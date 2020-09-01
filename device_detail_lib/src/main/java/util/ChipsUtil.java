package util;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.nex3z.flowlayout.FlowLayout;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import bean.LoginJSONBean;
import bean.LoginUserInfoBean;
import computer_room.bean.CharacterManageBean;
import computer_room.bean.NewUserManageBean;
import computer_room.bean.UserCharacterBean;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import i_v.UserUpdateListener;

/**
 * @ClassName: ChipsUtil
 * @Description: 标签视图的工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/15 10:58
 */
public class ChipsUtil implements OkHttpCallBack {

    private static ChipsUtil chipsUtil;
    //流式布局
    private FlowLayout flowLayout;
    private Context context;
    //保存每个用户的权限列表
    private List<NewUserManageBean.RoleListBean> tagTexts;
    //保存位置
    private int pos;

    //删除 Tag 成功后，通过接口回调给调用者
    private UserUpdateListener i_adapterCall;

    //角色管理的回调
    private I_AdapterCall i_adapterCharacterCall;

    //角色管理中的权限更新
    private CharacterManageBean data;

    //用户角色
    private List<UserCharacterBean> userRoleEditBeans = new ArrayList<>();

    //单例
    public static ChipsUtil getInstance() {

        if (chipsUtil == null)
            synchronized (ChipsUtil.class) {
                if (chipsUtil == null)
                    chipsUtil = new ChipsUtil();
            }

        return chipsUtil;
    }

    /**
     * 动态添加 Chip View
     *
     * @param context    动态添加View时需要使用到的 上下文
     * @param tagTexts   所有的标签内容
     * @param flowLayout 流式布局，自动换行
     */
    public void addChipView(Context context, List<String> tagTexts, FlowLayout flowLayout) {

        if (flowLayout == null)
            throw new NullPointerException("FlowLayout is Null");

        //保存相关实例
        this.flowLayout = flowLayout;
        this.context = context;

    }

    /**
     * 动态添加 Chip View
     *
     * @param context    动态添加View时需要使用到的 上下文
     * @param tagTexts   所有的标签内容
     * @param flowLayout 流式布局，自动换行
     * @param position
     * @param id         用户ID
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addChipViewObj(Context context, List<NewUserManageBean.RoleListBean> tagTexts, FlowLayout flowLayout, int position, int id, UserUpdateListener i_adapterCall, String url) {

        if (flowLayout == null)
            throw new NullPointerException("FlowLayout is Null");

        //保存相关实例
        this.flowLayout = flowLayout;
        this.context = context;
        //保存每个用户的列表
        this.tagTexts = tagTexts;
        this.pos = position;
        //保存适配器操作成功回调
        this.i_adapterCall = i_adapterCall;

        //获取调用者需要设置的 TagView
        for (NewUserManageBean.RoleListBean tag :
                tagTexts) {

            getChips(tag.getDesc_(), id, url, null, position);

        }

    }

    /**
     * 动态添加 Chip View
     *
     * @param context 动态添加View时需要使用到的 上下文
     * @param id
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addChipViewRole(Context context, List<CharacterManageBean.PermissionsBean> permissions, FlowLayout roleTagView, int id, String url, CharacterManageBean data, I_AdapterCall i_adapterCall) {

        if (flowLayout == null)
            throw new NullPointerException("FlowLayout is Null");

        //保存相关实例
        this.flowLayout = roleTagView;
        this.context = context;

        //保存角色管理中的实例
        this.data = data;

        //保存适配器操作成功回调
        this.i_adapterCharacterCall = i_adapterCall;

        //获取调用者需要设置的 TagView
        for (CharacterManageBean.PermissionsBean tag :
                permissions) {

            getChips(tag.getDesc_(), id, url, data, 0);

        }

    }

    /**
     * 动态添加 Chip View
     *
     * @param context 动态添加View时需要使用到的 上下文
     * @param id
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void addChipViewUser(Context context, List<LoginUserInfoBean.RoleListBean> roleListBeans, FlowLayout roleTagView) {

        if (roleTagView == null)
            throw new NullPointerException("FlowLayout is Null");

        //保存相关实例
        this.flowLayout = roleTagView;
        this.context = context;

        //获取调用者需要设置的 TagView
        for (LoginUserInfoBean.RoleListBean tag :
                roleListBeans) {
            Log.d("lkjldff", "addChipViewUser: " + tag.getDesc_());
            getChips(tag.getDesc_());

        }

    }

    /**
     * 将 Chip 动态添加到 FlowLayout 中，只显示，不做点击操作
     *
     * @param tagText
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getChips(String tagText) {

        final Chip chip = new Chip(context);

        chip.setText(tagText);
        chip.setChipTextColor(Color.WHITE);

        chip.setChipCloseColor(Color.WHITE);
        chip.setCornerRadius(50);

        //根据不同权限设置不同颜色
        setChipBg(chip, tagText);

        //设置 Chip 的样式
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        //添加 Chip
        flowLayout.addView(chip, params);

    }


    /**
     * 将 Chip 动态添加到 FlowLayout 中
     *
     * @param tagText 标签值
     * @param id      标签ID，暂时没用
     * @param url     删除标签的URL
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getChips(String tagText, int id, String url, CharacterManageBean characterData, int position) {

        final Chip chip = new Chip(context);

        chip.setText(tagText);
        chip.setChipTextColor(Color.WHITE);

        chip.setChipCloseColor(Color.WHITE);
        chip.setCornerRadius(50);

        //根据不同权限设置不同颜色
        setChipBg(chip, tagText);

        //设置 Chip 的样式
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);

        //标签的点击
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chip.setClosable(!chip.getClosable());
            }
        });

        //删除按钮的点击事件

        chip.setOnCloseClickListener(v -> {

            if (url == null) {
                i_adapterCall.refreshUserInfo(chip.getText().toString(), position);
            } else {

                Map<String, String> map = new HashMap<>();
                String characterUrl = LainNewApi.getInstance().getRootPath() + url;
                Map<String, String> characterMap = new HashMap<>();
                map.put("name", characterData.getName());
                map.put("desc_", characterData.getDesc_());
                map.put("id", String.valueOf(characterData.getId()));
                //记录角色拥有的权限
                List<String> characterRoleId = new ArrayList<>();

                //获取用户拥有的权限
                List<CharacterManageBean.PermissionsBean> permissionsBeans = characterData.getPermissions();

                //保存匹配的数据所有位置
                int delPos = 0;

                for (int i = 0; i < permissionsBeans.size(); i++) {

                    //如果获取到角色权限的ID，则记录
                    if (!chip.getText().toString().equals(permissionsBeans.get(i).getDesc_())) {

                        //保存拥有的权限ID
                        characterRoleId.add(String.valueOf(permissionsBeans.get(i).getId()));

                    } else {
                        //如果匹配，则保存位置，以供后期使用
                        delPos = i;
                    }

                }

                //移除需要删除的权限
                permissionsBeans.remove(delPos);

                //转成字符串
                String roleStr = "[" + String.join(",", characterRoleId) + "]";

                map.put("permissionIds", roleStr);

                //转成JSON
                String json = OkHttpUtil.getInstance().mapToRow(map);
                //将字符串转成数组，因为角色拥有的权限是以数据传递的
                json = json.replace("\"[", "[");
                json = json.replace("]\"", "]");

                OkHttpUtil.getInstance().sendPutOkHttp("updateCharacter", LainNewApi.getInstance().getRootPath() + url, json, this);


            }

        });

        //添加 Chip
        flowLayout.addView(chip, params);

    }

    /**
     * 更新用户的权限
     *
     * @param url
     * @param id
     */
    private void updateAction(String updateStr, int id) {

        Map<String, String> map = new HashMap<>();

        String actionUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.updateUserRole + id + "/" + updateStr;

        OkHttpUtil.getInstance().sendPutOkHttp("updateUserRole", actionUrl, OkHttpUtil.getInstance().mapToRow(map), this);

    }

    //修改Chip颜色
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void setChipBg(Chip chip, String tagText) {

        switch (tagText) {
            case "普通管理员":
                chip.setChipBackgroundColor(Color.BLUE);
                break;
            case "超级管理员":
                chip.setChipBackgroundColor(Color.RED);
                break;
            case "普通用户":
                chip.setChipBackgroundColor(Color.GREEN);
                break;
            case "添加权限":
                chip.setChipBackgroundColor(Color.BLUE);
                break;
            case "添加角色":
                chip.setChipBackgroundColor(Color.BLUE);
                break;
            case "权限管理":
                chip.setChipBackgroundColor(Color.BLUE);
                break;
            case "查看用户列表":
                chip.setChipBackgroundColor(Color.GREEN);
                break;
            case "角色管理":
                chip.setChipBackgroundColor(Color.RED);
                break;
            case "删除用户":
                chip.setChipBackgroundColor(Color.RED);
                break;
            case "用户管理":
                chip.setChipBackgroundColor(Color.RED);
                break;
            case "修改用户信息":
                chip.setChipBackgroundColor(Color.RED);
                break;
            case "增加用户信息":
                chip.setChipBackgroundColor(Color.RED);
                break;
            default:
                chip.setChipBackgroundColor(Color.WHITE);
                chip.setChipTextColor(Color.parseColor("#757575"));
                chip.setStrokeSize(5);
                chip.setStrokeColor(Color.parseColor("#5197FF"));
                chip.setChipCloseColor(Color.parseColor("#c4c9cd"));
                break;
        }

    }


    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        switch (requestTag) {
            case "updateUserRole":

                //删除用户权限成功
                LoginJSONBean upUserRoleBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);
                if (upUserRoleBean.getZt()) {

                    ToastManage.getInstance().toastShortShow("删除用户权限成功");

                }

                break;
            case "updateCharacter":

                //删除用户权限成功
                LoginJSONBean characterBean = MyGson.getInstance().fromJson(responseStr, LoginJSONBean.class);
                if (characterBean.getZt()) {

                    ToastManage.getInstance().toastShortShow("删除用户权限成功");
                    //回调接口
                    i_adapterCharacterCall.actionCall();

                }

                break;
        }


    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }
}
