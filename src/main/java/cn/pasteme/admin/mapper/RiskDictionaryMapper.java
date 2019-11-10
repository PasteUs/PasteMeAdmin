package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.RiskDictionaryType;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * @author Lucien
 * @version 1.1.0
 */
@Repository
public interface RiskDictionaryMapper {

    /**
     * 更新词典
     *
     * @param dictionary 词典
     * @return boolean
     */
    @Insert({"INSERT INTO `pasteme_admin_dictionary` (`type`, `dictionary`)",
            "VALUES (",
            "#{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler},",
            "#{dictionary, typeHandler=cn.pasteme.common.mapper.handler.JsonTypeHandler})"})
    boolean updateDictionary(@NotNull RiskDictionaryType type,
                             @NotNull List<String> dictionary);

    /**
     * 获取最新的字典
     *
     * @return RiskDictionaryDO
     */
    @Select({"SELECT * FROM `pasteme_admin_dictionary`",
            "WHERE `type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}",
            "ORDER BY `id` DESC",
            "LIMIT 0, 1"})
    @Results(id = "RiskDictionaryDO", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "type", property = "type", javaType = RiskDictionaryType.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class),
            @Result(column = "dictionary", property = "dictionary", javaType = List.class, typeHandler = cn.pasteme.common.mapper.handler.JsonTypeHandler.class)
    })
    RiskDictionaryDO getLatestDictionary(@NotNull RiskDictionaryType type);
}
