package com.oaup.admin.backend.dao;

import java.util.List;
import com.oaup.admin.backend.support.model.po.ProductUnitPrice;
import org.apache.ibatis.session.RowBounds;
import com.github.pagehelper.Page;
import com.hujiang.basic.framework.dao.annotation.MyBatisRepository;

@MyBatisRepository
public interface ProductUnitPriceDao {

	int insert(ProductUnitPrice object);  
    
    int insertBatch(List<ProductUnitPrice> objects);  
      
    ProductUnitPrice load(Long id);  
      
    int update(ProductUnitPrice object);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductUnitPrice> objects);  
    
    List<ProductUnitPrice> query(ProductUnitPrice object);
    
    Page<ProductUnitPrice> queryByPage(ProductUnitPrice object, RowBounds rowBounds);
}