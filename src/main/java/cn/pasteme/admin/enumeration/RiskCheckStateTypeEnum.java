package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * RiskCheckStateDO.type 值域
 * 可用于 supervise
 *
 * @author Lucien
 * @version 1.1.2
 */
public enum RiskCheckStateTypeEnum implements ValueEnum {

    // 已检查
    CHECKED(0),

    // 未检查
    UNCHECKED(1),

    // 已核查
    REVIEWED(2),

    // 需要核查
    REQUEST_REVIEW(3);

    @Getter
    final private int value;

    RiskCheckStateTypeEnum(int value) {
        this.value = value;
    }
}
