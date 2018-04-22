
package com.oaup.admin.backend.support.model.po;

import java.util.*;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class ProductPrice {

	
	/**
	 *主键、自增
	 */
	private Integer productPriceId;
	
	/**
	 *商品ID
	 */
	private Integer productId;
	
	/**
	 *商品单价
	 */
	private BigDecimal payPrice;
	
	/**
	 *市场价格
	 */
	private BigDecimal marketPrice;
	
	/**
	 *进货价格
	 */
	private BigDecimal costPrice;
	
	/**
	 *价格单位ID
	 */
	private BigDecimal unitPriceId;
	
	/**
	 *1 可用 0  删除
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