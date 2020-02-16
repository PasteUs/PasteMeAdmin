CREATE TABLE IF NOT EXISTS `pasteme_admin_announce`
(
    `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `content` TEXT,
    `link` VARCHAR(255),
    `type` INT NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '最后修改时间',
    `is_deleted` BIT,

    KEY `idx_date` (`update_time`),
    KEY `idx_delete` (`is_deleted`)
)
    COMMENT '存储通知'
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;
