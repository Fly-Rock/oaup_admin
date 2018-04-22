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
import com.oaup.admin.backend.support.model.dto.ProductRequest;
import com.oaup.admin.backend.support.model.dto.ProductResponse;
import com.hujiang.basic.framework.rest.validation.annotation.HibernateValidatorBased;
import java.util.List;
import com.oaup.admin.backend.service.facade.IProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DataResult<ProductResponse> getProductbyId(@PathVariable Long id) {
	
        return DataResult.ok(0, "success",productService.load(id));
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataResult<PageResponse<ProductResponse>> productByPage(@RequestBody PageRequest<ProductRequest> page) {
		return DataResult.ok(0, "success",productService.queryByPage(page));
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public DataResult<Object> query(@RequestBody ProductRequest request) {
		return DataResult.ok(0, "success",productService.query(request));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@HibernateValidatorBased("request")
	public DataResult<Integer> add(@RequestBody ProductRequest request) {
		return DataResult.ok(0, "success",productService.insert(request));
	}

	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public DataResult<Integer> batchAdd(@RequestBody List<ProductRequest> requests) {
	
		return DataResult.ok(0, "success",productService.insertBatch(requests));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public DataResult<Integer> update(@RequestBody ProductRequest request) {
		return DataResult.ok(0, "success",productService.update(request));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public DataResult<Integer> delete(@PathVariable Long id) {
		return DataResult.ok(0, "success",productService.delete(id));
	}
	
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public DataResult<Integer> delete(@RequestBody List<ProductRequest> requests) {
		return DataResult.ok(0, "success",productService.deleteBatch(requests));
	}
}