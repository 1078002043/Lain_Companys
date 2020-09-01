package util;

/**
 * @ClassName: WebSocketMessageReceive
 * @Description: 收到 WebSocket 的消息后，通过这个接口回调给调用者
 * @Author: YIN LUO FEI
 * @Date: 2020/6/5 11:08
 */
public interface WebSocketMessageReceive {
    /**
     * WebSocket接到到消息后回调
     * @param message 接收到的消息
     */
    void receiveMessage(String message);

}
