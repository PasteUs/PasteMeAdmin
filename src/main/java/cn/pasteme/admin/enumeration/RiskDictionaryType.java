package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * @author Lucien
 * @version 1.0.0
 */
public enum RiskDictionaryType implements ValueEnum {

    // 风险词
    RISK_WORD(0),

    // 停用词
    STOP_WORD(1);

    @Getter
    private int value;

    RiskDictionaryType(int value) {
        this.value = value;
    }
}
