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

import com.oaup.admin.backend.dao.ProductDao;
import com.oaup.admin.backend.support.model.po.Product;
import com.oaup.admin.backend.service.facade.IProductService;
import com.oaup.admin.backend.support.model.dto.ProductRequest;
import com.oaup.admin.backend.support.model.dto.ProductResponse;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public int insert(ProductRequest request){
    	return productDao.insert(BeanUtil.copy(request, Product.class));
    }
    
    public int insertBatch(List<ProductRequest> requests){
    	return productDao.insertBatch(BeanUtil.copyByList(requests, Product.class));
    } 
      
    public ProductResponse load(Long id){
    	
    	return BeanUtil.copy(productDao.load(id), ProductResponse.class);
    }
      
    public int update(ProductRequest request){
    	return productDao.update(BeanUtil.copy(request, Product.class));
    }  
      
    public int delete(Long id){
    	return productDao.delete(id);
    } 
    
    public int deleteBatch(List<ProductRequest> requests){
    	return productDao.deleteBatch(BeanUtil.copyByList(requests, Product.class));
    }
      
    public PageResponse<ProductResponse> queryByPage(PageRequest<ProductRequest> paramData){
		RowBounds rowBounds = new RowBounds(paramData.getPageNum(), paramData.getPageSize());
    	Page<Product> page = productDao.queryByPage(BeanUtil.copy(paramData.getParamData(), Product.class), rowBounds);
    	PageResponse<ProductResponse> pageResponse = new PageResponse<>();
    	pageResponse.getPagination().setTotalCount(page.getTotal());
    	pageResponse.setResultData(BeanUtil.copyByList(page.getResult(), ProductResponse.class));
    	pageResponse.getPagination().setTotalCount(page.getTotal());
		pageResponse.getPagination().setPageSize(paramData.getPageSize());
		pageResponse.getPagination().setCurrentPageIndex(paramData.getPageNum());
    	return pageResponse;
    }
    
     public List<ProductResponse> query(ProductRequest request){
     
     	List<Product> queryList=productDao.query(BeanUtil.copy(request, Product.class));
     	if(CollectionUtils.isNotEmpty(queryList)){
			return BeanUtil.copyByList(queryList, ProductResponse.class);
		}
		
		return new ArrayList<>();
		
    }

}