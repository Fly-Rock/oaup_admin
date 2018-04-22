package com.oaup.admin.backend.product;

import com.hujiang.basic.framework.core.util.BeanUtil;
import com.oaup.admin.backend.dao.ProductUnitPriceDao;
import com.oaup.admin.backend.support.model.po.ProductUnitPrice;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductUnitPriceBiz {

    @Autowired
    private ProductUnitPriceDao productUnitPriceDao;

    public int insert(ProductUnitPrice request) {
        return productUnitPriceDao.insert(request);
    }

    public int insertBatch(List<ProductUnitPrice> requests) {
        return productUnitPriceDao.insertBatch(requests);
    }

    public ProductUnitPrice load(Long id) {

        return productUnitPriceDao.load(id);
    }

    public int update(ProductUnitPrice request) {
        return productUnitPriceDao.update(request);
    }

    public int delete(Long id) {
        return productUnitPriceDao.delete(id);
    }

    public int deleteBatch(List<ProductUnitPrice> requests) {
        return productUnitPriceDao.deleteBatch(requests);
    }


    public List<ProductUnitPrice> query(ProductUnitPrice request) {

        List<ProductUnitPrice> queryList = productUnitPriceDao.query(BeanUtil.copy(request, ProductUnitPrice.class));
        if (CollectionUtils.isNotEmpty(queryList)) {
            return queryList;
        }

        return new ArrayList<>();

    }

}