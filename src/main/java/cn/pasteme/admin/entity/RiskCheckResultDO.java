package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 存储 NLP 结果
 *
 * @author Lucien
 * @version 1.1.0
 */
@Data
@Slf4j
public class RiskCheckResultDO {

    private Long key;

    private RiskCheckResultType type;

    /**
     * 实际上是 List<Pair<String, Long>> 格式
     * 向 Java 编译器妥协，使用 JSON 格式
     */
    private JSON result;

    public List<Pair<String, Long>> getResult() {
        try {
            if (null != result) {
                return result.toJavaObject(new TypeReference<List<Pair<String, Long>>>(){});
            }
        } catch (Exception e) {
            log.error("error = ", e);
        }
        return null;
    }

    public void setResult(List<Pair<String, Long>> result) {
        try {
            if (null != result) {
                this.result = (JSON) JSON.toJSON(result);
                return ;
            }
        } catch (Exception e) {
            log.error("error = ", e);
        }
        this.result = null;
    }
}
