package cn.pasteme.admin.dto;

import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PairListRiskCheckResultDTO extends AbstractRiskCheckResultDTO {

    private List<Pair<String, Long>> result;

    public PairListRiskCheckResultDTO(Long key, RiskCheckResultType type, List<Pair<String, Long>> result) {
        super(key, type);
        this.setResult(result);
    }

    public PairListRiskCheckResultDTO(RiskCheckResultDO riskCheckResultDO) {
        super(riskCheckResultDO);
        this.result = JSON.parseArray(
                riskCheckResultDO.getResult()
        ).toJavaObject(new TypeReference<List<Pair<String, Long>>>() {});
    }

    @Override
    public RiskCheckResultDO toDO() {
        String jsonString = JSON.toJSONString(result);
        return this.toDO(jsonString);
    }
}
