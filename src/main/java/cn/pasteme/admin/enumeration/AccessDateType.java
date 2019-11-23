package cn.pasteme.admin.enumeration;

import cn.pasteme.admin.util.DateConverter;
import cn.pasteme.admin.util.strategy.date.ConverterDayStart;
import cn.pasteme.admin.util.strategy.date.ConverterMonthStart;
import cn.pasteme.admin.util.strategy.date.ConverterYearStart;
import cn.pasteme.common.utils.exception.GlobalException;
import cn.pasteme.common.utils.result.ResponseCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 访问日期类型
 *
 * @author Moyu
 * @version 1.0.0
 */
@Slf4j
public enum AccessDateType {
    // 年
    DATE_YEAR("year", new DateConverter(new ConverterYearStart())),

    // 月
    DATE_MONTH("month", new DateConverter(new ConverterMonthStart())),

    // 日
    DATE_DAY("day", new DateConverter(new ConverterDayStart()));

    @Getter
    String name;
    @Getter
    DateConverter converter;

    AccessDateType(String name, DateConverter converter) {
        this.name = name;
        this.converter = converter;
    }

    public static DateConverter createConverter(String name) {
        for (AccessDateType accessDateType : AccessDateType.values()) {
            if (accessDateType.getName().equals(name)) {
                return accessDateType.getConverter();
            }
        }
        log.error("this type is an illegal parameter: {}", name);
        throw new GlobalException(ResponseCode.PARAM_ERROR);
    }
}
