package com.oaup.admin.backend.support.model.bo.product;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
public class ProductContentListRowTableBo implements Serializable {

    protected List<ProductTableRowBo> tableRow;
}
