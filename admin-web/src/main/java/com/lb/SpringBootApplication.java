package com.lb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;

@org.springframework.boot.autoconfigure.SpringBootApplication
@MapperScan("com.lb.mapper")
@EnableCaching
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

}
