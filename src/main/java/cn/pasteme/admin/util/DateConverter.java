package cn.pasteme.admin.util;

import cn.pasteme.admin.enumeration.AccessDateType;
import cn.pasteme.admin.util.strategy.date.ConverterDayStart;
import cn.pasteme.admin.util.strategy.date.ConverterMonthStart;
import cn.pasteme.admin.util.strategy.date.ConverterYearStart;
import cn.pasteme.admin.util.strategy.date.DateConverterStrategy;
import cn.pasteme.common.utils.exception.GlobalException;
import cn.pasteme.common.utils.result.ResponseCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.1
 */
@Slf4j
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
