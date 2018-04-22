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
import com.oaup.admin.backend.support.model.dto.ProductPriceRequest;
import com.oaup.admin.backend.support.model.dto.ProductPriceResponse;
import com.hujiang.basic.framework.rest.validation.annotation.HibernateValidatorBased;
import java.util.List;
import com.oaup.admin.backend.service.facade.IProductPriceService;

@RestController
@RequestMapping("/ProductPrice")
public class ProductPriceController {
	
	@Autowired
	private IProductPriceService productPriceService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DataResult<ProductPriceResponse> getProductPricebyId(@PathVariable Long id) {
	
        return DataResult.ok(0, "success",productPriceService.load(id));
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataResult<PageResponse<ProductPriceResponse>> productPriceByPage(@RequestBody PageRequest<ProductPriceRequest> page) {
		return DataResult.ok(0, "success",productPriceService.queryByPage(page));
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public DataResult<Object> query(@RequestBody ProductPriceRequest request) {
		return DataResult.ok(0, "success",productPriceService.query(request));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@HibernateValidatorBased("request")
	public DataResult<Integer> add(@RequestBody ProductPriceRequest request) {
		return DataResult.ok(0, "success",productPriceService.insert(request));
	}

	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public DataResult<Integer> batchAdd(@RequestBody List<ProductPriceRequest> requests) {
	
		return DataResult.ok(0, "success",productPriceService.insertBatch(requests));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public DataResult<Integer> update(@RequestBody ProductPriceRequest request) {
		return DataResult.ok(0, "success",productPriceService.update(request));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public DataResult<Integer> delete(@PathVariable Long id) {
		return DataResult.ok(0, "success",productPriceService.delete(id));
	}
	
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public DataResult<Integer> delete(@RequestBody List<ProductPriceRequest> requests) {
		return DataResult.ok(0, "success",productPriceService.deleteBatch(requests));
	}
}