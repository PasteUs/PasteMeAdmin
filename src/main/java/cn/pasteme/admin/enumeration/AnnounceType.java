package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

public enum  AnnounceType implements ValueEnum {

    UPDATE_LOG(0),

    EMERGENCY(1),

    DAILY_ANNOUNCEMENT(2);

    int value;

    AnnounceType(int value) { this.value = value; }

    @Override
    public int getValue() {
        return 0;
    }
}
