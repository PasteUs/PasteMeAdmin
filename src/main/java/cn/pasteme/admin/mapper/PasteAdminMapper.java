package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.PasteAdminDO;

import cn.pasteme.admin.enumeration.PasteState;
import cn.pasteme.admin.enumeration.PasteType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Lucien
 * @version 1.2.0
 */
@Repository
public interface PasteAdminMapper {

    /**
     * 创建表
     */
    @Update("CREATE TABLE IF NOT EXISTS `pasteme_permanent` (" +
            "`key` BIGINT UNSIGNED PRIMARY KEY," +
            "`count` BIGINT UNSIGNED DEFAULT 0," +
            "`type` INT NOT NULL," +
            "`state` INT NOT NULL)")
    void createTable();

    /**
     * 将 DO 插入数据库
     *
     * @return boolean
     */
    @Insert("INSERT INTO `pasteme_permanent` (" +
            "`key`, `count`, `type`, `state`) " +
            "VALUES (" +
            "#{key}, " +
            "#{count}, " +
            "#{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}, " +
            "#{state, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler})")
    boolean insertDO(PasteAdminDO pasteAdminDO);

    /**
     * 更新 Record
     *
     * @param pasteAdminDO DO
     * @return boolean
     */
    @Update("UPDATE `pasteme_permanent`" +
            "SET `count` = #{count}, " +
            "`type` = #{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}, " +
            "`state` = #{state, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}" +
            "WHERE `key` = #{key}")
    boolean updateDO(PasteAdminDO pasteAdminDO);

    /**
     * 通过主键获取记录
     *
     * @param key 主键
     * @return DO
     */
    @Select("SELECT `key`, `count`, `state`, `type` FROM `pasteme_permanent` WHERE `key` = #{key}")
    @Results(id = "PasteAdminDO", value = {
            @Result(column = "key", property = "key"),
            @Result(column = "count", property = "count"),
            @Result(column = "type", property = "type", javaType = PasteType.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class),
            @Result(column = "state", property = "state", javaType = PasteState.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class)
    })
    PasteAdminDO getDOByKey(Long key);

    /**
     * 让 count 字段自增
     *
     * @param key 主键
     * @return boolean
     */
    @Update("UPDATE `pasteme_permanent` SET `count` = `count` + 1 WHERE `key` = #{key}")
    boolean increaseCountByKey(Long key);

    /**
     * 统计主键计数
     *
     * @param key 主键
     * @return 主键的数量，一般来说只有 0/1 两种取值
     */
    @Select("SELECT COUNT(1) FROM `pasteme_permanent` WHERE `key` = #{key}")
    int countByKey(Long key);
}
