package cn.pasteme.admin.util;


import cn.pasteme.admin.util.strategy.date.DateConverterStrategy;

import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 * @date 2019/11/23 12:06
 */
public class DateConverter {

    private DateConverterStrategy dateConverterStrategy;

    public DateConverter(DateConverterStrategy dateConverterStrategy) {
        this.dateConverterStrategy = dateConverterStrategy;
    }

    public Date getStartDate(Date currentDate) {
        return dateConverterStrategy.startDateConverter(currentDate);
    }

    public Date getEndDate(Date currentDate) {
        return dateConverterStrategy.endDateConverter(currentDate);
    }
}
