package com.oaup.admin.backend.test.service;


import com.hujiang.basic.framework.core.config.CoreConfig;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.basic.framework.dao.config.DaoConfig;
import com.oaup.admin.backend.service.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@Import(value= {DaoConfig.class, CoreConfig.class, UserService.class })
public class UserServiceTest {

    @Autowired
	UserService userServer;


	@Test
	public void serviceTest(){
        System.out.println(JsonUtil.object2JSON(userServer.load("1")));


	}
}