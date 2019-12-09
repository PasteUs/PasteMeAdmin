package cn.pasteme.admin.util.strategy.date;

import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 */
public interface DateConverterStrategy {

    /**
     * 日期转换获取开始时间 年/月/日
     *
     * @param currentDate 当前日期
     * @return 开始日期
     */
    Date startDateConverter(Date currentDate);

    /**
     * 日期转换获取结束时间 年/月/日
     *
     * @param currentDate 当前日期
     * @return 结束日期
     */
    Date endDateConverter(Date currentDate);
}
