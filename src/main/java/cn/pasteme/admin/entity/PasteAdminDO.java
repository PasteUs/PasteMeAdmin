package cn.pasteme.admin.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Data
public class PasteAdminDO {

    private Long key;

    private Long count;

    private Integer type;

    private Integer state;
}
