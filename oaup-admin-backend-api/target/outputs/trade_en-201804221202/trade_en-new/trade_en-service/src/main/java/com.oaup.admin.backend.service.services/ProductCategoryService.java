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

import com.oaup.admin.backend.dao.ProductCategoryDao;
import com.oaup.admin.backend.support.model.po.ProductCategory;
import com.oaup.admin.backend.service.facade.IProductCategoryService;
import com.oaup.admin.backend.support.model.dto.ProductCategoryRequest;
import com.oaup.admin.backend.support.model.dto.ProductCategoryResponse;

@Service
public class ProductCategoryService implements IProductCategoryService {
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	public int insert(ProductCategoryRequest request){
    	return productCategoryDao.insert(BeanUtil.copy(request, ProductCategory.class));
    }
    
    public int insertBatch(List<ProductCategoryRequest> requests){
    	return productCategoryDao.insertBatch(BeanUtil.copyByList(requests, ProductCategory.class));
    } 
      
    public ProductCategoryResponse load(Long id){
    	
    	return BeanUtil.copy(productCategoryDao.load(id), ProductCategoryResponse.class);
    }
      
    public int update(ProductCategoryRequest request){
    	return productCategoryDao.update(BeanUtil.copy(request, ProductCategory.class));
    }  
      
    public int delete(Long id){
    	return productCategoryDao.delete(id);
    } 
    
    public int deleteBatch(List<ProductCategoryRequest> requests){
    	return productCategoryDao.deleteBatch(BeanUtil.copyByList(requests, ProductCategory.class));
    }
      
    public PageResponse<ProductCategoryResponse> queryByPage(PageRequest<ProductCategoryRequest> paramData){
		RowBounds rowBounds = new RowBounds(paramData.getPageNum(), paramData.getPageSize());
    	Page<ProductCategory> page = productCategoryDao.queryByPage(BeanUtil.copy(paramData.getParamData(), ProductCategory.class), rowBounds);
    	PageResponse<ProductCategoryResponse> pageResponse = new PageResponse<>();
    	pageResponse.getPagination().setTotalCount(page.getTotal());
    	pageResponse.setResultData(BeanUtil.copyByList(page.getResult(), ProductCategoryResponse.class));
    	pageResponse.getPagination().setTotalCount(page.getTotal());
		pageResponse.getPagination().setPageSize(paramData.getPageSize());
		pageResponse.getPagination().setCurrentPageIndex(paramData.getPageNum());
    	return pageResponse;
    }
    
     public List<ProductCategoryResponse> query(ProductCategoryRequest request){
     
     	List<ProductCategory> queryList=productCategoryDao.query(BeanUtil.copy(request, ProductCategory.class));
     	if(CollectionUtils.isNotEmpty(queryList)){
			return BeanUtil.copyByList(queryList, ProductCategoryResponse.class);
		}
		
		return new ArrayList<>();
		
    }

}