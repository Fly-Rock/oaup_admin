package com.oaup.admin.backend.support.model.bo.product;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
public class ProductDetailBannerBo implements Serializable {
    /**
     * 图片地址
     */
    private String image;
    private String pictureName;
}
