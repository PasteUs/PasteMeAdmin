package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * RiskStateDO.type 值域
 * 可用于 supervise
 *
 * @author Lucien
 * @version 1.1.0
 */
public enum PasteState implements ValueEnum {

    // 已检查
    CHECKED(0),

    // 未检查
    UNCHECKED(1),

    // 已核查
    REVIEWED(2),

    // 需要核查
    REQUEST_REVIEW(3);

    @Getter
    private int value;

    PasteState(int value) {
        this.value = value;
    }
}
