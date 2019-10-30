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
 * @version 1.0.0
 */
@Repository
public interface RiskDictionaryMapper {

    @Update("CREATE TABLE IF NOT EXISTS `pasteme_dictionary` (" +
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "`dictionary` JSON NOT NULL" +
            ")")
    void createTable();

    @Insert("INSERT INTO `pasteme_dictionary` (`dictionary`) VALUES (#{dictionary, typeHandler=cn.pasteme.common.mapper.handler.JsonTypeHandler})")
    boolean updateDictionary(@Param("dictionary") List<String> dictionary);

    @Select("SELECT * FROM `pasteme_dictionary` ORDER BY `id` DESC LIMIT 0, 1")
    @Results(id = "riskDictionary", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "dictionary", property = "dictionary", javaType = List.class, typeHandler = cn.pasteme.common.mapper.handler.JsonTypeHandler.class)
    })
    RiskDictionaryDO getLatestDictionary();
}
