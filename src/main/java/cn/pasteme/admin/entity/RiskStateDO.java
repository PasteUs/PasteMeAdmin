package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.PasteState;
import cn.pasteme.admin.enumeration.PasteType;

import lombok.Data;

/**
 * Risk Check 状态实体
 *
 * @author Lucien
 * @version 1.2.0
 */
@Data
public class RiskStateDO {

    /**
     * 主键
     */
    private Long key;

    /**
     * 这篇 Paste 的类型，同时也是 Classify label
     */
    private PasteType type;

    /**
     * 这篇的状态
     */
    private PasteState state;

    public RiskStateDO(Long key) {
        this.key = key;
        this.type = PasteType.NORMAL;
        this.state = PasteState.UNCHECKED;
    }
}
