alter table note_info_delete
    add column `is_leaf` int after parent_id;


CREATE TABLE `es_history`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `user_name`       varchar(32) DEFAULT NULL COMMENT '用户名',
    `comment`         varchar(32) DEFAULT NULL COMMENT '手工注释',
    `update_doc_time` datetime    DEFAULT NULL COMMENT 'doc更新日期',
    `create_time`     datetime    DEFAULT NULL,
    `create_by`       varchar(32) DEFAULT NULL,
    `update_time`     datetime    DEFAULT NULL,
    `update_by`       varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='ES历史表';

CREATE TABLE `card_item`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `type`        int         DEFAULT NULL COMMENT '类型',
    `value`       int         DEFAULT NULL COMMENT '值',
    `name`        varchar(32) DEFAULT NULL COMMENT '名称',
    `create_time` datetime    DEFAULT NULL,
    `create_by`   varchar(32) DEFAULT NULL,
    `update_time` datetime    DEFAULT NULL,
    `update_by`   varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='卡片项明细表';

CREATE TABLE `eng_play_list`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `user_name`    varchar(32) DEFAULT NULL COMMENT '用户名',
    `sentence_ids` text        DEFAULT NULL COMMENT '句子ID',
    `create_time`  datetime    DEFAULT NULL,
    `create_by`    varchar(32) DEFAULT NULL,
    `update_time`  datetime    DEFAULT NULL,
    `update_by`    varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='播放列表';