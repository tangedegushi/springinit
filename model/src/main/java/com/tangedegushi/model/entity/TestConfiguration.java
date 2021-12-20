package com.tangedegushi.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestConfiguration {

    private Logger logger = LoggerFactory.getLogger(TestBean.class.getName());

    public TestConfiguration() {
        logger.info("TestConfiguration容器启动初始化。。。");
    }

    @Bean(initMethod = "start", destroyMethod = "cleanUp")
    @Scope("prototype") //@Scope(“prototype”)指定Bean不是单例，而是原型多实例。对于原型，Spring启动时，不会加载Bean到容器中，当代码每次创建Bean时，都会创建一个新的Bean对象。
    public TestBean testBean() {
        logger.info("create test bean");
        return new TestBean();
    }
}
