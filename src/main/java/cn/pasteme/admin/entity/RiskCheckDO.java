package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.RiskStateDoState;
import cn.pasteme.admin.enumeration.RiskStateDoType;

import lombok.Data;

/**
 * Risk Check 状态实体
 *
 * @author Lucien
 * @version 1.2.2
 */
@Data
public class RiskCheckDO {

    /**
     * 主键
     */
    private Long key;

    /**
     * 这篇 Paste 的类型，同时也是 Classify label
     */
    private RiskStateDoType type;

    /**
     * 这篇的状态
     */
    private RiskStateDoState state;

    public RiskCheckDO(Long key) {
        this.key = key;
        this.type = RiskStateDoType.NORMAL;
        this.state = RiskStateDoState.UNCHECKED;
    }
}
