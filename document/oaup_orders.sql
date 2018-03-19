

-- ----------------------------
--  Table structure for `order_pay_info`
-- ----------------------------
DROP TABLE IF EXISTS `order_pay_info`;
CREATE TABLE `order_pay_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `order_id` bigint(20) NOT NULL COMMENT '当前主订单ID',
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型',
  `bank_code` varchar(50) DEFAULT NULL COMMENT '银行代码',
  `begin_time` datetime DEFAULT NULL COMMENT '支付开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '支付时间',
  `bill_amount` decimal(16,4) NOT NULL COMMENT '支付现金金额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改用户ID',
  `ext_param` varchar(200) DEFAULT NULL COMMENT '支付扩张参数',
  `origin_order_id` bigint(20) DEFAULT NULL COMMENT '结转订单ID',
  `pay_device_id` int(11) DEFAULT NULL COMMENT '支付设备类型',
  `pay_method` int(11) DEFAULT NULL COMMENT '支付方式',
  `pay_num` varchar(50) DEFAULT NULL COMMENT '支付单号',
  `pay_status` int(11) DEFAULT NULL COMMENT '支付状态',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_type` int(11) DEFAULT NULL COMMENT '支付类型0：正常；1：结转',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `trans_seq_no` varchar(50) DEFAULT NULL COMMENT '第三方交易号',
  `xb_fee` decimal(16,4) NOT NULL COMMENT '学币',
  `pay_channel` varchar(10) DEFAULT NULL COMMENT '支付渠道：1 支付包 2 微信 3 网银',
  PRIMARY KEY (`id`),
  KEY `ix_order_id` (`order_id`,`order_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增，订单Id',
  `order_number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `order_type` int(11) NOT NULL COMMENT '订单类型',
  `ali_trade_no` varchar(50) DEFAULT NULL COMMENT '第三方支付交易单号',
  `bank_code` varchar(20) DEFAULT NULL COMMENT '银行代码',
  `bill_date` datetime DEFAULT NULL COMMENT '支付时间',
  `bill_no` varchar(12) DEFAULT NULL DEFAULT '' COMMENT '支付单号',
  `pay_card_type` smallint(6) DEFAULT NULL COMMENT '支付卡类型',
  `pay_device_id` int(11) DEFAULT NULL COMMENT '支付设备类型',
  `cell_phone` varchar(32) DEFAULT NULL DEFAULT '' COMMENT '客户电话',
  `deal_fee` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '订单的现金收入',
  `deal_memo` varchar(300) DEFAULT NULL COMMENT '订单备注',
  `deal_user` varchar(32) NOT NULL DEFAULT '' COMMENT '交易用户名字',
  `deliver_id` bigint(20) DEFAULT NULL COMMENT '配送单id',
  `delivery_result` int(11) DEFAULT NULL COMMENT '配送单结果',
  `delivery_status` int(11) DEFAULT NULL COMMENT '当前配送单状态',
  `order_device_id` int(11) DEFAULT NULL COMMENT '下单设备类型',
  `from_ip` varchar(20) DEFAULT NULL COMMENT '下单的客户所在IP',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客户的用户id',
  `income` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '订单的确认收入总计',
  `is_delete` bit(1) NOT NULL COMMENT '订单是否可用',
  `is_audit` bit(1) DEFAULT NULL COMMENT '订单金额是否确认审核',
  `is_bill` bit(1) NOT NULL COMMENT '订单是否支付',
  `is_cancel` bit(1) NOT NULL COMMENT '订单是否已取消',
  `is_test` bit(1) DEFAULT NULL COMMENT '是否内部测试用户下单',
  `manual_discount_fee` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '人工折扣',
  `nsource` varchar(100) DEFAULT NULL COMMENT '下单来源',
  `operator_user_id` bigint(20) DEFAULT NULL COMMENT '下单操作人ID',
  `platform_id` smallint(6) DEFAULT NULL COMMENT '下单平台',
  `point_fee` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '下单所得积分',
  `pre_income` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '订单预收收入',
  `refer_url` varchar(128) DEFAULT NULL COMMENT '下单网站来源',
  `refund_type` varchar(50) DEFAULT NULL COMMENT '退款类型（0-小金库；1-直接退款）',
  `seller_id` bigint(20) DEFAULT NULL COMMENT '订单所属机构',
  `order_date` datetime DEFAULT NULL COMMENT '下单时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单修改时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `cancel_date` datetime DEFAULT NULL COMMENT '订单取消时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB  AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COMMENT '订单主表';




DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单ID',
  `account_date` datetime DEFAULT NULL,
  `batch_id` int(11) DEFAULT NULL COMMENT '卡码券商品的批次ID',
  `combine_discount_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '套餐商品的抵扣',
  `coupon_code` varchar(20) DEFAULT NULL COMMENT '商品的优惠券代码',
  `discount_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '促销抵扣',
  `is_refunded` bit(1) DEFAULT NULL COMMENT '是否已退',
  `manual_discount_amount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '人工折扣',
  `nsource` varchar(400) DEFAULT NULL COMMENT '下单来源',
  `point_discount` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '积分抵扣',
  `product_cate` int(11) DEFAULT NULL COMMENT '商品大类',
  `product_cost` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '商品成本价',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `product_name` varchar(2000) DEFAULT NULL COMMENT '商品名称',
  `product_type` int(11) DEFAULT NULL COMMENT '商品类型',
  `promotion_info` varchar(4000) DEFAULT NULL COMMENT '商品描述',
  `quantity` int(11) NOT NULL COMMENT '购买商品数量',
  `seller_id` bigint(20) DEFAULT NULL COMMENT 'BU下的机构ID',
  `timestamp` timestamp(6) NULL DEFAULT NULL COMMENT '更新时间戳',
  `unit_price` decimal(16,4) NOT NULL DEFAULT '0.0000' COMMENT '产品销售单价\r\n套餐商品-所有子商品单价之和\r\n非套餐商品-商品单价\r\n定金分摊价/尾款分摊价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT '订单详细信息表';


DROP TABLE IF EXISTS `order_deliver`;
CREATE TABLE `order_deliver` (
  `deliver_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `delivery_failed_qty` int(11) DEFAULT NULL COMMENT '配送失败的数量',
  `delivery_qty` int(11) DEFAULT NULL COMMENT '配送成功的数量',
  `delivery_status` int(11) DEFAULT NULL COMMENT '配送状态',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `product_id` bigint(20) NOT NULL COMMENT '商品ID',
  `product_type` int(11) DEFAULT NULL COMMENT '商品类型',
  `quantity` int(11) DEFAULT NULL COMMENT '配送总数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`deliver_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COMMENT '订单配送表';



-- ----------------------------
--  Table structure for `user_address`
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_default` bit(1) DEFAULT NULL COMMENT '是否默认',
  `qq` varchar(72) DEFAULT NULL COMMENT 'qq号',
  `province_id` int(11) DEFAULT NULL COMMENT '省份id',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `town_id` int(11) DEFAULT NULL COMMENT '城乡id',
  `ship_to_address` varchar(512) DEFAULT NULL COMMENT '收件人地址',
  `ship_to_email` varchar(256) DEFAULT NULL COMMENT '收件人邮箱',
  `ship_to_name` varchar(100) DEFAULT NULL COMMENT '收件人名称',
  `ship_to_phone` varchar(256) DEFAULT NULL COMMENT '收件人电话',
  `ship_to_zip` varchar(256) DEFAULT NULL COMMENT '收件人邮编',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `order_shipping`;
CREATE TABLE `order_shipping` (
  `ship_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键，自增，订单Id',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `ship_date` datetime DEFAULT NULL COMMENT '订单配送时间',
  `ship_flag` smallint(6) DEFAULT NULL COMMENT '订单配送状态 0：未配送；1：已配送',
  `ship_method` varchar(32) DEFAULT NULL COMMENT '订单配送方式',
  `ship_to_addr` varchar(400) DEFAULT NULL COMMENT '收货人地址信息',
  `ship_to_city` varchar(64) DEFAULT NULL COMMENT '收货人所在城市',
  `ship_to_name` varchar(64) DEFAULT NULL COMMENT '收货人名称',
  `ship_to_phone` varchar(32) DEFAULT NULL COMMENT '收货人电话',
  `ship_to_province` varchar(64) DEFAULT NULL COMMENT '收货人所在省份名称',
  `ship_to_time` varchar(64) DEFAULT NULL COMMENT '收获人指定的收获时间'
  PRIMARY KEY (`ship_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;



DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type` (
  `device_type_id` bigint(20) NOT NULL COMMENT '序号',
  `code` varchar(255) DEFAULT NULL COMMENT '业务代码',
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`device_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备类型配置表';

insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '-1', 'None', '未知类型');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '0', 'PC', 'PC');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '1000', 'iPhone', 'iPhone');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '1001', 'iPad', 'iPad');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '1002', 'iPod', 'iPod');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '2000', 'Android', 'Android');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '3000', 'WindowsPhone', 'WindowsPhone');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '4000', 'Mobile_Weixin', '微信');
insert into `device_type` ( `device_type_id`, `code`, `description`) values ( '5000', 'TV', 'TV');


DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type` (
  `order_type_id` bigint(20) NOT NULL COMMENT '序号',
  `code` varchar(255) DEFAULT NULL COMMENT '业务代码',
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`order_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单类型配置表';

insert into `order_type` ( `order_type_id`, `code`, `description`) values ( '1', 'Common', '普通单');
insert into `order_type` ( `order_type_id`, `code`, `description`) values ( '2', 'VIP', 'VIP订单');
insert into `order_type` ( `order_type_id`, `code`, `description`) values ( '3', 'Point', '积分兑换');
insert into `order_type` ( `order_type_id`, `code`, `description`) values ( '4', 'Point', '积分兑换');
insert into `order_type` ( `order_type_id`, `code`, `description`) values ( '5', 'CounponBuy', '优惠券购买');




















