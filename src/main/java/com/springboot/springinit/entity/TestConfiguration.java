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
    @Scope("prototype") //@Scope(“prototype”)指定Bean不是单例，而是原型多实例。对于原型，Spring启动时，不会加载Bean到容器中，当代码每次创建Bean时，都会创建一个新的Bean对象。
    public TestBean testBean() {
        System.out.println("create test bean");
        return new TestBean();
    }
}
