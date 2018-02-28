package com.oaup.admin.backend.support.model.bo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bin
 *
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID自动生成策略
     */

    protected String id;


    /**
     * 版本号
     */
    protected Integer version;

    /**
     * 创建时间
     */

    protected Date createDateTime;

    /**
     * 最后修改时间
     */

    protected Date updateDateTime;

    /**
     * 删除标记(0启用，1禁用)
     */
    private Integer deleted;

    public Integer getVersion() {

        return version;
    }

    public void setVersion(Integer version) {

        this.version = version;
    }

    public Date getCreateDateTime() {

        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {

        this.createDateTime = createDateTime;
    }

    public Date getUpdateDateTime() {

        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {

        this.updateDateTime = updateDateTime;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public Integer getDeleted() {

        return deleted == null ? 0 : deleted;
    }

    public void setDeleted(Integer deleted) {
        deleted=deleted==null?0:deleted;
        this.deleted = deleted;
    }

}
