package com.oaup.admin.backend.dao;

import java.util.List;
import com.oaup.admin.backend.support.model.po.ProductPrice;
import org.apache.ibatis.session.RowBounds;
import com.github.pagehelper.Page;
import com.hujiang.basic.framework.dao.annotation.MyBatisRepository;

@MyBatisRepository
public interface ProductPriceDao {

	int insert(ProductPrice object);  
    
    int insertBatch(List<ProductPrice> objects);  
      
    ProductPrice load(Long id);  
      
    int update(ProductPrice object);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductPrice> objects);  
    
    List<ProductPrice> query(ProductPrice object);
    
    Page<ProductPrice> queryByPage(ProductPrice object, RowBounds rowBounds);
}