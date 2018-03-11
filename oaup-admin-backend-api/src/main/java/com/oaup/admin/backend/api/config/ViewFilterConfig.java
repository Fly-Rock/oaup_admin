package com.oaup.admin.backend.api.config;

import com.hujiang.basic.framework.rest.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzhenhua on 2017/7/17.
 */
@Configuration
public class ViewFilterConfig {
    public ViewFilterConfig() {
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new RequestFilter());
        filterRegistrationBean.addUrlPatterns(new String[]{"/*"});
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,*.eot,*.woff,*.ttf,*.svg,/druid/*");
        return filterRegistrationBean;
    }
}
