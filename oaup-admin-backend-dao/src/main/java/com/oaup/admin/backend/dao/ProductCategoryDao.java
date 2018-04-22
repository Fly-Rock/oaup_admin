package com.oaup.admin.backend.dao;

import java.util.List;
import com.oaup.admin.backend.support.model.po.ProductCategory;
import org.apache.ibatis.session.RowBounds;
import com.github.pagehelper.Page;
import com.hujiang.basic.framework.dao.annotation.MyBatisRepository;

@MyBatisRepository
public interface ProductCategoryDao {

	int insert(ProductCategory object);  
    
    int insertBatch(List<ProductCategory> objects);  
      
    ProductCategory load(Long id);  
      
    int update(ProductCategory object);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductCategory> objects);  
    
    List<ProductCategory> query(ProductCategory object);
    
    Page<ProductCategory> queryByPage(ProductCategory object, RowBounds rowBounds);
}