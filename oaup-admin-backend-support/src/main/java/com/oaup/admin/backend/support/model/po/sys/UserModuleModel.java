package com.oaup.admin.backend.support.model.po.sys;

import lombok.Data;

/**
 * Created by lipingfei on 2017/10/16.
 */
@Data
public class UserModuleModel {
    private int permissionType;
    private int isBlock;
    private int moduleId;
    private int parentId;
    private String moduleName;
    private String path;
}
