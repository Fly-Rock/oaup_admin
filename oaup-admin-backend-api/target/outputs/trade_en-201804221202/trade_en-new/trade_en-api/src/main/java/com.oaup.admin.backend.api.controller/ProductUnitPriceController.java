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
import com.oaup.admin.backend.support.model.dto.ProductUnitPriceRequest;
import com.oaup.admin.backend.support.model.dto.ProductUnitPriceResponse;
import com.hujiang.basic.framework.rest.validation.annotation.HibernateValidatorBased;
import java.util.List;
import com.oaup.admin.backend.service.facade.IProductUnitPriceService;

@RestController
@RequestMapping("/ProductUnitPrice")
public class ProductUnitPriceController {
	
	@Autowired
	private IProductUnitPriceService productUnitPriceService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DataResult<ProductUnitPriceResponse> getProductUnitPricebyId(@PathVariable Long id) {
	
        return DataResult.ok(0, "success",productUnitPriceService.load(id));
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	public DataResult<PageResponse<ProductUnitPriceResponse>> productUnitPriceByPage(@RequestBody PageRequest<ProductUnitPriceRequest> page) {
		return DataResult.ok(0, "success",productUnitPriceService.queryByPage(page));
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public DataResult<Object> query(@RequestBody ProductUnitPriceRequest request) {
		return DataResult.ok(0, "success",productUnitPriceService.query(request));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@HibernateValidatorBased("request")
	public DataResult<Integer> add(@RequestBody ProductUnitPriceRequest request) {
		return DataResult.ok(0, "success",productUnitPriceService.insert(request));
	}

	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public DataResult<Integer> batchAdd(@RequestBody List<ProductUnitPriceRequest> requests) {
	
		return DataResult.ok(0, "success",productUnitPriceService.insertBatch(requests));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public DataResult<Integer> update(@RequestBody ProductUnitPriceRequest request) {
		return DataResult.ok(0, "success",productUnitPriceService.update(request));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public DataResult<Integer> delete(@PathVariable Long id) {
		return DataResult.ok(0, "success",productUnitPriceService.delete(id));
	}
	
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public DataResult<Integer> delete(@RequestBody List<ProductUnitPriceRequest> requests) {
		return DataResult.ok(0, "success",productUnitPriceService.deleteBatch(requests));
	}
}