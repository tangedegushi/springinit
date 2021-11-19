package com.springboot.springinit.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestConfiguration {

    public TestConfiguration() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    @Bean(initMethod = "start", destroyMethod = "cleanUp")
    @Scope("prototype")
    public TestBean testBean() {
        System.out.println("create test bean");
        return new TestBean();
    }
}
