package cn.pasteme.admin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Lucien, Moyu
 * @version 1.2.0
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
    boolean createRecord(@Param("key") Long key, @Param("date") Date date, @Param("ip") String ip);

    /**
     * 查询指定时间范围的指定访问 record 条数
     * @param key 主键
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return record 数
     */
    @Select({
            "<script>",
                "SELECT COUNT(*) FROM `pasteme_admin_access_count`",
                "WHERE",
                "<when test = 'start != null'>",
                    "`date` BETWEEN #{start} AND #{end}",
                "</when>",
                "<when test = 'key != 0 &amp; start != null'>",
                    "AND",
                "</when>",
                "<when test = 'key != 0'>",
                    "`key` = #{key}",
                "</when>",
            "</script>"
    })
    int countRecord(@Param("key") Long key, @Param("start") Date startDate, @Param("end") Date endDate);

//    @Select({
//
//    })

}
