package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * RiskCheckStateDO.state 值域
 * 同时也是 label
 *
 * @author Lucien
 * @version 1.1.2
 */
public enum RiskCheckStateEnum implements ValueEnum {

    // 普通
    NORMAL(0),

    // 非法
    RISK(1);

    @Getter
    final private int value;

    RiskCheckStateEnum(int value) {
        this.value = value;
    }
}
