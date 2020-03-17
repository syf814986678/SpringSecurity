package com.shiyifan.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2//开启swagger
//@PropertySource(value = "classpath:Swagger.properties") 根据配置文件设置是否启用Swagger
public class SwaggerConfig {
    //配置了Swagger的Docket的Bean实例
    //enable是否启动Swagger
    //@Value("${Swaggerflag}") 根据配置文件设置是否启用Swagger
   // private boolean swaggerflag; 根据配置文件设置是否启用Swagger
    @Bean
    public Docket docket(){

        //获取项目的环境：(需要传入参数Environment environment)
        // profiles=Profiles.of("dev");
        //boolean flag = environment.acceptsProfiles(profiles);//监听是否处于自己设置的环境中
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.enable(swaggerflag) 根据配置文件设置是否启用Swagger
                //.enable(flag) 根据环境判断是否开启
                .select()
                //配置扫描接口的方式 basePackage("com.shiyifan.Controller")
                //any扫描全部  none都不扫描
                //withClassAnnotation 扫描类上的注解
                //withMethodAnnotation 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.shiyifan.Controller"))
                //过滤什么路径
                //.paths(PathSelectors.ant("/syf"))//扫描/syf下所有请求的接口
                .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfo(
                "SYF-SpringBoot-Security-Swagger2",
                "这是一个swagge测试",
                "v1.0",
                "noahsark1.vip",
                new Contact("shiyifan", "noahsark1.vip", "814986678@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
