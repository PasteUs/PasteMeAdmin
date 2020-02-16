package cn.pasteme.admin.dto;

import lombok.Data;

/**
 * Announcement 请求时参数的封装
 *
 * @author Acerkoo
 * @version 1.0.0
 */
@Data
public class AnnounceRequestDTO extends AnnouncementDTO {

    /**
     * 类型
     */
    private int type;

}
