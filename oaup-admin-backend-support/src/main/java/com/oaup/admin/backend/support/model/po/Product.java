
package com.oaup.admin.backend.support.model.po;

import java.util.*;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class Product {

	
	/**
	 *主键、自增
	 */
	private Integer productId;
	
	/**
	 *商品分类
	 */
	private Integer categoryId;
	
	/**
	 *商品别名
	 */
	private String productAlias;
	
	/**
	 *商品名称
	 */
	private String productName;
	
	/**
	 *商品图片
	 */
	private String productImage;
	
	/**
	 *商品单价
	 */
	private BigDecimal minPayPrice;
	
	/**
	 *商户机构ID
	 */
	private Integer sellerId;
	
	/**
	 *1 上架可用 0 下架 -1 删除
	 */
	private Byte status;
	
	/**
	 *创建用户ID
	 */
	private Long createUserId;
	
	/**
	 *更新时间
	 */
	private Date updateTime;
	
	/**
	 *创建时间
	 */
	private Date createTime;
}