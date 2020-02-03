package cn.pasteme.admin.entity;

import lombok.Data;

import java.util.Date;

/**
 *
 * @author Acerkoo
 * @version 1.0.0
 */

@Data
public class AnnounceDO {

    private long id;

    private String title;

    private String content;

    private Date time;

    private String link;

    private int type;

}
