package com.oaup.admin.backend.support.model.bo.product;

import lombok.Data;

import java.util.List;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
/**
 * 图片对象
 */
public class ProductDetailImageBo extends ProductContentBaseBo {
    private List<ProductDetailBannerBo> bannerList;
    private String title;
    private String subtitle;
}
