package com.oaup.admin.backend.support.model.bo.product;

import lombok.Data;

import java.util.List;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
/**
 * 温馨提示：
 */
public class ProductDetailReminderBo extends ProductContentBaseBo {
    private String title;
    private List<ProductContentBo> contentList;
}
