package http;

/**
 * Author：YIN_LUO_FEI
 * Date：2019/11/18 22:42
 * Description：OkHttp 请求成功回调接口
 **/
public interface OkHttpCallBack {
    /**
     * OkHttp 请求成功回调接口
     * @param requestTag 请求的 标识
     * @param requestUrl 请求的 URL
     * @param responseStr 请求成功返回的 JSON
     */
    public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr);

    /**
     * 请求失败的回调
     * @param requestTag 请求的 标识
     * @param requestUrl 请求的 URL
     * @param errorMsg 请求失败返回的信息
     */
    public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg);

}
