package cn.pasteme.admin.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 */
@Data
public class AccessCountDO {

    private Long key;

    private Date date;

    private String ip;

    public AccessCountDO(Long key, Date date, String ip) {
        this.key = key;
        this.date = date;
        this.ip = ip;
    }
}
