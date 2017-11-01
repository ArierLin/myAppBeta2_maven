-- CREATE DATABASE demo;
-- USE demo;
-- 
-- DROP TABLE IF EXISTS demo;

use myapp_beta1;

DROP TABLE IF EXISTS role_authority_relation;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS authority;

CREATE TABLE authority(
	id INT(32) NOT NULL AUTO_INCREMENT ,
	name VARCHAR(32) NOT NULL ,
	ddesc VARCHAR(255) ,
	parent_id INT(32) ,
	code INT(32) ,
	url VARCHAR(64) ,
	create_time VARCHAR(32),
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE role(
	id INT(32) NOT NULL AUTO_INCREMENT ,
	name VARCHAR(32) NOT NULL ,
	ddesc VARCHAR(255) ,
	ttype INT(2) DEFAULT -1 ,
	create_time VARCHAR(32) ,
	update_time VARCHAR(32) ,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 系统顶级菜单
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('10000','功能菜单','菜单最顶级','0','10000','','');
-- 商品管理菜单
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('20001','商品管理','商品管理','10000','20001','','');
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('20101','商品管理','商品管理','20001','20101','goods/toGoodsManagementPage','');
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('20102','商品类型管理','商品类型管理','20001','20102','goods/toGoodTypeManagementPage','');
-- webservice菜单
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('70001','webService服务','webService服务','10000','70001','','');
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('70101','常见webService服务','常见webService服务','70001','70101','webService/toWebServiceListPage','');
-- 系统管理菜单
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('80001','系统管理','系统管理','10000','80001','','');
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('80101','角色管理','角色管理','80001','80101','role/toRoleManagementPage','');
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('80102','权限管理','权限管理','80001','80102','authority/toAuthorityManagementPage','');
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('80103','用户管理','用户管理','80001','80103','user/toUserManagementPage','');
INSERT INTO authority(id,name,ddesc,parent_id,code,url,create_time) values('80104','系统升级管理','系统升级管理(文件上传、断点续传)','80001','80104','system/toSystemUpgradeManagementPage','');

-- 角色数据
INSERT INTO role(id,name,ddesc,ttype,create_time,update_time) values('10000','admin','管理员角色','1','2017-01-05 10:00:00','');
INSERT INTO role(id,name,ddesc,ttype,create_time,update_time) values('10001','user','普通用户角色','1','2017-01-05 10:00:00','');
INSERT INTO role(id,name,ddesc,ttype,create_time,update_time) values('10002','test','测试数据','-1','2016-12-30 15:00:00','');


CREATE TABLE role_authority_relation(
	role_id INT(32),
	authority_id INT(32),
	create_time VARCHAR(32),
	KEY `fk_role_authority_role_id` (`role_id`),
	KEY `fk_role_authority_authority_id` (`authority_id`),
	CONSTRAINT `fk_role_authority_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
	CONSTRAINT `fk_role_authority_authority_id` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`)
);

INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '10000', '2017-01-05 10:00:00');
INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '20001', '2017-01-05 10:00:00');
INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '20101', '2017-01-05 10:00:00');
INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '20102', '2017-01-05 10:00:00');
-- INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '20103', '2017-01-05 10:00:00');
INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '80001', '2017-01-05 10:00:00');
INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '80101', '2017-01-05 10:00:00');
INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10000', '80102', '2017-01-05 10:00:00');

INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10001', '10000', '2017-01-05 10:00:00');
-- INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10001', '20103', '2017-01-05 10:00:00');
INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10002', '10000', '2017-01-05 10:00:00');
-- INSERT INTO `role_authority_relation` (`role_id`, `authority_id`, `create_time`) VALUES ('10002', '20103', '2017-01-05 10:00:00');

DROP TABLE IF EXISTS user;

CREATE TABLE user(
	id INT(32),
	user_name VARCHAR(64),
	password VARCHAR(64),
	is_del INT(2),
	create_time VARCHAR(32),
	update_time VARCHAR(32),
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user(id,user_name,password,is_del,create_time,update_time) values('10000','admin','123456',-1,'2016-12-30 15:00:00','');
INSERT INTO user(id,user_name,password,is_del,create_time,update_time) values('10001','user','123456',-1,'2016-12-30 15:00:00','');

DROP TABLE IF EXISTS user_role_relation;

CREATE TABLE user_role_relation(
	user_id INT(32),
	role_id INT(32),
	create_time VARCHAR(32),
	KEY `fk_user_role_user_id` (`user_id`),
	KEY `fk_user_role_role_id` (`role_id`),
	CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
	CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
);

INSERT INTO user_role_relation (`user_id`, `role_id`, `create_time`) VALUES ('10000', '10000', '2017-01-05 10:00:00');
INSERT INTO user_role_relation (`user_id`, `role_id`, `create_time`) VALUES ('10001', '10001', '2017-01-05 10:00:00');



