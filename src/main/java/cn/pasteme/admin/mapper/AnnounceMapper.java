package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.AnnounceDO;
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
     * 新增一条 Announcement
     *
     */

    @Insert({"INSERT INTO `pasteme_admin_announce`",
            "(`title`, `content`, `link`, `type`, `date`)",
            "VALUES",
            "(#{announceDO.title}, #{announceDO.content}, #{announceDO.link}, #{type}, #{announceDO.time})", })
    boolean createAnnouncement(AnnounceDO announceDO, @Param("type") int type);

    @Select("SELECT COUNT(*) FROM `pasteme_admin_announce`")
    int countAnnouncement();

    /**
     * 查询第 page 页的内容
     *
     * @param begin
     * @param pageSize
     * @return
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
            @Result(property = "time", column = "date"),
            @Result(property = "type", column = "type")
    })
    List<AnnounceDO> getAnnouncementByPage(@Param("begin") int begin, @Param("pageSize") int pageSize);

    /**
     * 删除指定通知
     *
     * @param id
     * @return
     */
    @Update({"DELETE FROM `pasteme_admin_announce` WHERE `id`=#{id}"})
    boolean deleteAnnouncement(@Param("id") Long id);

    /**
     * 更新信息
     *
     */
    @Update({"UPDATE `pasteme_admin_announce` set",
            "`title`=#{announceDO.title}, `content`=#{announceDO.content}, `link`=#{announceDO.link}, `type`=#{type}, `date`=#{announceDO.time}",
            "WHERE `id`=#{announceDO.id}"})
    boolean updateAnnouncement(AnnounceDO announceDO, @Param("type") int type);

}
