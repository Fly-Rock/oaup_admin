
package com.oaup.admin.backend.support.model.po;

import java.util.*;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class ProductDescription {

	
	/**
	 *商品id
	 */
	private Integer productId;
	
	/**
	 *商品名称
	 */
	private String content;
	
	/**
	 *更新时间
	 */
	private Date updateTime;
	
	/**
	 *创建时间
	 */
	private Date createTime;

	private String contentStyle;
}