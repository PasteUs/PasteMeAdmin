package cn.pasteme.admin.dto;

import cn.pasteme.admin.enumeration.AnnounceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AnnounceResultDTO {

    private Long id;

    private String title;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String link;

    private AnnounceType type;

}
