package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * RiskCheckDO.state 值域
 * 同时也是 label
 *
 * @author Lucien
 * @version 1.1.1
 */
public enum RiskStateState implements ValueEnum {

    // 普通
    NORMAL(0),

    // 非法
    PORN(1);

    @Getter
    private int value;

    RiskStateState(int value) {
        this.value = value;
    }
}
