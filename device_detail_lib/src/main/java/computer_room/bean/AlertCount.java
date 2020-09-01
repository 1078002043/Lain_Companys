package computer_room.bean;

/**
 * 报警数量
 */
public class AlertCount {
    //报警名称
    private String name;
    //报警数量
    private int count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
