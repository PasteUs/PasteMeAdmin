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
     * 创建 pasteme_admin_risk_check_result
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_risk_check_result` (",
            "`key` BIGINT UNSIGNED NOT NULL PRIMARY KEY,",
            "`type` INT NOT NULL,",
            "`result` JSON NOT NULL",
            ")"})
    void createPasteMeAdminRiskCheckResult();

    /**
     * 创建 pasteme_admin_risk_state
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_risk_state` (",
            "`key` BIGINT UNSIGNED NOT NULL PRIMARY KEY,",
            "`type` INT NOT NULL,",
            "`state` INT NOT NULL",
            ")"})
    void createPasteMeAdminRiskState();

    /**
     * 创建 pasteme_admin_access_count
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_access_count` (",
            "`key`  BIGINT UNSIGNED NOT NULL PRIMARY KEY,",
            "`count` BIGINT UNSIGNED",
            ")"})
    void createPasteMeAdminAccessCount();

    /**
     * 创建 pasteme_admin_dictionary
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_dictionary` (",
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,",
            "`type` INT NOT NULL,",
            "`dictionary` JSON NOT NULL",
            ")"})
    void createPasteMeAdminDictionary();

    /**
     * 创建 pasteme_admin_announcement
     */
    @Update({"CREATE TABLE IF NOT EXISTS `pasteme_admin_announce`",
            "(",
            "    `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,",
            "    `title` VARCHAR(255) NOT NULL,",
            "    `content` TEXT,",
            "    `link` VARCHAR(255),",
            "    `type` INT NOT NULL,",
            "    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',",
            "    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',",
            "    `is_deleted` BIT,",
            "    KEY `idx_date` (`date`)",
            "    KEY `idx_delete` (`is_deleted`)",
            ")" })
    void createPasteMeAdminAnnounce();
}

