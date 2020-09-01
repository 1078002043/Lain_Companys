package util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.parser.moshi.JsonReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import base.Lain_Application;
import okio.BufferedSource;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/5 16:47
 * Description：AE 动画工具，目前没有需要使用到
 **/
public class LottieUtil {

    private static volatile LottieUtil lottieUtil;
    private LottieAnimationView animationView;

    public static LottieUtil getInstance() {

        if (lottieUtil == null)
            synchronized (LottieUtil.class) {
                if (lottieUtil == null)
                    lottieUtil = new LottieUtil();

            }

        return lottieUtil;
    }

    /**
     * 无限循环动画
     *
     * @param lottieName    动画文件名称
     * @param animationView 动画实例
     */
    public void showLottieAnimation(String lottieName, LottieAnimationView animationView) {

        //获取 动画的缓存
        @SuppressLint("RestrictedApi") LottieComposition composition = LottieCompositionCache.getInstance().get(lottieName);
        //判断是否存在缓存，如果存在，则直接从缓存中拿
        if (composition != null) {
            animationView.setComposition(composition);
            return;
        }

        //如果不存在缓存，读取文件流，进行播放动画
        InputStream is = null;

        try {
            //打开文件
            is = Lain_Application.getContext().getAssets().open(lottieName, Context.MODE_PRIVATE);
            //获取文件的长度
            int length = is.available();
            //创建一个文件长度的 byte 数组
            byte[] buffer = new byte[length];
            //将读取的数据保存到 byte[] 中
            is.read(buffer);
            //获取读取的数据
            String temp = new String(buffer);
            //设置动画，并且设置缓存
            animationView.setAnimationFromJson(temp, lottieName);
            animationView.getRepeatCount();//设置动画循环播放，设置 1 时，播放完成停留在最后一秒
//        animationView.setImageAssetsFolder("images/");//assets目录下的子目录，存放动画所需的图片
            animationView.playAnimation();//播放动画

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //关闭读取流
            try {
                if (is != null)
                    is.close();
            } catch (IOException c) {
                c.printStackTrace();
            }

        }


    }


    /**
     * 自定义次数循环动画
     *
     * @param lottieName    动画文件名称
     * @param animationView 动画实例
     */
    public void showLottieAnimation(int repeatCount, String lottieName, LottieAnimationView animationView) {

        animationView.setAnimation(lottieName);//在assets目录下的动画json文件名。
        animationView.setRepeatCount(repeatCount);//设置动画循环播放
//        animationView.setImageAssetsFolder("images/");//assets目录下的子目录，存放动画所需的图片
        animationView.playAnimation();//播放动画

    }

}
