package com.example.mymodule;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 2019-10-26 14：00
 * 文件上传工具类
 * BUG：第一次安装时，出问题无法上传文件，因为文件还没有创建完成
 */
public class FileUpload implements Callback {
    //单例实例
    private volatile static FileUpload fileUpload;

    //双重单例
    public static FileUpload getInstance() {
        if (fileUpload == null)
            synchronized (FileUpload.class) {
                if (fileUpload == null) {
                    fileUpload = new FileUpload();
                    return fileUpload;
                }
            }
        return fileUpload;
    }

    /**
     * 上传文件，默认后缀 .log
     *
     * @param file 上传的文件
     */
    public void uploadFile(File file) {

        //小文云端上传文件 URL
        String url = "http://39.106.38.83/my_up/public/upload.php";

        OkHttpClient okHttpClient = new OkHttpClient();

        //上传文件的 Body
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //提交 form-data 数据
        RequestBody fileBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file);
        //文件参数
        builder.addFormDataPart("file", "head_image", fileBody);

        RequestBody requestBody = builder.build();
        //构建好请求的 Body 后，进行请求
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //发送请求
        okHttpClient.newCall(request).enqueue(this);

    }

    /**
     * 上传文件，指定文件的后缀
     *
     * @param file   文件
     * @param suffix 后缀
     */
    public void uploadFile(File file, String suffix) {

        //小文云端上传文件 URL
        String url = "http://39.106.38.83/my_up/public/upload.php";

        OkHttpClient okHttpClient = new OkHttpClient();

        //上传文件的 Body
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        //提交 form-data 数据
        RequestBody fileBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file);
        //文件参数
        builder.addFormDataPart("file", "head_image", fileBody);
        //上传文件的后缀
        builder.addFormDataPart("suffix", suffix);

        RequestBody requestBody = builder.build();
        //构建好请求的 Body 后，进行请求
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //发送请求
        okHttpClient.newCall(request).enqueue(this);

    }

    //上传失败
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

    }

    //上传成功
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        Log.d("upload_status", "上传成功: " + response.body().string());
    }
}
