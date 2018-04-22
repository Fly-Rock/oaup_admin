package com.oaup.admin.backend.support.model.dto;

import java.io.Serializable;
import java.util.*;
import lombok.Data;
@Data
public class ProductPriceResponse implements Serializable {
	
	
	private Integer productPriceId;
	
	
	private Integer productId;
	
	
	private BigDecimal payPrice;
	
	
	private BigDecimal marketPrice;
	
	
	private BigDecimal costPrice;
	
	
	private BigDecimal unitPriceId;
	
	
	private Byte status;
	
	
	private Long createUserId;
	
	
	private Date updateTime;
	
	
	private Date createTime;
	
	
}