package com.oaup.admin.backend.dao;

import java.util.List;
import com.oaup.admin.backend.support.model.po.ProductDescription;
import org.apache.ibatis.session.RowBounds;
import com.github.pagehelper.Page;
import com.hujiang.basic.framework.dao.annotation.MyBatisRepository;

@MyBatisRepository
public interface ProductDescriptionDao {

	int insert(ProductDescription object);  
    
    int insertBatch(List<ProductDescription> objects);  
      
    ProductDescription load(Long id);  
      
    int update(ProductDescription object);  
      
    int delete(Long id);  
    
    int deleteBatch(List<ProductDescription> objects);  
    
    List<ProductDescription> query(ProductDescription object);
    
    Page<ProductDescription> queryByPage(ProductDescription object, RowBounds rowBounds);
}