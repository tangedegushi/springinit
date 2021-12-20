package com.tangedegushi.model;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.tangedegushi.model.dao"})
//@MapperScan是Mybatis中注解，可以替换直接在数据操作的类上使用@Mapper，比如在StudentDao上使用@Mapper
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
