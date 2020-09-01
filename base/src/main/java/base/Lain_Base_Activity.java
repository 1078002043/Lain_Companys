package base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.base.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * 抽象基 Activity，抽取共有属性，全局初始化
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/7/26 17 : 05
 */
public abstract class Lain_Base_Activity extends AppCompatActivity {
    //全局Toolbar

    public Toolbar lainViewToolbar;

    //Log 日志的TAG
    public static String TAG = "Lain_Base_Activity";
    //运行时权限的请求码
    private static final int REQUEST_CODE = 100;
    /*
        运行时权限
        CALL_PHONE：拨打电话
        CAMERA：照相机
        READ_EXTERNAL_STORAGE：读SD卡
        WRITE_EXTERNAL_STORAGE 写SD卡
     */
    public static final String[] PERMISSION = new String[]{
            Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * 利用反射机制，实现带数据的动态跳转
     *
     * @param context   跳转Context
     * @param className 需要跳转到哪一个类
     * @param messages  跳转时携带的信息
     */
    public static void myStartActivity(Context context, Class className, String... messages) {

        Intent startIntent = new Intent(context, className);
        //遍历Message，不断的将数据保存到 Intent 中进行传输
        for (String message :
                messages) {
            startIntent.putExtra(message, message);
        }
        //开启活动
        context.startActivity(startIntent);

    }

    /**
     * 利用反射机制，实现带数据的动态跳转，带 KEY 值的跳转
     *
     * @param context   跳转Context
     * @param className 需要跳转到哪一个类
     * @param messages  跳转时携带的信息
     */
    public static void myStartActivity(Context context, Class className, String key, String messages) {

        Intent startIntent = new Intent(context, className);
        startIntent.putExtra(key, messages);

        //开启活动
        context.startActivity(startIntent);

    }

    /**
     * 获取Toolbar的标题
     *
     * @return Toolbar标题
     */
    protected abstract String getToolbarTitle();

    /**
     * 利用反射机制，实现动态跳转
     *
     * @param context   跳转Context
     * @param className 需要跳转到哪一个类
     */
    public static void myStartActivity(Context context, Class className) {
        Intent startIntent = new Intent(context, className);
        context.startActivity(startIntent);
    }

    /**
     * 返回 Activity Layout
     *
     * @return Layout ID
     */
    public abstract int setLayoutView();

    /**
     * 检查是否有未授权的危险权限，如果有则进行请求权限
     *
     * @param manifests 运行时权限，接收来自 PERMISSION
     */
    public void checkPermission(String[] manifests) {
        //保存未授权的权限
        ArrayList<String> manifestAl = new ArrayList<>();
        //遍历权限集合，如果有未权限的权限，添加到集合中
        for (String manifest :
                manifests) {

            if (ContextCompat.checkSelfPermission(this, manifest) != PackageManager.PERMISSION_GRANTED) {
                manifestAl.add(manifest);
            }


        }
        //如果集合中有未权限的权限，则进行再次授权
        if (manifestAl.size() > 0)
            ActivityCompat.requestPermissions(this, manifests, REQUEST_CODE);

    }

    //ITEM 全局点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //如果点击了返回按钮，则结束当前活动
        if (item.getItemId() == android.R.id.home)
            finish();

        return true;
    }

    /**
     * 全局初始化
     *
     * @param savedInstanceState 保存临时数据
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //设置视图
        setContentView(setLayoutView());
        //ButterKnife
        ButterKnife.bind(this);
        //绑定ToolBar
        lainViewToolbar = findViewById(R.id.insert_toolbar);

        //获取每个CLASS的名称
        TAG = getClass().getName();
        //全局Toolbar
        setSupportActionBar(lainViewToolbar);
        //获取 Toolbar
        ActionBar actionBar = getSupportActionBar();
        //设置Toolbar标题
        if (actionBar != null) {
            actionBar.setTitle(getToolbarTitle());
            //显示Toolbar的返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置Toolbar的返回图标
            actionBar.setHomeAsUpIndicator(R.mipmap.back_icon);
        }

        //设置状态栏颜色
        makeStatusBarTransparent(this);

    }

    /**
     * APP启动时检查是否有未授权的权限，如果有则进行请求获取权限
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 将状态栏设置颜色
     *
     * @param activity 需要设置的Activity
     */
    public void makeStatusBarTransparent(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

    }

}
