package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.AnnounceType;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Announcement 的实体
 *
 * @author Acerkoo
 * @version 1.0.1
 */
@Data
public class AnnounceDO {

    /**
     * id 主键
     */
    private long id;

    /**
     * title 标题
     */
    private String title;

    /**
     * content 内容
     */
    private String content;

    /**
     * time 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String link;

    private AnnounceType type;
}
