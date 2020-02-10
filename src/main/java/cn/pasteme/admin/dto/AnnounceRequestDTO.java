package cn.pasteme.admin.dto;

import lombok.Data;

/**
 * Announcement 的实体
 *
 * @author Acerkoo
 * @version 1.0.0
 */

@Data
public class AnnounceRequestDTO {

    private String title;

    private String content;

    private String link;

    private int type;
}
