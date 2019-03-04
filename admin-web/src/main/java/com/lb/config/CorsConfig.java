package com.lb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Date: 2019-3-1
 * Description: 处理跨域问题
 */
@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许服务端访问
        corsConfiguration.addAllowedOrigin("*");
        // 允许本地访问
        corsConfiguration.addAllowedOrigin("http://localhost:4200");
        // 允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        // 允许withCredentials报文头
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}
