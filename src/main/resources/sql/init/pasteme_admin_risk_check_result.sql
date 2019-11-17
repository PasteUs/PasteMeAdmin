CREATE TABLE IF NOT EXISTS `pasteme_admin_risk_check_result`
(
    `id`     BIGINT UNSIGNED NOT NULL COMMENT '主键' PRIMARY KEY AUTO_INCREMENT,
    `key`    BIGINT UNSIGNED NOT NULL COMMENT 'Paste 主键',
    `type`   INT             NOT NULL COMMENT '风控检测类型',
    `result` JSON            NOT NULL COMMENT '检测结果',
    KEY `idx_key` (`key`),
    KEY `idx_key_type` (`key`, `type`)
)
    COMMENT '风控检测结果'
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;