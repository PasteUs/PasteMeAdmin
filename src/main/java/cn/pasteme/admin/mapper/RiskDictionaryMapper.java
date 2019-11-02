package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.RiskDictionaryDO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucien
 * @version 1.0.1
 */
@Repository
public interface RiskDictionaryMapper {

    /**
     * 更新词典
     *
     * @param dictionary 词典
     * @return boolean
     */
    @Insert("INSERT INTO `pasteme_admin_dictionary` (`dictionary`) VALUES (#{dictionary, typeHandler=cn.pasteme.common.mapper.handler.JsonTypeHandler})")
    boolean updateDictionary(@Param("dictionary") List<String> dictionary);

    /**
     * 获取最新的字典
     *
     * @return RiskDictionaryDO
     */
    @Select("SELECT * FROM `pasteme_admin_dictionary` ORDER BY `id` DESC LIMIT 0, 1")
    @Results(id = "RiskDictionaryDO", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "dictionary", property = "dictionary", javaType = List.class, typeHandler = cn.pasteme.common.mapper.handler.JsonTypeHandler.class)
    })
    RiskDictionaryDO getLatestDictionary();
}
