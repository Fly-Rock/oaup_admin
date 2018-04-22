package com.oaup.admin.backend.support.model.dto;

import java.io.Serializable;
import java.util.*;
import lombok.Data;
@Data
public class ProductCategoryRequest implements Serializable{
	
	
	
	private Integer categoryId;
	
	
	private String categoryName;
	
	
	private String categoryAlias;
	
	
	private Integer sort;
	
	
	private Integer parentId;
	
	
	private Byte status;
	
	
	private Date updateTime;
	
	
	private Date createTime;
	
}