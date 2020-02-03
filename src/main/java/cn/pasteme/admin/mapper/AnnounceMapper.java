package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.AnnounceDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.0
 */

@Repository
public interface AnnounceMapper {

    /**
     * 新增一条 Announcement
     *
     * @param title
     * @param content
     * @param link
     * @param type
     * @param date
     */

    @Insert({"INSERT INTO `pasteme_admin_announce`",
            "(`title`, `content`, `link`, `type`, `date`)",
            "values",
            "(#{title}, #{content}, #{link}, #{type}, #{date})", })
    boolean postAnnouncement(@Param("title") String title, @Param("content") String content,
                          @Param("link") String link, @Param("type") int type, @Param("date") Date date);

    @Select("SELECT * From `pasteme_admin_announce`")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "link", column = "link"),
            @Result(property = "type", column = "type"),
            @Result(property = "time", column = "date")
    })
    List<AnnounceDO> getAll();

    /**
     *
     * @param left
     * @param right
     * @return
     */
    @Select({"select * from `pasteme_admin_announce`",
            "where `id` between #{left} and #{right}" })
    List<AnnounceDO> getAnnouncementByLR(@Param("left") int left, @Param("right") int right);

    /**
     *
     * @param id
     * @return
     */
    @Update({"delete from `pasteme_admin_announce` where `id`=#{id}"})
    boolean deleteAnnouncement(@Param("id") int id);

    /**
     *
     * @param id
     * @param title
     * @param content
     * @param link
     * @param type
     * @param date
     * @return
     */
    @Update({"update `pasteme_admin_announce` set",
            "`title`=#{title}, `content`=#{content}, `link`=#{link}, `type`=#{type}, `date`=#{date}",
            "where `id`=#{id}"})
    boolean putAnnouncement(@Param("id") Long id, @Param("title") String title, @Param("content") String content,
                            @Param("link") String link, @Param("type") int type, @Param("date") Date date);

}
