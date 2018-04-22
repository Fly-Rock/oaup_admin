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

import com.oaup.admin.backend.dao.ProductPriceDao;
import com.oaup.admin.backend.support.model.po.ProductPrice;
import com.oaup.admin.backend.service.facade.IProductPriceService;
import com.oaup.admin.backend.support.model.dto.ProductPriceRequest;
import com.oaup.admin.backend.support.model.dto.ProductPriceResponse;

@Service
public class ProductPriceService implements IProductPriceService {
	
	@Autowired
	private ProductPriceDao productPriceDao;
	
	public int insert(ProductPriceRequest request){
    	return productPriceDao.insert(BeanUtil.copy(request, ProductPrice.class));
    }
    
    public int insertBatch(List<ProductPriceRequest> requests){
    	return productPriceDao.insertBatch(BeanUtil.copyByList(requests, ProductPrice.class));
    } 
      
    public ProductPriceResponse load(Long id){
    	
    	return BeanUtil.copy(productPriceDao.load(id), ProductPriceResponse.class);
    }
      
    public int update(ProductPriceRequest request){
    	return productPriceDao.update(BeanUtil.copy(request, ProductPrice.class));
    }  
      
    public int delete(Long id){
    	return productPriceDao.delete(id);
    } 
    
    public int deleteBatch(List<ProductPriceRequest> requests){
    	return productPriceDao.deleteBatch(BeanUtil.copyByList(requests, ProductPrice.class));
    }
      
    public PageResponse<ProductPriceResponse> queryByPage(PageRequest<ProductPriceRequest> paramData){
		RowBounds rowBounds = new RowBounds(paramData.getPageNum(), paramData.getPageSize());
    	Page<ProductPrice> page = productPriceDao.queryByPage(BeanUtil.copy(paramData.getParamData(), ProductPrice.class), rowBounds);
    	PageResponse<ProductPriceResponse> pageResponse = new PageResponse<>();
    	pageResponse.getPagination().setTotalCount(page.getTotal());
    	pageResponse.setResultData(BeanUtil.copyByList(page.getResult(), ProductPriceResponse.class));
    	pageResponse.getPagination().setTotalCount(page.getTotal());
		pageResponse.getPagination().setPageSize(paramData.getPageSize());
		pageResponse.getPagination().setCurrentPageIndex(paramData.getPageNum());
    	return pageResponse;
    }
    
     public List<ProductPriceResponse> query(ProductPriceRequest request){
     
     	List<ProductPrice> queryList=productPriceDao.query(BeanUtil.copy(request, ProductPrice.class));
     	if(CollectionUtils.isNotEmpty(queryList)){
			return BeanUtil.copyByList(queryList, ProductPriceResponse.class);
		}
		
		return new ArrayList<>();
		
    }

}