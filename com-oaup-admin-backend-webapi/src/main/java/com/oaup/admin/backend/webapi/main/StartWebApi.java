package com.oaup.admin.backend.webapi.main;

import com.hujiang.basic.framework.rest.main.HJApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by lichuanjie on 2018/4/22.
 */
@SpringBootApplication(scanBasePackages = "com.oaup.admin")
@EnableConfigurationProperties
public class StartWebApi {
    public static void main(String[] args) {
        HJApplication.start(StartWebApi.class, args);
    }
}
