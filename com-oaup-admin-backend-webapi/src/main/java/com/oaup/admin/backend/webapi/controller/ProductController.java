package com.oaup.admin.backend.webapi.controller;

import com.hujiang.basic.framework.rest.model.DataResult;
import com.oaup.admin.backend.service.facade.IProductService;
import com.oaup.admin.backend.support.model.dto.request.product.ProductDetailReq;
import com.oaup.admin.backend.support.model.dto.response.product.ProductDetailResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Slf4j
@Controller
@RequestMapping(value = "/cms/product/")
public class ProductController {
    @Autowired
    private IProductService productService;

    // @SessionVerify(verifyMode = SessionVerifyMode.BOTH, autoBinder = true, allowAnonymous = false)
    public DataResult<ProductDetailResp> queryOrganizationsInfo(@RequestBody ProductDetailReq request) {
        return DataResult.ok(0,"",productService.queryProductDetail(request));
    }

}
