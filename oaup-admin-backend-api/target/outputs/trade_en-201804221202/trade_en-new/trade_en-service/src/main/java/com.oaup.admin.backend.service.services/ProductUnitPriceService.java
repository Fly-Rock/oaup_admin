package com.oaup.admin.backend.service.services;

import java.util.List;
import java.util.ArrayList;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;
import com.github.pagehelper.Page;
import com.hujiang.basic.framework.core.util.BeanUtil;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;

import com.oaup.admin.backend.dao.ProductUnitPriceDao;
import com.oaup.admin.backend.support.model.po.ProductUnitPrice;
import com.oaup.admin.backend.service.facade.IProductUnitPriceService;
import com.oaup.admin.backend.support.model.dto.ProductUnitPriceRequest;
import com.oaup.admin.backend.support.model.dto.ProductUnitPriceResponse;

@Service
public class ProductUnitPriceService implements IProductUnitPriceService {
	
	@Autowired
	private ProductUnitPriceDao productUnitPriceDao;
	
	public int insert(ProductUnitPriceRequest request){
    	return productUnitPriceDao.insert(BeanUtil.copy(request, ProductUnitPrice.class));
    }
    
    public int insertBatch(List<ProductUnitPriceRequest> requests){
    	return productUnitPriceDao.insertBatch(BeanUtil.copyByList(requests, ProductUnitPrice.class));
    } 
      
    public ProductUnitPriceResponse load(Long id){
    	
    	return BeanUtil.copy(productUnitPriceDao.load(id), ProductUnitPriceResponse.class);
    }
      
    public int update(ProductUnitPriceRequest request){
    	return productUnitPriceDao.update(BeanUtil.copy(request, ProductUnitPrice.class));
    }  
      
    public int delete(Long id){
    	return productUnitPriceDao.delete(id);
    } 
    
    public int deleteBatch(List<ProductUnitPriceRequest> requests){
    	return productUnitPriceDao.deleteBatch(BeanUtil.copyByList(requests, ProductUnitPrice.class));
    }
      
    public PageResponse<ProductUnitPriceResponse> queryByPage(PageRequest<ProductUnitPriceRequest> paramData){
		RowBounds rowBounds = new RowBounds(paramData.getPageNum(), paramData.getPageSize());
    	Page<ProductUnitPrice> page = productUnitPriceDao.queryByPage(BeanUtil.copy(paramData.getParamData(), ProductUnitPrice.class), rowBounds);
    	PageResponse<ProductUnitPriceResponse> pageResponse = new PageResponse<>();
    	pageResponse.getPagination().setTotalCount(page.getTotal());
    	pageResponse.setResultData(BeanUtil.copyByList(page.getResult(), ProductUnitPriceResponse.class));
    	pageResponse.getPagination().setTotalCount(page.getTotal());
		pageResponse.getPagination().setPageSize(paramData.getPageSize());
		pageResponse.getPagination().setCurrentPageIndex(paramData.getPageNum());
    	return pageResponse;
    }
    
     public List<ProductUnitPriceResponse> query(ProductUnitPriceRequest request){
     
     	List<ProductUnitPrice> queryList=productUnitPriceDao.query(BeanUtil.copy(request, ProductUnitPrice.class));
     	if(CollectionUtils.isNotEmpty(queryList)){
			return BeanUtil.copyByList(queryList, ProductUnitPriceResponse.class);
		}
		
		return new ArrayList<>();
		
    }

}