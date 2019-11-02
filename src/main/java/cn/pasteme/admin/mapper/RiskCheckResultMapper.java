package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Repository
public interface RiskCheckResultMapper {

    /**
     * 创建 DO
     *
     * @param riskCheckResultDO DO
     * @return boolean
     */
    @Insert({"INSERT INTO `pasteme_admin_risk_check_result` (",
            "`key`, `type`, `result`",
            ") VALUES (",
            "#{key},",
            "#{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler},",
            "#{result, typeHandler=cn.pasteme.common.mapper.handler.JsonTypeHandler})"
    })
    boolean createDO(RiskCheckResultDO riskCheckResultDO);

    /**
     * 更细 DO
     *
     * @param key 主键
     * @param type 类型
     * @param result result
     * @return boolean
     */
    @Update({"UPDATE `pasteme_admin_risk_check_result` SET",
            "`result` = #{result, typeHandler=cn.pasteme.common.mapper.handler.JsonTypeHandler}",
            "WHERE `key` = #{key}",
            "AND `type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}"})
    boolean updateResult(Long key, RiskCheckResultType type, List<Pair<String, Long>> result);

    /**
     * 查询 DO
     *
     * @param key 主键
     * @param type 类型
     * @return DO
     */
    @Select({"SELECT `result` FROM `pasteme_admin_risk_check_result`",
            "WHERE `key` = #{key}",
            "AND `type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}"
    })
    @Results(id = "RiskCheckResultDO", value = {
            @Result(column = "key", property = "key"),
            @Result(column = "type", property = "type", javaType = RiskCheckResultType.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class),
            @Result(column = "result", property = "result", javaType = List.class, typeHandler = cn.pasteme.common.mapper.handler.JsonTypeHandler.class)
    })
    RiskCheckResultDO getResultByKeyAndType(Long key, RiskCheckResultType type);
}
