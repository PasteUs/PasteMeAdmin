CREATE TABLE IF NOT EXISTS `pasteme_admin_access_count`
(
    `id`   BIGINT UNSIGNED NOT NULL COMMENT '主键' PRIMARY KEY AUTO_INCREMENT,
    `key`  BIGINT UNSIGNED NOT NULL COMMENT 'Paste 主键，等于 0 的话说明是访问主页',
    `date` TIMESTAMP       NOT NULL COMMENT '访问时间',
    `ip`   VARCHAR(64)     NOT NULL COMMENT 'IP 地址',
    KEY `idx_date` (`date`),
    KEY `idx_key` (`key`),
    KEY `idx_key_date` (`key`, `date`)
)
    COMMENT = '访问统计'
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;