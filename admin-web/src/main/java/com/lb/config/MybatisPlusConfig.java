package com.lb.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Date: 2019-3-1
 * Description:
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.lb.mapper.*.mapper*")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }
}
