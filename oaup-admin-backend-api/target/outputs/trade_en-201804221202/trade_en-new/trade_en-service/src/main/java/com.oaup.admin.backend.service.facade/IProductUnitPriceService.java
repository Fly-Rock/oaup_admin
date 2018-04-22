package com.oaup.admin.backend.service.facade;

import com.oaup.admin.backend.support.model.dto.ProductUnitPriceRequest;
import com.oaup.admin.backend.support.model.dto.ProductUnitPriceResponse;
import java.util.List;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;

public interface IProductUnitPriceService {

	int insert(ProductUnitPriceRequest request);  
    
    int insertBatch(List<ProductUnitPriceRequest> requests);  
      
    ProductUnitPriceResponse load(Long id);  
      
    int update(ProductUnitPriceRequest user);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductUnitPriceRequest> users);  
    
    List<ProductUnitPriceResponse> query(ProductUnitPriceRequest request);
    
    PageResponse<ProductUnitPriceResponse> queryByPage(PageRequest<ProductUnitPriceRequest> paramData);
	
}