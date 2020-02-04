package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.AnnounceType;
import lombok.Data;

import java.util.Date;

/**
 * Announcement 的实体
 *
 * @author Acerkoo
 * @version 1.0.0
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

    private Date time;

    private String link;

    private int type;

//    public void setType(int value) {
//        if (value == 0) type = AnnounceType.UPDATE_LOG;
//        else if (value == 1) type = AnnounceType.EMERGENCY;
//        else type = AnnounceType.DAILY_ANNOUNCEMENT;
//    }

}
