package com.oaup.admin.backend.support.model.dto;

import java.io.Serializable;
import java.util.*;
import lombok.Data;
@Data
public class ProductDescriptionResponse implements Serializable {
	
	
	private Integer productId;
	
	
	private String content;
	
	
	private Date updateTime;
	
	
	private Date createTime;
	
	
}