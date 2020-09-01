package cn.com.lain;

import android.util.Log;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bean.TemperatureBean;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <temp href="http://d.android.com/tools/testing">Testing documentation</temp>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
       System.out.println(sdf.format(date));

    }
}