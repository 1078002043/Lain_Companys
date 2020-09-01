package http;

import android.util.Log;

import com.google.common.base.Utf8;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Objects;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import util.ToastManage;

/**
 * @ClassName: TokenInterceptor
 * @Description: 请求拦截，如果Token过期，则进行刷新
 * @Author: YIN LUO FEI
 * @Date: 2020/7/24 14:09
 */
public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);

        //获取请求体
        ResponseBody responseBody = response.body();
        //获取请求结果的长度
        long contentLength = responseBody.contentLength();

        //获取请求中的资源，返回缓冲区
        BufferedSource bufferedSource = Objects.requireNonNull(responseBody).source();
        //设置请求的长度为最大
        bufferedSource.request(Long.MAX_VALUE);
        //从资源中获取 Buffer
        Buffer buffer = bufferedSource.getBuffer();
        //定义解析数据的编码
        Charset charset = UTF8;
        //获取媒体类型
        MediaType contentType = responseBody.contentType();

        //如果不为空
        if (contentType != null) {

            try {
                //设置数据解析编码
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                return response;
            }

        }

        //保存请求的结果
        String result;
        //获取Token是否过期
        Response newRequest = null;

        //如果内容长度不为0
        if (contentLength != 0) {

            //获取请求到的数据
            result = buffer.clone().readString(charset);
            //获取是否已过期
            newRequest = getTokenExpired(chain, result);

            Log.d(getClass().getName(), "请求结果: " + result);

        }

        if (newRequest != null) return newRequest;

        return response;

    }

    @Nullable
    private Response getTokenExpired(@NotNull Chain chain, String response) throws IOException {

        String tokenExpired = isTokenExpired(response);

        //如果Token过期所执行的操作
        if (tokenExpired.equals("token校验失败")) {

//            ToastManage.getInstance().toastShortShow(tokenExpired + "，请重新登陆");

            //刷新Token
            OkHttpUtil.getInstance().loginRequest("admin", "admin", null);

            Request newRequest = chain.request()
                    .newBuilder()
                    .header("token", MySharePreference.getInstance().getPreference("token", "token"))
                    .build();

            //重新请求
            return chain.proceed(newRequest);

        } else if (tokenExpired.equals("该用户未拥有相应权限，无法进行操作")) {

//            ToastManage.getInstance().toastShortShow("tokenExpired");

        }

        return null;
    }

    /**
     * 判断Token是否过期
     *
     * @param response Token 的响应信息
     * @return 是否已过期
     */
    private String isTokenExpired(String response) {

        if (response.equals("{\"message\":\"token校验失败\",\"status\":401}")) {
            return "token校验失败";
        } else if (response.equals("{\"status\":\"401 UNAUTHORIZED\"}"))
            return "该用户未拥有相应权限，无法进行操作";

        return "正常";
    }

    /**
     * 判断是否是纯文本
     *
     * @param buffer 请求的流
     * @return 返回判断结果
     * @throws EOFException 解压缩异常
     */
    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

}
