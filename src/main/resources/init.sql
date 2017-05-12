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
