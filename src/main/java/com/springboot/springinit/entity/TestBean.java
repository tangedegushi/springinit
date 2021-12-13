package com.springboot.springinit.entity;

import org.springframework.beans.factory.annotation.Value;

public class TestBean {

    @Value("#{'${spring.datasource.username}'}")
    private String username;
    @Value("www.baidu.com")
    private String url;
    @Value("123456")
    private String password;

    public TestBean() {
        System.out.println("test bean construct init");
    }

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public String toString() {
        return "username:" + this.username + ",url:" + this.url + ",password:" + this.password;
    }

    public void start() {
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean 销毁。。。");
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
