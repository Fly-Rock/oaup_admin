package com.oaup.admin.backend.api.controller;

import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;
import com.hujiang.basic.framework.rest.model.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.oaup.admin.backend.support.model.dto.ProductCategoryRequest;
import com.oaup.admin.backend.support.model.dto.ProductCategoryResponse;
import com.hujiang.basic.framework.rest.validation.annotation.HibernateValidatorBased;
import java.util.List;
import com.oaup.admin.backend.service.facade.IProductCategoryService;

@RestController
@RequestMapping("/ProductCategory")
public class ProductCategoryController {
	
	@Autowired
	private IProductCategoryService productCategoryService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DataResult<ProductCategoryResponse> getProductCategorybyId(@PathVariable Long id) {
	
        return DataResult.ok(0, "success",productCategoryService.load(id));
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataResult<PageResponse<ProductCategoryResponse>> productCategoryByPage(@RequestBody PageRequest<ProductCategoryRequest> page) {
		return DataResult.ok(0, "success",productCategoryService.queryByPage(page));
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public DataResult<Object> query(@RequestBody ProductCategoryRequest request) {
		return DataResult.ok(0, "success",productCategoryService.query(request));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@HibernateValidatorBased("request")
	public DataResult<Integer> add(@RequestBody ProductCategoryRequest request) {
		return DataResult.ok(0, "success",productCategoryService.insert(request));
	}

	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public DataResult<Integer> batchAdd(@RequestBody List<ProductCategoryRequest> requests) {
	
		return DataResult.ok(0, "success",productCategoryService.insertBatch(requests));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public DataResult<Integer> update(@RequestBody ProductCategoryRequest request) {
		return DataResult.ok(0, "success",productCategoryService.update(request));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public DataResult<Integer> delete(@PathVariable Long id) {
		return DataResult.ok(0, "success",productCategoryService.delete(id));
	}
	
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public DataResult<Integer> delete(@RequestBody List<ProductCategoryRequest> requests) {
		return DataResult.ok(0, "success",productCategoryService.deleteBatch(requests));
	}
}