package com.oaup.admin.backend.test.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
//@Import(value= {DaoConfig.class, CoreConfig.class, UserService.class })
public class UserServiceTest {


	@Test
	public void serviceTest(){


	}
}