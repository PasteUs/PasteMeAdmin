package cn.pasteme.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Repository
public interface PasteAdminTestMapper {

    @Delete("DELETE FROM `pasteme_permanent` WHERE `key` = #{key}")
    boolean delete(@Param("key") Long key);
}
