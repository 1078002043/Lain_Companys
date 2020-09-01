package com.example.mymodule;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.allenliu.versionchecklib.callback.OnCancelListener;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.CustomDownloadingDialogListener;
import com.allenliu.versionchecklib.v2.callback.CustomVersionDialogListener;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;
import com.king.view.circleprogressview.CircleProgressView;

/**
 * APP 更新工具类，暂时不用了
 */
public class UpdateAppUtil {
    //单例实例
    private volatile static UpdateAppUtil updateAppUtil;
    //下载类
    private volatile static DownloadBuilder builder;
    //上下文
    private Context context;
    //更新窗口的UI
    private UIData uiData;


    //单例
    public static UpdateAppUtil getInstance() {
        if (updateAppUtil == null)
            synchronized (UpdateAppUtil.class) {
                if (updateAppUtil == null) {
                    updateAppUtil = new UpdateAppUtil();
                    return updateAppUtil;
                }
            }

        return updateAppUtil;
    }

    /**
     * 更新APP
     *
     * @param context 需要使用到 Context
     * @param url     更新APK的 URL
     */
    public void updateApp(final Context context, final String url, String title, String content, Activity activity) {
        //保存 context
        this.context = context;
        //初始化 下载类
        builder = AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl("https://www.baidu.com")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(DownloadBuilder downloadBuilder, String result) {
//                            V2.1.1可以根据服务器返回的结果，动态在此设置是否强制更新等
//                            downloadBuilder.setForceUpdateListener(() -> {
//                                forceUpdate();
//                            });
                        //更新窗口成功弹出
                        Toast.makeText(context, "request successful", Toast.LENGTH_SHORT).show();
                        //构建下载
                        return crateUIData(url, title, content);
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {
                        //更新窗口弹出失败
                        Toast.makeText(context, "request failed", Toast.LENGTH_SHORT).show();
                    }
                });

        //下载返回监听
        builder.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel() {
                Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
            }
        });

        //取消更新时，返回监听
        builder.setOnCancelListener(() -> {
            Toast.makeText(context, "Cancel Hanlde", Toast.LENGTH_SHORT).show();
        });

        //自定义下载路径
        builder.setDownloadAPKPath(Environment.getExternalStorageDirectory() + "/lain_log/apk/");

        //自定义后台更新 通知
        builder.setNotificationBuilder(createCustomNotification());
        //更新窗口的 弹窗
        builder.setCustomVersionDialogListener(createCustomDialogTwo());
        //正在更新时的 进度条
        builder.setCustomDownloadingDialogListener(createCustomDownloadingDialog());

        //无论是否已下载 APK, 始终重复下载

        /*if (builder.isForceRedownload()) {
            builder.setForceUpdateListener(() -> {
                forceUpdate(activity);
            });
        }*/

        builder.setForceRedownload(true);
        //显示更新提示
        builder.executeMission(context);

    }
    /**
     * 强制更新操作
     * 通常关闭整个activity所有界面，这里方便测试直接关闭当前activity
     * 也用于有下载版本时，会直接安装而不是重新下载
     */
    private void forceUpdate(Activity activity) {
        activity.finish();
    }

    /**
     * 构建下载相关的实例
     *
     * @return 下载窗口实例
     * @important 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    public UIData crateUIData(String url, String title, String content) {

        UIData uiData = UIData.create();

        //更新窗口的标题
        uiData.setTitle(setDownloadTitle(title));
        //APK 下载的 URL
        uiData.setDownloadUrl(url);
        //更新窗口的内容
        uiData.setContent(setDowunloadContent(content));



        return uiData;
    }

    /**
     * 更新窗口
     *
     * @return 更新窗口的监控
     */
    private CustomVersionDialogListener createCustomDialogTwo() {
        return (context, versionBundle) -> {
            //加载 Dialog
            BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_dialog_two_layout);
            //更新的标题
            TextView titleView = baseDialog.findViewById(R.id.tv_title);
            titleView.setText(versionBundle.getTitle());
            //更新内容
            TextView textView = baseDialog.findViewById(R.id.tv_msg);
            textView.setText(versionBundle.getContent());
            //下载 / 升级 按钮
            Button actionBtn = baseDialog.findViewById(R.id.versionchecklib_version_dialog_commit);
            if (versionBundle.getTitle().equals("下载视频监控插件"))
                actionBtn.setText("下载");

            //是否允许点击 Back 键能隐藏 更新窗口
            baseDialog.setCanceledOnTouchOutside(true);
            //返回 Dialog 实例
            return baseDialog;
        };
    }

    /**
     * 自定义下载中对话框，下载中会连续回调此方法 updateUI
     * 务必用库传回来的context 实例化你的dialog
     *
     * @return 进度条的 监控
     */
    private CustomDownloadingDialogListener createCustomDownloadingDialog() {
        return new CustomDownloadingDialogListener() {
            @Override
            public Dialog getCustomDownloadingDialog(Context context, int progress, UIData versionBundle) {
                //返回 Dialog 实例
                BaseDialog baseDialog = new BaseDialog(context, R.style.BaseDialog, R.layout.custom_download_layout);
                return baseDialog;
            }

            @Override
            public void updateUI(Dialog dialog, int progress, UIData versionBundle) {
                //更新UI
                TextView tvProgress = dialog.findViewById(R.id.tv_progress);    //更新文字进度
                //更新进度条
                CircleProgressView progressBar = dialog.findViewById(R.id.pb);
                progressBar.setProgress(progress);
                //更新进度
                progressBar.setLabelText(progress + " %");
            }
        };
    }

    /**
     * 自定义的后台下载通知
     *
     * @return 通知实例
     */
    private NotificationBuilder createCustomNotification() {
        return NotificationBuilder.create()
                //下载时不能取消，下载完成便行
                .setRingtone(true)
                .setIcon(R.mipmap.dialog4)
                .setTicker("custom_ticker")
                .setContentTitle("莱安智能 更新中...")
                .setContentText(context.getString(R.string.custom_content_text));
    }

    /**
     * 设置 APK 下载的名称
     *
     * @param apkName 下载 APK 的名称
     */
    public void setApkName(String apkName) {
        builder.setApkName(apkName);
    }

    /**
     * 释放所有的更新通知
     */
    public void destroyUpdate() {
        //合适的地方关闭
        AllenVersionChecker.getInstance().cancelAllMission();
    }

    /**
     * 自定义下载标题，默认为 更新标题
     * @param title 下载提示的标题
     * @return 返回标题
     */
    private String setDownloadTitle(String title) {

        if (title.isEmpty())
            return context.getString(R.string.update_title);
        //返回自定义标题
        return title;
    }

    /**
     * 自定义内容，默认为更新APP的内容
     * @param content 内容
     * @return 内容
     */
    private String setDowunloadContent(String content) {
        if (content.isEmpty())
            return context.getString(R.string.updatecontent);
        //自定义内容
        return content;
    }

}
