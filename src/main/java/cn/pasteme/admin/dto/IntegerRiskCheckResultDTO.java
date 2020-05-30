package cn.pasteme.admin.dto;

import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IntegerRiskCheckResultDTO extends AbstractRiskCheckResultDTO {

    private Integer result;

    public IntegerRiskCheckResultDTO(Long key, RiskCheckResultType type, Integer result) {
        super(key, type);
        this.setResult(result);
    }

    public IntegerRiskCheckResultDTO(RiskCheckResultDO riskCheckResultDO) {
        super(riskCheckResultDO);
        this.result = Integer.valueOf(riskCheckResultDO.getResult());
    }

    @Override
    public RiskCheckResultDO toDO() {
        return this.toDO(this.result.toString());
    }
}
