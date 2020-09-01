package view_interface;

import java.util.List;

import bean.Alert8052Bean;
import bean.Device8052Bean;

public interface I_Base8052Fragment<R, A> {

    /**
     * 获取实时数据的请求链接
     * @return 链接
     */
    String getLinkReal();
    /**
     * 获取报警数据的请求链接
     * @return 链接
     */
    String getLinkAlert();

    <R> void deal8052Real(List<R> realList);

    <A> void deal8052Alert(List<A> alertList);
}
