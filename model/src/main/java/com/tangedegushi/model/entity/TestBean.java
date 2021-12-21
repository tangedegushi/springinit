package com.tangedegushi.model.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;

public class TestBean {

    private Logger logger = LoggerFactory.getLogger(TestBean.class.getName());

    @Value("#{'${spring.datasource.username}'}")
    private String username;
    @Value("www.baidu.com")
    private String url;
    @Value("123456")
    private String password;

    public TestBean() {
        logger.info("test bean construct init");
    }

    @Async
    public void sayHello() {
        logger.info("TestBean sayHello...{}",Thread.currentThread().getName());
    }

    public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }

    public void start() {
        logger.info("TestBean {}","初始化。。。");
    }

    public void cleanUp() {
        logger.info("TestBean 销毁。。。");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
