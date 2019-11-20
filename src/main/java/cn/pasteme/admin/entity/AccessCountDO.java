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
}
