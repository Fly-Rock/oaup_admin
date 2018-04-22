package com.oaup.admin.backend.dao;

import java.util.List;
import com.oaup.admin.backend.support.model.po.Product;
import org.apache.ibatis.session.RowBounds;
import com.github.pagehelper.Page;
import com.hujiang.basic.framework.dao.annotation.MyBatisRepository;

@MyBatisRepository
public interface ProductDao {

	int insert(Product object);  
    
    int insertBatch(List<Product> objects);  
      
    Product load(Long id);  
      
    int update(Product object);  
      
    int delete(Long id);  
    
    int deleteBatch(List<Product> objects);  
    
    List<Product> query(Product object);
    
    Page<Product> queryByPage(Product object, RowBounds rowBounds);
}