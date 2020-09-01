package com.example.ffmeger_com;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.style.IconMarginSpan;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.coder.ffmpeg.call.CommonCallBack;
import com.coder.ffmpeg.jni.FFmpegCommand;
import com.coder.ffmpeg.utils.FFmpegUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @ClassName: FfmegerControl
 * @Description: 播放控件
 * @Author: YIN LUO FEI
 * @Date: 2020/6/15 13:38
 */
public class FfmegerControl {

    private String mVideoPath;
    private String targetPath;

    private PromptDialog mErrorDialog;

    private Context context;

    private static FfmegerControl control;
    //文件名称
    private String fileName;
    //转换后的文件名称
    private String transformName;
    //文件路径
    private String filePath;
    //保存封面的路径
    private String pngPath;

    public static FfmegerControl getInstance() {
        if (control == null)
            synchronized (FfmegerControl.class) {
                if (control == null)
                    control = new FfmegerControl();
            }
        return control;
    }

    /**
     * 初始化文件，获取文件的绝对路径
     *
     * @param context  上下文
     * @param fileName 文件名称，后缀为转码格式  test.mp4 转码为 mp4
     * @param filePath 文件路径
     */
    public void initFile(@NonNull Context context, @NonNull String fileName, @NonNull String transformName, @NonNull String filePath) throws FileNotFoundException {

        //保存上下文
        this.context = context;
        //保存文件名称
        this.fileName = fileName;
        //转码后保存的文件名称
        this.transformName = transformName;
        //保存文件的路径
        this.filePath = filePath;

        //指定文件的路径
        mVideoPath = new File(filePath, this.fileName).getAbsolutePath();
        //开始转码

        //因为代码执行了，但文件还没生成，会导致闪退的问题
        if (!new File(mVideoPath).exists()) {
            throw new FileNotFoundException("录制的文件未找到");
        }
        //抽离视频
        extractVideo();

    }

    /**
     * 抽离视频
     */
    private void extractVideo() {
        targetPath = filePath + File.separator + this.transformName;
        FFmpegCommand.runAsync(FFmpegUtils.extractVideo(mVideoPath, targetPath), callback("抽取视频完成", targetPath));
    }

    /**
     * 获取视频的第一帧图片做为封面
     * @return
     */
    private void screenShot() {
        String pngName = this.fileName;
        pngPath = filePath + pngName.replace("mp4", "png");
        FFmpegCommand.runAsync(FFmpegUtils.screenShot(mVideoPath, pngPath), coverPngCallBack("视频截图完成", pngPath));
    }

    /**
     * 视频转码
     */
    private void transformVideo() {

        targetPath = filePath + File.separator + this.transformName;

        FFmpegCommand.runAsync(FFmpegUtils.transformVideo(mVideoPath, targetPath), callback("视频转码完成", targetPath));

    }

    /**
     * 视频封面回调
     * @return
     */
    private CommonCallBack coverPngCallBack(String msg, String targetPath) {

        return new CommonCallBack() {
            @Override
            public void onComplete() {
                Log.d("save_cover", "onComplete: 保存封面成功");
            }
        };

    }

    /**
     * 转码成功后的回调方法
     *
     * @param msg        转码成功的消息
     * @param targetPath 转码后文件保存路径
     * @return
     */
    private CommonCallBack callback(final String msg, final String targetPath) {

        if (mErrorDialog == null) {
            mErrorDialog = PromptDialog.newInstance("进度", msg, "", "停止");
            mErrorDialog.setHasNegativeButton(false);
            mErrorDialog.setOnPromptListener(new PromptDialog.OnPromptListener() {
                @Override
                public void onPrompt(boolean isPositive) {

                    FFmpegCommand.exit();

                }
            });
            mErrorDialog.setOnPromptListener(new PromptDialog.OnPromptListener() {
                @Override
                public void onPrompt(boolean isPositive) {

                }
            });

        }

        return new CommonCallBack() {
            //转换错误
            @Override
            public void onError(Throwable t) {

            }
            //转换完成
            @Override
            public void onComplete() {

                //如果转换成功后，返回的文件路径不为则，则进行文件替换

                //转码后的文件
                File tPath = new File(targetPath);

                //转码前的文件
                File mVideoFile = new File(mVideoPath);

                //先将转码前的文件删除
                boolean mVideoDel = mVideoFile.delete();

                //再对转码后的文件进行重命名
                boolean tPathReName = tPath.renameTo(mVideoFile);

                //获取视频封面，必须在将视频文件转码完成后执行才不会闪退
                screenShot();

            }

            @Override
            public void onStart() {
                //开始转换

            }

            @Override
            public void onCancel() {
                //取消转换

            }

            @Override
            public void onProgress(int progress) {
                //转换进度

            }
        };
    }

}
