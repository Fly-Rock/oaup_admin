package com.oaup.admin.backend.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Created by caiqing on 17/11/24.
 */
@Configuration
@EnableAsync
public class ThreadConfig {
    /** Set the ThreadPoolExecutor's core pool size. */
    private int corePoolSize = 5;
    /** Set the ThreadPoolExecutor's maximum pool size. */
    private int maxPoolSize = 15;
    /** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
    private int queueCapacity = Integer.MAX_VALUE;
    
    @Bean(name = "migrateSocialityExecutor")
    public Executor getMigrateSocialityExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MigrateSocialityExecutor-");
        executor.initialize();
        return executor;
    }
    
}
