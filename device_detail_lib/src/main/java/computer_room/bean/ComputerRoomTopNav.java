package computer_room.bean;

/**
 * 解析机房监控顶部导航栏JSON BEAN类
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/8/1 13 : 55
 */
public class ComputerRoomTopNav {
    /**
     * iconUrl :
     * id : 1
     * name : 主页
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
     * iconUrl :
     * id : 1
     * isShow : 1
     * name : 主页
     * number : 1
     */

    /*private String iconUrl;
    private int id;
    private int isShow;
    private String name;
    private int number;

    public static ComputerRoomTopNav objectFromData(String str) {

        return new Gson().fromJson(str, ComputerRoomTopNav.class);
    }

    public static ComputerRoomTopNav objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ComputerRoomTopNav.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ComputerRoomTopNav> arrayDevice_Classfication_BeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ComputerRoomTopNav>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ComputerRoomTopNav> arrayDevice_Classfication_BeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ComputerRoomTopNav>>() {
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
