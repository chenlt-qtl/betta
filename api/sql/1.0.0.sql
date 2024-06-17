alter table note_info_delete add column `is_leaf` int after parent_id;


CREATE TABLE `es_history` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
      `comment` varchar(32) DEFAULT NULL COMMENT '手工注释',
      `update_doc_time` datetime DEFAULT NULL COMMENT 'doc更新日期',
      `create_time` datetime DEFAULT NULL,
      `create_by` varchar(32) DEFAULT NULL,
      `update_time` datetime DEFAULT NULL,
      `update_by` varchar(32) DEFAULT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='ES历史表';