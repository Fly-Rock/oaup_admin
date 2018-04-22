package com.oaup.admin.backend.product;

import com.hujiang.basic.framework.core.util.BeanUtil;
import com.oaup.admin.backend.dao.ProductPriceDao;
import com.oaup.admin.backend.support.model.po.ProductPrice;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductPriceBiz  {
	
	@Autowired
	private ProductPriceDao productPriceDao;
	
	public int insert(ProductPrice request){
    	return productPriceDao.insert(request);
    }
    
    public int insertBatch(List<ProductPrice> requests){
    	return productPriceDao.insertBatch(requests);
    } 
      
    public ProductPrice load(Long id){
    	
    	return productPriceDao.load(id);
    }
      
    public int update(ProductPrice request){
    	return productPriceDao.update(request);
    }  
      
    public int delete(Long id){
    	return productPriceDao.delete(id);
    } 
    

    
     public List<ProductPrice> query(ProductPrice request){
     
     	List<ProductPrice> queryList=productPriceDao.query(BeanUtil.copy(request, ProductPrice.class));
     	if(CollectionUtils.isNotEmpty(queryList)){
			return queryList;
		}
		
		return new ArrayList<>();
		
    }

}