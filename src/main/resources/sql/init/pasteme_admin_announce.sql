CREATE TABLE IF NOT EXISTS `pasteme_admin_announce`
(
    `id` BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `title`  varchar(255) NOT NULL,
    `content`  varchar(255) NOT NULL,
    `link`  varchar(255) NOT NULL,
    `type` int not null,
    `date` TIMESTAMP       NOT NULL COMMENT '创建时间',

    KEY `idx_date` (`date`)
)
    COMMENT '存储通知'
    COLLATE = utf8mb4_general_ci
    ENGINE = Innodb
    DEFAULT CHARSET = utf8mb4;
