package cn.pasteme.admin.dto;

import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import lombok.Data;

/**
 * @author Lucien
 * @version 2.1.0
 */
@Data
public abstract class AbstractRiskCheckResultDTO {

    /**
     * 主键
     */
    private Long key;

    /**
     * 风险检查的类型
     */
    private RiskCheckResultType type;

    /**
     * DTO 转换为 RiskCheckResultDO
     * @return RiskCheckResultDO
     */
    public abstract RiskCheckResultDO toDO();

    /**
     * 构造函数
     * @param key 主键
     * @param type 类型
     */
    protected AbstractRiskCheckResultDTO(Long key, RiskCheckResultType type) {
        this.setKey(key);
        this.setType(type);
    }

    /**
     * 从 DO 拷贝 key 和 type 字段
     * @param riskCheckResultDO DO
     */
    protected AbstractRiskCheckResultDTO(RiskCheckResultDO riskCheckResultDO) {
        this.setKey(riskCheckResultDO.getKey());
        this.setType(riskCheckResultDO.getType());
    }

    /**
     * 返回一个 result 为 入参 result，key 和 type 与当前 DTO 一致的 DO
     * @param result Result String
     * @return RiskCheckResultDO
     */
    protected RiskCheckResultDO toDO(String result) {
        RiskCheckResultDO riskCheckResultDO = new RiskCheckResultDO();
        riskCheckResultDO.setKey(this.getKey());
        riskCheckResultDO.setType(this.getType());
        riskCheckResultDO.setResult(result);
        return riskCheckResultDO;
    }
}
