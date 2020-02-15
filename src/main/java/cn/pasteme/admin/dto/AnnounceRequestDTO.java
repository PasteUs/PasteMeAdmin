package cn.pasteme.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Announcement 的参数封装
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
