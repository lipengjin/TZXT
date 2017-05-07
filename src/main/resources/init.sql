use `tzxt`;

DROP TABLE IF EXISTS `user`;
create table `user`
(
	id bigint not null auto_increment
		primary key,
	user_name varchar(255) default '' not null comment '用户名',
	password varchar(255) null comment '密码',
	real_name varchar(255) null comment '真实名称',
	unit_id bigint not null comment '单位ID',
	unit varchar(255) not null comment '单位名称',
	role_id bigint not null comment '角色ID',
	role varchar(255) not null comment '角色名称',
	auth int(1) not null comment '角色权限',
	locked tinyint(1) default FALSE not null comment '角色是否锁定 默认 false',
	create_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_at datetime default CURRENT_TIMESTAMP not null comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
comment '用户信息表'
;

INSERT INTO user VALUES (1, 'admin', '123456', '管理员', 1, 'unit1', 1, '系统管理员', 1, FALSE, now(), now());

DROP table IF EXISTS `unit`;
create table `unit`
(
	id bigint not null auto_increment
		primary key,
	name varchar(255) default '' not null comment '单位名',
	create_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_at datetime default CURRENT_TIMESTAMP not null comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
	comment '单位表'
;

INSERT INTO unit VALUES (1, 'unit1', now(), now());

DROP TABLE IF EXISTS `role`;
create table role
(
	id bigint not null auto_increment
		primary key,
	name varchar(255) default '' not null comment '角色名',
	create_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_at datetime default CURRENT_TIMESTAMP not null comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
	comment '角色表'
;

INSERT INTO role VALUES (1, '系统管理员', now(), now()),
	(2, 'Guest', now(), now());

DROP TABLE IF EXISTS `role_auths`;
CREATE TABLE role_auths (
	id bigint not null auto_increment primary key,
	`role_id` BIGINT NOT NULL COMMENT '角色ID',
	`auth_type` INT(1) NOT NULL COMMENT '权限类型',
	`auth` VARCHAR(255) not NULL COMMENT '权限',
	`ledger_id` BIGINT DEFAULT NULL COMMENT '台账ID，如果权限类型是数据权限 则不为null',
	create_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_at datetime default CURRENT_TIMESTAMP not null comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
	comment '角色权限表'
;

INSERT INTO role_auths VALUES (1, 1, 0, '系统管理', NULL, now(), now()),
	(2, 1, 0, '菜单管理', NULL, now(), now()),
	(3, 1, 0, '角色管理', NULL, now(), now()),
	(4, 1, 0, '用户管理', NULL , now(), now());

DROP TABLE IF EXISTS `ledger_dictionary`;
create table ledger_dictionary
(
	id bigint not null auto_increment
		primary key,
	ledger_id bigint not null comment '台账表ID',
	`index` int not null comment '字段序号',
	field_name varchar(255) not null comment '字段名称',
	field_type varchar(255) not null comment '字段类型',
	comment varchar(255) default '' not null comment '注释',
	create_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_at datetime default CURRENT_TIMESTAMP not null comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
	comment '台账表'
;

DROP TABLE if EXISTS `ledger`;
create table ledger
(
	id bigint not null auto_increment
		primary key,
	name varchar(255) default '' not null comment '台账表名',
	comment varchar(255) default '' not null comment '注释',
	create_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_at datetime default CURRENT_TIMESTAMP not null comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
	comment '台账表'
;

DROP TABLE IF EXISTS `filed_type`;
create table filed_type
(
	id bigint not null auto_increment
		primary key,
	type varchar(255) not null comment '字段类型',
	display varchar(255) not null comment '字段类型 含义'
) ENGINE=InnoDB DEFAULT CHARSET=utf8
	comment '数据字典 字段类型表'
;

INSERT INTO filed_type VALUES (1, 'INT', '整形数据'),
	(2, 'FLOAT', '浮点型数据'),
	(3, 'VARCHAR', '字符型数据'),
	(4, 'DATETIME', '日期类型数据'),
	(5, 'TEXT', '文本类型数据'),
	(6, 'BOOLEAN', 'bool类型数据');
