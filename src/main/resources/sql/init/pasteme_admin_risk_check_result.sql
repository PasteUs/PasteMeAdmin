CREATE TABLE IF NOT EXISTS `pasteme_admin_risk_check_result`
(
    `id`     BIGINT UNSIGNED NOT NULL COMMENT '主键' PRIMARY KEY AUTO_INCREMENT,
    `key`    BIGINT UNSIGNED NOT NULL COMMENT 'Paste 主键',
    `type`   INT             NOT NULL COMMENT '风控检测类型',
    `result` TEXT            NOT NULL COMMENT '检测结果，为了兼容 MySQL 5.5 版本使用 TEXT 替代 JSON',
    KEY `idx_key` (`key`),
    KEY `idx_key_type` (`key`, `type`)
)
    COMMENT '风控检测结果'
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;