package com.oaup.admin.backend.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.Page;
import com.hujiang.basic.framework.dao.annotation.MyBatisRepository;
import com.oaup.admin.backend.support.model.po.User;

@MyBatisRepository  
public interface UserDao {  
      
    int insert(User user);  
    
    int insertBatch(List<User> users);  
      
    User load(String id);  
      
    int update(User user);  
      
    int delete(String id);  
    
    int deleteBatch(List<User> users);  
      
    Page<User> query(User user, RowBounds rowBounds);
    
    
      
}  