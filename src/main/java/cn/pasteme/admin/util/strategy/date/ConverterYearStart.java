package cn.pasteme.admin.util.strategy.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 * @date 2019/11/23 12:29
 */
public class ConverterYearStart implements DateConverterStrategy {

    @Override
    public Date startDateConverter(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.YEAR, 0);
        // 设置为 1 号,当前日期既为本年 1 天
        c.set(Calendar.DAY_OF_YEAR, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    @Override
    public Date endDateConverter(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }
}
