package com.oaup.admin.backend.Permission;


import com.oaup.admin.backend.service.facade.ISystemPermissionService;
import com.oaup.admin.backend.support.model.dto.UserIdRequest;
import com.oaup.admin.backend.support.model.po.sys.SystemPermissionPO;
import com.oaup.admin.backend.support.model.po.sys.UserModuleModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemPermissionService implements ISystemPermissionService {


    @Override
    public boolean checkSystemPermission(Integer userId, String alias, String subAlias) {
        return true;
    }

    @Override
    public boolean checkSuperAdmin(Integer userId) {
        return true;
    }

    @Override
    public List<SystemPermissionPO> getSystemPermissionByModule(Integer userId, String alias) {
       return new ArrayList<>();
    }

    public List<UserModuleModel> getUserModuleModelList(UserIdRequest request) {
        List<UserModuleModel> userModuleModelList = new ArrayList<>();
        return userModuleModelList;
    }


}
