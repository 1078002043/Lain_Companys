package bean;

public class HydrogenAlertBean {


    /**
     * hydrogenSulfideAlarmId : 1
     * hydrogenSulfideId : 1
     * hydrogenSulfideName : 硫化氢
     * hydrogenSulfideData : 50.0
     * info : 硫化氢报警
     * hydrogenSulfideAlarmTime : 2019-12-26 14:07:35
     */

    private int hydrogenSulfideAlarmId;
    private int hydrogenSulfideId;
    private String hydrogenSulfideName;
    private double hydrogenSulfideData;
    private String info;
    private String hydrogenSulfideAlarmTime;

    public int getHydrogenSulfideAlarmId() {
        return hydrogenSulfideAlarmId;
    }

    public void setHydrogenSulfideAlarmId(int hydrogenSulfideAlarmId) {
        this.hydrogenSulfideAlarmId = hydrogenSulfideAlarmId;
    }

    public int getHydrogenSulfideId() {
        return hydrogenSulfideId;
    }

    public void setHydrogenSulfideId(int hydrogenSulfideId) {
        this.hydrogenSulfideId = hydrogenSulfideId;
    }

    public String getHydrogenSulfideName() {
        return hydrogenSulfideName;
    }

    public void setHydrogenSulfideName(String hydrogenSulfideName) {
        this.hydrogenSulfideName = hydrogenSulfideName;
    }

    public double getHydrogenSulfideData() {
        return hydrogenSulfideData;
    }

    public void setHydrogenSulfideData(double hydrogenSulfideData) {
        this.hydrogenSulfideData = hydrogenSulfideData;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHydrogenSulfideAlarmTime() {
        return hydrogenSulfideAlarmTime;
    }

    public void setHydrogenSulfideAlarmTime(String hydrogenSulfideAlarmTime) {
        this.hydrogenSulfideAlarmTime = hydrogenSulfideAlarmTime;
    }
}
