package com.oaup.admin.backend.support.model.bo.product;

import lombok.Data;

import java.util.List;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
/**
 *综合类
 */
public class ProductDetailCompositiveBo extends ProductContentBaseBo {
    private String title;
    private String subtitle;
    private List<Object> contentListBlocks;
}
