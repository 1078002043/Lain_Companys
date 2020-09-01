package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: TimeUtil
 * @Description: 时间获取工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/27 15:54
 */
public class TimeUtil {

    private volatile static TimeUtil timeUtil;

    public static TimeUtil getInstance() {

        if (timeUtil == null)
            synchronized (TimeUtil.class) {
                if (timeUtil == null)
                    timeUtil = new TimeUtil();
            }

        return timeUtil;
    }

    public String getTimeOfMonthStart() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return String.format("%tF %<tT", ca.getTimeInMillis());
    }

    public String getTimeOfNextMonthStart() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return String.format("%tF %<tT", ca.getTimeInMillis());
    }

    public String getTimeOfWeekStart() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek());
        return String.format("%tF %<tT", ca.getTimeInMillis());
    }

    public String getTimeOfDayStart() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Date curDate = new Date(System.currentTimeMillis());

        return formatter.format(curDate);

    }

    public String getTimeCurrent() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());

        return formatter.format(curDate);

    }

}
