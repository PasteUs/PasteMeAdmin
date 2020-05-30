package cn.pasteme.admin.entity;

import cn.pasteme.admin.dto.AbstractRiskCheckResultDTO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 存储 NLP 结果
 *
 * @author Lucien
 * @version 1.1.0
 */
@Data
@Slf4j
public class RiskCheckResultDO {

    /**
     * 主键
     */
    private Long key;

    /**
     * 风险检查的类型
     */
    private RiskCheckResultType type;

    /**
     * JSON result
     */
    private String result;
}
