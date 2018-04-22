package com.oaup.admin.backend.service.facade;

import com.oaup.admin.backend.support.model.dto.ProductRequest;
import com.oaup.admin.backend.support.model.dto.ProductResponse;
import java.util.List;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;

public interface IProductService {

	int insert(ProductRequest request);  
    
    int insertBatch(List<ProductRequest> requests);  
      
    ProductResponse load(Long id);  
      
    int update(ProductRequest user);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductRequest> users);  
    
    List<ProductResponse> query(ProductRequest request);
    
    PageResponse<ProductResponse> queryByPage(PageRequest<ProductRequest> paramData);
	
}