package cn.pasteme.admin.mapper;

import cn.pasteme.admin.bo.PasteAccessCountBO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Lucien, Moyu
 * @version 1.3.0
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
     *
     * @param key 主键
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return record 数
     */
    @Select({"<script>",
                "SELECT COUNT(*) FROM `pasteme_admin_access_count`",
                "WHERE 1 = 1",
                "<when test = 'start != null'>",
                    "AND `date` BETWEEN #{start} AND #{end}",
                "</when>",
                "<when test = 'key != 0'>",
                    "AND `key` = #{key}",
                "</when>",
            "</script>"})
    int countRecord(@Param("key") Long key, @Param("start") Date startDate, @Param("end") Date endDate);

    /**
     *  Paste 访问次数排序
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return Paste 访问次数排序
     */
    @Select({"<script>",
                "SELECT `key`, COUNT(*) AS `count` FROM `pasteme_admin_access_count`",
                "WHERE 1 = 1",
                "AND `key` != 0",
                "<when test = 'start != null'>",
                    "AND `date` BETWEEN #{start} AND #{end}",
                "</when>",
                "GROUP BY `key`",
                "ORDER BY `count` DESC",
            "</script>"})
    @Results(id = "PasteAccessCountBO", value = {
            @Result(column = "key", property = "key"),
            @Result(column = "count", property = "count", javaType = Integer.class)
    })
    List<PasteAccessCountBO> rankPasteQuantity(@Param("start") Date startDate, @Param("end") Date endDate);

}
