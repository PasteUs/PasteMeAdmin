package cn.pasteme.admin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Lucien
 * @version 1.1.0
 */
@Repository
public interface AccessCountMapper {

    /**
     * 新增一条 record
     *
     * @param key 主键
     * @param date 日期
     * @param ip IP
     * @return boolean
     */
    @Insert({"INSERT INTO `pasteme_admin_access_count`",
            "(`key`, `date`, `ip`)",
            "VALUES",
            "(#{key}, #{date}, #{ip})"})
    boolean createRecord(Long key, Date date, String ip);
}
