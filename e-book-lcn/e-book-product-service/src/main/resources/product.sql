CREATE DATABASE ;

USE `book-product`;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) DEFAULT NULL COMMENT '产品名称',
  `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '产品状态：0待审，1上架，2下架，3停售，4测试',
  `price` INT(10) NOT NULL COMMENT '产品价格 单位分',
  `detail` TEXT COMMENT '产品详情',
  `deleted` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='产品信息';



INSERT INTO `product` (`id`, `name`, `status`, `price`, `detail`, `deleted`, `create_time`, `update_time`) VALUES('1','尚学堂-实战java程序设计','1','100','尚学堂-实战java程序设计','0','2017-12-03 09:08:12','2017-12-17 16:44:39');
INSERT INTO `product` (`id`, `name`, `status`, `price`, `detail`, `deleted`, `create_time`, `update_time`) VALUES('2','尚学堂-百战程序员经典案例','1','200','尚学堂-百战程序员经典案例','0','2017-12-03 09:08:12','2017-12-17 16:44:41');
INSERT INTO `product` (`id`, `name`, `status`, `price`, `detail`, `deleted`, `create_time`, `update_time`) VALUES('3','尚学堂-人工智能基础教程','1','300','尚学堂-人工智能基础教程','0','2017-12-17 16:44:35','2017-12-17 16:45:15');


