alter table note_info_delete add column `is_leaf` int after parent_id;


CREATE TABLE `es_history` (
       `id` bigint NOT NULL AUTO_INCREMENT,
       `user_name` bigint DEFAULT NULL COMMENT '用户名',
       `comment` varchar(1000) DEFAULT NULL COMMENT '手工注释',
       `update_time` datetime DEFAULT NULL,
       `update_by` varchar(32) DEFAULT NULL,
       PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='ES历史表';