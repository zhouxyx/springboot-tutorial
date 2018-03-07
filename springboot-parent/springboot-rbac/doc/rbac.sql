/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.50 : Database - db-sso
*********************************************************************
*/

CREATE TABLE `sys_resource` (
  `resource_id` bigint(20) NOT NULL,
  `resource_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `type` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '资源类型 比如:菜单，按钮',
  `icon` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `product` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '所属产品',
  `url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `parent_id` bigint(20) NOT NULL,
  `tree_path` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '记录tree的层级关系',
  `permission_key` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `row_status` tinyint(1) DEFAULT '0' COMMENT '0正常，1删除',
  `row_version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`resource_id`,`resource_name`,`type`,`icon`,`product`,`url`,`parent_id`,`tree_path`,`permission_key`,`row_status`,`row_version`) values (1,'用户查询','URL',NULL,'ODC',NULL,0,',','user:query',0,'2018-03-07 23:17:39');
insert  into `sys_resource`(`resource_id`,`resource_name`,`type`,`icon`,`product`,`url`,`parent_id`,`tree_path`,`permission_key`,`row_status`,`row_version`) values (2,'用户新增','URL',NULL,'ODC',NULL,0,',','user:add',0,'2018-03-07 23:17:56');
insert  into `sys_resource`(`resource_id`,`resource_name`,`type`,`icon`,`product`,`url`,`parent_id`,`tree_path`,`permission_key`,`row_status`,`row_version`) values (3,'用户删除','URL',NULL,'ODC',NULL,0,',','user:del',0,'2018-03-07 23:18:31');
insert  into `sys_resource`(`resource_id`,`resource_name`,`type`,`icon`,`product`,`url`,`parent_id`,`tree_path`,`permission_key`,`row_status`,`row_version`) values (4,'用户更新','URL',NULL,'ODC',NULL,0,',','user:update',0,'2018-03-07 23:18:32');

/*Table structure for table `sys_role` */

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `product` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '所属产品',
  `row_status` tinyint(1) DEFAULT '0' COMMENT '0正常，1删除',
  `row_VERSION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_desc`,`product`,`row_status`,`row_VERSION`) values (1,'Admin','Admin','ODC',0,'2018-03-07 23:16:22');
insert  into `sys_role`(`role_id`,`role_name`,`role_desc`,`product`,`row_status`,`row_VERSION`) values (2,'User','User','ODC',0,'2018-03-07 23:16:33');

/*Table structure for table `sys_role_resource` */

CREATE TABLE `sys_role_resource` (
  `role_resource_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  `row_status` tinyint(1) DEFAULT '0' COMMENT '0正常，1删除',
  `row_version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `sys_role_resource` */

insert  into `sys_role_resource`(`role_resource_id`,`role_id`,`resource_id`,`row_status`,`row_version`) values (1,1,1,0,'2018-03-07 23:19:17');
insert  into `sys_role_resource`(`role_resource_id`,`role_id`,`resource_id`,`row_status`,`row_version`) values (2,1,2,0,'2018-03-07 23:19:20');
insert  into `sys_role_resource`(`role_resource_id`,`role_id`,`resource_id`,`row_status`,`row_version`) values (3,1,3,0,'2018-03-07 23:19:22');
insert  into `sys_role_resource`(`role_resource_id`,`role_id`,`resource_id`,`row_status`,`row_version`) values (4,1,4,0,'2018-03-07 23:19:27');
insert  into `sys_role_resource`(`role_resource_id`,`role_id`,`resource_id`,`row_status`,`row_version`) values (5,2,1,0,'2018-03-07 23:19:30');
insert  into `sys_role_resource`(`role_resource_id`,`role_id`,`resource_id`,`row_status`,`row_version`) values (6,2,2,0,'2018-03-07 23:19:41');

/*Table structure for table `sys_user` */

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL,
  `user_name` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  `row_status` tinyint(1) DEFAULT '0' COMMENT '0正常，1删除',
  `row_version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`user_name`,`password`,`phone`,`row_status`,`row_version`) values (1,'Admin','123456','13800138000',0,'2018-03-07 23:51:45');
insert  into `sys_user`(`user_id`,`user_name`,`password`,`phone`,`row_status`,`row_version`) values (123456,'zhouxyx','123456','13800138000',0,'2018-03-06 22:44:52');

/*Table structure for table `sys_user_role` */

CREATE TABLE `sys_user_role` (
  `user_role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `row_status` tinyint(1) DEFAULT '0' COMMENT '0正常，1删除',
  `row_version` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_role_id`,`user_id`,`role_id`,`row_status`,`row_version`) values (1,123456,1,0,'2018-03-07 23:16:47');
insert  into `sys_user_role`(`user_role_id`,`user_id`,`role_id`,`row_status`,`row_version`) values (2,123456,2,0,'2018-03-07 23:16:53');
insert  into `sys_user_role`(`user_role_id`,`user_id`,`role_id`,`row_status`,`row_version`) values (3,1,2,0,'2018-03-07 23:51:58');


