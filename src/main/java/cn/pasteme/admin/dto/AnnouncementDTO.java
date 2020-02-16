package cn.pasteme.admin.dto;

import lombok.Data;

/**
 * Announcement DTO抽象类
 *
 * @author Acerkoo
 * @version 1.0.0
 */
@Data
public abstract class AnnouncementDTO {

    /**
     * title 标题
     */
    private String title;

    /**
     * content 内容
     */
    private String content;

    /**
     * link 公告附加链接
     */
    private String link;

}
