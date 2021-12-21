package com.tangedegushi.model.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * IO密集型CPU_COUNT * 2,计算密集型CPU_COUNT + 1
     * */
    private static final int CORE_POOL_SIZE = CPU_COUNT * 2;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 4 + 1;
    private static final int QUEUE_CAPACITY = 128;
    private static final int KEEP_ALIVE = 100;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAXIMUM_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE);
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
                throwable.printStackTrace();
                logger.warn("handleUncaughtException method {}",method.toString());
            }
        };
    }
}
