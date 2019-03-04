package com.lb.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Date: 2019-3-1
 * Description: 文件上传配置
 */
@Configuration
public class FileUploadConfig {

    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize("50Mb");
        // 设置上传数据总大小
        factory.setMaxRequestSize("100MB");
        return factory.createMultipartConfig();
    }
}
