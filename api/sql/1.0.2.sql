-- 历史表

CREATE TABLE `sys_history` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `type` int DEFAULT NULL COMMENT '类型',
      `value` varchar(4000) DEFAULT NULL COMMENT '内容',
      `create_time` datetime DEFAULT NULL,
      `create_by` varchar(32) DEFAULT NULL,
      `update_time` datetime DEFAULT NULL,
      `update_by` varchar(32) DEFAULT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='系统历史表';