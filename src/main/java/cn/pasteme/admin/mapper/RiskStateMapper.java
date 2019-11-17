package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.RiskCheckDO;
import cn.pasteme.admin.enumeration.RiskStateDoState;
import cn.pasteme.admin.enumeration.RiskStateDoType;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Lucien
 * @version 1.2.3
 */
@Repository
public interface RiskStateMapper {

    /**
     * 将 DO 插入数据库
     *
     * @param riskCheckDO DO
     * @return boolean
     */
    @Insert({"INSERT INTO `pasteme_admin_risk_state` (`key`, `type`, `state`)",
            "VALUES (#{key},",
            "#{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}, ",
            "#{state, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler})"})
    boolean insertDO(RiskCheckDO riskCheckDO);

    /**
     * 更新 Record
     *
     * @param riskCheckDO DO
     * @return boolean
     */
    @Update({"<script>",
            "UPDATE `pasteme_admin_risk_state`",
            "<set>",
                "<if test='type != null'>",
                    "`type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}",
                "</if>",
                "<if test='state != null'>",
                    ", `state` = #{state, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}",
                "</if>",
            "</set>",
            "WHERE `key` = #{key}",
    "</script>"})
    boolean updateDO(RiskCheckDO riskCheckDO);

    /**
     * 通过主键获取记录
     *
     * @param key 主键
     * @return DO
     */
    @Select("SELECT `key`, `state`, `type` FROM `pasteme_admin_risk_state` WHERE `key` = #{key}")
    @Results(id = "RiskCheckDO", value = {
            @Result(column = "key", property = "key"),
            @Result(column = "type", property = "type", javaType = RiskStateDoType.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class),
            @Result(column = "state", property = "state", javaType = RiskStateDoState.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class)
    })
    RiskCheckDO getDoByKey(Long key);

    /**
     * 统计主键计数
     *
     * @param key 主键
     * @return 主键的数量，一般来说只有 0/1 两种取值
     */
    @Select("SELECT COUNT(1) FROM `pasteme_admin_risk_state` WHERE `key` = #{key}")
    int countByKey(Long key);
}
