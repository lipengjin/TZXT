﻿use `tzxt`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
	`password` varchar(255) DEFAULT NULL COMMENT '密码',
	`email` varchar(255) DEFAULT NULL COMMENT 'email',
	`phone` varchar(255) DEFAULT NULL COMMENT '手机',
	`real_name` varchar(255) DEFAULT NULL COMMENT '真实名称',
	`unit_id` bigint(20) NOT NULL COMMENT '单位ID',
	`unit` varchar(255) NOT NULL COMMENT '单位名称',
	`account_type` INT(1) NOT NULL COMMENT '账号类型',
	`role_id` bigint(20) NOT NULL COMMENT '角色ID',
	`role` varchar(255) NOT NULL COMMENT '角色名称',
	`auth` int(1) NOT NULL DEFAULT '1' COMMENT '角色权限 1 - 查询， 2 - 数据录入  默认1',
	`locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '角色是否锁定 默认 false',
	`create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

INSERT INTO user VALUES (1, 'admin', '1ac99a9be6072f8e                ', 'qqq@qq.com', '12345678999', '管理员', 1, 'unit1', 1, 1, '系统管理员', 2, FALSE, now(), now());
INSERT INTO user VALUES (2, 'guest', '1ac99a9be6072f8e                ', 'qqq@qq.com', '12345678999', '普通用户', 1, 'unit1', 2, 2, 'Guest', 2, FALSE, now(), now());

DROP table IF EXISTS `unit`;
CREATE TABLE `unit` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL DEFAULT '' COMMENT '单位名',
	`create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='单位表';

INSERT INTO unit VALUES (1, 'unit1', now(), now());

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名',
	`create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

INSERT INTO role VALUES (1, '系统管理员', now(), now()),
	(2, 'Guest', now(), now());

DROP TABLE IF EXISTS `role_auths`;
CREATE TABLE `role_auths` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`role_id` bigint(20) NOT NULL COMMENT '角色ID',
	`auth_type` int(1) NOT NULL COMMENT '权限类型',
	`auth` varchar(255) NOT NULL COMMENT '权限',
	`ledger_id` bigint(20) DEFAULT NULL COMMENT '台账ID，如果权限类型是数据权限 则不为null',
	`create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

INSERT INTO role_auths VALUES (1, 1, 0, '系统管理', NULL, now(), now()),
	(2, 1, 0, '菜单管理', NULL, now(), now()),
	(3, 1, 0, '角色管理', NULL, now(), now()),
	(4, 1, 0, '用户管理', NULL , now(), now());

DROP TABLE IF EXISTS `ledger_dictionary`;
CREATE TABLE `ledger_dictionary` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`ledger_id` bigint(20) NOT NULL COMMENT '台账表ID',
	`ld_index` int(11) NOT NULL COMMENT '字段序号',
	`field_name` varchar(255) NOT NULL COMMENT '字段名称',
	`field_type` varchar(255) NOT NULL COMMENT '字段类型',
	`ld_comment` varchar(255) NOT NULL DEFAULT '' COMMENT '注释',
	`length` int(11) DEFAULT 0 COMMENT '长度',
	`source_field` VARCHAR(255) NOT NULL COMMENT '原始数据表字段',
	`create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='台账数据字典表';

DROP TABLE if EXISTS `ledger`;
CREATE TABLE `ledger` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL DEFAULT '' COMMENT '台账表名',
	`table_name` VARCHAR(255) not NULL COMMENT '台账在数据库中的表名',
	`source_table` varchar(255) NOT NULL COMMENT '此台账表关联的原始表',
	`comment` varchar(255) NOT NULL DEFAULT '' COMMENT '注释',
	`start_date` VARCHAR(20) NULL DEFAULT '' COMMENT '管理员设置的此台账的拉取时间段开始',
	`end_date` VARCHAR(20) NULL DEFAULT '' COMMENT '管理员设置的此台账的拉取时间段结束',
	`pull_status` INT(1) NOT NULL DEFAULT 0 COMMENT '数据拉取状态 默认为 从未拉取过',
	`create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='台账表';

DROP TABLE IF EXISTS `filed_type`;
CREATE TABLE `filed_type` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`type` varchar(255) NOT NULL COMMENT '字段类型',
	`display` varchar(255) NOT NULL COMMENT '字段类型 含义',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='数据字典 字段类型表';

INSERT INTO filed_type VALUES
	(1, 'INT', '整形数据'),
	(2, 'INT', '整形数据'),
	(3, 'FLOAT', '浮点型数据'),
	(4, 'VARCHAR', '字符型数据'),
	(5, 'DATETIME', '日期类型数据'),
	(6, 'TEXT', '文本类型数据'),
	(7, 'BOOLEAN', 'bool类型数据');

-- 测试 原始表
CREATE TABLE `source1` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`unit` VARCHAR(255) COMMENT '单位名称',
	`mouth` VARCHAR(20) COMMENT '月报时间',
	`field1` VARCHAR(125) COMMENT 'field1Comment',
	`field2` INT(1) COMMENT 'field2Comment',
	`field3` BOOLEAN COMMENT 'field3Comment',
	`field4` TEXT COMMENT 'field4Comment',
	`field5` DATETIME COMMENT 'field5Comment',
	`field6` FLOAT COMMENT 'field6Comment',
	`field7` VARCHAR(125) COMMENT 'field7Comment',
	`field8` VARCHAR(125) COMMENT 'field8Comment',
	`field9` VARCHAR(125) COMMENT 'field9Comment',
	`field10` VARCHAR(125) COMMENT 'field10Comment',
	`field11` INT(1) COMMENT 'field11Comment',
	`field12` BOOLEAN COMMENT 'field12Comment',
	`field13` TEXT COMMENT 'field13Comment',
	`field14` DATETIME COMMENT 'field14Comment',
	`field15` FLOAT COMMENT 'field15Comment',
	`field16` VARCHAR(125) COMMENT 'field16Comment',
	`field17` VARCHAR(125) COMMENT 'field17Comment',
	`field18` VARCHAR(125) COMMENT 'field18Comment',
	`field19` VARCHAR(125) COMMENT 'field19Comment',
	`field20` INT(1) COMMENT 'field20Comment',
	`field21` BOOLEAN COMMENT 'field21Comment',
	`field22` TEXT COMMENT 'field22Comment',
	`field23` DATETIME COMMENT 'field23Comment',
	`field24` FLOAT COMMENT 'field24Comment',
	`field25` VARCHAR(125) COMMENT 'field25Comment',
	`field26` VARCHAR(125) COMMENT 'field26Comment',
	`field27` VARCHAR(125) COMMENT 'field27Comment',
	`field28` VARCHAR(125) COMMENT 'field28Comment',
	`field29` INT(1) COMMENT 'field29Comment',
	`field30` BOOLEAN COMMENT 'field30Comment',
	`field31` TEXT COMMENT 'field31Comment',
	`field32` DATETIME COMMENT 'field32Comment',
	`field33` FLOAT COMMENT 'field33Comment',
	`field34` VARCHAR(125) COMMENT 'field34Comment',
	`field35` VARCHAR(125) COMMENT 'field35Comment',
	`field36` VARCHAR(125) COMMENT 'field36Comment',
	`field37` VARCHAR(125) COMMENT 'field37Comment',
	`field38` INT(1) COMMENT 'field38Comment',
	`field39` BOOLEAN COMMENT 'field39Comment',
	`field40` TEXT COMMENT 'field40Comment',
	`field41` DATETIME COMMENT 'field41Comment',
	`field42` FLOAT COMMENT 'field42Comment',
	`field43` VARCHAR(125) COMMENT 'field43Comment',
	`field44` VARCHAR(125) COMMENT 'field44Comment',
	`field45` VARCHAR(125) COMMENT 'field45Comment',
	`field46` VARCHAR(125) COMMENT 'field46Comment',
	`field47` INT(1) COMMENT 'field47Comment',
	`field48` BOOLEAN COMMENT 'field48Comment',
	`field49` TEXT COMMENT 'field49Comment',
	`field50` DATETIME COMMENT 'field50Comment',
	`field51` FLOAT COMMENT 'field51Comment',
	`field52` VARCHAR(125) COMMENT 'field52Comment',
	`field53` VARCHAR(125) COMMENT 'field53Comment',
	`field54` VARCHAR(125) COMMENT 'field54Comment',
	`field55` VARCHAR(125) COMMENT 'field55Comment',
	`field56` INT(1) COMMENT 'field56Comment',
	`field57` BOOLEAN COMMENT 'field57Comment',
	`field58` TEXT COMMENT 'field58Comment',
	`field59` DATETIME COMMENT 'field59Comment',
	`field60` FLOAT COMMENT 'field60Comment',
	`field61` VARCHAR(125) COMMENT 'field61Comment',
	`field62` VARCHAR(125) COMMENT 'field62Comment',
	`field63` VARCHAR(125) COMMENT 'field63Comment',
	`field64` VARCHAR(125) COMMENT 'field64Comment',
	`field65` INT(1) COMMENT 'field65Comment',
	`field66` BOOLEAN COMMENT 'field66Comment',
	`field67` TEXT COMMENT 'field67Comment',
	`field68` DATETIME COMMENT 'field68Comment',
	`field69` FLOAT COMMENT 'field69Comment',
	`field70` VARCHAR(125) COMMENT 'field70Comment',
	`field71` VARCHAR(125) COMMENT 'field71Comment',
	`field72` VARCHAR(125) COMMENT 'field72Comment',
	`field73` VARCHAR(125) COMMENT 'field73Comment',
	`field74` INT(1) COMMENT 'field74Comment',
	`field75` BOOLEAN COMMENT 'field75Comment',
	`field76` TEXT COMMENT 'field76Comment',
	`field77` DATETIME COMMENT 'field77Comment',
	`field78` FLOAT COMMENT 'field78Comment',
	`field79` VARCHAR(125) COMMENT 'field79Comment',
	`field80` VARCHAR(125) COMMENT 'field80Comment',
	`field81` VARCHAR(125) COMMENT 'field81Comment',
	`field82` VARCHAR(125) COMMENT 'field82Comment',
	`field83` INT(1) COMMENT 'field83Comment',
	`field84` BOOLEAN COMMENT 'field84Comment',
	`field85` TEXT COMMENT 'field85Comment',
	`field86` DATETIME COMMENT 'field86Comment',
	`field87` FLOAT COMMENT 'field87Comment',
	`field88` VARCHAR(125) COMMENT 'field88Comment',
	`field89` VARCHAR(125) COMMENT 'field89Comment',
	`field90` VARCHAR(125) COMMENT 'field90Comment',
	`field91` VARCHAR(125) COMMENT 'field91Comment',
	`field92` INT(1) COMMENT 'field92Comment',
	`field93` BOOLEAN COMMENT 'field93Comment',
	`field94` TEXT COMMENT 'field94Comment',
	`field95` DATETIME COMMENT 'field95Comment',
	`field96` FLOAT COMMENT 'field96Comment',
	`field97` VARCHAR(125) COMMENT 'field97Comment',
	`field98` VARCHAR(125) COMMENT 'field98Comment',
	`field99` VARCHAR(125) COMMENT 'field99Comment',
	`field100` VARCHAR(125) COMMENT 'field100Comment',
	`create_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='source1Comment';
