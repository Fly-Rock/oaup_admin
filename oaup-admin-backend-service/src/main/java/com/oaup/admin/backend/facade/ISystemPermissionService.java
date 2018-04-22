package com.oaup.admin.backend.facade;

import com.oaup.admin.backend.support.model.dto.UserIdRequest;
import com.oaup.admin.backend.support.model.po.sys.SystemPermissionPO;
import com.oaup.admin.backend.support.model.po.sys.UserModuleModel;

import java.util.List;

public interface ISystemPermissionService {
    boolean checkSystemPermission(Integer userId, String alias, String subAlias);

    boolean checkSuperAdmin(Integer userId);

    List<SystemPermissionPO> getSystemPermissionByModule(Integer userId, String alias);

    List<UserModuleModel> getUserModuleModelList(UserIdRequest userId);
}
