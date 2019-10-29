package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.PasteAdminDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Repository
public interface PasteAdminMapper {

    /**
     * 创建表
     */
    @Update("CREATE TABLE IF NOT EXISTS `permanents` (" +
            "`key` BIGINT UNSIGNED PRIMARY KEY," +
            "`count` BIGINT UNSIGNED DEFAULT 0," +
            "`type` INT NOT NULL," +
            "`state` INT NOT NULL)")
    void createTable();

    /**
     * 创建记录
     *
     * @param key 主键
     * @param count Paste 的访问计数
     * @param type Paste 的分类
     * @param state Paste 的状态，0 未覆盖、1 已检验、2 已忽略、3 需要人工复核、4 无需复核
     * @return boolean
     */
    @Insert("INSERT INTO `permanents` (`key`, `count`, `type`, `state`)" +
            "VALUES (#{key}, #{count}, #{type}, #{state})")
    boolean set(Long key, Long count, Integer type, Integer state);

    /**
     * 通过主键获取记录
     *
     * @param key 主键
     * @return DO
     */
    @Select("SELECT `type`, `count`, `state`, `type` FROM `permanents` WHERE `key` = #{key}")
    PasteAdminDO get(Long key);
}
