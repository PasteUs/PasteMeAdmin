package cn.pasteme.admin.util.strategy.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 */
public class ConverterDayStart implements DateConverterStrategy {
    @Override
    public Date startDateConverter(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    @Override
    public Date endDateConverter(Date currentDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }
}
