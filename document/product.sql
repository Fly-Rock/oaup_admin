
DROP TABLE  IF EXISTS  `product_category`;
CREATE TABLE `product_category` (
  `category_id` INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `category_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品分类名称',
  `category_alias` varchar(50) NOT NULL DEFAULT '' COMMENT '别名',
  `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '父节点id',
  `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '1 可用 0 不可用 -1 删除',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品类型配置表';

# 一级分类
INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('保洁服务','BJFW',0,0,1,NOW(),NOW());

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('企业保洁','QYBJ',0,0,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('保姆月嫂','BMYS',0,0,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('租赁服务','ZLFW',0,0,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('零售商品','LSSP',0,0,1,NOW(),NOW());
# 二级分类

SELECT category_id from `product_category` where `status` = 1   limit 1 into @BJFWID;


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('日常保洁','RCBJ',0,@BJFWID,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('新居开荒','XJKH',0,@BJFWID,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('地板打蜡','DBDL',0,@BJFWID,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('卫生间保养','WSJBY',0,@BJFWID,1,NOW(),NOW());

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('厨房保养','CFBY',0,@BJFWID,1,NOW(),NOW());

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('擦玻璃','CBL',0,@BJFWID,1,NOW(),NOW());


##二级分类 企业保洁

SELECT category_id from `product_category` where `status` = 1 and `category_alias`='QYBJ'   limit 1 into @QYBJID;

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('企业开荒保洁','QYKHBJ',0,@QYBJID,1,NOW(),NOW());

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('企业长期服务','QYCQFW',0,@QYBJID,1,NOW(),NOW());

## 二级分类 保姆月嫂


SELECT category_id from `product_category` where `status` = 1 and `category_alias`='BMYS'   limit 1 into @BMYSID;

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('月嫂','YS',0,@BMYSID,1,NOW(),NOW());

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('育儿嫂','YES',0,@BMYSID,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('保姆','BM',0,@BMYSID,1,NOW(),NOW());


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('老人陪护','LRPH',0,@BMYSID,1,NOW(),NOW());

## 二级分类 租赁服务

SELECT category_id from `product_category` where `status` = 1 and `category_alias`='ZLFW'   limit 1 into @ZLFWID;

INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('绿植租摆','LZZB',0,@ZLFWID,1,NOW(),NOW());







DROP TABLE  IF EXISTS  `product`;
CREATE TABLE `product` (
  `product_id` INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `category_id` INT(11) NOT NULL COMMENT '商品分类',
  `product_alias` varchar(50) DEFAULT  COMMENT '商品别名',
  `product_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
  `product_image` varchar(200) NOT NULL DEFAULT '' COMMENT '商品图片',
  `min_pay_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品单价',
  `seller_id` int(11) NOT NULL DEFAULT '0' COMMENT '商户机构ID',
  `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '1 上架可用 0 下架 -1 删除',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';


DROP TABLE  IF EXISTS  `product_description`;
CREATE TABLE `product_description` (
  `product_id` INT(11) NOT NULL COMMENT '商品id',
  `content` varchar(8000) NOT NULL DEFAULT '' COMMENT '商品名称',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品描述表';


DROP TABLE  IF EXISTS  `product_price`;
CREATE TABLE `product_price` (
  `product_price_id` INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `product_id` INT(11) NOT NULL  COMMENT '商品ID',
  `pay_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品单价',
  `market_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '市场价格',
  `cost_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '进货价格',
  `unit_price_id` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '价格单位ID',
  `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '1 可用 0  删除',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`product_price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品价格表';



DROP TABLE  IF EXISTS  `product_unit_price`;
CREATE TABLE `product_unit_price` (
  `unit_price_id` INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `unit_name` varchar(20)  NOT NULL  COMMENT '价格单位名称',
  `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '1 可用 0  删除',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`unit_price_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品价格单位表';


INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('平米','1',10000,NOW(),NOW());

INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('小时','1',10000,NOW(),NOW());

INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('盆','1',10000,NOW(),NOW());

INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('颗','1',10000,NOW(),NOW());

INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('株','1',10000,NOW(),NOW());

INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('件','1',10000,NOW(),NOW());

INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('桶','1',10000,NOW(),NOW());

INSERT INTO `product_unit_price`
(unit_name,`status`,`create_user_id`,`update_time`,`create_time`)
VALUES('月','1',10000,NOW(),NOW());




DROP TABLE  IF EXISTS  `product_attribute`;
CREATE TABLE `product_attribute` (
  `attribute_id` INT(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `product_category_id` INT(11) NOT NULL COMMENT '商品属性归属于商品类别',
  `title` varchar(200) DEFAULT  COMMENT '商品属性标题',
  `sub_title` varchar(200) DEFAULT  COMMENT '商品属性子标题',
  `attribute` varchar(50) NOT NULL DEFAULT '' COMMENT '属性代码',
  `content` varchar(200) DEFAULT  COMMENT '商品属性子标题',
  `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '1 上架可用 0 下架 -1 删除',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性表';


