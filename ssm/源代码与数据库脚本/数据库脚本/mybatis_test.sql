/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.24 : Database - mybatis_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatis_test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `mybatis_test`;

/*Table structure for table `batch` */

DROP TABLE IF EXISTS `batch`;

CREATE TABLE `batch` (
  `batch_id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` int(11) NOT NULL COMMENT '创建批次用户id',
  `number` varchar(32) NOT NULL COMMENT '批次编码',
  `createtime` datetime NOT NULL COMMENT '创建批次时间',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`batch_id`),
  KEY `FK_batch_1` (`cus_id`),
  CONSTRAINT `FK_batch_id` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `batch` */

insert  into `batch`(`batch_id`,`cus_id`,`number`,`createtime`,`note`) values (1,1,'00001','2017-07-22 00:00:00','首次购买'),(2,3,'00002','2017-03-11 00:00:00','委托购买');

/*Table structure for table `batchdetail` */

DROP TABLE IF EXISTS `batchdetail`;

CREATE TABLE `batchdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `batch_id` int(11) NOT NULL COMMENT '批次id',
  `product_id` int(11) NOT NULL COMMENT '理财产品id',
  `product_num` int(11) DEFAULT NULL COMMENT '理财产品购买数量',
  PRIMARY KEY (`id`),
  KEY `FK_batchdetail_1` (`batch_id`),
  KEY `FK_batchdetail_2` (`product_id`),
  CONSTRAINT `FK_batchdetai_1` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`batch_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_batchdetai_2` FOREIGN KEY (`product_id`) REFERENCES `finacial_products` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `batchdetail` */

insert  into `batchdetail`(`id`,`batch_id`,`product_id`,`product_num`) values (1,1,1,2),(2,1,2,1),(3,1,3,1),(4,2,1,2),(5,2,2,1);

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `cus_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '用户名称',
  `acno` varchar(32) DEFAULT NULL COMMENT '卡号',
  `gender` varchar(4) DEFAULT NULL COMMENT '性别',
  `phone` varchar(256) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`cus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`cus_id`,`username`,`acno`,`gender`,`phone`) values (1,'刘云','6228286666666','男','13800000000'),(2,'李健','622848111111','男','13811111111'),(3,'张丽丽','622848333333','女','13822222222');

/*Table structure for table `finacial_products` */

DROP TABLE IF EXISTS `finacial_products`;

CREATE TABLE `finacial_products` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '理财产品名称',
  `price` float(10,1) NOT NULL COMMENT '理财产品定价',
  `detail` text COMMENT '理财产品描述',
  `pic` varchar(64) DEFAULT NULL COMMENT '理财产品图片',
  `invasttime` datetime NOT NULL COMMENT '理财产品收益日期',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `finacial_products` */

insert  into `finacial_products`(`product_id`,`name`,`price`,`detail`,`pic`,`invasttime`) values (1,'一起富',5000.0,'投资少，风险小','img001','2017-06-21 00:00:00'),(2,'惠薪富',10000.0,'收益稳健','img002','2017-05-03 00:00:00'),(3,'安富尊容',15000.0,'年收益率提升5%','img003','2017-07-18 00:00:00'),(4,'富津利',2000.0,'企划收益率','img004','2017-04-11 00:00:00');

/*Table structure for table `gameplayer` */

DROP TABLE IF EXISTS `gameplayer`;

CREATE TABLE `gameplayer` (
  `id` int(110) DEFAULT NULL,
  `name` varchar(225) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `level` int(110) DEFAULT NULL,
  `ptype` int(110) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `gameplayer` */

insert  into `gameplayer`(`id`,`name`,`gender`,`level`,`ptype`) values (1,'牛牛战士','男',31,1),(2,'芙蓉净月','女',22,2);

/*Table structure for table `magician_info` */

DROP TABLE IF EXISTS `magician_info`;

CREATE TABLE `magician_info` (
  `range` int(110) DEFAULT NULL,
  `power` int(110) DEFAULT NULL,
  `gpid` int(110) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `magician_info` */

insert  into `magician_info`(`range`,`power`,`gpid`) values (213,210,2);

/*Table structure for table `pro_mapping_usr` */

DROP TABLE IF EXISTS `pro_mapping_usr`;

CREATE TABLE `pro_mapping_usr` (
  `pid` int(110) DEFAULT NULL,
  `uid` int(110) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pro_mapping_usr` */

insert  into `pro_mapping_usr`(`pid`,`uid`) values (1,1),(1,2),(1,3),(1,4);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(110) NOT NULL AUTO_INCREMENT,
  `productName` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`productName`) values (1,'apple');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `province` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `city` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`gender`,`email`,`province`,`city`,`birthday`) values (1,'张三','111','男','zhangsan@126.com','河南省','郑州市','1991-04-23'),(2,'李四','222','男','2222@126.com','河北省','邯郸市','1989-10-13'),(3,'刘丽','333','女','3333@126.com','江苏省','苏州市','1994-06-09'),(4,'孙丽','444','女','4444@126.com','四川省','成都市','1992-11-07'),(6,'李磊磊','123qwe','男','lileilei@126.com','云南','大理','1992-01-01');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(110) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) DEFAULT NULL,
  `gender` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `pid` int(110) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`username`,`gender`,`email`,`pid`) values (1,'jack','man','111@126.com',1),(2,'tom','man','222@126.com',1),(3,'jean','woman','333@126.com',1),(4,'blus','man','444@126.com',1);

/*Table structure for table `warrior_info` */

DROP TABLE IF EXISTS `warrior_info`;

CREATE TABLE `warrior_info` (
  `svalue` int(110) DEFAULT NULL,
  `power` int(110) DEFAULT NULL,
  `gpid` int(110) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `warrior_info` */

insert  into `warrior_info`(`svalue`,`power`,`gpid`) values (330,420,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
