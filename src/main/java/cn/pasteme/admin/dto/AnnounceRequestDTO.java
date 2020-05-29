package cn.pasteme.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Announcement 请求时参数的封装
 *
 * @author Acerkoo
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnnounceRequestDTO extends AbstractAnnouncementDTO {

    /**
     * 类型
     */
    private int type;

}
