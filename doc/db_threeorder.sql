/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - threeorder_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`threeorder_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `threeorder_db`;

/*Table structure for table `tb_cat_param` */

DROP TABLE IF EXISTS `tb_cat_param`;

CREATE TABLE `tb_cat_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cat_no` varchar(32) DEFAULT NULL COMMENT '类目编号',
  `cat_param` text COMMENT '类目参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='商品类目参数';

/*Table structure for table `tb_item` */

DROP TABLE IF EXISTS `tb_item`;

CREATE TABLE `tb_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `item_no` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `item_title` varchar(128) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
  `price` decimal(16,2) DEFAULT NULL COMMENT '单价',
  `num` decimal(16,2) DEFAULT NULL COMMENT '数量',
  `bar_code` varchar(128) DEFAULT NULL COMMENT '条形码',
  `pic_url` varchar(128) DEFAULT NULL COMMENT '图片',
  `cat_no` varchar(128) DEFAULT NULL COMMENT '所属类目',
  `status` char(1) DEFAULT 'Y' COMMENT '状态 Y:有效 N:无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4096 DEFAULT CHARSET=utf8 COMMENT='商品列表';

/*Table structure for table `tb_item_cat` */

DROP TABLE IF EXISTS `tb_item_cat`;

CREATE TABLE `tb_item_cat` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cat_no` varchar(32) DEFAULT NULL COMMENT '类目编号',
  `pcat_no` varchar(32) DEFAULT NULL COMMENT '上级类目编号',
  `item_level` int(11) DEFAULT NULL COMMENT '类目级别',
  `item_sort` int(11) DEFAULT NULL COMMENT '排序号',
  `status` char(1) DEFAULT 'Y' COMMENT '状态 Y:有效 N:无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2048 DEFAULT CHARSET=utf8 COMMENT='商品类目';

/*Table structure for table `tb_item_desc` */

DROP TABLE IF EXISTS `tb_item_desc`;

CREATE TABLE `tb_item_desc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `item_no` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `item_desc` text COMMENT '商品描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4096 DEFAULT CHARSET=utf8 COMMENT='商品描述';

/*Table structure for table `tb_item_param` */

DROP TABLE IF EXISTS `tb_item_param`;

CREATE TABLE `tb_item_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `item_no` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `item_param` text COMMENT '商品参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='商品参数';

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `order_time` datetime DEFAULT NULL COMMENT '订单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_type` int(11) DEFAULT '0' COMMENT '付款方式 0:货到付款 1:在线支付 2:公司转账',
  `order_status` int(11) DEFAULT '0' COMMENT '订单状态 0:待付款 1:待发货 2:已收货',
  `order_amt` decimal(16,2) DEFAULT NULL COMMENT '订单金额',
  `coup_amt` decimal(16,2) DEFAULT NULL COMMENT '优惠金额',
  `trans_amt` decimal(16,2) DEFAULT NULL COMMENT '运费',
  `act_order_amt` decimal(16,2) DEFAULT NULL COMMENT '实际订单金额=订单金额+运费-优惠金额',
  `order_rate` decimal(16,6) DEFAULT NULL COMMENT '税率',
  `act_tax_amt` decimal(16,2) DEFAULT NULL COMMENT '实际含税金额=实际订单金额*税率',
  `pay_amt` decimal(16,2) DEFAULT NULL COMMENT '已付金额',
  `buyler_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `shop_no` varchar(32) DEFAULT NULL COMMENT '店铺编号',
  `seller_id` varchar(32) DEFAULT NULL COMMENT '卖家编号',
  `order_desc` varchar(256) DEFAULT NULL COMMENT '订单描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Table structure for table `tb_order_item` */

DROP TABLE IF EXISTS `tb_order_item`;

CREATE TABLE `tb_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `item_no` varchar(32) DEFAULT NULL COMMENT '商品编号',
  `item_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `item_pic_url` varchar(256) DEFAULT NULL COMMENT '商品图片地址',
  `price` decimal(16,2) DEFAULT NULL COMMENT '单价',
  `num` decimal(16,2) DEFAULT NULL COMMENT '数量',
  `amt` decimal(16,2) DEFAULT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

/*Table structure for table `tb_order_shipping` */

DROP TABLE IF EXISTS `tb_order_shipping`;

CREATE TABLE `tb_order_shipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(32) DEFAULT NULL COMMENT '订单号',
  `recv_name` varchar(64) DEFAULT NULL COMMENT '收货人姓名',
  `recv_phone` varchar(32) DEFAULT NULL COMMENT '固定电话',
  `recv_mobile` varchar(32) DEFAULT NULL COMMENT '移动电话',
  `recv_province` varchar(12) DEFAULT NULL COMMENT '省份',
  `recv_city` varchar(16) DEFAULT NULL COMMENT '城市',
  `recv_district` varchar(32) DEFAULT NULL COMMENT '区、县',
  `recv_address` varchar(256) DEFAULT NULL COMMENT '收货人地址',
  `recv_zip` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `status` char(1) DEFAULT 'Y' COMMENT '是否生效 Y:有效N:无效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单物流信息表';

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `user_no` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone_num` varchar(64) DEFAULT NULL COMMENT '手机号',
  `email_addr` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `login_pass` varchar(256) DEFAULT NULL COMMENT '登录密码',
  `status` char(1) DEFAULT 'Y' COMMENT '状态 Y:有效 N:无效',
  `memo` varchar(256) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modi_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
