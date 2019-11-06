package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.RiskStateType;
import cn.pasteme.admin.enumeration.RiskStateState;

import lombok.Data;

/**
 * Risk Check 状态实体
 *
 * @author Lucien
 * @version 1.2.1
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
    private RiskStateState type;

    /**
     * 这篇的状态
     */
    private RiskStateType state;

    public RiskCheckDO(Long key) {
        this.key = key;
        this.type = RiskStateState.NORMAL;
        this.state = RiskStateType.UNCHECKED;
    }
}
