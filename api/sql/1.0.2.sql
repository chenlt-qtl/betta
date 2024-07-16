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

-- eng_user_score definition用户成绩表
drop table `eng_user_score`;
CREATE TABLE `eng_user_score` (
      `id` bigint NOT NULL AUTO_INCREMENT,
      `user` varchar(32) DEFAULT NULL,
      `word_name` varchar(200) DEFAULT NULL COMMENT '单词内容',
      `familiarity` int DEFAULT '3' COMMENT '熟悉度越高越熟悉，0为最低',
      `status` varchar(1) DEFAULT NULL,
      `create_time` datetime DEFAULT NULL,
      `create_by` varchar(32) DEFAULT NULL,
      `update_time` datetime DEFAULT NULL,
      `update_by` varchar(32) DEFAULT NULL,
      PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='用户成绩表';

alter table eng_article_word_rel add column word_name varchar(200) after word_id;
update eng_article_word_rel,eng_word set eng_article_word_rel.word_name = eng_word.word_name where eng_word.id = eng_article_word_rel.word_id;
delete from eng_article_word_rel eawr where word_name is null;
alter table eng_article_word_rel drop column word_id;
