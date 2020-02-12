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
     * title 标题
     * content 内容
     * time 最后修改时间
     * type Announcement 类型
     */

    private long id;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String link;

    private AnnounceType type;

}
