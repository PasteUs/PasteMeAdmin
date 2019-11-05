package cn.pasteme.admin.dto;

import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.algorithm.pair.Pair;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RiskCheckResultDTO extends RiskCheckResultDO {

    List<Pair<String, Long>> result;
}
