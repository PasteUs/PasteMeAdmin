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
            "    `title`  varchar(255) NOT NULL,",
            "    `content`  varchar(255),",
            "    `link`  varchar(255),",
            "    `type` int not null,",
            "    `date` TIMESTAMP       NOT NULL COMMENT '创建时间',",
            "    KEY `idx_date` (`date`)",
            ")" })
    void createPasteMeAdminAnnounce();
}

