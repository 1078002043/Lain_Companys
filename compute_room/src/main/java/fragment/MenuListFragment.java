package fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.compute_room.R;
import com.example.compute_room.R2;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.flowlayout.FlowLayout;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.LubanOptions;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TImage;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import base.GlideApp;
import base.Lain_Base_Fragment;
import bean.LoginUserInfoBean;
import butterknife.BindView;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import util.ChipsUtil;
import util.LainNewApi;
import util.ToastManage;
import view.Device_Detail;
import view.Lai_Login_View;
import view.MainAlertDetail;

/**
 * 抽屉布局的 Fragment
 * Yin Luo Fei
 * MenuListFragment
 */

public class MenuListFragment extends Lain_Base_Fragment implements NavigationView.OnNavigationItemSelectedListener,
        CompoundButton.OnCheckedChangeListener, OkHttpCallBack, View.OnClickListener, TakePhoto.TakeResultListener, InvokeListener {
    //抽柜布局
    @BindView(R2.id.vNavigation)
    NavigationView vNavigation;

    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    //保存上传的图片文件名称
    private String fileImageName;
    private File selImgFile;


    //Menu View
    private View menuView;

    private View headView;

    //用户名称
    private TextView userName;

    //用户头像
    private ImageView userIcon;

    //用户邮箱
    private TextView userEmail;
    //用户电话
    private TextView userTel;
    private FlowLayout userInfoRole;
    private AlertDialog changeDialog;

    //修改-用户名称
    private EditText userInfoName;
    //用户头像
    private ImageView userInfoIcon;
    //修改-密码
    private EditText userInfoPassword;
    //修改-确认密码
    private EditText userInfoRePassword;
    //修改-电话
    private EditText userInfoTelephone;
    //修改-邮箱
    private EditText userInfoEmail;
    //修改-确定
    private Button userChangeRight;
    //修改-取消
    private Button userChangeCancel;
    //获取到的用户相关信息
    private LoginUserInfoBean loginUserInfoBean;

    //保存用户修改信息，如果更新成功后，更新数据
    private Map<String, String> map;

    //初始化头像更换
    private BottomSheetDialog iconSheet;
    //选择相机
    private LinearLayout camera;
    //选择相册
    private LinearLayout picture;

    //每次点击用户时，记录用户的ID，通过ID来修改指定用户的头像
    private String userID;
    private String TAG = getClass().getName();
    private Bitmap bitmap;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取头部的View
        headView = vNavigation.getHeaderView(0);
        //获取用户名称控件
        userName = headView.findViewById(R.id.user_title);
        //获取用户头像
        userIcon = headView.findViewById(R.id.drawer_head_icon);
        //用户头像点击，显示修改用户信息面板
        userIcon.setOnClickListener(v -> {
            if (map == null) {
                //显示窗口之前，需要获取用户的相关信息
                setUserInfo();
            }
            changeDialog.show();
        });

        //用户电话
        userTel = headView.findViewById(R.id.user_telephone);
        //用户邮箱
        userEmail = headView.findViewById(R.id.user_email);
        //用户拥有的角色
        userInfoRole = headView.findViewById(R.id.user_info_role);

        //修改用户信息，加载自定义VIew Alert
        View loginSettingPanelView = getLayoutInflater().inflate(R.layout.user_info_edit, null);
        //显示自定义的 View
        changeDialog = new AlertDialog.Builder(getActivity())
                .setView(loginSettingPanelView)
                .create();

        //创建好Dialog后，结相关控件进行绑定
        initUserInfoPanel(loginSettingPanelView);

        //初始化头像更换
        iconSheet = new BottomSheetDialog(Objects.requireNonNull(getActivity()));
        View iconView = getLayoutInflater().inflate(R.layout.icon_sheet, null);
        //绑定控件
        camera = iconView.findViewById(R.id.camera);
        picture = iconView.findViewById(R.id.picture);
        //点击监听
        camera.setOnClickListener(this);
        picture.setOnClickListener(this);
        iconSheet.setContentView(iconView);

        //设置 导航栏 的点击事件
        vNavigation.setNavigationItemSelectedListener(this);
    }

    /**
     * 绑定修改资料的相关控件
     *
     * @param loginSettingPanelView 面板的View
     */
    private void initUserInfoPanel(View loginSettingPanelView) {

        //用户名称
        userInfoName = loginSettingPanelView.findViewById(R.id.user_info_name);
        //用户头像
        userInfoIcon = loginSettingPanelView.findViewById(R.id.user_main_icon);

        userInfoIcon.setOnClickListener(v -> {
            //初始化头像更换
            iconSheet.show();

        });

        //用户密码
        userInfoPassword = loginSettingPanelView.findViewById(R.id.user_info_password);
        //用户确认密码
        userInfoRePassword = loginSettingPanelView.findViewById(R.id.user_info_re_password);
        //用户电话
        userInfoTelephone = loginSettingPanelView.findViewById(R.id.user_info_telephone);
        //用户邮箱
        userInfoEmail = loginSettingPanelView.findViewById(R.id.user_info_email);
        //用户确定修改信息
        userChangeRight = loginSettingPanelView.findViewById(R.id.user_change_right);
        userChangeRight.setOnClickListener(v -> {

            //用户确定修改信息，获取用户相关信息
            map = getUserChangeInfo();

            //只更新用户，不更新用户的权限
            String url = LainNewApi.getInstance().getRootPath() + LainNewApi.addNewUserIcon;
            OkHttpUtil.getInstance().sendRowOkHttp("updateUserInfo", url, map, this, selImgFile);

        });

        //用户取消操作
        userChangeCancel = loginSettingPanelView.findViewById(R.id.user_change_cancel);
        userChangeCancel.setOnClickListener(v -> {

            //用户取消修改信息
            changeDialog.hide();

        });

    }

    //以下方法是图片选择需要使用到的
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(getActivity(), type, invokeParam, this);
    }

    /**
     * 获取修改后的信息
     *
     * @return 使用Map保存修改后的信息，再提交给后台
     */
    private Map<String, String> getUserChangeInfo() {

        //修改-密码
        String password = userInfoPassword.getText().toString();
        //修改-确认密码
        String rePassword = userInfoRePassword.getText().toString();

        //修改-用户名称
        String userName = userInfoName.getText().toString();

        //修改-电话
        String userTelephone = userInfoTelephone.getText().toString();
        //修改-邮箱
        String userEmail = userInfoEmail.getText().toString();

        Map<String, String> userInfo = new HashMap();

        //密码和确定密码都不为才会执行
        if (!password.isEmpty() && !rePassword.isEmpty()) {

            boolean result = password.equals(rePassword);
            //如果两次密码正确
            if (result) {
                //添加修改密码的参数
                userInfo.put("password", password);

            } else {
                ToastManage.getInstance().toastShortShow("两次输入的密码不一致，请重新输入");
            }


        }

        //收集修改后的信息
        userInfo.put("username", userName);
        userInfo.put("telephone", userTelephone);
        userInfo.put("id", String.valueOf(loginUserInfoBean.getId()));
        userInfo.put("email", userEmail);
        userInfo.put("gId", "1");
        userInfo.put("id", userID);
        userInfo.put("icon", selImgFile.getName());

        return userInfo;

    }

    /**
     * 返回Fragment Layout ID
     *
     * @param inflater           加载视图
     * @param container          视图容器
     * @param savedInstanceState 临时保存数据
     * @return View
     */
    @Override
    protected View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //绑定抽屉布局的 View
        return menuView = inflater.inflate(R.layout.fragment_menu, container, false);
    }

    /**
     * 抽柜的点击事件
     *
     * @param menuItem menu item
     * @return 是否处理事件成功
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        //切换menu id
        int itemId = menuItem.getItemId();

        if (itemId == R.id.menu_about) {

            //关于我们
            startActivityIntent(AboutMe.class);

        } else if (itemId == R.id.menu_exit_login) {

            //回到登陆页面
            startActivityIntent(Lai_Login_View.class);

            //退出登陆之前，需要将所有的Cookie清空
            OkHttpUtil.getInstance().cookiesManager.removeAllCookies();

            //退出登陆
            Objects.requireNonNull(getActivity()).finish();

        } else if (itemId == R.id.menu_alert_message) {

            //报警信息
            startActivityIntent(MainAlertDetail.class);

        } else if (itemId == R.id.change_user) {

            if (map == null) {
                //显示窗口之前，需要获取用户的相关信息
                setUserInfo();
            }

            //显示修改用户信息的弹窗
            changeDialog.show();

        } else if (itemId == R.id.menu_user_manage) {

            //进入用户管理页面
            Intent intent = new Intent(getActivity(), Device_Detail.class);
            intent.putExtra("device_tag", "系统管理");
            intent.putExtra("device_name", "用户管理");

            //保存相关数据
            LainNewApi.INTENT_TAG = "用户管理";
            LainNewApi.DEVICE_TAG = "系统管理";

            Objects.requireNonNull(getActivity()).startActivity(intent);

        }

        return true;
    }

    /**
     * 设置用户的相关信息
     */
    private void setUserInfo() {

        //如果获取不到用户信息，直接返回
        if (loginUserInfoBean == null)
            return;

        //设置用户头像
        GlideApp.with(this).load(LainNewApi.getInstance().getRootPath() + loginUserInfoBean.getIcon())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(userInfoIcon);
        //设置用户名称
        userInfoName.setText(loginUserInfoBean.getUsername());
        //设置用户电话
        userInfoTelephone.setText(loginUserInfoBean.getTelephone());
        //设置用户邮箱
        userInfoEmail.setText(loginUserInfoBean.getEmail());

    }

    //跳转页面
    private void startActivityIntent(Class className) {
        Intent intent = new Intent(getActivity(), className);
        Objects.requireNonNull(getActivity()).startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    /**
     * 展示用户的信息
     *
     * @param loginUserInfoBean 显示用户的相关数据
     */
    public void showUserInfo(LoginUserInfoBean loginUserInfoBean) {

        //保存获取到的用户相关信息
        this.loginUserInfoBean = loginUserInfoBean;

        GlideApp.with(this).load(LainNewApi.getInstance().getRootPath() + loginUserInfoBean.getIcon())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(userIcon);
        userName.setText(loginUserInfoBean.getUsername());

        userTel.setText(loginUserInfoBean.getTelephone());
        //用户邮箱
        userEmail.setText(loginUserInfoBean.getEmail());

        //设置用户电话图标
        Drawable telDrawable = getActivity().getDrawable(R.mipmap.user_icon);
        telDrawable.setBounds(0, 0, 40, 40);
        userTel.setCompoundDrawables(telDrawable, null, null, null);
        userTel.setCompoundDrawablePadding(15);

        //设置用户邮箱图片
        Drawable emailDrawable = getActivity().getDrawable(R.mipmap.user_email);
        emailDrawable.setBounds(0, 0, 44, 44);
        userEmail.setCompoundDrawables(emailDrawable, null, null, null);
        userEmail.setCompoundDrawablePadding(15);

        //设置用户名图标
        Drawable nameDrawable = getActivity().getDrawable(R.mipmap.name_icon);
        nameDrawable.setBounds(0, 0, 44, 44);
        userName.setCompoundDrawables(nameDrawable, null, null, null);
        userName.setCompoundDrawablePadding(15);

        //获取用户ID
        userID = String.valueOf(loginUserInfoBean.getId());

        //如果用户拥有权限，则在Menu列表中显示用户所拥有的权限
        if (loginUserInfoBean.getRoleList() != null) {

            //动态添加用户的权限
            ChipsUtil.getInstance().addChipViewUser(getActivity(), loginUserInfoBean.getRoleList(), userInfoRole);

        }

    }

    @Override
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

        switch (requestTag) {
            case "updateUserInfo":
                //更新用户的结果
                if (responseStr.equals("true")) {
                    ToastManage.getInstance().toastShortShow("更新用户信息成功");
                    changeDialog.hide();

                    //更新用户数据
                    userName.setText(map.get("username"));
                    userTel.setText(map.get("telephone"));
                    userEmail.setText(map.get("email"));
                    //更新Edit数据
                    userInfoName.setText(map.get("username"));
                    userInfoTelephone.setText(map.get("telephone"));
                    userInfoEmail.setText(map.get("email"));

                    //上传更新用户数据成功后，更新图片
                    GlideApp.with(MenuListFragment.this).load(bitmap)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(userIcon);

                }
                break;
        }

    }

    @Override
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.camera) {
            //点击选择相机
            TakePhoto takePhoto = getTakePhoto();
            configCompress(takePhoto);
            File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Uri imageUri = Uri.fromFile(file);

            takePhoto.onPickFromCapture(imageUri);
        } else if (id == R.id.picture) {
            //点击选择相册
            TakePhoto takePhoto = getTakePhoto();
            configCompress(takePhoto);

            takePhoto.onPickMultiple(1);
        }

    }

    /**
     * 选择图片的相关配置
     *
     * @param takePhoto 对图片选择框架的配置
     */
    private void configCompress(TakePhoto takePhoto) {

        //选择图片的大小最大值
        int maxSize = 200000;
        //选择图片的宽度
        int width = 1080;
        //选择图片的高度
        int height = 1080;
        //是否显示加载的等待图标
        boolean showProgressBar = true;
        boolean enableRawFile = true;
        //配置类
        CompressConfig config;
        //配置
        LubanOptions option = new LubanOptions.Builder().setMaxHeight(height).setMaxWidth(width).setMaxSize(maxSize).create();
        config = CompressConfig.ofLuban(option);
        config.enableReserveRaw(enableRawFile);
        config.enablePixelCompress(false);
        config.enableQualityCompress(false);
        //应用配置
        takePhoto.onEnableCompress(config, showProgressBar);

    }

    /**
     * 获取TakePhoto实例
     *
     * @return TokePhoto
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    /**
     * 选择图片成功后回调
     *
     * @param result 选择图片成功回调的结果
     */
    @Override
    public void takeSuccess(TResult result) {

        //获取未压缩过的图片文件
        TImage selImg = result.getImage();
        //获取图片文件的路径
        selImgFile = new File(selImg.getOriginalPath());
        //获取URI
        Uri uri = Uri.fromFile(selImgFile);

        //选择确定后，上传图片到服务器上
        String upUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.uploadIcon;

        //回调到主线程中进行操作
        getActivity().runOnUiThread(() -> {

            //保存文件的名称
            fileImageName = selImgFile.getName();
            //保存选择图片的BitMap，用于刷新页面
            bitmap = BitmapFactory.decodeFile(selImgFile.getAbsolutePath());
            //隐藏图片选择框
            iconSheet.cancel();

            //设置用户头像
            GlideApp.with(this).load(bitmap)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(userInfoIcon);

            //选择图片后，不会马上上传，修改完信息确认后，再提交图片文件加上用户的相关信息给后台

        });

    }

    /**
     * 选择图片失败
     *
     * @param result 失败的结果
     * @param msg    失败的信息
     */
    public void takeFail(TResult result, String msg) {
        Log.i(TAG, "takeFail:" + msg);
    }

    /**
     * 取消选择的回调
     */
    public void takeCancel() {
        Log.i(TAG, getResources().getString(org.devio.takephoto.R.string.msg_operation_canceled));
    }

    /**
     * 图片选择的权限
     *
     * @param invokeParam
     * @return 权限管理
     */
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

}
