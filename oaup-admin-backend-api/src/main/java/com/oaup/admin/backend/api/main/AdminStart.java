package com.oaup.admin.backend.api.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hujiang.basic.framework.rest.config.service.HJundertow;
import com.hujiang.basic.framework.rest.main.HJApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages="com.oaup.admin.backend")
@HJundertow
@Slf4j
@EnableAsync
public class AdminStart {

    public static void main(String[] args) {
    	HJApplication.start(AdminStart.class, args);
    }

}
