package computer_room.bean;

import org.jetbrains.annotations.NotNull;

/**
 * 视频监控回放
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/9/2 14 : 09
 */
public class VideoDateBean {

    private int year = 1970;
    private int month = 1;
    private int day = 1;
    private int hour = 0;
    private int min = 0;
    private int second = 0;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @NotNull
    @Override
    public String toString() {
        return
                year + "-" + month + "-" + day + "-" + hour + "-" + min + "-" + second;
    }

}
