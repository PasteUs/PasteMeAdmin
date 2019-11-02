package cn.pasteme.admin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Repository
public interface AccessCountMapper {

    /**
     * 新增一条 record，count 为 0
     *
     * @param key 主键
     * @return boolean
     */
    @Insert("INSERT INTO `pasteme_admin_access_count` (`key`, `count`) VALUES (#{key}, 0)")
    boolean createRecord(Long key);

    /**
     * 让 count 字段自增
     *
     * @param key 主键
     * @return boolean
     */
    @Update("UPDATE `pasteme_admin_access_count` SET `count` = `count` + 1 WHERE `key` = #{key}")
    boolean increaseCountByKey(Long key);

    /**
     * 返回访问计数，代表这个 Paste 被访问的次数
     *
     * @param key 主键
     * @return 访问计数
     */
    @Select("SELECT `count` FROM `pasteme_admin_access_count` WHERE `key` = #{key}")
    long getAccessCountByKey(Long key);
}
