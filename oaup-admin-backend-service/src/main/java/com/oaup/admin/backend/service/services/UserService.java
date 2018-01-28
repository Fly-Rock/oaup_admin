package com.oaup.admin.backend.service.services;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.hujiang.basic.framework.core.util.BeanUtil;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;
import com.oaup.admin.backend.dao.UserDao;
import com.oaup.admin.backend.service.facade.IUser;
import com.oaup.admin.backend.support.model.dto.UserRequest;
import com.oaup.admin.backend.support.model.dto.UserResponse;
import com.oaup.admin.backend.support.model.po.User;

@Service
public class UserService implements IUser{

	@Autowired
	UserDao userDao;
	
    public int insert(UserRequest user){
    	return userDao.insert(BeanUtil.copy(user, User.class));
    }
    
    public int insertBatch(List<UserRequest> users){
    	return userDao.insertBatch(BeanUtil.copyByList(users, User.class));
    } 
      
    public UserResponse load(String id){
    	return BeanUtil.copy(userDao.load(id), UserResponse.class);
    }
      
    public int update(UserRequest user){
    	return userDao.update(BeanUtil.copy(user, User.class));
    }  
      
    public int delete(String id){
    	return userDao.delete(id);
    } 
    
    public int deleteBatch(List<UserRequest> users){
    	return userDao.deleteBatch(BeanUtil.copyByList(users, User.class));
    }
      
    public PageResponse<UserResponse> query(PageRequest<UserRequest> paramData){
		RowBounds rowBounds = new RowBounds(paramData.getPageNum(), paramData.getPageSize());
    	Page<User> page = userDao.query(BeanUtil.copy(paramData.getParamData(), User.class), rowBounds);
    	PageResponse<UserResponse> pageResponse = new PageResponse<>();
        pageResponse.getPagination().setTotalCount(page.getTotal());
    	pageResponse.setResultData(BeanUtil.copyByList(page.getResult(), UserResponse.class));
    	return pageResponse;
    }
}
