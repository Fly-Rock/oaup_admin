
use oaup_account;

CREATE TABLE `account_user`
(
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名字',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `user_email` varchar(200) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `user_mobile` varchar(36) NOT NULL DEFAULT '' COMMENT '用户手机号',
  `user_password` varchar(200) NOT NULL DEFAULT '' COMMENT '用户密码md5加密',
  `sex` tinyint(4) NOT NULL DEFAULT '0',
  `lock_user` int(11) NOT NULL DEFAULT '0',
  `user_last_ip` varchar(15) NOT NULL DEFAULT '',
  `user_power` int(11) NOT NULL DEFAULT '0' COMMENT '用户权限级别',
  `user_level` int(11) NOT NULL DEFAULT '0' COMMENT '用户级别',
  `user_source` varchar(50) NOT NULL DEFAULT ''  COMMENT '用户来源',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户基础信息表';