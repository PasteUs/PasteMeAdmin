package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.AnnounceType;
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
     * createTime 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    /**
     * updateTime 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    /**
     * link 公告附加链接
     */
    private String link;

    /**
     * AnnounceType 通知类型
     */
    private AnnounceType type;
}
