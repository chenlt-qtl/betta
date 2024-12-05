CREATE TABLE `video_info`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `pid`         BIGINT DEFAULT 0,
    `title`       VARCHAR(200) NULL DEFAULT NULL COMMENT '名称',
    `url`         VARCHAR(300) NULL DEFAULT NULL COMMENT '网址',
    `img`         VARCHAR(100) NULL DEFAULT NULL COMMENT '图片',
    `is_leaf`     VARCHAR(1) NULL DEFAULT 0,
    `status`      VARCHAR(1) NULL DEFAULT NULL,
    `create_time` DATETIME NULL DEFAULT NULL,
    `create_by`   VARCHAR(32) NULL DEFAULT NULL,
    `update_time` DATETIME NULL DEFAULT NULL,
    `update_by`   VARCHAR(32) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) COMMENT='视频信息表' ENGINE=InnoDB;

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频信息', '0', '1', 'video', 'video/video/index', 1, 0, 'C', '0', '0', 'video:video:list', '#', 'admin', sysdate(), '', null, '视频信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'video:video:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'video:video:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'video:video:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'video:video:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'video:video:export',       '#', 'admin', sysdate(), '', null, '');


alter table video_info add column duration varchar(20)  NULL COMMENT '时长' after is_leaf;