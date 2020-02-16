package cn.pasteme.admin.dto;

import cn.pasteme.admin.enumeration.AnnounceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * Announcement 查询结果参数的封装
 *
 * @author Acerkoo
 * @version 1.0.0
 */
@Data
public class AnnounceResultDTO extends AnnouncementDTO {

    /**
     * id 主键
     */
    private Long id;

    /**
     * time 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    /**
     * type 通知类型
     */
    private AnnounceType type;

}
