package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.RiskCheckStateTypeEnum;
import cn.pasteme.admin.enumeration.RiskCheckStateEnum;

import lombok.Data;

/**
 * Risk Check 状态实体
 *
 * @author Lucien
 * @version 1.2.2
 */
@Data
public class RiskCheckStateDO {

    /**
     * 主键
     */
    private Long key;

    /**
     * 这篇 Paste 的类型，同时也是 Classify label
     */
    private RiskCheckStateEnum type;

    /**
     * 这篇的状态
     */
    private RiskCheckStateTypeEnum state;

    public RiskCheckStateDO(Long key) {
        this.key = key;
        this.type = RiskCheckStateEnum.NORMAL;
        this.state = RiskCheckStateTypeEnum.UNCHECKED;
    }
}
