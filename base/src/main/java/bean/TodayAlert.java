package bean;

public class TodayAlert {


    /**
     * count : 4
     * name : 温湿度
     * info :
     * isRead : true
     */

    private int count;
    private String name;
    private String info;
    private boolean isRead;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
