package com.oaup.admin.backend.support.model.po.sys;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by yefaquan on 2017/7/4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemPermissionPO {
    private Integer moduleId;
    private String moduleName;
    private String aliasName;
}
