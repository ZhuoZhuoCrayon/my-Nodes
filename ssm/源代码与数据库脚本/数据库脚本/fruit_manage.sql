/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.24 : Database - fruit_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fruit_manage` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `fruit_manage`;

/*Table structure for table `accessory` */

DROP TABLE IF EXISTS `accessory`;

CREATE TABLE `accessory` (
  `accessoryid` varchar(50) COLLATE utf8_bin NOT NULL,
  `fruitid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `price` double DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`accessoryid`),
  UNIQUE KEY `accessory_PK` (`accessoryid`),
  KEY `关系4_FK` (`fruitid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `accessory` */

insert  into `accessory`(`accessoryid`,`fruitid`,`name`,`price`,`createtime`) values ('4587egaskh-9j65-87s4-9245-9347ajasd','88e6ec6c-6d17-43a7-8782-38904ajskdh','包装袋',0.1,'2017-09-13 19:47:19'),('4758zhuyrg-2l4t-4d6e-1754-1342ujify','88e6ec6c-6d17-43a7-8782-38904ajskdh','纸板盒',0.3,'2017-09-13 19:50:12'),('fbfec4d6-6510-4c83-9ef2-5467ef6b07ca','88e6ec6c-6d17-43a7-8782-38904ajskdh','捆绳',0.1,'2017-09-13 21:17:39'),('sdasdugy8c-27hkj-dj9-sd-93hsd9834hk','88e6ec6c-6d17-43a7-8782-48957ajskdf','塑料袋',0.05,'2017-09-16 12:20:03');

/*Table structure for table `commodities` */

DROP TABLE IF EXISTS `commodities`;

CREATE TABLE `commodities` (
  `fruitid` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `price` double DEFAULT NULL,
  `locality` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`fruitid`),
  UNIQUE KEY `commodities_PK` (`fruitid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `commodities` */

insert  into `commodities`(`fruitid`,`name`,`price`,`locality`,`createtime`) values ('88e6ec6c-6d17-43a7-8782-38904ajskdh','黄桃',2.5,'浙江省金华市','2017-09-09 09:34:12'),('88e6ec6c-6d17-43a7-8782-48957ajskdf','火龙果',6.68,'浙江省绍兴市','2017-09-09 09:35:17'),('88e6ec6c-6d17-43a7-8782-89asdjh389a','橙子',0.4,'江苏省南京市','2017-09-09 09:36:44'),('88e6ec6c-6d17-43a7-8782-9534sd23h90','柚子',0.5,'上海市金山区','2017-09-09 09:37:51'),('88e6ec6c-6d17-43a7-8782-csrjdsk8347','猕猴桃',2.2,'上海市嘉定区','2017-09-09 09:38:03'),('88e6ec6c-6d17-43a7-8782-d1eae391234','苹果',0.7,'山东省青岛市','2017-09-09 09:39:22'),('88e6ec6c-6d17-43a7-8782-d1eae394106','橘子',1.6,'上海市普陀区','2017-09-09 09:40:11'),('88e6ec6c-6d17-43a7-8782-d1eae84dj46','香蕉',1.3,'江苏省昆山市','2017-09-09 09:41:23'),('88e6ec6c-6d17-43a7-8782-dhk327894aj','柿子',0.5,'江苏省苏州市','2017-09-09 09:42:52'),('88e6ec6c-6d17-43a7-8782-dijhksd2367','榴莲',13,'江苏省盐城县','2017-09-09 09:43:31'),('88e6ec6c-6d17-43a7-8782-hc2374gasd8','葡萄',2.3,'上海市马陆镇','2017-09-09 09:43:57'),('88e6ec6c-6d17-43a7-8782-jhk8340a783','荔枝',1.8,'江苏省无锡市','2017-09-09 09:44:05'),('88e6ec6c-6d17-43a7-8782-kduidfh3435','西瓜',0.3,'江苏省常州市','2017-09-09 09:45:14'),('88e6ec6c-6d17-43a7-8782-xce3940hsd4','芒果',3.6,'浙江省杭州市','2017-09-09 09:46:34');

/*Table structure for table `contract` */

DROP TABLE IF EXISTS `contract`;

CREATE TABLE `contract` (
  `contractid` varchar(50) COLLATE utf8_bin NOT NULL,
  `retailerid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `barcode` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`contractid`),
  UNIQUE KEY `contract_PK` (`contractid`),
  KEY `关系1_FK` (`retailerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `contract` */

insert  into `contract`(`contractid`,`retailerid`,`barcode`,`type`,`createtime`) values ('5636d4e7-36c3-4f5d-b11c-049c8c1f8945','351ab130-07c4-4a82-b713-8f71328111bc','201709250001',0,'2017-09-25 19:13:20');

/*Table structure for table `middle_tab` */

DROP TABLE IF EXISTS `middle_tab`;

CREATE TABLE `middle_tab` (
  `middleid` varchar(50) COLLATE utf8_bin NOT NULL,
  `contractid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `fruitid` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`middleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `middle_tab` */

insert  into `middle_tab`(`middleid`,`contractid`,`fruitid`,`number`) values ('9b17fc9c-3be8-47e1-af63-d393d51dcf2b','5636d4e7-36c3-4f5d-b11c-049c8c1f8945','88e6ec6c-6d17-43a7-8782-38904ajskdh',0),('b13bc528-db03-423f-a73b-839d2bb3b880','5636d4e7-36c3-4f5d-b11c-049c8c1f8945','88e6ec6c-6d17-43a7-8782-48957ajskdf',0),('b4219c06-3f5b-464a-860e-224fbdd2a1df','5636d4e7-36c3-4f5d-b11c-049c8c1f8945','88e6ec6c-6d17-43a7-8782-d1eae84dj46',0);

/*Table structure for table `retailer` */

DROP TABLE IF EXISTS `retailer`;

CREATE TABLE `retailer` (
  `retailerid` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `telphone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`retailerid`),
  UNIQUE KEY `retailer_PK` (`retailerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `retailer` */

insert  into `retailer`(`retailerid`,`name`,`telphone`,`address`,`status`,`createtime`) values ('351ab130-07c4-4a82-b713-8f71328111bc','刘成成','13566666666','上海市黄浦区',1,'2017-09-09 09:31:17'),('45j8r40p-4fu7-87t4-8723-sdfjh789x907','石恩华','13777777778','上海市普陀区',1,'2017-09-02 12:33:40'),('88e6ec6c-6d17-43a7-8782-d1eae394d802','蒋虎子','13888888888','上海市嘉定区',1,'2017-09-02 12:31:20'),('90h7dv5c-9j87-24r6-9087-anune089x021','胡晓丽','15522222222','上海市闵行区',1,'2017-09-04 09:14:55'),('90h7dv5c-9j87-24r6-9087-anune089x096','蒋俊佳','13666666666','上海市宝山区',1,'2017-09-03 09:14:55'),('90h7dv5c-9j87-24r6-9087-anune089x294','施俊杰','13444444444','上海市徐汇区',1,'2017-09-04 09:16:55'),('90h7dv5c-9j87-24r6-9087-anune089x325','钱晓晓','15533333333','上海市长宁区',1,'2017-09-04 09:17:55'),('90h7dv5c-9j87-24r6-9087-anune089x365','王二小','13555555555','上海市杨浦区',1,'2017-09-04 09:18:55'),('90h7dv5c-9j87-24r6-9087-anune089x476','任宇','13222222222','上海市虹口区',1,'2017-09-04 09:19:55'),('90h7dv5c-9j87-24r6-9087-anune089x734','周佳','15566666666','上海市金山区',1,'2017-09-04 09:20:55'),('90h7dv5c-9j87-24r6-9087-anune089x921','张晓冉','15511111111','上海市奉贤区',1,'2017-09-04 09:21:55'),('90h7dv5c-9j87-24r6-9087-anune089x954','牛夏利','13333333333','上海市松江区',1,'2017-09-04 09:22:55'),('90h7dv5c-9j87-24r6-9087-anune089x978','刘浩','13111111111','上海市青浦区',1,'2017-09-04 09:23:55');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` varchar(50) COLLATE utf8_bin NOT NULL,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `telphone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `user_PK` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `user` */

insert  into `user`(`userid`,`username`,`password`,`name`,`telphone`) values ('3af57d0545766ec940d2c32a6567cc06ae5','jack','1234','张三','15588888888'),('734hdak3kfd389jgh3kll4590wejrh983jk','tom','4321','张茜茜','13888888888'),('88e6ec6c-6d17-43a7-8782-d1eae394d802','jackson','2222','刘霜','13782345627');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
