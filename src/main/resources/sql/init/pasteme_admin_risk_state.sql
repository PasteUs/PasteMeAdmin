CREATE TABLE IF NOT EXISTS `pasteme_admin_risk_state`
(
    `id`    BIGINT UNSIGNED NOT NULL COMMENT '主键' PRIMARY KEY AUTO_INCREMENT,
    `key`   BIGINT UNSIGNED NOT NULL COMMENT 'Paste 主键',
    `type`  INT             NOT NULL COMMENT '类型',
    `state` INT             NOT NULL COMMENT '状态',
    KEY `idx_key` (`key`),
    UNIQUE (`key`)
) COLLATE = utf8mb4_general_ci
  ENGINE = Innodb
  DEFAULT CHARSET = utf8mb4;