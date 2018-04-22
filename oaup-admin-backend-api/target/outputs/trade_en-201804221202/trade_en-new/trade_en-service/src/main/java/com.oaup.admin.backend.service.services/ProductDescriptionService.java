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

import com.oaup.admin.backend.dao.ProductDescriptionDao;
import com.oaup.admin.backend.support.model.po.ProductDescription;
import com.oaup.admin.backend.service.facade.IProductDescriptionService;
import com.oaup.admin.backend.support.model.dto.ProductDescriptionRequest;
import com.oaup.admin.backend.support.model.dto.ProductDescriptionResponse;

@Service
public class ProductDescriptionService implements IProductDescriptionService {
	
	@Autowired
	private ProductDescriptionDao productDescriptionDao;
	
	public int insert(ProductDescriptionRequest request){
    	return productDescriptionDao.insert(BeanUtil.copy(request, ProductDescription.class));
    }
    
    public int insertBatch(List<ProductDescriptionRequest> requests){
    	return productDescriptionDao.insertBatch(BeanUtil.copyByList(requests, ProductDescription.class));
    } 
      
    public ProductDescriptionResponse load(Long id){
    	
    	return BeanUtil.copy(productDescriptionDao.load(id), ProductDescriptionResponse.class);
    }
      
    public int update(ProductDescriptionRequest request){
    	return productDescriptionDao.update(BeanUtil.copy(request, ProductDescription.class));
    }  
      
    public int delete(Long id){
    	return productDescriptionDao.delete(id);
    } 
    
    public int deleteBatch(List<ProductDescriptionRequest> requests){
    	return productDescriptionDao.deleteBatch(BeanUtil.copyByList(requests, ProductDescription.class));
    }
      
    public PageResponse<ProductDescriptionResponse> queryByPage(PageRequest<ProductDescriptionRequest> paramData){
		RowBounds rowBounds = new RowBounds(paramData.getPageNum(), paramData.getPageSize());
    	Page<ProductDescription> page = productDescriptionDao.queryByPage(BeanUtil.copy(paramData.getParamData(), ProductDescription.class), rowBounds);
    	PageResponse<ProductDescriptionResponse> pageResponse = new PageResponse<>();
    	pageResponse.getPagination().setTotalCount(page.getTotal());
    	pageResponse.setResultData(BeanUtil.copyByList(page.getResult(), ProductDescriptionResponse.class));
    	pageResponse.getPagination().setTotalCount(page.getTotal());
		pageResponse.getPagination().setPageSize(paramData.getPageSize());
		pageResponse.getPagination().setCurrentPageIndex(paramData.getPageNum());
    	return pageResponse;
    }
    
     public List<ProductDescriptionResponse> query(ProductDescriptionRequest request){
     
     	List<ProductDescription> queryList=productDescriptionDao.query(BeanUtil.copy(request, ProductDescription.class));
     	if(CollectionUtils.isNotEmpty(queryList)){
			return BeanUtil.copyByList(queryList, ProductDescriptionResponse.class);
		}
		
		return new ArrayList<>();
		
    }

}