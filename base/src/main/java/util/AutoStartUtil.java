package util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.AppOpsManagerCompat;

/**
 * 配置已知手机产商的自启设置页面
 * 每个产商都必须加入 try catch 因为不保证在每个系统版本中都能跳转成功
 * 如果以下手机产商都跳转失败，那就使用 Google 的原始方法
 */

public class AutoStartUtil {
    private Context context;
    private static AutoStartUtil autoStartUtil;

    public static AutoStartUtil getInstance() {
        if (autoStartUtil == null) {
            synchronized (AutoStartUtil.class) {
                if (autoStartUtil == null)
                    autoStartUtil = new AutoStartUtil();
            }
        }
        return autoStartUtil;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean selfStarting(Context context) {
        //跳转页面需要使用的 Context
        this.context = context;
        //获取手机产商，并且转成小写
        String manufacturers = SystemUtil.getDeviceBrand().toLowerCase();
        Log.d("kljld", "selfStarting: " + manufacturers);

        //如果获取不到手机产商，直接返回
        if (manufacturers.isEmpty())
            return false;

        //通过获取的 手机产商 来决定跳转的路径
        switch (manufacturers) {
            case "xiaomi":
                goXiaomiSetting();
                break;
            case "huawei":
                goHuaweiSetting();
                break;
            case "oppo":
                goOPPOSetting();
                break;
            case "vivo":
                goVIVOSetting();
                break;
            case "samsung":
                goSamsungSetting();
                break;
            case "meizu":
                goMeizuSetting();
                break;
            case "letv":
                goLetvSetting();
                break;
            case "smartisan":
                goSmartisanSetting();
                break;
        }

        return true;

    }

    //跳转锤子手机管家

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void goSmartisanSetting() {
        try {
            showActivity("com.smartisanos.security");
        } catch (Exception e) {
            e.printStackTrace();
            //跳转失败，使用原始的方式
            googleMethod();
        }
    }

    /**
     * 所有 手机产商 跳转 手机管家 失败后，都执行原始方法
     * 如果还是失败了，则不做任何处理
     */

    private void googleMethod() {
        //确认是否已开始，如果没有开启再执行
        try {
            Log.d("kdsfkf", "googleMethod: " + isIgnoringBatteryOptimizations());
            if (!isIgnoringBatteryOptimizations()) {
                requestIgnoreBatteryOptimizations();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //跳转魅族手机管家
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void goMeizuSetting() {
        try {
            showActivity("com.meizu.safe");
        } catch (Exception e) {
            e.printStackTrace();
            //跳转失败，使用原始的方式
            googleMethod();
        }
    }

    //跳转乐视手机管家
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void goLetvSetting() {
        try {
            showActivity("com.letv.android.letvsafe",
                    "com.letv.android.letvsafe.AutobootManageActivity");
        } catch (Exception e) {
            e.printStackTrace();
            //跳转失败，使用原始的方式
            googleMethod();
        }
    }

    //跳转 三星 手机管家
    private void goSamsungSetting() {
        try {
            showActivity("com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity("com.samsung.android.sm");
        }
    }

    //跳转 VIVO 手机管家
    private void goVIVOSetting() {
        try {
            showActivity("com.iqoo.secure");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转 OPPO 手机管家
     */
    private void goOPPOSetting() {
        try {
            showActivity("com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity("com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity("com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity("com.coloros.safecenter");
                }
            }
        }
    }

    /**
     * 跳转到 小米管家 下的自启设置页面
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void goXiaomiSetting() {
        try {
            showActivity("com.miui.securitycenter",
                    "com.miui.permcenter.autostart.AutoStartManagementActivity");
        } catch (Exception e) {
            e.printStackTrace();
            //跳转失败，使用原始的方式
            googleMethod();
        }
    }

    /**
     * 跳转华为手机管家的启动管理页
     */
    private void goHuaweiSetting() {
        try {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity("com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }

    /**
     * 跳转到指定应用的首页
     */

    private void showActivity(@NonNull String packageName) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            //跳转失败，使用原始的方式
            googleMethod();
        }
    }

    /**
     * 跳转到指定应用的指定页面
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showActivity(@NonNull String packageName, @NonNull String activityDir) {

        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    /**
     * 请求长时间后台运行，目前只对GOOGLE系统有效
     * 常用的都是上面的手机产商
     * 请求结果可以通过 onActivityResult 查看 resultCode = 0 拒绝   resultCode = -1 允许
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否已添加到后台长时间运行中，如果不是，调用 requestIgnoreBatteryOptimizations 请求
     *
     * @return
     */

    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
        }

        return isIgnoring;
    }

}
