package cn.pasteme.admin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AnnounceDO {

    private long id;

    private String title;

    private String content;

    private Date time;

    private String link;

    private int type;
}
