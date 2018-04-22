package com.oaup.admin.backend.support.model.dto.response.product;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
public class ProductDetailResp implements Serializable {
    private ProductTopBannerResp topBanner;
    private List<Object> detail;
}
