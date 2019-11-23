package cn.pasteme.admin.util.strategy.date;

import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 * @date 2019/11/23 12:26
 */
public interface DateConverterStrategy {

    Date startDateConverter(Date currentDate);

    Date endDateConverter(Date currentDate);
}
