package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;
import lombok.Data;

import java.util.List;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Data
public class RiskCheckResultDO {

    private Long key;

    private RiskCheckResultType type;

    private List<Pair<String, Long>> result;
}
