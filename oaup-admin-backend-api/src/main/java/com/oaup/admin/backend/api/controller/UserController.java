package com.oaup.admin.backend.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.basic.framework.dao.model.PageRequest;
import com.hujiang.basic.framework.dao.model.PageResponse;
import com.hujiang.basic.framework.rest.model.DataResult;
import com.hujiang.basic.framework.rest.validation.annotation.HibernateValidatorBased;
import com.oaup.admin.backend.service.facade.IUser;
import com.oaup.admin.backend.support.model.dto.UserRequest;
import com.oaup.admin.backend.support.model.dto.UserResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	IUser userService;
	
	@ApiOperation(value = "添加用户", httpMethod = "GET", response = DataResult.class, notes = "add user")
	@RequestMapping(value = "/{userid}", method = RequestMethod.GET)
	public DataResult<UserResponse> getUserbyid(@PathVariable String userid) {
        return new DataResult<UserResponse>(userService.load(userid));
	}

	@RequestMapping(value = "/usersByPage", method = RequestMethod.POST)
	public DataResult<PageResponse<UserResponse>> usersByPage(@RequestBody PageRequest<UserRequest> page) {
		return new DataResult<PageResponse<UserResponse>>(userService.query(page));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@HibernateValidatorBased("user")
	public DataResult<Integer> add(@RequestBody UserRequest user) {
		return new DataResult<Integer>(userService.insert(user));
	}

	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public DataResult<Integer> batchAdd(@RequestBody List<UserRequest> users) {
		DataResult<Integer> dr = new DataResult<Integer>(userService.insertBatch(users));
		System.out.println(JsonUtil.object2JSON(dr));
		return dr;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public DataResult<Integer> update(@RequestBody UserRequest user) {
		return new DataResult<Integer>(userService.update(user));
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public DataResult<Integer> delete(@PathVariable String userId) {
		return new DataResult<Integer>(userService.delete(userId));
	}
	
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public DataResult<Integer> delete(@RequestBody List<UserRequest> users) {
		return new DataResult<Integer>(userService.deleteBatch(users));
	}
 

}