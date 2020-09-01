package bean;

/**
 * @ClassName: LoginJSONBean
 * @Description: 解析登陆时返回的Token
 * @Author: YIN LUO FEI
 * @Date: 2020/7/24 14:39
 */
public class LoginJSONBean {


    /**
     * token : 5fd516804e5af4e158969f5f9904e061
     * zt : ok
     */

    private String token;
    private String zt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getZt() {
        return zt.equals("ok");
    }

    public void setZt(String zt) {
        this.zt = zt;
    }
}
