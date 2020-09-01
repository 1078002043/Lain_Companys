package fragment;

/**
 * @ClassName: PlaySuccessCallBack
 * @Description: 视频监控播放成功后，回调该接口
 * @Author: YIN LUO FEI
 * @Date: 2020/6/17 10:04
 */
public interface PlaySuccessCallBack {
    /**
     * 视频播放成功会回调该方法
     * @param port 接收播放端口
     */
    public void playSuccessCallBack(int port);

}
