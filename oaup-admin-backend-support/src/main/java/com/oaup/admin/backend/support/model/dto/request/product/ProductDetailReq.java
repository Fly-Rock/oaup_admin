package com.oaup.admin.backend.support.model.dto.request.product;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Data
public class ProductDetailReq implements Serializable {
    private String city;
    private Integer productId;
}
