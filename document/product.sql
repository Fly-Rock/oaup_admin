
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


INSERT INTO `product_category`
(`category_name`,`category_alias`,`sort`,`parent_id`,`status`,`update_time`,`create_time`)
VALUES ('深度除螨','SDCM',0,@BJFWID,1,NOW(),NOW());

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
  `product_alias` varchar(50) NOT NULL DEFAULT ''  COMMENT '商品别名',
  `product_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
  `product_image` varchar(200) NOT NULL DEFAULT '' COMMENT '商品图片',
  `min_pay_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品单价',
  `seller_id` int(11) NOT NULL DEFAULT '0' COMMENT '商户机构ID',
  `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '1 上架可用 0 下架 -1 删除',
  `create_user_id` bigint(20) NOT NULL COMMENT '创建用户ID',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';



DROP TABLE  IF EXISTS  `product_description`;
CREATE TABLE `product_description` (
  `product_description_id`  INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键、自增',
  `product_id` INT(11) NOT NULL COMMENT '商品id',
  `content` varchar(8000) NOT NULL DEFAULT '' COMMENT '商品名称',
  `content_style` varchar(20) NOT NULL DEFAULT '' COMMENT '内容模式类型',
  `sort` int(20) NOT NULL DEFAULT '0' COMMENT '排序',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`product_description_id`)
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性表(该表暂时无用)';


INSERT INTO product_description (product_id,`content_style`,`sort`, content, update_time, create_time)
VALUES (10000, 'detail_table',0,'{
        "style": "detail_table",
        "table_style": "a",
        "contentListRowTable": [
            {
                "tableRow": [
                    {
                        "title": "项目名称",
                        "routerData": ""
                    },
                    {
                        "title": "非会员价格",
                        "routerData": ""
                    },
                    {
                        "title": "会员价格",
                        "routerData": ""
                    }
                ]
            },
            {
                "tableRow": [
                    {
                        "title": "单人床",
                        "routerData": ""
                    },
                    {
                        "title": "138元/张",
                        "routerData": ""
                    },
                    {
                        "title": "127元/张",
                        "routerData": ""
                    }
                ]
            }
        ]
    }',NOW(),NOW());


INSERT INTO product_description (product_id,`content_style`,`sort`, content, update_time, create_time)
VALUES (10000,'detail_zonghe',0,' {
        "style": "detail_zonghe",
        "contentListBlocks": [
            {
                "style": "detail_reminder",
                "title": "温馨提示：",
                "contentList": [
                    {
                        "title": "1.单人床与双人床上除螨仅包含一套床上四件套；"
                    },
                    {
                        "title": "2.1.2米及以下为单人床，其他规格为双人床;"
                    }
                ]
            }
        ],
        "title": "",
        "subtitle": ""
    }',NOW(),NOW());


INSERT INTO product_description (product_id,`content_style`,`sort`, content, update_time, create_time)
VALUES (10000, 'detail_zonghe',0,'{
        "style": "detail_zonghe",
        "contentListBlocks": [
            {
                "style": "detail_image",
                "bannerList": [
                    {
                        "image": "https://ayipic.ayibang.com/cms__a6ebc9c72d21de090d8cfbd49259e1c2_1495787073.jpg",
                        "pictureName": "XQY深度除螨_服务场景"
                    }
                ]
            }
        ],
        "title": "服务场景",
        "subtitle": "多方位舒适体验"
    }',NOW(),NOW());




INSERT INTO product_description (product_id,`content_style`,`sort`, content, update_time, create_time)
VALUES (10000, 'detail_zonghe',0,' {
        "style": "detail_zonghe",
        "contentListBlocks": [
            {
                "style": "detail_content",
                "title": "螨虫存在于居室的阴暗角落、地毯、床垫、枕头、沙发、空调、凉席等处，是过敏性皮炎、鼻炎和呼吸道疾病的重要元凶！"
            },
            {
                "style": "detail_image",
                "bannerList": [
                    {
                        "image": "https://ayipic.ayibang.com/cms__76020f3b04b0b2d73be2fb0d09f2a659_1495787172.jpg",
                        "pictureName": "XQY深度除螨_业务介绍"
                    }
                ]
            },
            {
                "style": "detail_reminder",
                "title": "温馨提示：",
                "contentList": [
                    {
                        "title": "即使室内环境非常干净的家庭，平均每张床上也有200万只螨虫。"
                    },
                    {
                        "title": "阳光暴晒6小时的螨虫杀灭率仅为3.8%。"
                    },
                    {
                        "title": "84.3%的皮肤过敏为螨虫过敏，儿童更高达91.6%"
                    }
                ]
            }
        ],
        "title": "业务介绍",
        "subtitle": "选择我们，选择放心"
    }',NOW(),NOW());





INSERT INTO product_description (product_id,`content_style`,`sort`, content, update_time, create_time)
VALUES (10000, 'detail_zonghe',0,' {
        "style": "detail_zonghe",
        "contentListBlocks": [
            {
                "style": "detail_image",
                "bannerList": [
                    {
                        "image": "https://ayipic.ayibang.com/cms__834e20d273ff93476b021d0f47ab4deb_1495787088.jpg",
                        "pictureName": "XQY深度除螨_服务流程"
                    }
                ]
            }
        ],
        "title": "服务流程",
        "subtitle": "多个流程，精细化服务"
    }',NOW(),NOW());



INSERT INTO product_description (product_id,`content_style`,`sort`, content, update_time, create_time)
VALUES (10000, 'detail_zonghe',0,' {
        "style": "detail_zonghe",
        "contentListBlocks": [
            {
                "style": "detail_image",
                "bannerList": [
                    {
                        "image": "https://ayipic.ayibang.com/cms__63a43df4defc4963ec43329dfa146aaa_1495787138.jpg",
                        "pictureName": "XQY深度除螨_工具装备"
                    }
                ]
            }
        ],
        "title": "工具装备",
        "subtitle": "专业装备、安全放心"
    }',NOW(),NOW());




INSERT INTO product_description (product_id,`content_style`,`sort`, content, update_time, create_time)
VALUES (10000, 'detail_zonghe',0,'{
        "style": "detail_zonghe",
        "contentListBlocks": [
            {
                "style": "detail_image",
                "bannerList": [
                    {
                        "image": "https://ayipic.ayibang.com/cms__1b4f5649867500dc63ce8b45f64c293d_1495787105.jpg",
                        "pictureName": "XQY深度除螨_服务特色"
                    }
                ]
            },
            {
                "style": "detail_content",
                "title": "水过滤除螨避免污染"
            },
            {
                "style": "detail_content",
                "title": "云佳洁深度除螨使用的除螨仪依靠高频拍打和强大动力吸除螨虫，并通过水过滤系统收集螨虫，避免吸入机器中的螨虫弥散到空气中，造成螨虫在居室中的二次污染。"
            },
            {
                "style": "detail_image",
                "bannerList": [
                    {
                        "image": "https://ayipic.ayibang.com/cms__a2f8968dc6c5715881b374b8d1a72560_1495787123.jpg",
                        "pictureName": "XQY深度除螨_服务特色2"
                    }
                ]
            },
            {
                "style": "detail_content",
                "title": "标准化流程全面除螨"
            },
            {
                "style": "detail_content",
                "title": "“灭-除-止”三步综合作用，消灭螨虫、吸除螨虫、喷洒除螨剂抑制螨虫，阿姨帮深度除螨从根源着手，兼顾长期效果。"
            }
        ],
        "title": "服务特色",
        "subtitle": "安全、彻底、放心"
    }',NOW(),NOW());

