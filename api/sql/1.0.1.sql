CREATE TABLE `clock_in_data`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `time`       date  DEFAULT  NULL COMMENT '打卡日期',
    `value` varchar(200)    DEFAULT NULL,
    `create_time` datetime    DEFAULT NULL,
    `create_by`   varchar(32) DEFAULT NULL,
    `update_time` datetime    DEFAULT NULL,
    `update_by`   varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='打卡数据表';