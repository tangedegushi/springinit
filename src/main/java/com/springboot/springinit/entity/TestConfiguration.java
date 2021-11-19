package com.springboot.springinit.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    public TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    @Bean(initMethod = "start", destroyMethod = "cleanUp")
    public TestBean testBean() {
        return new TestBean();
    }
}
