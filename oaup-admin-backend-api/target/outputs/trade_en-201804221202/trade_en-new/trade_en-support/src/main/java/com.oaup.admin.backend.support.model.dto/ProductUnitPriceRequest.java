package com.oaup.admin.backend.support.model.dto;

import java.io.Serializable;
import java.util.*;
import lombok.Data;
@Data
public class ProductUnitPriceRequest implements Serializable{
	
	
	
	private Integer unitPriceId;
	
	
	private String unitName;
	
	
	private Byte status;
	
	
	private Long createUserId;
	
	
	private Date updateTime;
	
	
	private Date createTime;
	
}