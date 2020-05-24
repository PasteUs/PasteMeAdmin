package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    private JSONObject result;

    public Integer getIntegerResult() {
        if (null != this.result) {
            return this.result.getInteger("integer");
        }
        return null;
    }

    public void setIntegerResult(Integer value) {
        this.result = new JSONObject();
        this.result.put("integer", value);
    }

    public List<Pair<String, Long>> getResult() {
        if (null != result) {
            JSONArray jsonArray = result.getJSONArray("pairList");
            return jsonArray.toJavaObject(new TypeReference<List<Pair<String, Long>>>(){});
        }
        return null;
    }

    public void setResult(List<Pair<String, Long>> result) {
        this.result = new JSONObject();
        this.result.put("pairList", result);
    }
}
