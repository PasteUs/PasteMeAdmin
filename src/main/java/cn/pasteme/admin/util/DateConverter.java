package cn.pasteme.admin.util;


import cn.pasteme.admin.util.strategy.date.ConverterDayStart;
import cn.pasteme.admin.util.strategy.date.ConverterMonthStart;
import cn.pasteme.admin.util.strategy.date.ConverterYearStart;
import cn.pasteme.admin.util.strategy.date.DateConverterStrategy;
import cn.pasteme.common.utils.exception.GlobalException;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 */
@Slf4j
public class DateConverter {

    private DateConverterStrategy dateConverterStrategy;

    private DateConverter(DateConverterStrategy dateConverterStrategy) {
        this.dateConverterStrategy = dateConverterStrategy;
    }

    public Date getStartDate(Date currentDate) {
        return dateConverterStrategy.startDateConverter(currentDate);
    }

    public Date getEndDate(Date currentDate) {
        return dateConverterStrategy.endDateConverter(currentDate);
    }

    public static DateConverter getPeriodConverter(String type) {
        DateConverter dateConverter;
        switch (type) {
            case "year":
                dateConverter = new DateConverter(new ConverterYearStart());
                break;
            case "month":
                dateConverter = new DateConverter(new ConverterMonthStart());
                break;
            case "day":
                dateConverter = new DateConverter(new ConverterDayStart());
                break;
            default:
                log.error("this type is an illegal parameter: {}", type);
                throw new GlobalException(ResponseCode.PARAM_ERROR);
        }
        return dateConverter;
    }
}
