package com.oaup.admin.backend.support.model.dto;

import java.io.Serializable;
import java.util.*;
import lombok.Data;
@Data
public class ProductRequest implements Serializable{
	
	
	
	private Integer productId;
	
	
	private Integer categoryId;
	
	
	private String productAlias;
	
	
	private String productName;
	
	
	private String productImage;
	
	
	private BigDecimal minPayPrice;
	
	
	private Integer sellerId;
	
	
	private Byte status;
	
	
	private Long createUserId;
	
	
	private Date updateTime;
	
	
	private Date createTime;
	
}