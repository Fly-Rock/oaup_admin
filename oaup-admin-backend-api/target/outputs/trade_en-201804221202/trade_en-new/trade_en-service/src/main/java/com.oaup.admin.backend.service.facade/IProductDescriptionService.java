package com.oaup.admin.backend.service.facade;

import com.oaup.admin.backend.support.model.dto.ProductDescriptionRequest;
import com.oaup.admin.backend.support.model.dto.ProductDescriptionResponse;
import java.util.List;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;

public interface IProductDescriptionService {

	int insert(ProductDescriptionRequest request);  
    
    int insertBatch(List<ProductDescriptionRequest> requests);  
      
    ProductDescriptionResponse load(Long id);  
      
    int update(ProductDescriptionRequest user);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductDescriptionRequest> users);  
    
    List<ProductDescriptionResponse> query(ProductDescriptionRequest request);
    
    PageResponse<ProductDescriptionResponse> queryByPage(PageRequest<ProductDescriptionRequest> paramData);
	
}