package cn.pasteme.admin.util;

import cn.pasteme.admin.enumeration.AnnounceType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AnnounceConverter {

    public AnnounceType getAnnouncementType(int value) throws Exception {
        if (value == 0) {
            return AnnounceType.UPDATE_LOG;
        } else if (value == 1) {
            return AnnounceType.EMERGENCY;
        } else if (value == 2) {
            return AnnounceType.DAILY_ANNOUNCEMENT;
        }
        throw new RuntimeException("Cannot identify the type of this announcement");
    }


    public AnnounceType IntToTypeConverte(int value) {
        try {
            return getAnnouncementType(value);
        } catch (Exception e) {
            log.error("After select announcement type, ederror=", e);
        }
        return null;
    }
}
