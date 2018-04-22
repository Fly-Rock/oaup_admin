package com.oaup.admin.backend.service.facade;

import com.oaup.admin.backend.support.model.dto.ProductCategoryRequest;
import com.oaup.admin.backend.support.model.dto.ProductCategoryResponse;
import java.util.List;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;

public interface IProductCategoryService {

	int insert(ProductCategoryRequest request);  
    
    int insertBatch(List<ProductCategoryRequest> requests);  
      
    ProductCategoryResponse load(Long id);  
      
    int update(ProductCategoryRequest user);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductCategoryRequest> users);  
    
    List<ProductCategoryResponse> query(ProductCategoryRequest request);
    
    PageResponse<ProductCategoryResponse> queryByPage(PageRequest<ProductCategoryRequest> paramData);
	
}