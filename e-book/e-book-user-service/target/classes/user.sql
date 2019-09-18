CREATE DATABASE;

USE `book-user`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
  `password` VARCHAR(50) NOT NULL COMMENT '密码',
  `email` VARCHAR(50) NOT NULL COMMENT 'email',
  `deleted` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';



INSERT  INTO `user`(`id`,`user_name`,`password`,`email`,`deleted`,`create_time`,`update_time`) VALUES (1,'admin','admin','suibian@163.com',0,'2016-02-02 12:56:31','2016-02-02 14:47:25');

