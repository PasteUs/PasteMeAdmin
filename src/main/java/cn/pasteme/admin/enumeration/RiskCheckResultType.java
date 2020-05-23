package cn.pasteme.admin.enumeration;

import cn.pasteme.common.enumeration.ValueEnum;

import lombok.Getter;

/**
 * 风控检测结果的类型
 *
 * @author Lucien
 * @version 1.0.1
 */
public enum RiskCheckResultType implements ValueEnum {

    // 关键字匹配
    KEYWORD_MATCHING(0),

    // 关键字计数
    KEYWORD_COUNT(1),

    // 分词计数
    TOKEN_COUNT(2),

    // 文本分类
    TEXT_CLASSIFICATION(3);

    @Getter
    int value;

    RiskCheckResultType(int value) {
        this.value = value;
    }
}
