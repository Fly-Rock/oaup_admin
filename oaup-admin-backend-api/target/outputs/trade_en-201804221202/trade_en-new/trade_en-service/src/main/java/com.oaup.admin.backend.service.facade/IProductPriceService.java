package com.oaup.admin.backend.service.facade;

import com.oaup.admin.backend.support.model.dto.ProductPriceRequest;
import com.oaup.admin.backend.support.model.dto.ProductPriceResponse;
import java.util.List;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;

public interface IProductPriceService {

	int insert(ProductPriceRequest request);  
    
    int insertBatch(List<ProductPriceRequest> requests);  
      
    ProductPriceResponse load(Long id);  
      
    int update(ProductPriceRequest user);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductPriceRequest> users);  
    
    List<ProductPriceResponse> query(ProductPriceRequest request);
    
    PageResponse<ProductPriceResponse> queryByPage(PageRequest<ProductPriceRequest> paramData);
	
}