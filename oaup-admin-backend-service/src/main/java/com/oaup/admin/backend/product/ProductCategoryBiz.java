package com.oaup.admin.backend.product;

import com.hujiang.basic.framework.core.util.BeanUtil;
import com.oaup.admin.backend.dao.ProductCategoryDao;
import com.oaup.admin.backend.support.model.po.ProductCategory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryBiz {
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	public int insert(ProductCategory request){
    	return productCategoryDao.insert(request);
    }
    
    public int insertBatch(List<ProductCategory> requests){
    	return productCategoryDao.insertBatch(requests);
    } 
      
    public ProductCategory load(Long id){
    	
    	return productCategoryDao.load(id);
    }
      
    public int update(ProductCategory request){
    	return productCategoryDao.update(request);
    }  
      
    public int delete(Long id){
    	return productCategoryDao.delete(id);
    } 
    
    public int deleteBatch(List<ProductCategory> requests){
    	return productCategoryDao.deleteBatch(requests);
    }

     public List<ProductCategory> query(ProductCategory request){
     
     	List<ProductCategory> queryList=productCategoryDao.query(BeanUtil.copy(request, ProductCategory.class));
     	if(CollectionUtils.isNotEmpty(queryList)){
			return queryList;
		}
		
		return new ArrayList<>();
		
    }

}