package com.lb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Date: 2019-3-1
 * Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *以注入多个doket，也就是多个版本的api，可以在看到有三个版本groupName不能是重复的，v1和v2是ant风格匹配，配置文件
     * @return
     */
    @Bean
    public Docket api(){
        // 可以添加多个header或参数
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder.parameterType("header")
                .name("Authorization")
                .description("header中Authorization字段用于认证")
                .modelRef(new ModelRef("string"))
                //非必需，这里是全局配置，然而在登陆的时候是不用验证的
                .required(false).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2).groupName("v1").select()
                .apis(RequestHandlerSelectors.basePackage("com.lb.controller"))
                .paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo()).globalOperationParameters(aParameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot-admin的Server端 APIs")
                .contact("lb")
                .version("v0.01")
                .build();
    }
}
