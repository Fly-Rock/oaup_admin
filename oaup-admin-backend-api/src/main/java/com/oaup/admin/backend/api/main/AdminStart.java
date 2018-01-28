package com.oaup.admin.backend.api.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hujiang.basic.framework.rest.config.service.HJundertow;
import com.hujiang.basic.framework.rest.main.HJApplication;

@SpringBootApplication(scanBasePackages="com.oaup.admin.backend")
@HJundertow
public class AdminStart {

    public static void main(String[] args) {
    	HJApplication.start(AdminStart.class, args);
    }

}
