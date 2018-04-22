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
import com.oaup.admin.backend.support.model.dto.ProductDescriptionRequest;
import com.oaup.admin.backend.support.model.dto.ProductDescriptionResponse;
import com.hujiang.basic.framework.rest.validation.annotation.HibernateValidatorBased;
import java.util.List;
import com.oaup.admin.backend.service.facade.IProductDescriptionService;

@RestController
@RequestMapping("/ProductDescription")
public class ProductDescriptionController {
	
	@Autowired
	private IProductDescriptionService productDescriptionService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DataResult<ProductDescriptionResponse> getProductDescriptionbyId(@PathVariable Long id) {
	
        return DataResult.ok(0, "success",productDescriptionService.load(id));
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataResult<PageResponse<ProductDescriptionResponse>> productDescriptionByPage(@RequestBody PageRequest<ProductDescriptionRequest> page) {
		return DataResult.ok(0, "success",productDescriptionService.queryByPage(page));
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public DataResult<Object> query(@RequestBody ProductDescriptionRequest request) {
		return DataResult.ok(0, "success",productDescriptionService.query(request));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@HibernateValidatorBased("request")
	public DataResult<Integer> add(@RequestBody ProductDescriptionRequest request) {
		return DataResult.ok(0, "success",productDescriptionService.insert(request));
	}

	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public DataResult<Integer> batchAdd(@RequestBody List<ProductDescriptionRequest> requests) {
	
		return DataResult.ok(0, "success",productDescriptionService.insertBatch(requests));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public DataResult<Integer> update(@RequestBody ProductDescriptionRequest request) {
		return DataResult.ok(0, "success",productDescriptionService.update(request));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public DataResult<Integer> delete(@PathVariable Long id) {
		return DataResult.ok(0, "success",productDescriptionService.delete(id));
	}
	
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public DataResult<Integer> delete(@RequestBody List<ProductDescriptionRequest> requests) {
		return DataResult.ok(0, "success",productDescriptionService.deleteBatch(requests));
	}
}