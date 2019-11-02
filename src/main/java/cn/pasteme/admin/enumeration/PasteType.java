package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * RiskStateDO.state 值域
 * 同时也是 label
 *
 * @author Lucien
 * @version 1.1.0
 */
public enum PasteType implements ValueEnum {

    // 普通
    NORMAL(0),

    // 非法
    PORN(1);

    @Getter
    private int value;

    PasteType(int value) {
        this.value = value;
    }
}
