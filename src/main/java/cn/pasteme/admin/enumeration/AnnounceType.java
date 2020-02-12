package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;
import cn.pasteme.common.utils.exception.GlobalException;
import cn.pasteme.common.utils.result.ResponseCode;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Acerkoo
 * @version 1.0.0
 */

@Slf4j
public enum  AnnounceType implements ValueEnum {

    UPDATE_LOG(0),

    EMERGENCY(1),

    DAILY_ANNOUNCEMENT(2);

    int value;

    AnnounceType(int value) { this.value = value; }

    @Override
    public int getValue() { return value; }

    public static AnnounceType value2Type(int value) {
        for (AnnounceType announceType: AnnounceType.values()) {
            if (announceType.getValue() == value) {
                return announceType;
            }
        }
        log.error("this type is an illegal parameter: {}", value);
        throw new GlobalException(ResponseCode.PARAM_ERROR);
    }

}
