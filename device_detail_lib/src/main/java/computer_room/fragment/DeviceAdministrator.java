package computer_room.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.device_detail_lib.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.nex3z.flowlayout.FlowLayout;
import com.robertlevonyan.views.chip.Chip;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoFragment;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import base.BaseRecyclerViewAdapter;
import base.Base_Devices_Detail;
import base.GlideApp;
import computer_room.adapter.NewDeviceManageAdapter;
import computer_room.adapter.RoleManageAdapter;
import computer_room.adapter.RolePermissionAdapter;
import computer_room.bean.CharacterManageBean;
import computer_room.bean.NewUserManageBean;
import computer_room.bean.RoleManageBean;
import computer_room.bean.UserCharacterBean;
import computer_room.i_interface.I_Administrator;
import computer_room.present.AdministratorPresenter;
import http.MyGson;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import i_v.UserUpdateListener;
import util.ActivityUtil;
import util.I_MultiChip;
import util.LainNewApi;
import util.MultiChipUtil;
import util.ToastManage;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/20 16:18
 * Description：管理员
 **/
public class DeviceAdministrator extends Base_Devices_Detail implements BaseRecyclerViewAdapter.OnItemClickListener, I_Administrator, View.OnClickListener,
        TakePhoto.TakeResultListener, InvokeListener, OnSpinnerItemSelectedListener, I_MultiChip, UserUpdateListener {

    private static final String TAG = TakePhotoFragment.class.getName();
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    private Button addBtn;

    //编辑设置面板
    public BottomSheetDialog bottomSheetDialog;
    //角色管理面板
    public BottomSheetDialog characterSheet;
    public BottomSheetDialog roleSheet;
    //Presenter
    private AdministratorPresenter presenter;
    //管理员列表适配器
    private NewDeviceManageAdapter newDeviceManageAdapter;
    private RolePermissionAdapter rolePermissionAdapter;
    private RoleManageAdapter roleManageAdapter;
    //列表数据-用户管理
    private List<NewUserManageBean> newDeviceManages;
    //用户拥有权限列表
    List<NewUserManageBean.RoleListBean> roleListBeanList = new ArrayList<>();

    //列表数据-角色管理
    private List<CharacterManageBean> rolePermissionManages = new ArrayList<>();
    private List<RoleManageBean> roleManageBeans = new ArrayList<>();
    //用户名
    private EditText deviceUserName;
    //用户密码
    private EditText deviceUserPas;
    //用户头像
    private ImageView deviceUserIcon;
    //确认密码
    private EditText deviceUserRepas;
    //用户电话
    private EditText deviceUserTel;
    //用户邮箱
    private EditText deviceUserEmail;
    //设置面板的展开状态
    public BottomSheetBehavior mDialogBehavior;
    //面板中确认添加用户的按钮
    private Button addUserBtn;
    //取消添加用户add_user
    private Button cancelAdd;
    //用户的真实姓名
    private EditText userRealName;
    //用户数据所有容器的位置
    private int userDataPos = 0;
    //判断是更新用户还是添加用户
    public String upOrAdd = "update";
    //密码父布局
    private LinearLayout pasLayout;
    //确认密码的父布局
    private LinearLayout rePasLayout;
    //密码下的横线
    private View pasLine;
    //确认密码下的横线
    private View rePasLine;

    //用户角色列表
    private FlowLayout userRoleList;


    //初始化头像更换
    private BottomSheetDialog iconSheet;

    //选择相机
    private LinearLayout camera;
    //选择相册
    private LinearLayout picture;

    //每次点击用户时，记录用户的ID，通过ID来修改指定用户的头像
    private String userID;

    //保存权限管理的ID
    private String roleId;

    //权限管理的sheet控件
    EditText roleSheetName;
    EditText roleSheetPath;
    EditText roleSheetDesc;

    //角色管理的控件
    EditText characterSheetName;
    EditText characterSheetDesc;
    //角色拥有的权限列表，通过动态添加
    FlowLayout characterSheetRole;

    //更新 update  添加 insert
    String operation = "update";

    //用户管理中的Chip
    private List<Chip> chipList;
    //角色管理中的Chip
    private List<Chip> characterChip;

    //用户管理的 用户ID ，用户位置
    private int userManageId;
    private int userManagePos;
    //保存编辑时用户拥有的权限
    private List<String> userRoleEdit = new ArrayList<>();

    //用户角色
    private List<UserCharacterBean> userRoleEditBeans = new ArrayList<>();
    //角色的权限
    private List<UserCharacterBean> characterPermissionEditBeans = new ArrayList<>();

    //保存编辑角色时的权限
    private List<String> characterRoleEdit = new ArrayList<>();

    //获取角色的相关信息
    private String characterName;   //角色名称
    private String characterDesc;   //角色描述

    //记录当前列表点击的位置
    private int recyclerPosition;
    private UltimateRecyclerView superAdmin;

    //保存上传的图片文件名称
    private String fileImageName;
    private File selImgFile;

    //以下三个方法必须都加上选好图片后才能回调

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
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {

        //获取未压缩过的图片文件
        TImage selImg = result.getImage();
        selImgFile = new File(selImg.getOriginalPath());
        Uri uri = Uri.fromFile(selImgFile);


        //选择确定后，上传图片到服务器上
        String upUrl = LainNewApi.getInstance().getRootPath() + LainNewApi.uploadIcon;

        getActivity().runOnUiThread(() -> {

            fileImageName = selImgFile.getName();
            Bitmap bitmap = BitmapFactory.decodeFile(selImgFile.getAbsolutePath());
            deviceUserIcon.setImageBitmap(bitmap);
            iconSheet.cancel();

            //根据用户ID来修改用户头像
//            OkHttpUtil.getInstance().uploadFile("jpg", selImgFile, upUrl, "user_icon", userID, new OkHttpCallBack() {
//                @Override
//                public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {
//                    Log.d("dsfds", "httpRequestSuccess: " + responseStr);
//                }
//
//                @Override
//                public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {
//                    Log.d("dsfds", "httpRequestFailure: " + errorMsg);
//                }
//            });

        });


//        iconSheet.cancel();


    }


    public void takeFail(TResult result, String msg) {
        Log.i(TAG, "takeFail:" + msg);
    }


    public void takeCancel() {
        Log.i(TAG, getResources().getString(org.devio.takephoto.R.string.msg_operation_canceled));
    }


    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化Presenter
        presenter = new AdministratorPresenter(this);
        //初始化顶部导航栏
        magicIndicator2.setVisibility(View.VISIBLE);

        //初始化添加用户面板
        initUserPanel();
        //初始化角色管理面板
        initRoleSheetPanel();
        initRoleSheetPanel2();

        //初始化假数据
        newDeviceManages = new ArrayList<>();

        //用户管理
        superAdmin = viewList.get(0).findViewById(R.id.device_admin_recycler);
        newDeviceManageAdapter = new NewDeviceManageAdapter(getActivity(), newDeviceManages, R.layout.device_admin_temp, this);
        newDeviceManageAdapter.setItemClickListener(this);
        superAdmin.setLayoutManager(new LinearLayoutManager(getActivity()));
        superAdmin.setAdapter(newDeviceManageAdapter);

        //权限管理
        UltimateRecyclerView admin = viewList.get(2).findViewById(R.id.device_admin_recycler);
        roleManageAdapter = new RoleManageAdapter(getActivity(), roleManageBeans, R.layout.all_role_list);
        roleManageAdapter.setItemClickListener(this);
        admin.setLayoutManager(new LinearLayoutManager(getActivity()));
        admin.setAdapter(roleManageAdapter);

        //角色管理
        UltimateRecyclerView commonUser = viewList.get(1).findViewById(R.id.device_admin_recycler);
        rolePermissionAdapter = new RolePermissionAdapter(getActivity(), rolePermissionManages, R.layout.role_permission);
        rolePermissionAdapter.setItemClickListener(this);
        commonUser.setLayoutManager(new LinearLayoutManager(getActivity()));
        commonUser.setAdapter(rolePermissionAdapter);

        //获取用户管理信息
        presenter.getUserRoleInfo();

        //隐藏顶部面板
        head_panel.setVisibility(View.GONE);

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

    }

    //权限管理
    private void initRoleSheetPanel2() {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.role_sheet, null, false);

        roleSheetName = v.findViewById(R.id.role_sheet_name);
        roleSheetPath = v.findViewById(R.id.role_sheet_path);
        roleSheetDesc = v.findViewById(R.id.role_sheet_desc);

        Button roleSheetRight = v.findViewById(R.id.role_sheet_right);
        Button roleSheetCancel = v.findViewById(R.id.role_sheet_cancel);

        //确定
        roleSheetRight.setOnClickListener((btnV) -> {

            Map<String, String> map = new HashMap<>();

            //报警方式
            map.put("name", roleSheetName.getText().toString());
            map.put("desc_", roleSheetDesc.getText().toString());
            map.put("url", roleSheetPath.getText().toString());
            map.put("id", roleId);

            if (operation.equals("update")) {

                presenter.updatePermission(map);

            } else {

                presenter.insertPermission(map);

            }

        });
        //取消
        roleSheetCancel.setOnClickListener((btnV) -> {
            roleSheet.cancel();
        });

        roleSheet = new BottomSheetDialog(getActivity());
        roleSheet.setContentView(v);
        //必须是在 setContentView之后再获取 behavior
        mDialogBehavior = BottomSheetBehavior.from((View) v.getParent());
        //设置展开的状态
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    //角色管理
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initRoleSheetPanel() {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.role_manage_sheet, null, false);

        characterSheetName = v.findViewById(R.id.character_sheet_name);
        characterSheetDesc = v.findViewById(R.id.character_sheet_desc);
        characterSheetRole = v.findViewById(R.id.character_sheet_role);

        Button characterSheetRight = v.findViewById(R.id.character_sheet_right);
        Button characterSheetCancel = v.findViewById(R.id.character_sheet_cancel);

        //确定
        characterSheetRight.setOnClickListener((btnV) -> {

            //获取拥有权限对应的ID
            updateCharacterManageRole();

            characterName = characterSheetName.getText().toString();
            characterDesc = characterSheetDesc.getText().toString();

            //转成字符串
            String roleStr = "[" + String.join(",", characterRoleEdit) + "]";

            Map<String, String> map = new HashMap<>();
            map.put("name", characterName);
            map.put("desc_", characterDesc);
            map.put("permissionIds", roleStr);

            if (operation.equals("update")) {
                //更新需要加上角色ID
                map.put("id", roleId);
                presenter.updateCharacter(map);

            } else {

                presenter.insertCharacter(map);

            }

        });

        //取消
        characterSheetCancel.setOnClickListener((btnV) -> {
            characterSheet.cancel();
        });

        characterSheet = new BottomSheetDialog(getActivity());
        characterSheet.setContentView(v);
        //必须是在 setContentView之后再获取 behavior
        mDialogBehavior = BottomSheetBehavior.from((View) v.getParent());
        //设置展开的状态
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    //添加编辑用户面板
    private void initUserPanel() {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.super_manage_edit, null, false);
        //用户名
        deviceUserName = v.findViewById(R.id.device_user_name);
        //密码
        deviceUserPas = v.findViewById(R.id.device_user_pas);
        //用户头像
        deviceUserIcon = v.findViewById(R.id.user_edit_icon);
        //确认密码
        deviceUserRepas = v.findViewById(R.id.device_user_repas);
        //电话
        deviceUserTel = v.findViewById(R.id.device_user_tel);
        //邮箱
        deviceUserEmail = v.findViewById(R.id.device_user_email);
        //确认添加用户
        addUserBtn = v.findViewById(R.id.true_commit);
        //取消添加用户
        cancelAdd = v.findViewById(R.id.cancel_commit);
        //密码父布局
        pasLayout = v.findViewById(R.id.layout_pas);
        //确认密码的父布局
        rePasLayout = v.findViewById(R.id.layout_repas);
        //密码的横线View
        pasLine = v.findViewById(R.id.pas_line);
        rePasLine = v.findViewById(R.id.repas_line);
        userRoleList = v.findViewById(R.id.user_role_edit_tag);

        //添加 与 取消 按钮的点击 事件
        addUserBtn.setOnClickListener(this);
        cancelAdd.setOnClickListener(this);
        deviceUserIcon.setOnClickListener(this);

        bottomSheetDialog = new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(v);
        //必须是在 setContentView之后再获取 behavior
        mDialogBehavior = BottomSheetBehavior.from((View) v.getParent());
        //设置展开的状态
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    //ViewPager
    @Override
    public List<Integer> getViewPagerView() {
        ArrayList<Integer> viewList = new ArrayList<>();
        //管理员
        viewList.add(R.layout.device_administrator_temp);
        //普通用户
        viewList.add(R.layout.device_administrator_temp);
        //超级用户
        viewList.add(R.layout.device_administrator_temp);
        return viewList;
    }

    //Fragment
    @Override
    protected View getFragmentLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.device_detail_template, container, false);
    }

    //实时更新
    @Override
    public void run() {

    }

    /**
     * 顶部导航栏
     *
     * @return 顶部导航栏的参数
     */
    @Override
    public ArrayList<String> getTopItem() {
        ArrayList<String> topList = new ArrayList<>();
        topList.add("用户管理");
        topList.add("角色管理");
        topList.add("权限管理");
        return topList;
    }

    //点击编辑按钮时，就会弹出编辑面板
    @Override
    public void onItemClickListener(View v, int position) {

        //记录当前列表点击的位置
        recyclerPosition = position;

        int id = v.getId();//编辑用户
        if (id == R.id.user_change) {
            editUserInfo(position);
            //删除用户
        } else if (id == R.id.user_del) {//记录下用户的位置，后期好取出来提示用户
            userDataPos = position;
            //删除用户操作
            delUser(position);
        } else if (id == R.id.phone_layout) {//拨打电话
            callPhone(position);
        } else if (id == R.id.email_layout) {
            sendEmail(position);
        } else if (id == R.id.user_manage_icon) {

            //记录用户的ID
            userID = String.valueOf(newDeviceManages.get(position).getId());


        } else if (id == R.id.role_change) {

            //更新
            characterEdit(position);

        } else if (id == R.id.all_role_change) {
            //更新
            operation = "update";

            roleId = roleManageBeans.get(position).getId() + "";

            //设置权限信息
            roleSheetName.setText(roleManageBeans.get(position).getName());
            roleSheetPath.setText(roleManageBeans.get(position).getUrl());
            roleSheetDesc.setText(roleManageBeans.get(position).getDesc_());

            //显示权限管理面板
            roleSheet.show();

        } else if (id == R.id.all_role_delete) {

            //删除权限
            presenter.deleteRole(roleManageBeans.get(position).getId());

        } else if (id == R.id.role_del) {

            //删除角色
            presenter.deleteCharacter(rolePermissionManages.get(position).getId());

        }

    }

    /**
     * 更新角色相关信息
     *
     * @param position 角色对应的位置
     */
    private void characterEdit(int position) {
        operation = "update";
        roleId = rolePermissionManages.get(position).getId() + "";

        //获取角色
        List<CharacterManageBean.PermissionsBean> permissions = rolePermissionManages.get(position).getPermissions();

        //设置相应的数据
        characterSheetName.setText(rolePermissionManages.get(position).getName());
        characterSheetDesc.setText(rolePermissionManages.get(position).getDesc_());

        //如果该用户存在该权限，则默认选中
        for (Chip chip :
                characterChip) {

            //必须先设置为未选中状态
            chip.setChipSelected(false);

            for (int i = 0; i < permissions.size(); i++) {

                if (chip.getText().toString().equals(permissions.get(i).getDesc_())) {

                    chip.setChipSelected(true);
                    break;

                }

            }

        }

        //显示角色管理面板
        characterSheet.show();
    }

    /**
     * 发送邮箱
     *
     * @param position 获取邮箱账号
     */
    private void sendEmail(int position) {

        Uri uri = Uri.parse("mailto:" + newDeviceManages.get(position).getEmail());
        String[] email = {newDeviceManages.get(position).getEmail()};
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra(Intent.EXTRA_CC, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, "请修改主题");
        intent.putExtra(Intent.EXTRA_TEXT, "请填写正文");
        startActivity(Intent.createChooser(intent, "请选择邮件"));

    }

    /**
     * 删除用户
     *
     * @param position 用户的数据
     */
    private void delUser(int position) {

        NewUserManageBean newUserManageBean = newDeviceManages.get(position);
        //获取用户的UID，来删除用户
        presenter.deleteUserByUid(newUserManageBean.getId());

    }

    /**
     * 添加用户信息
     */
    private void editUserInfo(int position) {
        //获取数据
        NewUserManageBean adminBean = newDeviceManages.get(position);
        //设置用户名
        deviceUserName.setText(adminBean.getUsername());
        //设置密码
        deviceUserPas.setText(adminBean.getPassword());
        //设置电话
        deviceUserTel.setText(adminBean.getTelephone());
        //设置邮箱
        deviceUserEmail.setText(adminBean.getEmail());
        //设置真实姓名
//        userRealName.setText(adminBean.getVsername());
        //如果是更新，则隐藏确认密码项
        if (upOrAdd.equals("update")) {
            //隐藏密码
            rePasLayout.setVisibility(View.GONE);
            //隐藏横线
            rePasLine.setVisibility(View.GONE);
            //如果是更新，设置头像
            GlideApp.with(this).load(LainNewApi.getInstance().getRootPath() + adminBean.getIcon())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    //设置缓存策略才可以正常加载图片
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .into(deviceUserIcon);


        }

        userID = String.valueOf(adminBean.getId());

        //获取该用户拥有的权限
        roleListBeanList = adminBean.getRoleList();

        //如果该用户存在该权限，则默认选中
        for (Chip chip :
                chipList) {

            //必须先设置为未选中状态
            chip.setChipSelected(false);

            for (int i = 0; i < roleListBeanList.size(); i++) {

                if (chip.getText().toString().equals(roleListBeanList.get(i).getDesc_())) {

                    chip.setChipSelected(true);
                    break;

                }

            }

        }

        //保存点击的用户ID
        userManageId = adminBean.getId();
        //保存点击的用户的位置
        userManagePos = adminBean.getId();

        //设置底部面板完全展示
        mDialogBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        //显示面板
        bottomSheetDialog.show();
        //更新用户
        upOrAdd = "update";
        userDataPos = position;
    }

    /**
     * 更新用户列表
     *
     * @param newUserManageBeans 管理员列表
     */
    @Override
    public void getAllUserInfo(List<NewUserManageBean> newUserManageBeans) {
        this.newDeviceManages.clear();
        newDeviceManageAdapter.notifyDataSetChanged();


        this.newDeviceManages.addAll(newUserManageBeans);

        //每次刷新都修改最后一个
//        newDeviceManageAdapter.notifyDataSetChanged();

        //用户列表请求成功后，再请求角色列表
        if (rolePermissionManages.isEmpty())
            presenter.getRolePermission();

    }

    //获取所有角色管理
    @Override
    public void getRolePermission(List<CharacterManageBean> newRoleManageBeanList) {

        this.rolePermissionManages.clear();
        this.rolePermissionManages.addAll(newRoleManageBeanList);
        rolePermissionAdapter.notifyDataSetChanged();

        //获取角色列表成功后，更新角色列表
        List<String> roleList = new ArrayList<>();
        for (CharacterManageBean character :
                newRoleManageBeanList) {

            roleList.add(character.getDesc_());

        }

        //每次添加之前，必须先将所有的子View清空
        userRoleList.removeAllViews();
        //更新角色列表
        chipList = MultiChipUtil.getInstance().addChipView(getActivity(), roleList, userRoleList, this);

        //角色列表请求成功后，再请求权限列表
        if (roleManageBeans.isEmpty())
            presenter.getRoleManage();

    }

    //获取所有权限
    @Override
    public void getRoleManage(List<RoleManageBean> roleManageBeans) {

        this.roleManageBeans.clear();
        this.roleManageBeans.addAll(roleManageBeans);
        roleManageAdapter.notifyDataSetChanged();

        //获取权限列表成功后，更新角色列表
        List<String> roleList = new ArrayList<>();
        for (RoleManageBean character :
                roleManageBeans) {

            roleList.add(character.getDesc_());

        }

        //更新角色列表
        characterChip = MultiChipUtil.getInstance().addChipView(getActivity(), roleList, characterSheetRole, this);

    }

    @Override
    public void updatePermission(boolean response) {

        if (response) {

            ToastManage.getInstance().toastShortShow("更新权限信息成功");
            //刷新页面
            presenter.getRoleManage();
            //隐藏Sheet
            roleSheet.cancel();

        } else {

            ToastManage.getInstance().toastShortShow("更新权限信息失败");

        }

    }

    /**
     * 添加用户成功
     */
    @Override
    public void addUserSuccess() {
        //提示添加成功
        ToastManage.getInstance().toastLongShow("添加 " + deviceUserName.getText().toString() + " 用户成功");
        //取消底部面板的显示
        bottomSheetDialog.cancel();
        //刷新 页面
        presenter.getUserRoleInfo();
    }

    /**
     * 添加用户失败
     */
    @Override
    public void addUserFailed() {
        //添加失败
        ToastManage.getInstance().toastLongShow("添加用户失败，请检查填写信息是否正确");
    }

    /**
     * 删除用户的结果
     *
     * @param delResult 请求服务器删除用户返回的结果
     */
    @Override
    public void delUserResponse(String delResult) {
        switch (delResult) {
            //删除成功
            case "true":
                ToastManage.getInstance().toastShortShow("删除 " + newDeviceManages.get(userDataPos).getUsername() + " 用户成功");
                //刷新列表
                presenter.getUserRoleInfo();
                break;
            //删除失败
            case "false":
                ToastManage.getInstance().toastShortShow("删除 " + newDeviceManages.get(userDataPos).getUsername() + " 用户失败");
                break;
        }
    }

    @Override
    public void updateUserResponse(String updateResult) {

        switch (updateResult) {
            //更新成功
            case "true":
                ToastManage.getInstance().toastShortShow("更新用户信息成功");
                //刷新设备列表
                presenter.getUserRoleInfo();
                //关闭底部面板
                bottomSheetDialog.cancel();

                break;
            //更新失败
            case "false":
                ToastManage.getInstance().toastShortShow("更新用户信息失败");
                break;
        }
    }

    @Override
    public void deleteRole(boolean result) {

        //删除权限的结果
        if (result) {

            ToastManage.getInstance().toastShortShow("删除权限成功");
            //刷新设备列表
            presenter.getRoleManage();

        } else {

            ToastManage.getInstance().toastShortShow("删除权限失败");

        }

    }

    @Override
    public void insertRole(boolean zt) {

        //添加权限成功回调
        //删除权限的结果
        if (zt) {

            ToastManage.getInstance().toastShortShow("添加权限成功");
            //刷新设备列表
            presenter.getRoleManage();
            //隐藏sheet
            roleSheet.cancel();

        } else {

            ToastManage.getInstance().toastShortShow("添加权限失败");

        }

    }

    /**
     * 插入角色
     *
     * @param responseStr 插入的结果
     */
    @Override
    public void insertCharacter(String responseStr) {

        //添加成功后刷新页面
        presenter.getRolePermission();
        //关闭Sheet
        characterSheet.cancel();
        ToastManage.getInstance().toastShortShow("添加角色成功");
    }

    /**
     * 删除角色
     *
     * @param responseStr 删除角色的结果
     */
    @Override
    public void deleteCharacter(String responseStr) {

        //添加成功后刷新页面
        presenter.getRolePermission();
        ToastManage.getInstance().toastShortShow("删除角色成功");

    }

    @Override
    public void updateCharacter(boolean responseStr) {
        Log.d("popopoptoy", "updateCharacter: " + responseStr);
        if (responseStr) {
            //添加成功后刷新页面
            presenter.getRolePermission();
            //关闭Sheet
            characterSheet.cancel();
            ToastManage.getInstance().toastShortShow("更新角色成功");
        } else {

            ToastManage.getInstance().toastShortShow("更新角色失败");

        }


    }

    /**
     * 更新用户结果的结果，先更新权限，再更新用户的信息
     *
     * @param zt 更新的结果
     */
    @Override
    public void updateUserRole(boolean zt) {

        //用户权限更新成功后，再更新数据
        if (zt) {

            ToastManage.getInstance().toastShortShow("更新用户成功");
            bottomSheetDialog.cancel();
            presenter.getUserRoleInfo();

        } else {

            ToastManage.getInstance().toastShortShow("更新用户失败");

        }

    }

    /**
     * 将输入框的内容清空
     */
    public void clearEditText() {
        deviceUserName.setText("");
        deviceUserPas.setText("");
        deviceUserTel.setText("");
        deviceUserEmail.setText("");
        userRealName.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        int id = view.getId();
        if (id == R.id.true_commit) {
            if (upOrAdd.equals("add_user"))
                //开始添加用户
                startAddUser();
            else
                //更新用户
                startUpdateUser();
        } else if (id == R.id.cancel_commit) {//取消添加
            bottomSheetDialog.cancel();
        } else if (id == R.id.camera) {
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
        } else if (id == R.id.user_edit_icon) {

            //点击头像后，弹出选择，是从 相机还是相册 中选择，然后再上传到后台
            //初始化头像更换
            iconSheet.show();

        }

    }

    private void configCompress(TakePhoto takePhoto) {

        int maxSize = 200000;
        int width = 1080;
        int height = 1080;
        boolean showProgressBar = true;
        boolean enableRawFile = true;
        CompressConfig config;

        LubanOptions option = new LubanOptions.Builder().setMaxHeight(height).setMaxWidth(width).setMaxSize(maxSize).create();
        config = CompressConfig.ofLuban(option);
        config.enableReserveRaw(enableRawFile);
        config.enablePixelCompress(false);
        config.enableQualityCompress(false);

        takePhoto.onEnableCompress(config, showProgressBar);


    }

    /**
     * 拨打电话
     */
    private void callPhone(int position) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + newDeviceManages.get(position).getTelephone()));
        startActivity(intent);
    }

    /**
     * 开始更新用户信息
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startUpdateUser() {
        //如果输入的信息都不为空，则添加用户
        if (!updateInfoNull()) {

            //检查电话是否合格
            if (deviceUserTel.length() < 11) {
                ToastManage.getInstance().toastShortShow("电话号码最少11位，当前只有：" + deviceUserTel.length() + "位");
                return;
            }

            //更新用户
            addNewUser();

        }
    }

    /**
     * 更新时的信息判断是否为空
     *
     * @return 更新时的信息填写是否完整
     */
    private boolean updateInfoNull() {
        //如果有一项是空的，返回 true
//        if (ActivityUtil.getInstance().EditTextIsNull(deviceUserName) || ActivityUtil.getInstance().EditTextIsNull(deviceUserPas)
//                || ActivityUtil.getInstance().EditTextIsNull(deviceUserTel) || ActivityUtil.getInstance().EditTextIsNull(deviceUserEmail)
//                || ActivityUtil.getInstance().EditTextIsNull(userRealName)) {
//            ToastManage.getInstance().toastShortShow("信息填写不完整，请检查后重新输入");
//            return true;
//        }

        return false;
    }

    /**
     * 添加用户
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startAddUser() {
        //确保再次输入的密码是一致
        String pas = deviceUserPas.getText().toString();
        String rePas = deviceUserRepas.getText().toString();

        //如果输入的信息都不为空，则添加用户
        if (!commitInfoNull()) {
            //所有信息都输入完成后，判断密码是否一致
            if (!pas.equals(rePas)) {
                ToastManage.getInstance().toastLongShow("密码不相同，请检查后重新输入");
                return;
            }
            //检查电话是否合格
            if (deviceUserTel.length() < 11) {
                ToastManage.getInstance().toastShortShow("电话号码最少11位，当前只有：" + deviceUserTel.length() + "位");
                return;
            }


            //添加新用户，再添加用户的权限
            addNewUser();
        }
    }

    /**
     * 添加新用户
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addNewUser() {

        Map<String, String> userInfo = new HashMap<>();
        //用户名
        userInfo.put("username", ActivityUtil.getInstance().EditTextToString(deviceUserName));
//        if (upOrAdd.equals("add_user")) {
//            //密码
//            userInfo.put("password", ActivityUtil.getInstance().EditTextToString(deviceUserPas));
//        }
        //密码
        userInfo.put("password", ActivityUtil.getInstance().EditTextToString(deviceUserPas));
        //电话
        userInfo.put("telephone", ActivityUtil.getInstance().EditTextToString(deviceUserTel));
        //邮箱
        userInfo.put("email", ActivityUtil.getInstance().EditTextToString(deviceUserEmail));
        userInfo.put("gId", "1");
        userInfo.put("icon", fileImageName);
        Log.d("kljkldf", "addNewUser: " + fileImageName);
        //获取用户拥有  的角色
        String manageCharacter = updateUserManageRole();
        //将角色转成JSON
        String roleStr = Objects.requireNonNull(MyGson.getInstance()).toJson(userRoleEditBeans);
        userInfo.put("roleList", roleStr);

        //判断是更新还是添加用户
        if (upOrAdd.equals("add_user")) {

            //发送请求添加用户
            presenter.addNewUser(userInfo, selImgFile);
        } else {

            //设备的标识
            userInfo.put("id", newDeviceManages.get(userDataPos).getId() + "");
            //发送请求更新用户
            presenter.updateUserRole(userInfo, selImgFile);

        }
    }

    /**
     * 判断插入用户信息是否为空，如果为空，则进行提示
     */
    private boolean commitInfoNull() {
        //如果有一项是空的，返回 true
//        if (ActivityUtil.getInstance().EditTextIsNull(deviceUserName) || ActivityUtil.getInstance().EditTextIsNull(deviceUserPas) || ActivityUtil.getInstance().EditTextIsNull(deviceUserRepas)
//                || ActivityUtil.getInstance().EditTextIsNull(deviceUserTel) || ActivityUtil.getInstance().EditTextIsNull(deviceUserEmail)
//                || ActivityUtil.getInstance().EditTextIsNull(userRealName)) {
//            ToastManage.getInstance().toastShortShow("信息填写不完整，请检查后重新输入");
//            return true;
//        }

        return false;
    }

    @Override
    public void magicSelected(int position) {

        switch (position) {
            case 0:
                break;
            case 1:
                //为空时，才会请求
                if (rolePermissionManages.isEmpty())
                    presenter.getRolePermission();
                break;
            case 2:
                if (roleManageBeans.isEmpty())
                    presenter.getRoleManage();
                break;
        }

    }

    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {

        //选择角色列表

    }

    /**
     * 标签点击事件回调
     *
     * @param v            点击的VIEW，通过转换成 Chip 来获取相关数据
     * @param chipSelected 点击选中的结果
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void multiChipListener(View v, boolean chipSelected) {

        //强制转换成Chip
        Chip chip = (Chip) v;

//        if (chipSelected) {
//
//            //获取新添加的权限ID
//            for (int i = 0; i < rolePermissionManages.size(); i++) {
//
//                if (rolePermissionManages.get(i).getDesc_().equals(chip.getText().toString())) {
//                    //保存权限的ID
//                    userRoleEdit.add(String.valueOf(rolePermissionManages.get(i).getId()));
//                }
//
//            }
//
//        }

        //如果选中
//        if (chipSelected) {
//
//            //获取新添加的权限ID
//            for (int i = 0; i < rolePermissionManages.size(); i++) {
//
//                if (rolePermissionManages.get(i).getDesc_().equals(chip.getText().toString())) {
//                    userRoleEdit.add(String.valueOf(rolePermissionManages.get(i).getId()));
//                }
//
//            }
//
//            //如果是选择true，权限获取当前所拥有的所有权限，再加上新添加的权限
//            for (int i = 0; i < roleListBeanList.size(); i++) {
//
//                userRoleEdit.add(String.valueOf(roleListBeanList.get(i).getId()));
//
//            }
//
//        } else {
//
//            //获取拥有的权限对应ID，移除相应的权限
//            for (int i = 0; i < roleListBeanList.size(); i++) {
//
//                if (roleListBeanList.get(i).getDesc_().equals(chip.getText().toString())) {
//                    roleListBeanList.remove(i);
//                    continue;
//                }
//
//                //获取权限ID，再更新
//                userRoleEdit.add(String.valueOf(roleListBeanList.get(i).getId()));
//
//            }
//
//        }

    }

    /**
     * 更新用户的权限
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String updateUserManageRole() {

        //这里已经是确定更新，添加用户信息了，获取用户选择的权限
        //获取新添加的权限ID
        //每次编辑权限之前，一定要先将之前的权限清空
        userRoleEditBeans.clear();
        userRoleEdit.clear();

        for (int i = 0; i < chipList.size(); i++) {

            if (chipList.get(i).getChipSelected()) {

                //如果选中，则获取相应的ID
                for (CharacterManageBean character :
                        rolePermissionManages) {

                    //保存相应权限ID
                    if (chipList.get(i).getText().toString().equals(character.getDesc_())) {

                        userRoleEdit.add(String.valueOf(character.getId()));

                        UserCharacterBean characterBean = new UserCharacterBean();
                        characterBean.setId(String.valueOf(character.getId()));
                        userRoleEditBeans.add(characterBean);

                    }

                }

            }

        }

        //转成字符串
        String roleStr = String.join(",", userRoleEdit);

        return roleStr;

    }

    /**
     * 更新角色的权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateCharacterManageRole() {

        //这里已经是确定更新，添加用户信息了，获取用户选择的权限
        //获取新添加的权限ID
        //每次编辑权限之前，一定要先将之前的权限清空
        characterRoleEdit.clear();
        characterPermissionEditBeans.clear();

        for (int i = 0; i < characterChip.size(); i++) {

            if (characterChip.get(i).getChipSelected()) {

                //如果选中，则获取相应的ID
                for (RoleManageBean roleManageBean :
                        roleManageBeans) {

                    //保存相应权限ID
                    if (characterChip.get(i).getText().toString().equals(roleManageBean.getDesc_())) {

                        characterRoleEdit.add(String.valueOf(roleManageBean.getId()));

                        UserCharacterBean characterBean = new UserCharacterBean();
                        characterBean.setId(String.valueOf(roleManageBean.getId()));
                        characterPermissionEditBeans.add(characterBean);

                    }

                }

            }

        }

    }

    /**
     * 显示用户管理中的编辑面板
     *
     * @param tag 用户管理中的TAG
     */
    @Override
    public void showUserManagePanel(String tag) {

        operation = "insert";
        upOrAdd = "add_user";

        switch (tag) {
            case "用户":

                //用户名
                deviceUserName.setText("");
                //用户密码
                deviceUserPas.setText("");
                //确认密码
                deviceUserRepas.setText("");
                //用户电话
                deviceUserTel.setText("");
                //用户邮箱
                deviceUserEmail.setText("");

                //显示密码
                rePasLayout.setVisibility(View.VISIBLE);
                //显示横线
                rePasLine.setVisibility(View.VISIBLE);

                bottomSheetDialog.show();

                break;
            case "角色":

                //角色管理
                characterSheetName.setText("");
                characterSheetDesc.setText("");

                characterSheet.show();
                break;
            case "权限":

                roleSheetName.setText("");
                roleSheetPath.setText("");
                roleSheetDesc.setText("");

                roleSheet.show();
                break;
        }

    }

    @Override
    public void refreshUserInfo(String s, int pos) {

        List<NewUserManageBean.RoleListBean> roleListBeanList = newDeviceManages.get(pos).getRoleList();

        int itemPos = 0;
        Log.d(TAG, "refreshUserInfo: " + roleListBeanList.hashCode() + "----" + newDeviceManages.get(pos).getRoleList().hashCode());

        List<String> userCharacterList = new ArrayList<>();

        for (int i = 0; i < roleListBeanList.size(); i++) {

            //如果匹配，则结束本次操作
            if (s.equals(roleListBeanList.get(i).getDesc_())) {
                itemPos = i;
                continue;
            }

            //否则记录用户的ID
            userCharacterList.add(String.valueOf(roleListBeanList.get(i).getId()));

        }

        roleListBeanList.remove(itemPos);
        newDeviceManageAdapter.notifyItemChanged(pos);

        //转成字符串
        String roleStr = String.join(",", userCharacterList);

        presenter.updateCharacterRole(newDeviceManages.get(pos).getId(), roleStr);


    }
}
