package com.lb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Date: 2019-2-28
 * Description:线程池配置、启用异步
 */
@EnableAsync(proxyTargetClass = true)
@Configuration
public class AsycTaskExecutorConfig {

    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数
        taskExecutor.setCorePoolSize(50);
        // 最大线程数
        taskExecutor.setMaxPoolSize(100);
        return taskExecutor;
    }
}
