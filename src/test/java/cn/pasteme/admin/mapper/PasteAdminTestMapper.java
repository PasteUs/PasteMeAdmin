package cn.pasteme.admin.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Lucien
 * @version 1.1.0
 */
@Repository
public interface PasteAdminTestMapper {

    @Delete("DELETE FROM `${tableName}` WHERE `key` = #{key}")
    void delete(@Param("tableName") String tableName, @Param("key") Long key);
}
