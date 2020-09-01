package bean;

/**
 * 解析机房监控设备
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/1 14 : 44
 */
public class EnvironmentBean {
    /**
     * iconUrl :
     * id : 1
     * name : 温湿度
     * number : 1
     */

    private String iconUrl;
    private String id;
    private String name;
    private String number;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * iconUrl : /resources/icon/72x72/environment/humiture.png 图标
     * id : 1 ID
     * isShow : 1 是否显示
     * name : 温湿度 设备名称
     * number : 1 请求位置，根据这个位置来请求具体的设备
     */

  /*  private String iconUrl;
    private int id;
    private int isShow;
    private String name;
    private int number;
    private String ehmHum;
    private String ehmTemp;


    public String getEhmHum() {
        return ehmHum;
    }

    public void setEhmHum(String ehmHum) {
        this.ehmHum = ehmHum;
    }

    public String getEhmTemp() {
        return ehmTemp;
    }

    public void setEhmTemp(String ehmTemp) {
        this.ehmTemp = ehmTemp;
    }

    public static EnvironmentBean objectFromData(String str) {

        return new Gson().fromJson(str, EnvironmentBean.class);
    }

    public static EnvironmentBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), EnvironmentBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<EnvironmentBean> arrayMonitorBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<EnvironmentBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<EnvironmentBean> arrayMonitorBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<EnvironmentBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }*/



}
