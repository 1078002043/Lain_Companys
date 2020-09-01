package com.example.mymodule;

import androidx.annotation.NonNull;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * 程序异常捕获
 * 功能：程序发生异常后，将异常捕获并发送到小文的服务器上
 */
public class MyCrashHandle implements Thread.UncaughtExceptionHandler {
    //文件路径
    private String filePath = "";

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        //收集输出异常的字符流
        //捕获的异常文件上传到服务器
        FileUpload.getInstance().uploadFile(getLogFile());
        //字符串流
        StringWriter sw = new StringWriter();
        //将异常打印到字符流中
        e.printStackTrace(new PrintWriter(sw, true));
        //打印捕获的异常，并保存到文件当前  时间 + 回车 + 日志
        Logger.e("程序出现异常了 " + new Date(System.currentTimeMillis()) + "\n\n\t" + sw.toString());
    }

    /**
     * 根据文件路径获取文件对象
     *
     * @return File 对象
     */
    private File getLogFile() {
        return new File(filePath);
    }

    /**
     * 设置文件路径，用于获取日志文件，上传到服务器上
     *
     * @param filePath 文件路径
     */
    public void setLogFile(String filePath) {
        this.filePath = filePath;
    }

}
