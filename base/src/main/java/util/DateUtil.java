package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取时间的工具类
 */
public class DateUtil {

    private static DateUtil dateUtil;
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static DateUtil getInstance() {
        if (dateUtil == null)
            synchronized (DateUtil.class) {
                if (dateUtil == null)
                    dateUtil = new DateUtil();
            }
        return dateUtil;
    }

    /**
     * 获取当前时间
     * @return
     */
    public String getCurrentDate() {
        return sdf.format(new Date());
    }

    /**
     * 获取当日开始时间
     * @return
     */
    public String getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        return sdf.format(todayStart.getTime());
    }

    /**
     * 获取当日结束时间
     * @return
     */
    public String getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return sdf.format(todayEnd.getTime());
    }

}
