package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucien
 * @version 1.2.3
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
     * 更新 DO
     *
     * @param riskCheckResultDO DO
     * @return boolean
     */
    @Update({"UPDATE `pasteme_admin_risk_check_result` SET",
            "`result` = #{result, typeHandler=cn.pasteme.common.mapper.handler.JsonTypeHandler}",
            "WHERE `key` = #{key}",
            "AND `type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}"})
    boolean updateResult(RiskCheckResultDO riskCheckResultDO);

    /**
     * 查询 DO
     *
     * @param key 主键
     * @param type 类型
     * @return DO
     */
    @Select({"SELECT * FROM `pasteme_admin_risk_check_result`",
            "WHERE `key` = #{key}",
            "AND `type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}"
    })
    @Results(id = "RiskCheckResultDO", value = {
            @Result(column = "key", property = "key"),
            @Result(column = "type", property = "type", javaType = RiskCheckResultType.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class),
            @Result(column = "result", property = "result", javaType = JSON.class, typeHandler = cn.pasteme.common.mapper.handler.JsonTypeHandler.class)
    })
    RiskCheckResultDO getResultByKeyAndType(Long key, RiskCheckResultType type);

    /**
     * 分页查询某类结果集
     *
     * @param type 类型
     * @param limit 一页的大小
     * @param offset 偏移
     * @return DO List
     */
    @Select({"SELECT * FROM `pasteme_admin_risk_check_result`",
            "WHERE `type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}",
            "LIMIT #{limit}",
            "OFFSET #{offset}",
            "ORDER BY `key` DESC"
    })
    @ResultMap(value = "RiskCheckResultDO")
    List<RiskCheckResultDO> getResultsByType(RiskCheckResultType type, Long limit, Long offset);

    /**
     * 获取某类结果的数量
     *
     * @param type 类型
     * @return 数量
     */
    @Select({"SELECT COUNT(1) FROM `pasteme_admin_risk_check_result`",
            "WHERE `type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}"
    })
    Long getCountByType(RiskCheckResultType type);
}
