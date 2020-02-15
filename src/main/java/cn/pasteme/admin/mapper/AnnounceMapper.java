package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.enumeration.AnnounceType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.1
 */
@Repository
public interface AnnounceMapper {

    /**
     * 新增一条公告
     *
     * @param announceDO 公告实体信息
     * @return 是否插入成功
     */
    @Insert({"INSERT INTO `pasteme_admin_announce`",
            "(`title`, `content`, `link`, `type`, `date`)",
            "VALUES",
            "(#{title}, #{content}, #{link},",
            "#{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler},",
            "#{time})", })
    boolean createAnnouncement(AnnounceDO announceDO);

    /**
     * 查询公告总数量
     *
     * @return 公告数量
     */
    @Select("SELECT COUNT(*) FROM `pasteme_admin_announce`")
    int countAnnouncement();

    /**
     * 查询第 page 页的内容
     *
     * @param begin 开始索引
     * @param pageSize 页大小
     * @return 公告信息列表
     */
    @Select({"SELECT *",
            "FROM `pasteme_admin_announce`",
            "ORDER BY `date` DESC",
            "LIMIT #{begin}, #{pageSize}",
    })
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "link", column = "link"),
            @Result(property = "type", column = "type", javaType = AnnounceType.class, typeHandler = cn.pasteme.common.mapper.handler.ValueEnumTypeHandler.class ),
            @Result(property = "time", column = "date" )
    })
    List<AnnounceDO> getAnnouncementByPage(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 删除指定通知
     *
     * @param id 删除公告的主键值
     * @return 是否删除成功
     */
    @Update({"DELETE FROM `pasteme_admin_announce` WHERE `id`=#{id}"})
    boolean deleteAnnouncement(@Param("id") Long id);

    /**
     * 更新公告信息
     *
     * @param announceDO 公告信息
     * @return 是否更新成功
     */
    @Update({"UPDATE `pasteme_admin_announce` set",
            "`title`=#{title}, `content`=#{content}, `link`=#{link}, `date`=#{time},",
            "`type`=#{type, typeHandler=cn.pasteme.common.mapper.handler.ValueEnumTypeHandler}",
            "WHERE `id`=#{id}"})
    boolean updateAnnouncement(AnnounceDO announceDO);
}
