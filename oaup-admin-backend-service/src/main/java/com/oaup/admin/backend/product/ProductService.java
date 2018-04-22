package com.oaup.admin.backend.product;

import com.hujiang.basic.framework.core.util.JsonUtil;
import com.oaup.admin.backend.service.facade.IProductService;
import com.oaup.admin.backend.support.model.bo.product.ProductDetailCompositiveBo;
import com.oaup.admin.backend.support.model.bo.product.ProductDetailTableBo;
import com.oaup.admin.backend.support.model.dto.request.product.ProductDetailReq;
import com.oaup.admin.backend.support.model.dto.response.product.ProductDetailResp;
import com.oaup.admin.backend.support.model.po.ProductDescription;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@Service
@Slf4j
public class ProductService implements IProductService {
    @Autowired
    private ProductBiz productBiz;
    @Autowired
    private ProductDescriptionBiz descriptionBiz;

    public ProductDetailResp queryProductDetail(ProductDetailReq req) {
        ProductDetailResp resp = new ProductDetailResp();
        ProductDescription queryProduct = new ProductDescription();
        queryProduct.setProductId(10000);
        List<ProductDescription> productList = descriptionBiz.query(queryProduct);
        if (CollectionUtils.isEmpty(productList)) {
            return resp;
        }
        productList.stream().forEach(m -> {
            if (m.getContentStyle().equals("detail_table")) {
                ProductDetailTableBo detailTableBo = JsonUtil.json2Object(m.getContent(), ProductDetailTableBo.class);
            }
            if(m.getContentStyle().equals("detail_zonghe")){
                ProductDetailCompositiveBo list = JsonUtil.json2Object(m.getContent(), ProductDetailCompositiveBo.class);
            }

        });
        return null;
    }
}

