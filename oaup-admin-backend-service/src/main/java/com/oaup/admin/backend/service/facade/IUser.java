package com.oaup.admin.backend.service.facade;

import java.util.List;

import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;
import com.oaup.admin.backend.support.model.dto.UserRequest;
import com.oaup.admin.backend.support.model.dto.UserResponse;

public interface IUser {

    int insert(UserRequest user);  
    
    int insertBatch(List<UserRequest> users);  
      
    UserResponse load(String id);  
      
    int update(UserRequest user);  
      
    int delete(String id);  
    
    int deleteBatch(List<UserRequest> users);  
      
    PageResponse<UserResponse> query(PageRequest<UserRequest> paramData);
}
