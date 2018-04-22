package com.oaup.admin.backend.product;

import com.hujiang.basic.framework.core.util.BeanUtil;
import com.oaup.admin.backend.dao.ProductDao;
import com.oaup.admin.backend.support.model.po.Product;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBiz {

    @Autowired
    private ProductDao productDao;

    public int insert(Product request) {
        return productDao.insert(BeanUtil.copy(request, Product.class));
    }

    public int insertBatch(List<Product> requests) {
        return productDao.insertBatch(BeanUtil.copyByList(requests, Product.class));
    }

    public Product load(Long id) {

        return productDao.load(id);
    }

    public int update(Product request) {
        return productDao.update(request);
    }

    public int delete(Long id) {
        return productDao.delete(id);
    }

    public int deleteBatch(List<Product> requests) {
        return productDao.deleteBatch(requests);
    }


    public List<Product> query(Product request) {

        List<Product> queryList = productDao.query(BeanUtil.copy(request, Product.class));
        if (CollectionUtils.isNotEmpty(queryList)) {
            return queryList;
        }

        return new ArrayList<>();

    }

}