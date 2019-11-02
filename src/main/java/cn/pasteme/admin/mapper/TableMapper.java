package cn.pasteme.admin.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 将建表语句都写在这里
 *
 * @author Lucien
 * @version 1.0.0
 */
@Repository
public interface TableMapper {

    /**
     * 创建表
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_risk_state` (",
            "`key` BIGINT UNSIGNED NOT NULL PRIMARY KEY,",
            "`type` INT NOT NULL,",
            "`state` INT NOT NULL",
            ")"})
    void createPasteMeAdminRiskState();

    /**
     * 创建表
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_access_count` (",
            "`key`  BIGINT UNSIGNED NOT NULL PRIMARY KEY,",
            "`count` BIGINT UNSIGNED",
            ")"})
    void createPasteMeAdminAccessCount();

    /**
     * 创建表
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_dictionary` (",
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,",
            "`dictionary` JSON NOT NULL",
            ")"})
    void createPasteMeAdminDictionary();
}
