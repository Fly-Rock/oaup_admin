package com.oaup.admin.backend.support.model.bo.product;

import lombok.Data;

import java.util.List;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
public class ProductDetailTableBo extends ProductContentBaseBo {

    private String tableStyle;
    private List<ProductContentListRowTableBo> contentListRowTable;
}
