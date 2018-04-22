
package com.oaup.admin.backend.support.model.po;

import java.util.*;
import java.math.BigDecimal;
import lombok.Data;
@Data
public class ProductCategory {

	
	/**
	 *主键、自增
	 */
	private Integer categoryId;
	
	/**
	 *商品分类名称
	 */
	private String categoryName;
	
	/**
	 *别名
	 */
	private String categoryAlias;
	
	/**
	 *排序
	 */
	private Integer sort;
	
	/**
	 *父节点id
	 */
	private Integer parentId;
	
	/**
	 *1 可用 0 不可用 -1 删除
	 */
	private Byte status;
	
	/**
	 *更新时间
	 */
	private Date updateTime;
	
	/**
	 *创建时间
	 */
	private Date createTime;
}