package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * RiskCheckDO.state 值域
 * 同时也是 label
 *
 * @author Lucien
 * @version 1.1.2
 */
public enum RiskStateDoType implements ValueEnum {

    // 普通
    NORMAL(0),

    // 非法
    RISK(1);

    @Getter
    final private int value;

    RiskStateDoType(int value) {
        this.value = value;
    }
}
